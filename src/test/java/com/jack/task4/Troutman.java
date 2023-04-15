package com.jack.task4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.utility.ExcelUtlity;
import com.jack.utility.Scraping;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Troutman {
	
	ObjectMapper mapper = new ObjectMapper();
	ExcelUtlity utitle = new ExcelUtlity();
	List<Scraping> lists = new ArrayList<Scraping>();
	
	
	
	String filename = "D:\\Fiverr Work\\Jack Project 4\\Troutman Pepper\\input.xlsx";
	String sheetName = "Sheet2";
	
	
//	cd C:\Program Files\Google\Chrome\Application
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=C:\Users\tajnu\eclipse-workspace\JackProject\files\chrome
	
	@Test
	public void testx() {
		System.out.println("Okay");
	}

	
	@Test(dataProvider = "userdata")
	public void run(String link) throws IOException {
		Scraping sm = new Scraping();
		sm.setLink(link);
		
		Connection connection = Jsoup.connect(link);
		 connection.userAgent("Mozilla/5.0");
		 Document doc = connection.get();
		

		//Name
		try {
			String name = doc.selectXpath("//h1[@class='type-bio-name']").text();
			sm.setName(name);
		} catch (Exception e) {
			
		}
		
		//Title
			try {
				String title = doc.selectXpath("//div[contains(@class,'header-content__position')]/p[1]").text();
				sm.setTitle(title);
			} catch (Exception e) {
				
			}
			
			//	Phone
			try {
				String phone = doc.selectXpath("(//a[contains(@class,'header-content__phone')])[1]").text();
				sm.setPhone(phone);
			} catch (Exception e) {
					
			}
		
		//	Email
		try {
			String email = doc.selectXpath("(//a[contains(@class,'header-content__email')])[1]").text();
			sm.setEmail(email);
		} catch (Exception e) {
			
		}
		
//		Location
		try {
			String main = "";
			int locaSize = doc.selectXpath("(//p[normalize-space()='Located in:']/following-sibling::ul/li)").size();
			for (int i = 1; i < locaSize+1; i++) {
				String location = doc.selectXpath("(//p[normalize-space()='Located in:']/following-sibling::ul/li)["+i+"]").text();
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
		

		
//		practice
		try {
			String main = "";
			int practiceSize = doc.selectXpath("(//p[normalize-space()='Areas of Focus:']/following-sibling::ul/li)").size();
			for (int i = 1; i < practiceSize+1; i++) {
				String practice = doc.selectXpath("(//p[normalize-space()='Areas of Focus:']/following-sibling::ul/li)["+i+"]").text();
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
			bio.append(doc.selectXpath("//div[@id='overview']").text());
			sm.setBio(bio);
		} catch (Exception e) {
			// TODO: handle exception
		}

		
//		Edu
		try {
			String main = "";
			int eduSize = doc.selectXpath("(//h3[normalize-space()='Education']/following-sibling::ul[1]/li)").size();
			for (int i = 1; i < eduSize+1; i++) {
				String edu = doc.selectXpath("(//h3[normalize-space()='Education']/following-sibling::ul[1]/li)["+i+"]").text();
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
			String main = "";
			int clkSize =doc.selectXpath("//h3[normalize-space()='Clerkships']/following-sibling::ul[1]/li").size();
			for (int i = 1; i < clkSize+1; i++) {
				String practice = doc.selectXpath("(//h3[normalize-space()='Clerkships']/following-sibling::ul[1]/li)["+i+"]").text();
					main+=";"+practice;
			}
			sm.setClerkship(main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		Admissions
		try {
			String main = "";
			int LangSize =doc.selectXpath("(//h3[normalize-space()='Bar Admissions']/following-sibling::ul[1]/li)").size();
			
			for (int i = 1; i < LangSize+1; i++) {
				String bar = doc.selectXpath("(//h3[normalize-space()='Bar Admissions']/following-sibling::ul[1]/li)["+i+"]").text();
				
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
		try {
			String main = "";
			int LangSize = doc.selectXpath("(//h3[normalize-space()='Languages']/following-sibling::ul[1]/li)").size();
			
			for (int i = 1; i < LangSize+1; i++) {
				String language = doc.selectXpath("(//h3[normalize-space()='Languages']/following-sibling::ul[1]/li)["+i+"]").text();
					main+=";"+language;
			
			}
			sm.setLanguage(main);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//		img
		try {
			String img = doc.selectXpath("//img[@data-object-fit='cover']").attr("src");
			sm.setImg(img);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
			lists.add(sm);
	
		
		
		
	}
	

	@Test
	public void saveList() throws Exception {
		
		mapper.writeValue(new File("D:\\Fiverr Work\\Jack Project 4\\Troutman Pepper\\row.json"), lists);
		System.out.println("Data saved");
		
		
	}
	
//	@BeforeClass
//	public void setup() {
//		
//		System.out.println("I setup ");
//		driver = new EdgeDriver();
//		WebDriverManager.edgedriver().setup();
//		
////		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tajnu\\eclipse-workspace\\JackProject\\files\\devtools\\chromedriver.exe");
////		opt = new ChromeOptions();
////		opt.setExperimentalOption("debuggerAddress", "localhost:8050");
////		
////		driver = new ChromeDriver(opt);
//////		driver.get(appUrl);
//		driver.manage().window().maximize();
//		
//	}
	
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
