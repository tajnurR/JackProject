package com.jack.task2;

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
import com.jack.utility.Scraping;

public class Kirkland {

	

	ExcelUtlity utitle = new ExcelUtlity();
	
	WebDriver driver;
	ChromeOptions opt;
	
	String filename = ".//files//excel//input.xlsx";
	String sheetName = "Sheet1";
	
	String fileOut = ".//files//excel//output.xlsx";
	String sheetOut = "Sheet1";
	
	List<Scraping> lists = new ArrayList<Scraping>();
//	fsdfsdfsdfsdfsdf
//	cd C:\Program Files\Google\Chrome\Application
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=D:\Work\Chrome
	
	@Test
	public void testx() {
		System.out.println("Okay");
	}

	
	@Test(dataProvider = "userdata")
	public void run(String link) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Actions actions = new Actions(driver);
		int row = utitle.getRowCount(fileOut, sheetOut);
		row++;
//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		
		Scraping sm = new Scraping();
		
//		utitle.setCellData(fileOut, sheetOut, row, 0, link);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='profile-heading__name']/span")));
		
		
		

	//	Name
		try {
			String name = driver.findElement(By.xpath("//h1[@class='profile-heading__name']/span")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 1, name);
			sm.setName(name);

		} catch (Exception e) {
			
		}
		
//		//	Title
		try {
			String title = driver.findElement(By.xpath("//header[@class='profile-heading__main']/div[@class='profile-heading__experience']/span")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 2, title);
			sm.setTitle(title);
		} catch (Exception e) {
			
		}
//		
//		//	Email
		try {
			String email = driver.findElement(By.xpath("//a[@class='profile-heading__email']")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 3, email);
			sm.setEmail(email);
		} catch (Exception e) {
			
		}
//		
		//	Phone
		try {
			String phone = driver.findElement(By.xpath("//div[@class='profile-heading__locations']/p[1]/a[2]")).getText();
//			utitle.setCellData(fileOut, sheetOut, row, 4, phone);
			sm.setPhone(phone);
		} catch (Exception e) {
			
		}
//
//		Location
		try {
			String main = "";
			int locationSize = driver.findElements(By.xpath("//div[@class='profile-heading__locations']/p")).size();
			for (int i = 1; i < locationSize+1; i++) {
				String location = driver.findElement(By.xpath("//div[@class='profile-heading__locations']/p["+i+"]/a[1]")).getText();
				if (i<=1) {
//					utitle.setCellData(fileOut, sheetOut, row, 5, location);
					sm.setLocation1(location);
				}else {
					main+=";"+location;
				}
			}
//			utitle.setCellData(fileOut, sheetOut, row, 6, main);
			sm.setLocationAll(main);
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//		practice
		
//		try {
//			driver.findElement(By.xpath("//button[normalize-space()='Areas of Work']")).click();
//			Thread.sleep(2000);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		try {
			
			String main = "";
			int practiceSize = driver.findElements(By.xpath("//ul[@class='listing-services__items']/li")).size();
			for (int i = 1; i < practiceSize+1; i++) {
				String practice = driver.findElement(By.xpath("//ul[@class='listing-services__items']/li["+i+"]")).getAttribute("innerHTML");
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
//			utitle.setCellData(fileOut, sheetOut, row, 10, main);
			sm.setPraticAll(main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Bio
		try {
			StringBuilder bio = new StringBuilder();
				bio.append(driver.findElement(By.xpath("//div[@class='overview-block__body section__column-body']")).getAttribute("innerHTML"));
				try {

					bio.append(driver.findElement(By.xpath("//div[@class='read-more']")).getAttribute("innerHTML"));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
//			utitle.setCellDataBig(fileOut, sheetOut, row, 7, bio);
			sm.setBio(bio);
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Admissions
		try {
			String main = "";
			int LangSize = driver.findElements(By.xpath("//div[contains(@class,'normalized-rte-list--admissions')]/ul/li")).size();
			
			for (int i = 1; i < LangSize+1; i++) {
				String bar = driver.findElement(By.xpath("//div[contains(@class,'normalized-rte-list--admissions')]/ul/li["+i+"]")).getAttribute("innerHTML");
				
				if (i<=1) {
//					utitle.setCellData(fileOut, sheetOut, row, 11, bar);
					sm.setBar1(bar);
				}else {
					main+=";"+bar;
				}
					
			
			}
//			utitle.setCellData(fileOut, sheetOut, row, 12, main);
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
			int eduSize = driver.findElements(By.xpath("//div[contains(@class,'normalized-rte-list--education')]/ul/li")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("//div[contains(@class,'normalized-rte-list--education')]/ul/li["+i+"]")).getAttribute("innerHTML");
				if (i<=5) {
//					utitle.setCellData(fileOut, sheetOut, row, 12+i, edu);
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
//			utitle.setCellData(fileOut, sheetOut, row, 18, main);
			sm.setEduAll(main);
		
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
//		Language
		try {
			String main = "";
			int eduSize = driver.findElements(By.xpath("//div[contains(@class,'normalized-rte-list--languages')]/ul/li")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("//div[contains(@class,'normalized-rte-list--languages')]/ul/li["+i+"]")).getAttribute("innerHTML");
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
//		try {
			
//			Select lo = new Select(driver.findElement(By.xpath("//div[@class='col width_narrow']//select[@class='select2-hidden-accessible']")));
//			lo.selectByValue("clerkship");
//			Thread.sleep(1000);
				
//			String main = "";
//			int eduSize = driver.findElements(By.xpath("(//span[normalize-space()='Judicial Clerkship']/following-sibling::div/p)|(//span[normalize-space()='Judicial Clerkship']/following-sibling::div/ul/li)")).size();
//			for (int i = 1; i < eduSize+1; i++) {
//				String edu = driver.findElement(By.xpath("(//span[normalize-space()='Judicial Clerkship']/following-sibling::div/p)["+i+"]|(//span[normalize-space()='Judicial Clerkship']/following-sibling::div/ul/li)["+i+"]")).getText();
//					main+=";"+edu;
//			}
//			utitle.setCellData(fileOut, sheetOut, row, 20, main);
			
//		
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
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
			String img = driver.findElement(By.xpath("//img[@class='profile-heading__snapshot']")).getAttribute("src");
//			utitle.setCellData(fileOut, sheetOut, row, 21, img);
			sm.setImg(img);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		lists.add(sm);
		
		driver.close();
		driver.switchTo().window(ls.get(0));
	}
	
	
	@Test
	public void saveList() throws IOException {
		int row = utitle.getRowCount(fileOut, sheetOut);
		
		for (int i = 0; i < lists.size(); i++) {
			row++;
			utitle.setCellData(fileOut, sheetOut, row, 0, lists.get(i).getLink());
			utitle.setCellData(fileOut, sheetOut, row, 1, lists.get(i).getName());
			utitle.setCellData(fileOut, sheetOut, row, 2, lists.get(i).getTitle());
			utitle.setCellData(fileOut, sheetOut, row, 3, lists.get(i).getEmail());
			utitle.setCellData(fileOut, sheetOut, row, 4, lists.get(i).getPhone());
			utitle.setCellData(fileOut, sheetOut, row, 5, lists.get(i).getLocation1());
			utitle.setCellData(fileOut, sheetOut, row, 6, lists.get(i).getLocationAll());
			utitle.setCellDataBig(fileOut, sheetOut, row, 7, lists.get(i).getBio());
			utitle.setCellData(fileOut, sheetOut, row, 8, lists.get(i).getPratic1());
			utitle.setCellData(fileOut, sheetOut, row, 9, lists.get(i).getPratic2());
			utitle.setCellData(fileOut, sheetOut, row, 10, lists.get(i).getPraticAll());
			utitle.setCellData(fileOut, sheetOut, row, 11, lists.get(i).getBar1());
			utitle.setCellData(fileOut, sheetOut, row, 12, lists.get(i).getBarAll());
			utitle.setCellData(fileOut, sheetOut, row, 13, lists.get(i).getEdu1());
			utitle.setCellData(fileOut, sheetOut, row, 14, lists.get(i).getEdu2());
			utitle.setCellData(fileOut, sheetOut, row, 15, lists.get(i).getEdu3());
			utitle.setCellData(fileOut, sheetOut, row, 16, lists.get(i).getEdu4());
			utitle.setCellData(fileOut, sheetOut, row, 17, lists.get(i).getEdu5());
			utitle.setCellData(fileOut, sheetOut, row, 18, lists.get(i).getEduAll());
			utitle.setCellData(fileOut, sheetOut, row, 19, lists.get(i).getLanguage());
			utitle.setCellData(fileOut, sheetOut, row, 20, lists.get(i).getClerkship());
			utitle.setCellData(fileOut, sheetOut, row, 21, lists.get(i).getImg());

			
		}
	}
	
	
	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
		
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\JackProject\\files\\devtools\\chromedriver.exe");
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
