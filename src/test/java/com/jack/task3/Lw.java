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
import com.jack.utility.Scraping;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lw {
	
	ObjectMapper mapper = new ObjectMapper();
	ExcelUtlity utitle = new ExcelUtlity();
	List<Scraping> lists = new ArrayList<Scraping>();
	
	WebDriver driver;
	ChromeOptions opt;
	
	String filename = "D:\\Fiverr Work\\Jack Project 2\\Latham & Watkins\\All Links.xlsx";
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
		Actions actions = new Actions(driver);

//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		Scraping sm = new Scraping();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='hero-people-detail__header']")));

		sm.setLink(link);
		
//	Name
	try {
		String name = driver.findElement(By.xpath("//h1[@class='hero-people-detail__name']")).getText();
		sm.setName(name);
	} catch (Exception e) {
		
	}
		
////		Titile
		try {
			String title = driver.findElement(By.xpath("//p[@class='hero-people-detail__title']")).getText();
			sm.setTitle(title);
		} catch (Exception e) {
			
		}
		
////	Email
	try {
		String email = driver.findElement(By.xpath("//div[@class='contact-tabs__panels']/div[1]/a")).getText();
		sm.setEmail(email);
	} catch (Exception e) {
		
	}
	
	
	//Phone
	try {
		String phone = driver.findElement(By.xpath("//div[@class='contact-tabs__panels']/div[1]/ul/li[1]")).getText();
		sm.setPhone(phone);
	} catch (Exception e) {
		
	}
		
		
////		Location
		try {
		String main = "";
		int locationSize = driver.findElements(By.xpath("(//ul[contains(@class,'office--tabs')]/li)")).size();
		for (int i = 1; i < locationSize+1; i++) {
			String location = driver.findElement(By.xpath("(//ul[contains(@class,'office--tabs')]/li)["+i+"]")).getText();
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
		
		
//		Language
		try {
			String main = "";
			int numberOfLan = driver.findElements(By.xpath("//h3[normalize-space()='Languages Spoken']/following-sibling::ul/li")).size();
			for (int i = 1; i < numberOfLan+1; i++) {
				String lang = driver.findElement(By.xpath("(//h3[normalize-space()='Languages Spoken']/following-sibling::ul/li)["+i+"]")).getText();
				main+=" ;"+lang;
			}
			
			sm.setLanguage(main);
		} catch (Exception e) {
			
		}

//		Bio
		try {
			StringBuilder bio = new StringBuilder();
			bio.append(driver.findElement(By.xpath("//section[@id='profile']//div[@class='section-content']")).getText());
			bio.append(" ");
			try {
				bio.append(driver.findElement(By.xpath("//section[@id='experience']//div[@class='section-content']")).getText());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			sm.setBio(bio);
		} catch (Exception e) {
			
		}
		
////		Experience
//		try {
//			StringBuilder exp = new StringBuilder();
//			exp.append();
//			
//			utitle.setCellDataBig(fileOut, sheetOut, row, 5, exp);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 5, missing);
//		}
		
		
//		Bar
		try {
			String main = "";
			int numberOfLan = driver.findElements(By.xpath("//h3[normalize-space()='Bar Qualification']/parent::div/ul/li")).size();
			for (int i = 1; i < numberOfLan+1; i++) {
				String bar = driver.findElement(By.xpath("//h3[normalize-space()='Bar Qualification']/parent::div/ul/li["+i+"]")).getText();
				if (i <= 1) {
					sm.setBar1(bar);
				}
				main+=";"+bar;
			}
			
			sm.setBarAll(main);
		} catch (Exception e) {
			
		}
		
////		Image
//		try {
//			String title = driver.findElement(By.xpath("//div[@class='hero-people-detail__image']/div/img")).getAttribute("src");
//			utitle.setCellData(fileOut, sheetOut, row, 7, title);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 7, missing);
//		}
//		https://www.lw.com/en/people/christopher-drewry
		
		
//		PRACTICES
		try {
			String main = "";
			int numberOfLan = driver.findElements(By.xpath("//h3[normalize-space()='Practices']/parent::div/ul/li")).size();
			for (int i = 1; i < numberOfLan+1; i++) {
				String par = driver.findElement(By.xpath("//h3[normalize-space()='Practices']/parent::div/ul/li["+i+"]/a")).getText();
				if (i <= 2) {
					if (i==1) {
						sm.setPratic1(par);
					}else if (i==2) {
						sm.setPratic2(par);
					}
				}
				main+=";"+par;
			}
			
			sm.setPraticAll(main);
		} catch (Exception e) {
			
		}
		
		
//		Education
		try {
			String main = "";
			int numberOfLan = driver.findElements(By.xpath("//h3[normalize-space()='Education']/parent::div/ul/li")).size();
			for (int i = 1; i < numberOfLan+1; i++) {
				String edu = driver.findElement(By.xpath("//h3[normalize-space()='Education']/parent::div/ul/li["+i+"]")).getText();
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
		
		//	Image 
	try {
		String img = driver.findElement(By.xpath("//div[@class='hero-people-detail__image']/div/img")).getAttribute("src");
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
		
		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project 2\\Latham & Watkins\\row.json"), lists);
		System.out.println("Data Saved");
	}
	
	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
//		
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
