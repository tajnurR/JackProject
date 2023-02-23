package com.jack.task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import com.jack.utility.Scraping;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Stblaw {

	
	ObjectMapper mapper = new ObjectMapper();
	ExcelUtlity utitle = new ExcelUtlity();
	
	WebDriver driver;
	ChromeOptions opt;
	
//	String filename = "D:\\Work\\JackProject\\files\\excel\\input.xlsx";
//	String sheetName = "Sheet1";
	String filename = "E:\\Work Temp\\jack 2\\Stblaw\\input.xlsx";
	String sheetName = "Sheet1";
	
	String fileOut = "D:\\Work\\JackProject\\files\\excel\\output.xlsx";
	String sheetOut = "Sheet1";
	
	
//	https://www.Stblaw.com/
//	cd C:\Program Files\Google\Chrome\Application
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=D:\Work\Chrome
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=E:\Work Temp\Jack Script\file\chrome
	
	@Test
	public void testx() {
		System.out.println("Okay");
	}

	
	List<Scraping> lists = new ArrayList<Scraping>();
	
	
	@Test(dataProvider = "userdata")
	public void run(String link) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Actions actions = new Actions(driver);
//		int row = utitle.getRowCount(fileOut, sheetOut);
//		row++;
//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		
		Scraping sm = new Scraping();
		
		
		sm.setLink(link);
//		utitle.setCellData(fileOut, sheetOut, row, 0, link);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='hero-area-name']")));
		
		
		

	//	Name
		try {
			String name = driver.findElement(By.xpath("//div[@class='hero-area-name']")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 1, name);
			sm.setName(name);

		} catch (Exception e) {
			
		}
		
//		//	Title
		try {
			String title = driver.findElement(By.xpath("(//div[@class='hero-area-title'])[1]")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 2, title);
			sm.setTitle(title);
		} catch (Exception e) {
			
		}
//		
//		//	Email
		try {
			String email = driver.findElement(By.xpath("//div[@class='hero-area-email']/a")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 3, email);
			sm.setEmail(email);
		} catch (Exception e) {
			
		}
//		
		//	Phone
		try {
			String phone = driver.findElement(By.xpath("//div[@class='hero-area-phone']/a")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 4, phone);
			sm.setPhone(phone);
		} catch (Exception e) {
			
		}
//
//		Location
		try {
			String main = "";
			int locationSize = driver.findElements(By.xpath("//div[contains(@class,'hero-area-address')]")).size();
			for (int i = 1; i < locationSize+1; i++) {
				String location = driver.findElement(By.xpath("//div[contains(@class,'hero-area-address')]["+i+"]")).getText();
//				if (i<=1) {
					main+=";"+location;
//					sm.setLocation1(location);
//				}else {
//					main+=";"+location;
//				}
			}
//			utitle.setCellData(fileOut, sheetOut, row, 6, main);
//			sm.setLocationAll(main);
			sm.setLocation1(main);
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//		practice
		try {
			
			String main = "";
			int practiceSize = driver.findElements(By.xpath("//div[normalize-space()='Practice Focus']/following-sibling::div/ul/li")).size();
			for (int i = 1; i < practiceSize+1; i++) {
				String practice = driver.findElement(By.xpath("//div[normalize-space()='Practice Focus']/following-sibling::div/ul/li["+i+"]")).getText();
				if (i<=2) {
//					utitle.setCellData(fileOut, sheetOut, row, 7+i, practice);
					if (i==1) {
						sm.setPratic1(practice);
					}else if (i==2) {
						sm.setPratic2(practice);
					}
				}else {
					main+=";"+practice;
				}
			}
			sm.setPraticAll(main);
//			utitle.setCellData(fileOut, sheetOut, row, 10, main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		
		
//		Admissions
		try {
			

			try {
				driver.findElement(By.xpath("//a[@id='btnAdmissionsShowMore']")).click();
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String main = "";
			int LangSize = driver.findElements(By.xpath("//div[normalize-space()='Admissions']/following-sibling::ul/li")).size();
			
			for (int i = 1; i < LangSize+1; i++) {
				String bar = driver.findElement(By.xpath("//div[normalize-space()='Admissions']/following-sibling::ul/li["+i+"]")).getText();
				
				if (i<=1) {				
					sm.setBar1(bar);
				}else {
					main+=";"+bar;
				}
			}
			sm.setBarAll(main);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		
		
//		//	Bar Admissions
//		try {
//			String bar = driver.findElement(By.xpath("//h5[normalize-space()='Bar Admissions']/following-sibling::p[1]")).getAttribute("innerHTML");
//			utitle.setCellData(fileOut, sheetOut, row, 11, bar);
//		} catch (Exception e) {
//			
//		}
		
					
//		Education
		try {
				
			String main = "";
			int eduSize = driver.findElements(By.xpath("//div[normalize-space()='Education']/following-sibling::ul/li")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("//div[normalize-space()='Education']/following-sibling::ul/li["+i+"]")).getText();
				if (i<=5) {
					if (i==1) {
						sm.setEdu1(edu);
					}else if (i==2) {
						sm.setEdu2(edu);
					}else if (i==3) {
						sm.setEdu3(edu);
					}else if (i==4) {
						sm.setEdu4(edu);
					}else if (i==5) {
						sm.setEdu5(edu);
					}
				}else {
					main+=";"+edu;
				}
			}
			sm.setEduAll(main);
		
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
//		Language
		try {
			String main = "";
			int eduSize = driver.findElements(By.xpath("//div[normalize-space()='Languages Spoken']/following-sibling::ul/li")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("//div[normalize-space()='Languages Spoken']/following-sibling::ul/li["+i+"]")).getAttribute("innerHTML");
					main+=";"+edu;
			}
//			utitle.setCellData(fileOut, sheetOut, row, 19, main);
			sm.setLanguage(main);
		
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
//		//	Language
//		try {
//			String langu = driver.findElement(By.xpath("//div[@id='spoken_languages']/div/ul/li[1]")).getAttribute("innerHTML");
//			utitle.setCellData(fileOut, sheetOut, row, 19, langu);
//		} catch (Exception e) {
//			
//		}
		
		
//		CLERKSHIPS
		try {
				
			String main = "";
			int eduSize = driver.findElements(By.xpath("//div[normalize-space()='Clerkships']/following-sibling::ul/li")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("//div[normalize-space()='Clerkships']/following-sibling::ul/li["+i+"]")).getText();
					main+=";"+edu;
			}
			sm.setClerkship(main);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		
		
//		//	Clerkships
//		try {
//			String cls = driver.findElement(By.xpath("//h5[normalize-space()='Clerkships']/following-sibling::p")).getAttribute("innerHTML");
//			utitle.setCellData(fileOut, sheetOut, row, 19, cls);
//		} catch (Exception e) {
//			
//		}
		
		
////		Image 
		try {
			String img = driver.findElement(By.xpath("//div[@class='hero-area-image']/img")).getAttribute("src");
			sm.setImg(img);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//		Bio
		try {
			
			try {
				driver.findElement(By.xpath("//a[@aria-label='Biography']")).click();
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			StringBuilder bio = new StringBuilder();
				bio.append(driver.findElement(By.xpath("//div[@class='bio-content-long PrintMe']")).getText());
				try {

					bio.append(driver.findElement(By.xpath("//div[@id='experience']/div[contains(@class,'general--wrapper')]")).getText());
				} catch (Exception e) {
					// TODO: handle exception
				}
				
//			utitle.setCellDataBig(fileOut, sheetOut, row, 7, bio);
			sm.setBio(bio);
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		lists.add(sm);
		
		driver.close();
		driver.switchTo().window(ls.get(0));
	}
	
	@Test
	public void saveList() throws Exception {
		
		mapper.writeValue(new File("E:\\Work Temp\\jack 2\\Stblaw\\StblawOutput.json"), lists);
		
		
	}
	
	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
		
//		System.setProperty("webdriver.chrome.driver", "D:\\Work\\JackProject\\files\\devtools\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "E:\\Work Temp\\Jack Script\\file\\devtools\\chromedriver.exe");
		opt = new ChromeOptions();
		opt.setExperimentalOption("debuggerAddress", "localhost:8050");
		opt.setHeadless(true);
		
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
