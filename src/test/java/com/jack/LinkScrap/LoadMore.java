package com.jack.LinkScrap;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.utility.LinkList;


public class LoadMore {
	ObjectMapper mapper = new ObjectMapper();
	List<LinkList> lists = new ArrayList<LinkList>();
	
	ChromeDriver driver;
	ChromeOptions opt;
	
	@Test
	public void testx() throws InterruptedException, JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Okay");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		
		try {
			driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Thread.sleep(10000);
		
		boolean status = false;
		
		do {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='get-more']")));
			driver.findElement(By.xpath("//button[@id='get-more']")).click();
			status = true;
			
		}while(status);
		
//		int beforeCount = 1;
//		int afterCount = 0;
//		
//		while(beforeCount != afterCount) {
//			
//			
//			beforeCount = driver.findElements(By.xpath("//a[contains(@id,'name')]")).size();
//			
//			try {
//				driver.findElement(By.xpath("//button[@id='get-more']")).click();
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@id,'name')])["+(beforeCount+1)+"]")));
//				afterCount = driver.findElements(By.xpath("//a[contains(@id,'name')]")).size();
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//		}
		
		int beforeCount = driver.findElements(By.xpath("//a[contains(@id,'name')]")).size();
		for (int i = 1; i <= beforeCount; i++) {
			LinkList l = new LinkList();
			String link = driver.findElement(By.xpath("(//a[contains(@id,'name')])["+i+"]")).getAttribute("href");
			System.out.println(link);
			l.setLink(link);
			
			
			lists.add(l);
		}
		
		
		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project 4\\Dentons 2\\link.json"), lists);
		System.out.println("Data Saved");
		
	}
	
//	@Test
//	public void saveList() throws Exception {
//		
//		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project 4\\Dentons 2\\link.json"), lists);
//		System.out.println("Data Saved");
//		driver.close();
//		
//	}
	
	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
		
		opt = new ChromeOptions();
	
		opt.addArguments("--remote-allow-origins=*");
		
		driver = new ChromeDriver(opt);
		
		
//		driver = new EdgeDriver();
//		WebDriverManager.edgedriver().setup();
		driver.manage().window().maximize();
//		driver.get("https://www.dentons.com/en/our-professionals");
		
		driver.get("https://www.dentons.com/en/our-professionals?Filters=%26regionid%3D00C27DB0UNITE,550948D1MIDDL%26sectorid%3D%26practiceid%3D%26positionid%3D%26languageid%3D%26inpid%3D%26countryid%3D%26page%3D1");
		
	}

}
