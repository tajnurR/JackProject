package com.jack.task;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jack.utility.ExcelUtlity;



public class Cravath {
	ExcelUtlity utitle = new ExcelUtlity();
	
//	WebDriver driver;
//	ChromeOptions opt;
	
	String filename = "D:\\Fiverr Work\\Jack Project\\debevoise 2\\img.xlsx";
	String sheetName = "Sheet1";
	
	String fileOut = "//files//excel//output.xlsx";
	String sheetOut = "Sheet1";
	
//	cd C:\Program Files\Google\Chrome\Application
//	chrome.exe --remote-debugging-port=8050 --user-data-dir=C:\Users\tajnu\eclipse-workspace\JackProject\files\chrome
	
	@Test
	public void name() {
		System.out.println("Run");
	}
	
	@Test(dataProvider = "userdata")
	public void run(String id, String imgLink) throws IOException {

//		Image Dwonload save
//		URL imgUrl = new URL(imgLink);
//		InputStream is = imgUrl.openStream();
//		OutputStream fos = new FileOutputStream("D:\\Fiverr Work\\Jack Project\\goodwinlaw\\img\\"+id+".jpg");
//		 int ch;
//         while ((ch = is.read()) != -1) { 
//                fos.write(ch);
//               
//         }
//         System.out.println(id+" Save Done");
//		is.close();
//        fos.close();
		
//		Rename 
		String rootPath = "D:\\Fiverr Work\\Jack Project\\debevoise 2\\img\\"+imgLink;
		File f = new File(rootPath);
		String getEx = FilenameUtils.getExtension(rootPath);
		String storepaath = "D:\\Fiverr Work\\Jack Project\\debevoise 2\\rimg\\"+id+"."+getEx;
		File r = new File(storepaath);
		
		if (f.exists()) {
//			System.out.println(rootPath);
			f.renameTo(r);
			System.out.println(storepaath+"    Saved");
		}else {
			System.out.println("false");
		}
		
		
	}
	
//	@BeforeClass
//	public void setup() {
//		
//		System.out.println("I setup ");
//		
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tajnu\\eclipse-workspace\\JackProject\\files\\devtools\\chromedriver.exe");
//		opt = new ChromeOptions();
//		opt.setExperimentalOption("debuggerAddress", "localhost:8050");
//		
//		driver = new ChromeDriver(opt);
////		driver.get(appUrl);
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
