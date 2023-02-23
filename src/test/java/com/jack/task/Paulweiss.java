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

public class Paulweiss {
	

	

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
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='breadcrumb bioBreadcrumb']")));

		utitle.setCellData(fileOut, sheetOut, row, 0, link);
		
//		Practices
		try {
			String main = "";
			int practicesSize = driver.findElements(By.xpath("//ul[@class='pratices-menu bio-pratices-menu']/li")).size();
			
			for (int i = 0; i < practicesSize; i++) {
				String p = driver.findElement(By.xpath("//ul[@class='pratices-menu bio-pratices-menu']/li["+(i+1)+"]")).getText();
				main = main + "/"+ p;
			}
			utitle.setCellData(fileOut, sheetOut, row, 1, main);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

//		Edu
		try {
			driver.findElement(By.xpath("(//div[@class='education'])[1]/b/a")).click();
			Thread.sleep(1000);
			String edu = driver.findElement(By.xpath("//div[@class='partners-two m-partners-two']//div[@class='education']//div[@class='block-print']")).getAttribute("innerHTML");
			utitle.setCellData(fileOut, sheetOut, row, 2, edu.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		clerkship
		try {
			driver.findElement(By.xpath("(//div[@class='clerkship'])[1]/b/a")).click();
			Thread.sleep(1000);
			String clerk = driver.findElement(By.xpath("//div[@class='partners-two m-partners-two']//div[@class='clerkship']//div[@class='block-print']")).getText();
			utitle.setCellData(fileOut, sheetOut, row, 3, clerk);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Bar Addmission
		try {
			driver.findElement(By.xpath("(//div[@class='bar-admissions'])[1]/b/a")).click();
			Thread.sleep(1000);
			String bar = driver.findElement(By.xpath("//div[@class='partners-two m-partners-two']//div[@class='bar-admissions']//div[@class='block-print']")).getText();
			utitle.setCellData(fileOut, sheetOut, row, 4, bar);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Bio
		try {
			StringBuilder bio = new StringBuilder();
			int practicesSize = driver.findElements(By.xpath("//div[@class='pratices-pages']/p")).size();
			
			for (int i = 0; i < practicesSize; i++) {
				bio.append(
						driver.findElement(By.xpath("//div[@class='pratices-pages']/p["+(i+1)+"]")).getText()
						);
				
			}
			utitle.setCellDataBig(fileOut, sheetOut, row, 5, bio);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Experience
		try {
			StringBuilder exps = new StringBuilder();
			exps.append(
						driver.findElement(By.xpath("//div[@class='experience']")).getText()
						);
			utitle.setCellDataBig(fileOut, sheetOut, row, 6, exps);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			String img = driver.findElement(By.xpath("//div[@class='partner-img']/img")).getAttribute("src");
			utitle.setCellData(fileOut, sheetOut, row, 7, img);
			System.out.println(img);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//
//		try {
//			StringBuilder st = new StringBuilder();
//			st.append(
//					driver.findElement(By.xpath("//div[@class='experience']")).getText()
//					);
//			System.out.println(st);
//			utitle.setCellDataBig(fileOut, sheetOut, row, 1, st);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
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
