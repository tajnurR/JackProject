package com.jack.LinkScrap;

import java.io.IOException;
import java.time.Duration;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Test {
	
	@org.testng.annotations.Test
	public void name() throws IOException {
		
		String[] link = {
				"https://www.jenner.com/en/people/christopher-j-abbott"
		};
		
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		for (int i = 0; i < link.length; i++) {
			driver.get(link[i]);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(@class,'type__bioDisplay')]")));
			System.out.println(driver.getTitle());
			String name = driver.findElement(By.xpath("//h1[contains(@class,'type__bioDisplay')]")).getText();
			System.out.println(name);
		}
		
		
	}
}
