package com.jack.task4;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v108.network.model.ConnectionType;
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

public class Jenner {
	
	ObjectMapper mapper = new ObjectMapper();
	ExcelUtlity utitle = new ExcelUtlity();
	List<Scraping> lists = new ArrayList<Scraping>();
	
	
	String filename = "D:\\Fiverr Work\\Jack Project 4\\Jenner & Block LLP\\input.xlsx";
	String sheetName = "Sheet2";

	
//	cd C:\Program Files\Google\Chrome\Application
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=C:\Users\tajnu\eclipse-workspace\JackProject\files\chrome
	
	@Test
	public void testx() {
		System.out.println("Okay");
	}

	
	@Test(dataProvider = "userdata")
	public void run(String link, String html) throws IOException {
		Scraping sm = new Scraping();
		
//		Connection connection = Jsoup.connect(link);
//		connection.method(Connection.Method.GET);
//		connection.referrer("http://www.google.com/");
//		 connection.userAgent("Mozilla/5.0 (Linux; Android 13) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.5563.116 Mobile Safari/537.36");
//		 connection.header("accept", "*/*");
//		 connection.timeout(3000);
//		 connection.header("content-type", "text/plain;charset=UTF-8");
//		 Document doc = connection.get();
		
		Document doc = Jsoup.parse(html);
		
		sm.setLink(link);

		//Name
//		try {
//			String name = doc.selectXpath("//h1[contains(@class,'color-yellow type__bioDisplay')]").text();
//			sm.setName(name);
//		} catch (Exception e) {
//			
//		}
		
		//Title
//			try {
//				String title = doc.selectXpath("//h2[contains(@class,'color-white type__h2')]").text();
//				sm.setTitle(title);
//			} catch (Exception e) {
//				
//			}
			
			//	Phone
//			try {
//				String phone = doc.selectXpath("(//a[contains(@href,'tel:')])[1]").text();
//				sm.setPhone(phone);
//			} catch (Exception e) {
//					
//			}
		
		//	Email
//		try {
//			String email = doc.selectXpath("(//a[contains(@href,'mailto:')])[1]").attr("href");
//			sm.setEmail(email);
//		} catch (Exception e) {
//			
//		}
		
//		Location
//		try {
//			String main = "";
//			int locaSize = doc.selectXpath("(//h3[normalize-space()='Office']/following-sibling::p)").size();
//			for (int i = 1; i < locaSize+1; i++) {
//				String location = doc.selectXpath("(//h3[normalize-space()='Office']/following-sibling::p)["+i+"]").text();
//				if (i<=1) {
//					sm.setLocation1(location);
//				}else {
//					main+=";"+location;
//				}
//			}
//			sm.setLocationAll(main);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		

		
//		practice
//		try {
//			String pratic = doc.selectXpath("//div[@id='areas-of-focus']/div/div/div").attr("innerHTML");
//			sm.setPraticAll(pratic);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
		
//		Bio
//		try {
//			
//			StringBuilder bio = new StringBuilder();
//			bio.append(doc.selectXpath("//div[@id='overview']").text());
//			
//			sm.setBio(bio);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

		
//		Edu
		try {
		
//			driver.findElement(By.xpath("(//span[text()='Education']/parent::button)[1]")).click();
//			Thread.sleep(1000);
			
			String main = "";
			int eduSize = doc.selectXpath("((//span[text()='Education']/parent::button)[1]/parent::h3/following-sibling::div/div/div[contains(@class,'rte')]/ul/li)").size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = doc.selectXpath("((//span[text()='Education']/parent::button)[1]/parent::h3/following-sibling::div/div/div[contains(@class,'rte')]/ul/li)["+i+"]").text();
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
		
		
//		Clerkships
		try {
			//driver.findElement(By.xpath("(//span[text()='Clerkships']/parent::button)[1]")).click();
			//Thread.sleep(1000);
			
			String main = "";
			int clkSize = doc.selectXpath("((//span[text()='Clerkships']/parent::button)[1]/parent::h3/following-sibling::div/div/div[contains(@class,'rte')]/ul/li)").size();
			for (int i = 1; i < clkSize+1; i++) {
				String practice = doc.selectXpath("((//span[text()='Clerkships']/parent::button)[1]/parent::h3/following-sibling::div/div/div[contains(@class,'rte')]/ul/li)["+i+"]").text();
					main+=";"+practice;
			}
			sm.setClerkship(main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Admissions
		try {
			
//			driver.findElement(By.xpath("(//span[text()='Admissions']/parent::button)[1]")).click();
//			Thread.sleep(1000);
			String main = "";
			int LangSize = doc.selectXpath("((//span[text()='Admissions']/parent::button)[1]/parent::h3/following-sibling::div/div/div[@class='rte']/ul/li)").size();
			
			for (int i = 1; i < LangSize+1; i++) {
				String bar = doc.selectXpath("((//span[text()='Admissions']/parent::button)[1]/parent::h3/following-sibling::div/div/div[@class='rte']/ul/li)["+i+"]").text();
				
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
		
		
		//Language
//		try {
//			String main = "";
//			int LangSize = driver.findElements(By.xpath("(//h2[normalize-space()='Languages']/parent::div/following-sibling::div/div/div[@class='firstul']/ul/li)")).size();
//			
//			for (int i = 1; i < LangSize+1; i++) {
//				String language = driver.findElement(By.xpath("(//h2[normalize-space()='Languages']/parent::div/following-sibling::div/div/div[@class='firstul']/ul/li)["+i+"]")).getText();
//					main+=";"+language;
//			
//			}
//			sm.setLanguage(main);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
//		img
//		try {
//			String img = doc.selectXpath("(//img[@alt='Attorney Image'])[2]").attr("src");
//			sm.setImg(img);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
			lists.add(sm);
	}
	

	@Test
	public void saveList() throws Exception {
		
		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project 4\\Jenner & Block LLP\\row.json"), lists);
		System.out.println("Data saved");
		
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
