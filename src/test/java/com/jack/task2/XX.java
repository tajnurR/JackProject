 package com.jack.task2;

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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jack.utility.ExcelUtlity;

public class XX {
	

	ExcelUtlity utitle = new ExcelUtlity();
	
	WebDriver driver;
	ChromeOptions opt;
	
	String filename = ".//files//excel//input.xlsx";
	String sheetName = "Sheet1";
	
	String fileOut = "D:\\Fiverr Work\\Jack Project\\Sidley\\input.xlsx";
	String sheetOut = "Sheet1";
	
//	cd C:\Program Files\Google\Chrome\Application
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=D:\Work\Chrome
	
	@Test
	public void testx() throws IOException, InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Actions actions = new Actions(driver);
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        
        
		int size;
		int px = 0;
		while (true) {
			size = driver.findElements(By.xpath("//div[contains(@class,'KIFWI List')]/div/div[contains(@style,'top: "+px+"px;')]//a[contains(@class,'style__ProfileName')]")).size();
			if (size==0) {
//				js.executeScript("window.scrollBy(0,240)","");
			}
			
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'KIFWI List')]/div/div[contains(@style,'top: "+px+"px;')]//a[contains(@class,'style__ProfileName')]")));
				String link = driver.findElement(By.xpath("//div[contains(@class,'KIFWI List')]/div/div[contains(@style,'top: "+px+"px;')]//a[contains(@class,'style__ProfileName')]")).getAttribute("href");
				System.out.println(link);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			px=px+120;
			
		}
		
		
	}

	
	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
		
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\devtools\\chromedriver.exe");
		opt = new ChromeOptions();
		opt.setExperimentalOption("debuggerAddress", "localhost:8050");
		
		driver = new ChromeDriver(opt);
//		driver.get(appUrl);
		driver.manage().window().maximize();
		
	}
	
//	@DataProvider(name="userdata")
//	public String[][] getData() throws IOException{
//		
//		System.out.println("In Get data method....");
//		
//		int rowcount=utitle.getRowCount(filename, sheetName);
//		System.out.println("Row Count is: "+rowcount);
//		
//		
//		int cellcount = utitle.getCellData(filename, sheetName, 0);
//		System.out.println("Cell Count is: "+cellcount);
//		
//		String loginData[][]=new String[rowcount][cellcount];
//		
//		for (int row = 1; row <= rowcount; row++) {
//			
//			
//			
//			for (int cell = 0; cell < cellcount; cell++) {
//				loginData[row-1][cell] = utitle.getCellData(filename, sheetName, row, cell);
//				
//			}
//			
//		}
//		return loginData;
//	}

}
