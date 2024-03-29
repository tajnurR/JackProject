package com.jack.task3;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.utility.ExcelUtlity;
import com.jack.utility.Scraping;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Winston {
	

	ObjectMapper mapper = new ObjectMapper();
	ExcelUtlity utitle = new ExcelUtlity();
	List<Scraping> lists = new ArrayList<Scraping>();
	
	WebDriver driver;
	ChromeOptions opt;
	
	String filename = "D:\\Fiverr Work\\Jack Project 2\\Winston & Strawn LLP\\input.xlsx";
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
	public void run(String link) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Actions actions = new Actions(driver);
//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		Scraping sm = new Scraping();
		
		sm.setLink(link);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'bio-top-banner__title-area')]/h1)[1]")));
		} catch (Exception e) {
			driver.navigate().refresh();
		}
		

	//	Name
		try {
			String name = driver.findElement(By.xpath("(//div[contains(@class,'bio-top-banner__title-area')]/h1)[1]")).getText();
			sm.setName(name);
		} catch (Exception e) {
			
		}
		
//		//	Title
		try {
			String title = driver.findElement(By.xpath("(//h1[contains(@class,'title--bio-reset')])[1]/following-sibling::p[1]")).getText();
			sm.setTitle(title);
		} catch (Exception e) {
			
		}
		
//		//	Email
		try {
			String email = driver.findElement(By.xpath("(//a[contains(@href,'@')])[3]")).getAttribute("href");
			sm.setEmail(email);
		} catch (Exception e) {
			
		}
//		
//		//	Phone
		try {
			String phone = driver.findElement(By.xpath("(//li[contains(@class,'item-number')])[3]")).getText();
			sm.setPhone(phone);
		} catch (Exception e) {
			
		}
//
//		Location
		try {
			String main = "";
			int locationSize = driver.findElements(By.xpath("((//div[contains(@class,'bio-top-banner__title-area')]/h1)[1]/parent::div/p[@class='bio-top-banner__text']/a)")).size();
			for (int i = 1; i < locationSize+1; i++) {
				String location = driver.findElement(By.xpath("((//div[contains(@class,'bio-top-banner__title-area')]/h1)[1]/parent::div/p[@class='bio-top-banner__text']/a)["+i+"]")).getText();
				if (i<=1) {
					sm.setLocation1(location);
				}else {
					main+=";"+location;
				}
			}
			sm.setLocationAll(main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		//Pratice
		try {
			
			String main = "";
			int practiceSize = driver.findElements(By.xpath("//h3[normalize-space()='Services']/following-sibling::div/ul/li")).size();
			for (int i = 1; i < practiceSize+1; i++) {
				String practice = driver.findElement(By.xpath("//h3[normalize-space()='Services']/following-sibling::div/ul/li["+i+"]")).getText();
				if (i<=2) {
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
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Bio
		try {
			
			StringBuilder bio = new StringBuilder();
				bio.append(driver.findElement(By.xpath("(//div[@data-tab='overview']/div/div/div[contains(@class,'bio-rte')])[1]")).getAttribute("innerHTML"));
				
				try {
					driver.findElement(By.xpath("//a[normalize-space()='Experience']")).click();
					Thread.sleep(1000);
					bio.append(driver.findElement(By.xpath("//h3[normalize-space()='Experience']/following-sibling::div")).getAttribute("innerHTML"));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			sm.setBio(bio);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Admissions
		
		try {
		driver.findElement(By.xpath("//a[normalize-space()='Credentials']")).click();
		Thread.sleep(1000);
			} catch (Exception e) {
		// TODO: handle exception
		}
		
		try {
			
			String main = "";
			int LangSize = driver.findElements(By.xpath("((//h3[normalize-space()='Admissions']/following-sibling::div)[1]/div/div[contains(@class,'circle-description')])")).size();
			
			for (int i = 1; i < LangSize+1; i++) {
				String bar = driver.findElement(By.xpath("((//h3[normalize-space()='Admissions']/following-sibling::div)[1]/div/div[contains(@class,'circle-description')])["+i+"]")).getText();
				
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
		
		
		
					
//		Education
		try {			
			String main = "";
			int eduSize = driver.findElements(By.xpath("(//h3[normalize-space()='Education']/following-sibling::div)[1]/div")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("(//h3[normalize-space()='Education']/following-sibling::div)[1]/div["+i+"]/div")).getText();
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
			int eduSize = driver.findElements(By.xpath("((//h3[normalize-space()='Languages']/following-sibling::div)[1]/div/div[contains(@class,'circle-description')])")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("((//h3[normalize-space()='Languages']/following-sibling::div)[1]/div/div[contains(@class,'circle-description')])["+i+"]")).getText();
					main+=";"+edu;
			}
			sm.setLanguage(main);
		} catch (Exception e) {
			
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
			int eduSize = driver.findElements(By.xpath("(//h3[normalize-space()='Clerkships']/following-sibling::div/div/div/div[@class='badge-label'])")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("(//h3[normalize-space()='Clerkships']/following-sibling::div/div/div/div[@class='badge-label'])["+i+"]")).getText();
					main+=";"+edu;
			}
			sm.setClerkship(main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
////		Image 
		try {
			String img = driver.findElement(By.xpath("(//div[@class='bio-hero__photo-container'])[2]/img[1]")).getAttribute("src");
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
		
		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project 2\\Winston & Strawn LLP\\row.json"), lists);
		System.out.println("Data Saved");
		driver.close();
	}
	
	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
		
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tajnu\\eclipse-workspace\\JackProject\\files\\devtools\\chromedriver.exe");
//		opt = new ChromeOptions();
//		opt.setExperimentalOption("debuggerAddress", "localhost:8050");
//		
//		driver = new ChromeDriver(opt);
////		driver.get(appUrl);
//		driver.manage().window().maximize();
		
		driver = new EdgeDriver();
		WebDriverManager.edgedriver().setup();
		
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
