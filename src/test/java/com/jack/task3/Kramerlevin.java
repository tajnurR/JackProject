package com.jack.task3;

import java.io.File;
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
import org.openqa.selenium.edge.EdgeDriver;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Kramerlevin {
	

	ObjectMapper mapper = new ObjectMapper();
	ExcelUtlity utitle = new ExcelUtlity();
	List<Scraping> lists = new ArrayList<Scraping>();
	
	WebDriver driver;
	ChromeOptions opt;
	
	String filename = "D:\\Fiverr Work\\Jack Project 2\\Kramer Levin 1\\input.xlsx";
	String sheetName = "Sheet2";
	
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		Scraping sm = new Scraping();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='attorney-header__bio-wrapper']/ul/li[1]/h1")));
		sm.setLink(link);
		
//		Name
		try {
			String name = driver.findElement(By.xpath("//div[@class='attorney-header__bio-wrapper']/ul/li[1]/h1")).getText();
			sm.setName(name);
		} catch (Exception e) {
			
		}
		
//		Title
		try {
			String title = driver.findElement(By.xpath("//li[contains(@class,'attorney-info__item--position')]")).getText();
			sm.setTitle(title);
		} catch (Exception e) {
			
		}
		
//		location
		try {
			String location = driver.findElement(By.xpath("//li[contains(@class,'attorney-info__item--office')]")).getText();
			sm.setLocation1(location);
		} catch (Exception e) {
			
		}
		
//		Email
		try {
			String email = driver.findElement(By.xpath("//li[contains(@class,'attorney-info__item--email')]/a")).getText();
			sm.setEmail(email);
		} catch (Exception e) {
			
		}
		
//		phone
		try {
			String phone = driver.findElement(By.xpath("//li[contains(@class,'attorney-info__item--phone')]")).getText();
			sm.setPhone(phone);
		} catch (Exception e) {
			
		}
		

		
//		Practices
		try {
			String main = "";
			int numberOf = driver.findElements(By.xpath("//h3[normalize-space()='Focus Areas']/following-sibling::ul/li")).size();
			for (int i = 1; i < numberOf+1; i++) {
				String pat = driver.findElement(By.xpath("(//h3[normalize-space()='Focus Areas']/following-sibling::ul/li)["+i+"]")).getText();
				if (i<= 2) {
					if (i==1) {
						sm.setPratic1(pat);
					}else if (i==2) {
						sm.setPratic2(pat);
					}
				}else {
					main+=pat+" ,";
					
				}
			}
			sm.setPraticAll(main);
		} catch (Exception e) {
			
		}
		
//		Bio and Experience
		try {
			StringBuilder bio = new StringBuilder();
			bio.append(driver.findElement(By.xpath("//div[contains(@class,'page-attorney__profile-column--left')]/div[1]")).getText());
			bio.append(" ");
			try {
				bio.append(driver.findElement(By.xpath("//h2[normalize-space()='Experience']/following-sibling::div")).getText());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			sm.setBio(bio);
		} catch (Exception e) {
			
		}
		
		
//		Bar Addmission
		try {
			String main = "";
			int numberOf = driver.findElements(By.xpath("//h3[normalize-space()='Bar Admissions']/following-sibling::ul/li")).size();
			for (int i = 1; i < numberOf+1; i++) {
				String bar = driver.findElement(By.xpath("//h3[normalize-space()='Bar Admissions']/following-sibling::ul/li["+i+"]")).getText();
				if (i<= 1) {
					sm.setBar1(bar);
				}else {
					main+=bar+" ,";
				}
			}
			sm.setBarAll(main);
			
		} catch (Exception e) {
			
		}
		
//		CLERKSHIPS
		try {
				
			String main = "";
			int eduSize = driver.findElements(By.xpath("//h3[normalize-space()='Clerkships']/following-sibling::ul/li")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("(//h3[normalize-space()='Clerkships']/following-sibling::ul/li)["+i+"]")).getText();
					main+=";"+edu;
			}
			sm.setClerkship(main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//		Langulage
		try {
			String main = "";
			int numberOf = driver.findElements(By.xpath("//h3[normalize-space()='Languages']/following-sibling::ul/li")).size();
			for (int i = 1; i < numberOf+1; i++) {
				String lang = driver.findElement(By.xpath("//h3[normalize-space()='Languages']/following-sibling::ul/li["+i+"]")).getText();
				main+=lang+" ,";
			}
			
			sm.setLanguage(main);
		} catch (Exception e) {
			
		}
		

//		Education
		try {
			String main = "";
			int numberOf = driver.findElements(By.xpath("//h3[normalize-space()='Education']/following-sibling::ul/li")).size();
			for (int i = 1; i < numberOf+1; i++) {
				String edu = driver.findElement(By.xpath("//h3[normalize-space()='Education']/following-sibling::ul/li["+i+"]")).getText();
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
			
		}
		
//		Image 
		try {
			String img = driver.findElement(By.xpath("//span[@data-class='attorney-header__photo']/span[1]")).getAttribute("data-src");
			sm.setImg(img);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		lists.add(sm);

		
		driver.close();
		driver.switchTo().window(ls.get(0));
	}
	
	
	@Test
	public void saveList() throws Exception {
		
		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project 2\\Kramer Levin 1\\row.json"), lists);
		System.out.println("Data Saved");
		driver.close();
		
	}
	
	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
		driver = new EdgeDriver();
		WebDriverManager.edgedriver().setup();
//		
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tajnu\\eclipse-workspace\\JackProject\\files\\devtools\\chromedriver.exe");
//		opt = new ChromeOptions();
//		opt.setExperimentalOption("debuggerAddress", "localhost:8050");
//		
//		driver = new ChromeDriver(opt);
////		driver.get(appUrl);
//		driver.manage().window().maximize();
		
		
		
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
