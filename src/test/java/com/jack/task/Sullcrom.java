package com.jack.task;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

public class Sullcrom {

	

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
//	}
	
	@Test(dataProvider = "userdata")
	public void run(String link) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		Actions actions = new Actions(driver);
		int row = utitle.getRowCount(fileOut, sheetOut);
		row++;
//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='frame yesHeadshot']/h2")));

		utitle.setCellData(fileOut, sheetOut, row, 0, link);
		
		
//		PRACTICES 
//		try {
//			int rang = driver.findElements(By.xpath("//strong[normalize-space()='PRACTICES & CAPABILITIES']/parent::div/ul/li")).size();
//			String main = "";
//			for (int i = 0; i < rang; i++) {
//				String pra = driver.findElement(By.xpath("//strong[normalize-space()='PRACTICES & CAPABILITIES']/parent::div/ul/li["+(i+1)+"]")).getText();
//				main += pra+"//";
//			}
//			utitle.setCellData(fileOut, sheetOut, row, 1, main);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
//		EDUCATION 
//		try {
//			int rang = driver.findElements(By.xpath("//strong[normalize-space()='EDUCATION']/parent::div/ul/li")).size();
//			String main = "";
//			for (int i = 0; i < rang; i++) {
//				String edu = driver.findElement(By.xpath("//strong[normalize-space()='EDUCATION']/parent::div/ul/li["+(i+1)+"]")).getText();
//				main += edu+"//";
//			}
//			utitle.setCellData(fileOut, sheetOut, row, 2, main);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
//		BAR ADMISSIONS
//		try {
//			int rang = driver.findElements(By.xpath("//strong[normalize-space()='BAR ADMISSIONS']/parent::div/ul/li")).size();
//			String main = "";
//			for (int i = 0; i < rang; i++) {
//				String bar = driver.findElement(By.xpath("//strong[normalize-space()='BAR ADMISSIONS']/parent::div/ul/li["+(i+1)+"]")).getText();
//				main += bar+"//";
//			}
//			utitle.setCellData(fileOut, sheetOut, row, 3, main);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
//		LANGUAGES
//		try {
//			int rang = driver.findElements(By.xpath("//strong[normalize-space()='LANGUAGES']/parent::div/ul/li")).size();
//			String main = "";
//			for (int i = 0; i < rang; i++) {
//				String lan = driver.findElement(By.xpath("//strong[normalize-space()='LANGUAGES']/parent::div/ul/li["+(i+1)+"]")).getText();
//				main += lan+"//";
//			}
//			utitle.setCellData(fileOut, sheetOut, row, 4, main);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}	
		
		
//		Bio
		try {
				StringBuilder lan = new StringBuilder(
				driver.findElement(By.xpath("//strong[normalize-space()='CLERKSHIPS']/parent::div")).getText()
						);
			
			utitle.setCellDataBig(fileOut, sheetOut, row, 1, lan);
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
