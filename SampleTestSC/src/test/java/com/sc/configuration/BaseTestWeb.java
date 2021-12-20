package com.sc.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseTestWeb {

	public WebDriver driver;
	@BeforeClass
	public void configbrowser() {
		
		System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\Webdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void closeBrowser(){
		driver.quit();
	}
}
