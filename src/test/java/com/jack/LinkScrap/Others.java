package com.jack.LinkScrap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jack.utility.ExcelUtlity;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Others {
	
	WebDriver driver;
//	ChromeOptions opt;
	
	
//	cd C:\Program Files\Google\Chrome\Application
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=D:\Work\Chrome
	
	@Test
	public void testx() throws IOException, InterruptedException {
		
		
//		int totalsize = driver.findElement(By.xpath(""))
		
//		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//		
//		Thread.sleep(2000);
//		int hasNext=driver.findElements(By.xpath("//li[@aria-label='Next']")).size();
//		System.out.println(hasNext);
//		while (hasNext != 0){
//			
//			try {
//				hasNext = driver.findElements(By.xpath("//li[@aria-label='Next']")).size();
//				System.out.println("===========  "+hasNext);
//			} catch (Exception e) {
//				
//			}
//			
//				int listSize = 0;
//				try {
//					listSize = driver.findElements(By.xpath("(//div[@class='contacts__card-content'])")).size();
//				} catch (Exception e) {
//					
//				}
//				
//			
//					for (int i = 1; i < listSize+1; i++) {
//						String link = driver.findElement(By.xpath("(//a[@class='CoveoResultLink'])["+i+"]")).getAttribute("href");
//						System.out.println(link);
//					}
//					
//					
//					try {
//						driver.findElement(By.xpath("//li[@aria-label='Next']/a")).click();
//						
//					} catch (Exception e) {
//						// TODO: handle exception
//					}	
//					
//					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='CoveoResultLink'])[1]")));
//			
//		} 
	}


	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
		driver = new EdgeDriver();
		WebDriverManager.edgedriver().setup();

		
		driver.get("https://www.polsinelli.com/people?search[post_type]=person");
//		driver.manage().window().maximize();
		
	}
	



}
