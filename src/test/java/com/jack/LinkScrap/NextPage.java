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


public class NextPage {
	ObjectMapper mapper = new ObjectMapper();
	List<LinkList> lists = new ArrayList<LinkList>();
	
	ChromeDriver driver;
	ChromeOptions opt;
	
	@Test
	public void testx() throws InterruptedException, JsonGenerationException, JsonMappingException, IOException {
		System.out.println("Okay");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Thread.sleep(6000);
		int numPage = 29;
		int numPageD = 0;
		
		do {
			numPageD++;
			Thread.sleep(5000);
			int people = driver.findElements(By.xpath("(//span[@class='field-content']/a)")).size();
			System.out.println("Number of Person: "+people);
			for (int j = 1; j <= people; j++) {
				LinkList l = new LinkList();
				String link = driver.findElement(By.xpath("(//span[@class='field-content']/a)["+j+"]")).getAttribute("href");
				l.setLink(link);
				System.out.println(link);
				
				
				
				lists.add(l);
			}
			
			try {
				driver.findElement(By.xpath("//li[contains(@class,'is-active')]/following-sibling::li[1]/a")).click();
				Thread.sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}while(numPage != numPageD);
		
		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project 4\\ARENTFOX SCHIFF LLP\\list.json"), lists);
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
//		driver.manage().window().maximize();
//		driver.get("https://www.dentons.com/en/our-professionals");
		
		driver.get("https://www.afslaw.com/attorneys");
		
	}

}
