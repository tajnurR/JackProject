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

public class Faegredrinker {
	

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
	public void testx() {
		System.out.println("Okay");
	}

	
	@Test(dataProvider = "userdata")
	public void run(String link) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Actions actions = new Actions(driver);
		int row = utitle.getRowCount(fileOut, sheetOut);
		row++;
//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		
		utitle.setCellData(fileOut, sheetOut, row, 0, link);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(@class,'title-text-pro')]")));

	//	Name
		try {
			String name = driver.findElement(By.xpath("//h1[contains(@class,'title-text-pro')]")).getText();
			utitle.setCellData(fileOut, sheetOut, row, 1, name);
		} catch (Exception e) {
			
		}
//		
//		
//		//	Title
		try {
			String title = driver.findElement(By.xpath("//h2[contains(@class,'subtitle-text-pro')]")).getText();
			utitle.setCellData(fileOut, sheetOut, row, 2, title);
		} catch (Exception e) {
			
		}
		
		//	Email
		try {
			String email = driver.findElement(By.xpath("//div[@class='hero__location-email']/a")).getText();
			utitle.setCellData(fileOut, sheetOut, row, 3, email);
		} catch (Exception e) {
			
		}
		
		//	Phone
		try {
			String phone = driver.findElement(By.xpath("(//div[@class='hero__location-list']/div/div/a)[1]")).getText();
			utitle.setCellData(fileOut, sheetOut, row, 4, phone);
		} catch (Exception e) {
			
		}

//		Location
		try {
			String main = "";
			int locationSize = driver.findElements(By.xpath("//div[@class='hero__location-list']/div/a/div[@class='hero__location-name']")).size();
			for (int i = 1; i < locationSize+1; i++) {
				String location = driver.findElement(By.xpath("(//div[@class='hero__location-list']/div/a/div[@class='hero__location-name'])["+i+"]")).getText();
				if (i<=1) {
					utitle.setCellData(fileOut, sheetOut, row, 5, location);
				}else {
					main+=";"+location;
				}
			}
			utitle.setCellData(fileOut, sheetOut, row, 6, main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		

		
//		Bio
		try {
			
			StringBuilder bio = new StringBuilder();
				bio.append(driver.findElement(By.xpath("//div[@class='readmore ng-scope']//div[@class='readmore__inner']")).getAttribute("innerHTML"));
				
//				try {
//					bio.append(driver.findElement(By.xpath("//div[@id='experience']")).getAttribute("innerHTML"));
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
				
			utitle.setCellDataBig(fileOut, sheetOut, row, 7, bio);
		} catch (Exception e) {
			// TODO: handle exception
		}
//

		
//		practice
		try {
			String main = "";
			int practiceSize = driver.findElements(By.xpath("//h4[normalize-space()='Related Services']/following-sibling::div[@class='tagslist']/a")).size();
			for (int i = 1; i < practiceSize+1; i++) {
				String practice = driver.findElement(By.xpath("(//h4[normalize-space()='Related Services']/following-sibling::div[@class='tagslist']/a)["+i+"]")).getText();
				if (i<=2) {
					utitle.setCellData(fileOut, sheetOut, row, 7+i, practice);
				}else {
					main+=";"+practice;
				}
			}
			utitle.setCellData(fileOut, sheetOut, row, 10, main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//	Bar Admissions
		try {
			String bar = driver.findElement(By.xpath("//h5[normalize-space()='Bar Admissions']/following-sibling::p[1]")).getAttribute("innerHTML");
			utitle.setCellData(fileOut, sheetOut, row, 11, bar);
		} catch (Exception e) {
			
		}
		
					
//		Education
		try {
				
			String main = "";
			int eduSize = driver.findElements(By.xpath("//h5[normalize-space()='Education']/following-sibling::p")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("//h5[normalize-space()='Education']/following-sibling::p["+i+"]")).getText();
				if (i<=5) {
					utitle.setCellData(fileOut, sheetOut, row, 11+i, edu);
				}else {
					main+=";"+edu;
				}
			}
			utitle.setCellData(fileOut, sheetOut, row, 17, main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		//	Language
		try {
			String langu = driver.findElement(By.xpath("//h5[normalize-space()='Languages']/following-sibling::p")).getAttribute("innerHTML");
			utitle.setCellData(fileOut, sheetOut, row, 18, langu);
		} catch (Exception e) {
			
		}
		
		//	Clerkships
		try {
			String cls = driver.findElement(By.xpath("//h5[normalize-space()='Clerkships']/following-sibling::p")).getAttribute("innerHTML");
			utitle.setCellData(fileOut, sheetOut, row, 19, cls);
		} catch (Exception e) {
			
		}
		
		
		driver.close();
		driver.switchTo().window(ls.get(0));
	}
	
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
	
	@DataProvider(name="userdata")
	public String[][] getData() throws IOException{
		
		System.out.println("In Get data method....");
		
		int rowcount=utitle.getRowCount(filename, sheetName);
		System.out.println("Row Count is: "+rowcount);
		
		
		int cellcount = utitle.getCellData(filename, sheetName, 0);
		System.out.println("Cell Count is: "+cellcount);
		
		String loginData[][]=new String[rowcount][cellcount];
		
		for (int row = 1; row <= rowcount; row++) {
			
			
			
			for (int cell = 0; cell < cellcount; cell++) {
				loginData[row-1][cell] = utitle.getCellData(filename, sheetName, row, cell);
				
			}
			
		}
		return loginData;
	}

}
