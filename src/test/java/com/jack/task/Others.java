package com.jack.task;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jack.utility.ExcelUtlity;

public class Others {
	

	ExcelUtlity utitle = new ExcelUtlity();
	
	WebDriver driver;
	ChromeOptions opt;
	
	String filename = ".//files//excel//input.xlsx";
	String sheetName = "Sheet1";
	
	String fileOut = ".//files//excel//output.xlsx";
	String sheetOut = "Sheet1";
	
//	cd C:\Program Files\Google\Chrome\Application
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=C:\Users\tajnu\eclipse-workspace\JackProject\files\chrome
	
	@Test
	public void testx() throws IOException, InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//		Actions actions = new Actions(driver);
		String hasNext = "";
		try {
			hasNext = driver.findElement(By.xpath("//a[@title='Next']")).getText();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		while (hasNext.isEmpty()) {
			
			int lists = driver.findElements(By.xpath("(//a[contains(@class,'CoveoResultLink')])")).size();
			try {
				for (int i = 1; i < lists+1; i++) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@class,'CoveoResultLink')])[1]")));
					int row = utitle.getRowCount(fileOut, sheetOut);
					row++;
					
					String link = driver.findElement(By.xpath("(//a[contains(@class,'CoveoResultLink')])["+i+"]")).getAttribute("href");
					utitle.setCellData(fileOut, sheetOut, row, 0, link);
					
//					(//a[contains(@class,'CoveoResultLink')])[1]/div/div/div/span[contains(text(),'@')]
					
					String email = driver.findElement(By.xpath("(//a[contains(@class,'CoveoResultLink')])["+i+"]/div/div/div/span[contains(text(),'@')]")).getText();
					utitle.setCellData(fileOut, sheetOut, row, 1, email);
					
					System.out.println(row+" Email: "+email);
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			Thread.sleep(3000);
			
		}
		
//		int fix = 90;
//		int count = 1;
//		
//		while (fix != count) {
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'attorney-results__card')]/div/ul/li[contains(@class,'attorney-card__list-item-wrap--info')]/div/div/ul/li[@class='type__item-title'])[1]")));
//			
//			count++;
//			System.out.println("Page Mind: "+count);
//			int getTotalElement = driver.findElements(By.xpath("//div[contains(@class,'attorney-results__card')]/div/ul/li[contains(@class,'attorney-card__list-item-wrap--info')]/div/div/ul/li[@class='type__item-title']")).size();
//			
//			
//			for (int i = 1; i < getTotalElement+1; i++) {
//				int row = utitle.getRowCount(fileOut, sheetOut);
//				row++;
////				Name
//				try {
//					String name = driver.findElement(By.xpath("(//div[contains(@class,'attorney-results__card')]/div/ul/li[contains(@class,'attorney-card__list-item-wrap--info')]/div/div/ul/li[@class='type__item-title'])["+i+"]")).getText();
//					utitle.setCellData(fileOut, sheetOut, row, 0, name);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				
////				Link
//				try {
//					String link = driver.findElement(By.xpath("(//div[contains(@class,'attorney-results__card')]/div/ul/li[contains(@class,'attorney-card__list-item-wrap--info')]/div/div/ul/li[@class='type__item-title']/a)["+i+"]")).getAttribute("href");
//					utitle.setCellData(fileOut, sheetOut, row, 1, link);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				
////				Title
//				try {
//					String title = driver.findElement(By.xpath("(//div[contains(@class,'attorney-results__card')]/div/ul/li[contains(@class,'attorney-card__list-item-wrap--info')]/div/div/ul/li[2])["+i+"]")).getText();
//					utitle.setCellData(fileOut, sheetOut, row, 2, title);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				
////				Email
//				try {
//					String email = driver.findElement(By.xpath("(//div[contains(@class,'attorney-results__card')]/div/ul/li[contains(@class,'attorney-card__list-item-wrap--info')]/div/div/ul/li/a[@class='attorney-info__email'])["+i+"]")).getText();
//					utitle.setCellData(fileOut, sheetOut, row, 3, email);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				
////				Office
//				try {
//					String office = driver.findElement(By.xpath("(//div[contains(@class,'attorney-results__card')]/div/ul/li[contains(@class,'attorney-card__list-item-wrap--info')]/div/div/ul/li/a[contains(@href,'offices')])["+i+"]")).getText();
//					utitle.setCellData(fileOut, sheetOut, row, 4, office);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				
//				
////				Phone
//				try {
//					String phone = driver.findElement(By.xpath("(//div[contains(@class,'attorney-results__card')]/div/ul/li[contains(@class,'attorney-card__list-item-wrap--info')]/div/div/ul/li/a[contains(@class,'attorney-info__phone')])["+i+"]")).getText();
//					utitle.setCellData(fileOut, sheetOut, row, 5, phone);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				
////				Img
//				try {
//					String name = driver.findElement(By.xpath("(//div[contains(@class,'attorney-results__card')]/div/ul/li/div[contains(@class,'list-item--photo')]/img)["+i+"]")).getAttribute("src");
//					utitle.setCellData(fileOut, sheetOut, row, 6, name);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				
//				
//			}
			
			
//			driver.findElement(By.xpath("//a[normalize-space()='>']")).click();
//			Thread.sleep(3000);
//		}
	}

	
//	@Test(dataProvider = "userdata")
//	public void run(String link) throws IOException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		Actions actions = new Actions(driver);
//		int row = utitle.getRowCount(fileOut, sheetOut);
//		row++;
////		driver.manage().deleteAllCookies();
//		driver.switchTo().newWindow(WindowType.TAB);
//		Set<String> windowHan = driver.getWindowHandles();
//		List<String> ls = new ArrayList<String>(windowHan);
//		driver.get(link);
//		
//		utitle.setCellData(fileOut, sheetOut, row, 0, link);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='block-blankrome-page-title']/h1")));
//
//
//
//		
////		practice
//		try {
//			String main = "";
//			int practiceSize = driver.findElements(By.xpath("//ul[@class='list-unbulleted | margin-none']/li/a[contains(@href,'offices')]")).size();
//			for (int i = 1; i < practiceSize+1; i++) {
//				String practice = driver.findElement(By.xpath("//ul[@class='list-unbulleted | margin-none']/li/a[contains(@href,'offices')]["+i+"]")).getText();
//				if (i<=1) {
//					utitle.setCellData(fileOut, sheetOut, row, 1, practice);
//					System.out.println("Practice "+practice);
//				}else {
//					main+=";"+practice;
//				}
//			}
//			System.out.println("Main "+main);
//			utitle.setCellData(fileOut, sheetOut, row, 2, main);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		
//
//		
//	
//		
//		driver.close();
//		driver.switchTo().window(ls.get(0));
//	}
//	
	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tajnu\\eclipse-workspace\\JackProject\\files\\devtools\\chromedriver.exe");
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
