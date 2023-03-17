package com.jack.task2;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.utility.ExcelUtlity;
import com.jack.utility.LinkList;
import com.jack.utility.Scraping;

public class Hello {
	


	
	ObjectMapper mapper = new ObjectMapper();
	ExcelUtlity utitle = new ExcelUtlity();
	
	WebDriver driver;
	ChromeOptions opt;
	
//	String filename = "D:\\Work\\JackProject\\files\\excel\\input.xlsx";
//	String sheetName = "Sheet1";
	String filename = "D:\\Fiverr Work\\Jack Project\\bakermckenzie\\input.xlsx";
	String sheetName = "Sheet1";
	
	String fileOut = "D:\\Work\\JackProject\\files\\excel\\output.xlsx";
	String sheetOut = "Sheet1";
	
	
//	https://www.Paulhastings.com/
//	cd C:\Program Files\Google\Chrome\Application
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=D:\Work\Chrome
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=E:\Work Temp\Jack Script\file\chrome
	
	@Test
	public void testx() {
		System.out.println("Okay");
	}

	List<LinkList> lists = new ArrayList<LinkList>();
	
	
	@Test
	public void run() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Actions actions = new Actions(driver);
		
		
		
		int size = driver.findElements(By.xpath("(//h3[@class='prof__name'])")).size();
		
		
		while (size != 5) {
			try {
				size = driver.findElements(By.xpath("(//h3[@class='prof__name'])")).size();
				System.out.println("Size: "+size);
				driver.findElement(By.xpath("//a[normalize-space()='View More Professionals']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h3[@class='prof__name'])["+(size+1)+"]")));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
		
		System.out.println("Total Size: "+size);
		
		for (int i = 1; i < size+1; i++) {
			System.out.println("Id: "+i);
			LinkList st = new LinkList();
			String links = driver.findElement(By.xpath("(//h3[@class='prof__name']/a)["+i+"]")).getAttribute("href");
			System.out.println("Profile: "+links);
			
			st.setLink(links);
			try {
				String name= driver.findElement(By.xpath("(//div[contains(text(),'Partner')])["+i+"]")).getText();
				st.setTandL(name);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				String email= driver.findElement(By.xpath("(//div[@class='bottom']/div/span[@class='phone'])["+i+"]")).getText();
				st.setPhone(email);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				String phone1= driver.findElement(By.xpath("(//div[@class='prof-contact']/div/following-sibling::a[@class='read-more'])["+i+"]")).getAttribute("data-email");
				st.setEmail(phone1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			
			lists.add(st);
			
		}
		
		
		

	}
	
	@Test
	public void saveList() throws Exception {
		System.out.println("Data Saving");
		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project\\debevoise 2\\Luxembourg.json"), lists);
		System.out.println("Saved");
	}
	
	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
		
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\devtools\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", "E:\\Work Temp\\Jack Script\\file\\devtools\\chromedriver.exe");
		opt = new ChromeOptions();
		opt.setExperimentalOption("debuggerAddress", "localhost:8050");
		
		driver = new ChromeDriver(opt);
//		driver.get(appUrl);
		driver.manage().window().maximize();
		
	}
	
	


}
