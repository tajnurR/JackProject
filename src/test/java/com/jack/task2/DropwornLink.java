 package com.jack.task2;

import java.io.File;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import com.jack.utility.ExcelUtlity;
import com.jack.utility.LinkList;

public class DropwornLink {
	
	ObjectMapper mapper = new ObjectMapper();
	ExcelUtlity utitle = new ExcelUtlity();
	
	WebDriver driver;
	ChromeOptions opt;
	
	String filename = ".//files//excel//input.xlsx";
	String sheetName = "Sheet1";
	
	String fileOut = "D:\\Fiverr Work\\Jack Project\\mofo\\input.xlsx";
	String sheetOut = "Sheet1";
	
//	cd C:\Program Files\Google\Chrome\Application
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=D:\Work\JackProject\files\Chrome
	
	List<LinkList> lists = new ArrayList<LinkList>();
	
	@Test
	public void testx() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions actions = new Actions(driver);
		
		int fix = 0;
		int count = 0;
		
		int locationSize = driver.findElements(By.xpath("//select[@aria-label='location filter']/option")).size();

		for (int j = 0; j < locationSize; j++) {
			System.out.println(locationSize);
			try {
				Select lo = new Select(driver.findElement(By.xpath("//select[@aria-label='location filter']")));

				lo.selectByIndex(j+1);

				Thread.sleep(5000);
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='lawyer-name'])[1]")));
				count = driver.findElements(By.xpath("(//div[@class='lawyer-name'])")).size();

			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			System.out.println("fix="+fix+"  "+"count="+count);
		while (fix != count) {
			
			fix = count;
//			System.out.println(count);
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	        try {
	        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='lawyer-name'])["+(count+1)+"]")));
			} catch (Exception e) {
				// TODO: handle exception
			}
	        
	       
	        count = driver.findElements(By.xpath("(//div[@class='lawyer-name'])")).size();
	        
//			System.out.println(count);
		}
		
		for (int x = 1; x < count+1; x++) {
			LinkList st = new LinkList();
			String link = "";
			try {
				link = driver.findElement(By.xpath("(//div[@class='lawyer-name']/a)["+x+"]")).getAttribute("href");
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			st.setLink(link);
			System.out.println(link);
			lists.add(st);
		}
		
		}
		
		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project\\whitecase\\list.json"), lists);
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
