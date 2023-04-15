package com.jack.task4;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
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

public class Cooley {
	
	ObjectMapper mapper = new ObjectMapper();
	ExcelUtlity utitle = new ExcelUtlity();
	List<Scraping> lists = new ArrayList<Scraping>();
	
	WebDriver driver;
	ChromeOptions opt;
	
	String filename = "D:\\Fiverr Work\\Jack Project 4\\Cooly\\input.xlsx";
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Actions actions = new Actions(driver);
		
//		driver.manage().deleteAllCookies();
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windowHan = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHan);
		driver.get(link);
		Scraping sm = new Scraping();
		
		sm.setLink(link);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='block-bio-name-title']/h1|//section[contains(@class,'bio-description')]//div[contains(@class,'item-section')]/h1")));

//		Name 
		try {
			String name = driver.findElement(By.xpath("//div[@class='block-bio-name-title']/h1|//section[contains(@class,'bio-description')]//div[contains(@class,'item-section')]/h1")).getText();
			sm.setName(name);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//Title
		try {
			String title = driver.findElement(By.xpath("//div[@class='block-bio-name-title']/h4|//section[contains(@class,'bio-description')]//div[contains(@class,'item-section')]/h4")).getText();
			sm.setTitle(title);
		}catch(Exception e){
			
		}
		
		
		//Email
		try {
			String email = driver.findElement(By.xpath("(//a[contains(@href,'mailto:')])[1]")).getText();
			sm.setEmail(email);
		}catch(Exception e){
			
		}
		
		//Phone
		try {
			String phone = driver.findElement(By.xpath("(//a[contains(@href,'tel:')])")).getText();
			sm.setPhone(phone);
		}catch(Exception e){
			
		}
		
		
//		Location
		try {
			String main = "";
			int locaSize = driver.findElements(By.xpath("(//p[normalize-space()='Locations']/following-sibling::ul/li)")).size();
			for (int i = 1; i < locaSize+1; i++) {
				String location = driver.findElement(By.xpath("(//p[normalize-space()='Locations']/following-sibling::ul/li)["+i+"]")).getText();
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
			bio.append(driver.findElement(By.xpath("//p[contains(normalize-space(),'Download full')]/parent::div")).getText());
			
			sm.setBio(bio);
		} catch (Exception e) {
			// TODO: handle exception
		}

		

		
//		practice
		try {
			String main = "";
			int practiceSize = driver.findElements(By.xpath("(//h5[normalize-space()='Practices & Industries']/following-sibling::ul/li)")).size();
			for (int i = 1; i < practiceSize+1; i++) {
				String practice = driver.findElement(By.xpath("(//h5[normalize-space()='Practices & Industries']/following-sibling::ul/li)["+i+"]")).getText();
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
		
		

		
//		Edu
		try {
			
			String main = "";
			int eduSize = driver.findElements(By.xpath("(//h5[normalize-space()='Education']/following-sibling::ul/li)")).size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = driver.findElement(By.xpath("(//h5[normalize-space()='Education']/following-sibling::ul/li)["+i+"]")).getText();
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
			// TODO: handle exception
		}
		
		
		
//		Admissions
		try {
			
			String main = "";
			int LangSize = driver.findElements(By.xpath("(//h5[normalize-space()='Admissions & Credentials']/following-sibling::p)")).size();
			
			for (int i = 1; i < LangSize+1; i++) {
				String bar = driver.findElement(By.xpath("(//h5[normalize-space()='Admissions & Credentials']/following-sibling::p)["+i+"]")).getText();
				
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
		
//		img
		try {
			String img = driver.findElement(By.xpath("//section[@class='hero-image']/img")).getAttribute("src");
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
		
		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project 4\\Cooly\\row.json"), lists);
		System.out.println("Data Saved");
		driver.close();
		
	}
	
	
	
	@BeforeClass
	public void setup() {
		
		System.out.println("I setup ");
		
		driver = new EdgeDriver();
		WebDriverManager.edgedriver().setup();
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
