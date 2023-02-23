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

public class Shearman {
	

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
//	@Test
//	public void name() throws InterruptedException, IOException {
//		
//		
//		int pg = 82;
//		int x = 1;
//		while (x != pg) {
//			int totalPerPage = driver.findElements(By.xpath("(//div[contains(@class,'search-content xs-sm-hidden')])")).size();
//			int row = utitle.getRowCount(fileOut, sheetOut);
//	
//		for (int i = 0; i < totalPerPage/2; i++) {
//			
//			
//			row++;
//			try {
//				String details = driver.findElement(By.xpath("(//div[contains(@class,'search-content xs-sm-hidden')])["+(i+1)+"]")).getText();
//				utitle.setCellData(fileOut, sheetOut, row, 0, details);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//			try {
//				String link = driver.findElement(By.xpath("(//div[contains(@class,'search-content xs-sm-hidden')])["+(i+1)+"]/div[1]/div/a")).getAttribute("href");
//				utitle.setCellData(fileOut, sheetOut, row, 1, link);
//				System.out.println(link);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//		
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("(//a[@class='next page-numbers'])[1]")).click();
//		
//		}
//		
//	}
	
	@Test(dataProvider = "userdata")
	public void run(String link) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions actions = new Actions(driver);
		int row = utitle.getRowCount(fileOut, sheetOut);
		row++;
//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		utitle.setCellData(fileOut, sheetOut, row, 0, link);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='OverviewButton']")));

		
		String missing = "Missing";
//		
////		Email
//		try {
//			String email = driver.findElement(By.xpath("//div[@class='masonry-grid-item-container']/div/p[2]|//div[@class='block-bio-details']/p")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 1, email);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 1, missing);
//		}
		
		
////		Media & Insight
//		try {
//			String media = driver.findElement(By.xpath("//h5[normalize-space()='Media & Insight']/parent::div/ul")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 2, media);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 2, missing);
//		}
		
		
//		Practices & Industries
		try {
			
			driver.findElement(By.xpath("//a[@id='EducationButton']")).click();
			Thread.sleep(1000);
			String main = "";
			int numberOf = driver.findElements(By.xpath("//p[normalize-space()='Practices']/following-sibling::ul/li")).size();
			for (int i = 1; i < numberOf+1; i++) {
				String lang = driver.findElement(By.xpath("//p[normalize-space()='Practices']/following-sibling::ul/li["+i+"]")).getText();
				if (i<=2) {
					utitle.setCellData(fileOut, sheetOut, row, 7+i, lang);
				}else {
					main+="; "+lang;
				}
			}
			
			utitle.setCellData(fileOut, sheetOut, row, 10, main);
		} catch (Exception e) {
			utitle.setCellData(fileOut, sheetOut, row, 10, missing);
		}

//		div[@class='qualifications-listing__school']/following-sibling::div[@class='rtf']
		
//		edu
		try {
			String col6 = "";
			
			int numberOfLan = driver.findElements(By.xpath("(//div[@class='rtf']//div[@class='qualifications-listing__school'])")).size();
			for (int i = 1; i < numberOfLan+1; i++) {
				String eduName = "";
				eduName = driver.findElement(By.xpath("(//div[@class='rtf']//div[@class='qualifications-listing__school'])["+i+"]")).getText();
				if (i<=5) {
				try {
					String eduNameLast = driver.findElement(By.xpath("(//div[@class='rtf']//div[@class='qualifications-listing__school'])["+i+"]/following-sibling::div[@class='rtf'][1]")).getText();
					eduName+=" "+eduNameLast;
				} catch (Exception e) {
					// TODO: handle exception
				}
				
					utitle.setCellData(fileOut, sheetOut, row, 0+i, eduName);
				}else {
					String eduNameLast = driver.findElement(By.xpath("(//div[@class='rtf']//div[@class='qualifications-listing__school'])["+i+"]/following-sibling::div[@class='rtf'][1]")).getText();
					eduName+=" "+eduNameLast;
					col6+=";"+eduName;
				}
				
				
			}
			utitle.setCellData(fileOut, sheetOut, row, 7, col6);
			
		} catch (Exception e) {
			utitle.setCellData(fileOut, sheetOut, row, 7, missing);
		}
		
//		Admissions & Credentials
//		try {
//			String main = "";
//			int numberOfLan = driver.findElements(By.xpath("//h5[normalize-space()='Admissions & Credentials']/parent::div/p")).size();
//			for (int i = 1; i < numberOfLan+1; i++) {
//				String lang = driver.findElement(By.xpath("//h5[normalize-space()='Admissions & Credentials']/parent::div/p["+i+"]")).getText();
//				main+=lang+" $$ ";
//			}
//			
//			utitle.setCellData(fileOut, sheetOut, row, 5, main);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 5, missing);
//		}
//		
////		Memberships
//		try {
//			String main = "";
//			int numberOfLan = driver.findElements(By.xpath("//h5[normalize-space()='Memberships']/parent::div/ul/li")).size();
//			for (int i = 1; i < numberOfLan+1; i++) {
//				String lang = driver.findElement(By.xpath("//h5[normalize-space()='Memberships']/parent::div/ul/li["+i+"]/p")).getText();
//				main+=lang+" $$ ";
//			}
//			
//			utitle.setCellData(fileOut, sheetOut, row, 6, main);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 6, missing);
//		}
//		
////		Experience
//		try {
//			String exp = driver.findElement(By.xpath("//h5[normalize-space()='Experience']/parent::section/div/div/ul")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 7, exp);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 7, missing);
//		}
//
////		Bio
//		try {
//			StringBuilder bio = new StringBuilder();
//			bio.append(driver.findElement(By.xpath("//section[contains(@class,'background-white masonry-grid-item')]//div[contains(@class,'')]")).getText());
//			
//			utitle.setCellDataBig(fileOut, sheetOut, row, 8, bio);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 8, missing);
//		}
//		
//
////		Rankings & Accolades
//		try {
//			String rank = driver.findElement(By.xpath("//h5[normalize-space()='Rankings & Accolades']/parent::div/ul")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 9, rank);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 9, missing);
//		}
//		
////		All Contact 
//		try {
//			String contact = driver.findElement(By.xpath("//div[@class='bio-contact-mods row']")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 10, contact);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 10, missing);
//		}
//		
////		Passions 
//		try {
//			String pass = driver.findElement(By.xpath("//h5[normalize-space()='Passions']/parent::div/ul/p")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 11, pass);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 11, missing);
//		}
//		
//
//		
////		Image
//		try {
//			String title = driver.findElement(By.xpath("//section[@class='hero-image']/img")).getAttribute("src");
//			utitle.setCellData(fileOut, sheetOut, row, 12, title);
//		} catch (Exception e) {
//			utitle.setCellData(fileOut, sheetOut, row, 12, missing);
//		}
//
//	
//		
		

		
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
