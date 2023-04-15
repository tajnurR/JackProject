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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.utility.ExcelUtlity;
import com.jack.utility.Scraping;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Steptoe {
	

	ObjectMapper mapper = new ObjectMapper();
	ExcelUtlity utitle = new ExcelUtlity();
	List<Scraping> lists = new ArrayList<Scraping>();
	
	WebDriver driver;
	ChromeOptions opt;
	
	String filename = "D:\\Fiverr Work\\Jack Project 2\\Steptoe & Johnson LLP//input.xlsx";
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Actions actions = new Actions(driver);
		
//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		Scraping sm = new Scraping();
		
		sm.setLink(link);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'black')]/following-sibling::h1")));

	//	Name
		try {
			String name = driver.findElement(By.xpath("//div[contains(@class,'black')]/following-sibling::h1")).getText();
			sm.setName(name);
		} catch (Exception e) {
			
		}
//		
//		
//		//	Title
		try {
			String title = driver.findElement(By.xpath("//div[contains(@class,'black')]/following-sibling::h2")).getText();
			sm.setTitle(title);
		} catch (Exception e) {
			
		}
		
		//	Email
		try {
			String email = driver.findElement(By.xpath("//a[contains(@data-href,'mailto:')]")).getAttribute("data-href");
			sm.setEmail(email);
		} catch (Exception e) {
			
		}
//		
//		//	Phone
		try {
			String phone = driver.findElement(By.xpath("(//a[contains(@href,'tel:')])[1]")).getText();
			sm.setPhone(phone);
		} catch (Exception e) {
			
		}

//		Location
		try {
			String main = "";
			int locationSize = driver.findElements(By.xpath("//div[contains(@class,'bio-header__office-pair')]/div")).size();
			for (int i = 1; i < locationSize+1; i++) {
				String location = driver.findElement(By.xpath("(//div[contains(@class,'bio-header__office-pair')]/div)["+i+"]")).getText();
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
		
		

		
//		Bio
		try {
			
			StringBuilder bio = new StringBuilder();
				bio.append(driver.findElement(By.xpath("//h2[normalize-space()='Overview']/parent::div/following-sibling::div[@class='rte']")).getText());
				
//				try {
//					bio.append(driver.findElement(By.xpath("(//section[contains(@class,'explore-experience')])[1]")).getAttribute("innerHTML"));
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
				
			sm.setBio(bio);
		} catch (Exception e) {
			// TODO: handle exception
		}
//

		
//		practice
		
		try {
			driver.findElement(By.xpath("//button[normalize-space()='Areas of Work']")).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			String main = "";
			int practiceSize = driver.findElements(By.xpath("//h2[normalize-space()='Areas of Work']/parent::div/following-sibling::div")).size();
			for (int i = 1; i < practiceSize+1; i++) {
				String practice = driver.findElement(By.xpath("//h2[normalize-space()='Areas of Work']/parent::div/following-sibling::div["+i+"]")).getText();
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
		
//		Admissions
		try {
			String main = "";
			int LangSize = driver.findElements(By.xpath("//div[@id='bar-court-admissions']/div/div/ul/li")).size();
			
			for (int i = 1; i < LangSize+1; i++) {
				String bar = driver.findElement(By.xpath("//div[@id='bar-court-admissions']/div/div/ul/li["+i+"]")).getAttribute("innerHTML");
				
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
			int eduSize = driver.findElements(By.xpath("//div[@id='education']/div/div/ul/li")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("//div[@id='education']/div/div/ul/li["+i+"]")).getAttribute("innerHTML");
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
			int eduSize = driver.findElements(By.xpath("//p[normalize-space()='Languages']/following-sibling::p")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("//p[normalize-space()='Languages']/following-sibling::p["+i+"]")).getText();
					main+=";"+edu;
			}
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
			int eduSize = driver.findElements(By.xpath("//div[@id='clerkships']/div/div/ul/li")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("//div[@id='clerkships']/div/div/ul/li["+i+"]")).getAttribute("innerHTML");
					main+=";"+edu;
			}
			sm.setClerkship(main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//		//	Clerkships
//		try {
//			String cls = driver.findElement(By.xpath("//h5[normalize-space()='Clerkships']/following-sibling::p")).getAttribute("innerHTML");
//			utitle.setCellData(fileOut, sheetOut, row, 19, cls);
//		} catch (Exception e) {
//			
//		}
		
		
//		Image 
		try {
			String img = driver.findElement(By.xpath("//picture[@data-class='bio-header__image']/img")).getAttribute("src");
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
		
		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project 2\\Steptoe & Johnson LLP\\row.json"), lists);
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
