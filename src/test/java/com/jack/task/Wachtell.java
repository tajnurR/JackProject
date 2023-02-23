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

public class Wachtell {
	

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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		Actions actions = new Actions(driver);
		int row = utitle.getRowCount(fileOut, sheetOut);
		row++;
//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='attorney--name']")));

		utitle.setCellData(fileOut, sheetOut, row, 0, link);
		
////		Phone Number
//		try {
//			String phone = driver.findElement(By.xpath("//li[@class='attorney--meta-phone']")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 1, phone);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
////		Email
//		try {
//			String email = driver.findElement(By.xpath("//li[@class='attorney--meta-email']")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 2, email);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
////		Clerkships
//		try {
//			String Clerkships = driver.findElement(By.xpath("//div[@class='attorney--clerkships']")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 3, Clerkships);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
////		Bio (Bold)
//		try {
//			String bioB = driver.findElement(By.xpath("//div[@class='attorney--biography']/p[1]")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 4, bioB);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
////		Bio
//		try {
//			int bios = driver.findElements(By.xpath("//div[@class='attorney--biography']/p")).size();
//			
//			StringBuilder main = new StringBuilder();
//			for (int i = 2; i < bios+1; i++) {
//				String bioP = driver.findElement(By.xpath("//div[@class='attorney--biography']/p["+i+"]")).getText();
//				main.append(bioP);
////				System.out.println("Bio==="+bioP);
//				
//			}
//			System.out.println("Bio SB==="+main);
//		
//			utitle.setCellDataBig(fileOut, sheetOut, row, 5, main);
//		} catch (Exception e) {
//			System.err.println(e);
//		}
//		
////		Publications
//		try {
//			String pub = driver.findElement(By.xpath("//ul[@class='attorney--publications']")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 6, pub);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
////		img
//		try {
//			String img = driver.findElement(By.xpath("//div[@class='attorney--image']/img")).getAttribute("src");
//			utitle.setCellData(fileOut, sheetOut, row, 7, img);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
//		https://www.wlrk.com/attorney/dklam/

//		EDUCATION
		try {
			int prats = driver.findElements(By.xpath("//div[@class='attorney--education']/ul/li")).size();
			String main = "";
			for (int i = 1; i < (prats+1); i++) {
				String eduI = driver.findElement(By.xpath("//div[@class='attorney--education']/ul/li["+i+"]")).getText();
				if (i <= 5) {
					utitle.setCellData(fileOut, sheetOut, row, i+0, eduI);
				}else {
					main+=eduI+", ";
				}
				
				
			}
			utitle.setCellData(fileOut, sheetOut, row, 6, main);
			
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
