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

public class Duanemorris {
	

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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='Biography']")));

	//	Name
		try {
			String name = driver.findElement(By.xpath("//h1[@class='name']")).getText();
			utitle.setCellData(fileOut, sheetOut, row, 1, name);
		} catch (Exception e) {
			
		}
		
		//	Title
		try {
			String title = driver.findElement(By.xpath("//h2[@class='title']")).getText();
			utitle.setCellData(fileOut, sheetOut, row, 2, title);
		} catch (Exception e) {
			
		}
		
//		Address
		try {
			String main = "";
			int practiceSize = driver.findElements(By.xpath("(//ul[@class='list-inline']/li/span[@class='address'])")).size();
			for (int i = 1; i < practiceSize+1; i++) {
				String addres = driver.findElement(By.xpath("(//ul[@class='list-inline']/li/span[@class='address'])["+i+"]")).getText();
				if (i<=1) {
					utitle.setCellData(fileOut, sheetOut, row, 3, addres);
				}else {
					main+=";"+addres;
				}
			}
			utitle.setCellData(fileOut, sheetOut, row, 4, main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//	img
		try {
			String img = driver.findElement(By.xpath("//ul[@class='list-inline']/li/img")).getAttribute("src");
			utitle.setCellData(fileOut, sheetOut, row, 5, img);
		} catch (Exception e) {
			
		}
		
		
//		Phone and Email
		try {
			
			int practiceSize = driver.findElements(By.xpath("(//ul[@class='list-inline']/li/span[@class='address2'])")).size();
			for (int i = 1; i < practiceSize+1; i++) {
				String pande = driver.findElement(By.xpath("(//ul[@class='list-inline']/li/span[@class='address2'])["+i+"]")).getText();
				if (i<=1) {
					utitle.setCellData(fileOut, sheetOut, row, 6, pande);
				}else if(i==2){
					utitle.setCellData(fileOut, sheetOut, row, 7, pande);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Bio
		try {
			try {
				driver.findElement(By.xpath("//h2[normalize-space()='Biography']/parent::a")).click();
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			StringBuilder bio = new StringBuilder();
			bio.append(driver.findElement(By.xpath("//div[@id='Biography']")).getText());
			
			utitle.setCellDataBig(fileOut, sheetOut, row, 8, bio);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		
//		practice
		try {
			
			try {
				driver.findElement(By.xpath("//h2[normalize-space()='Resume']/parent::a")).click();
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String main = "";
			int practiceSize = driver.findElements(By.xpath("//h3[normalize-space()='Areas of Practice']/following-sibling::ul[1]/li")).size();
			for (int i = 1; i < practiceSize+1; i++) {
				String practice = driver.findElement(By.xpath("//h3[normalize-space()='Areas of Practice']/following-sibling::ul[1]/li["+i+"]")).getText();
				if (i<=2) {
					utitle.setCellData(fileOut, sheetOut, row, 8+i, practice);
				}else {
					main+=";"+practice;
				}
			}
			utitle.setCellData(fileOut, sheetOut, row, 11, main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		


		
//		Edu
		try {
			String main = "";
			int eduSize = driver.findElements(By.xpath("//h3[normalize-space()='Education']/following-sibling::ul[1]/li")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("//h3[normalize-space()='Education']/following-sibling::ul[1]/li["+i+"]")).getText();
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
		
////		Clerkships
//		try {
//			String clerkships = driver.findElement(By.xpath("(//h4[contains(normalize-space(),'Clerkships')])[1]/parent::div/div")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 12, clerkships);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
//		Admissions
		try {
			String main = "";
			int LangSize = driver.findElements(By.xpath("//h3[normalize-space()='Admissions']/following-sibling::ul[1]/li")).size();
			
			for (int i = 1; i < LangSize+1; i++) {
				String bar = driver.findElement(By.xpath("//h3[normalize-space()='Admissions']/following-sibling::ul[1]/li["+i+"]")).getText();
				
				if (i<=1) {
					utitle.setCellData(fileOut, sheetOut, row, 18, bar);
				}else {
					main+=";"+bar;
				}
					
			
			}
			utitle.setCellData(fileOut, sheetOut, row, 19, main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
		
//		Exp
		try {
			
			StringBuilder exp = new StringBuilder();
			exp.append(driver.findElement(By.xpath("//h3[normalize-space()='Experience']/following-sibling::ul[1]")).getText());
			
			utitle.setCellDataBig(fileOut, sheetOut, row, 20, exp);
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
