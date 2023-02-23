package com.jack.task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
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

public class Cozen {
	

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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Actions actions = new Actions(driver);
		int row = utitle.getRowCount(fileOut, sheetOut);
		row++;
//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		
		utitle.setCellData(fileOut, sheetOut, row, 0, link);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bioinfo']/h1")));

	//	Name
		try {
			String name = driver.findElement(By.xpath("//div[@class='bioinfo']/h1")).getText();
			utitle.setCellData(fileOut, sheetOut, row, 1, name);
		} catch (Exception e) {
			
		}
		

		
//		practice
		try {
			String main = "";
			int practiceSize = driver.findElements(By.xpath("//div[@id='PracticeAreasContainer']/div/dl/dd")).size();
			for (int i = 1; i < practiceSize+1; i++) {
				String practice = driver.findElement(By.xpath("//div[@id='PracticeAreasContainer']/div/dl/dd["+i+"]")).getText();
				if (i<=2) {
					utitle.setCellData(fileOut, sheetOut, row, 1+i, practice);
				}else {
					main+=";"+practice;
				}
			}
			utitle.setCellData(fileOut, sheetOut, row, 4, main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//		Bio
		try {
			
			StringBuilder bio = new StringBuilder();
			bio.append(driver.findElement(By.xpath("//div[@id='MainContent']")).getText());
			
			try {
				bio.append(driver.findElement(By.xpath("//h2[normalize-space()='Experience']/parent::div/parent::div")).getText());
			} catch (Exception e) {
				// TODO: handle exception
			}
			utitle.setCellDataBig(fileOut, sheetOut, row, 5, bio);
		} catch (Exception e) {
			// TODO: handle exception
		}

		
//		Edu
		try {

			
			String main = "";
			int eduSize = driver.findElements(By.xpath("//div[@id='expandEducation']/div/ul/li")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("//div[@id='expandEducation']/div/ul/li["+i+"]")).getText();
				if (i<=5) {
					utitle.setCellData(fileOut, sheetOut, row, 5+i, edu);
				}else {
					main+=";"+edu;
				}
			}
			utitle.setCellData(fileOut, sheetOut, row, 11, main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Clerkships
		try {
			String main = "";
			int clkSize = driver.findElements(By.xpath("//div[@id='Clerkships']/p")).size();
			for (int i = 1; i < clkSize+1; i++) {
				String practice = driver.findElement(By.xpath("//div[@id='Clerkships']/p["+i+"]")).getText();
					main+=";"+practice;
			}
			utitle.setCellData(fileOut, sheetOut, row, 12, main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Admissions
		try {
			String main = "";
			int LangSize = driver.findElements(By.xpath("//div[@id='Admissions']/ul/li")).size();
			
			for (int i = 1; i < LangSize+1; i++) {
				String bar = driver.findElement(By.xpath("//div[@id='Admissions']/ul/li["+i+"]")).getText();
				
				if (i<=1) {
					utitle.setCellData(fileOut, sheetOut, row, 12+i, bar);
				}else {
					main+=";"+bar;
				}
					
			
			}
			utitle.setCellData(fileOut, sheetOut, row, 14, main);
		} catch (Exception e) {
			// TODO: handle exception
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
