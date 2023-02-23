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

public class DropwornLink {
	

	ExcelUtlity utitle = new ExcelUtlity();
	
	WebDriver driver;
	ChromeOptions opt;
	
	String filename = ".//files//excel//input.xlsx";
	String sheetName = "Sheet1";
	
	String fileOut = "D:\\Fiverr Work\\Jack Project\\Sidley\\input.xlsx";
	String sheetOut = "Sheet1";
	
//	cd C:\Program Files\Google\Chrome\Application
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=D:\Work\JackProject\files\Chrome
	
	@Test
	public void testx() throws IOException, InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Actions actions = new Actions(driver);
		
		int fix = 0;
		int count = 0;
		
//		ArrayList list = new ArrayList();
		
		int locationSize = driver.findElements(By.xpath("//ul[@data-bind='foreach : OfficeFilters']/li")).size();
//		for (int i = 0; i < locationSize; i++) {
//			String locaionName = driver.findElement(By.xpath("//select[@name='Location']/option")).getAttribute("label");
//			list.add(locaionName);
//		}
		
		for (int j = 1; j < locationSize+1; j++) {
//			Select lo = new Select(driver.findElement(By.xpath("//ul[@data-bind='foreach : OfficeFilters']")));
//			lo.selectByIndex(j+1);
			try {
				driver.findElement(By.xpath("//div[@data-name='office']//div[@role='button']")).click();
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}

			driver.findElement(By.xpath("//ul[@data-bind='foreach : OfficeFilters']/li["+j+"]")).click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'contact-name')])[1]")));
			count = driver.findElements(By.xpath("//div[contains(@class,'contact-name')]")).size();
			
		
		
		while (fix != count) {
			
			fix = count;
//			System.out.println(count);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	        try {
				driver.findElement(By.xpath("//button[normalize-space()='Load More']")).click();
			} catch (Exception e) {
				// TODO: handle exception
			}
	        Thread.sleep(3000);
	        count = driver.findElements(By.xpath("//div[contains(@class,'contact-name')]")).size();
	        
//			System.out.println(count);
		}
		
		for (int x = 1; x < count+1; x++) {
			int row = utitle.getRowCount(fileOut, sheetOut);
			row++;
			try {
				String link = driver.findElement(By.xpath("(//div[contains(@class,'contact-name')])["+x+"]/a")).getAttribute("href");
				utitle.setCellData(fileOut, sheetOut, row, 0, link);
//				
//				String img = driver.findElement(By.xpath("(//div[@class='search__resultstitle']/following-sibling::ul/li/a/div/img)["+x+"]")).getAttribute("src");
//				utitle.setCellData(fileOut, sheetOut, row, 1, img);
				
				System.out.println(link);
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		}
	}

	
	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
		
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\JackProject\\files\\devtools\\chromedriver.exe");
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
