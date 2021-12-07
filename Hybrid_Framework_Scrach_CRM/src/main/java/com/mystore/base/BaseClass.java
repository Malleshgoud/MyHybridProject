package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.w3c.dom.DOMConfiguration;

import com.mystore.actiondriver.Action;
import com.mystore.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator; 

public class BaseClass {

	public static Properties prop;
//	public static WebDriver driver;
	
	// Declare ThreadLocal Driver
		public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
		
		public static WebDriver getDriver() {
			// Get Driver from threadLocalmap
			return driver.get();
		}

	
		@BeforeSuite(groups = {"Smoke", "Sanity", "Regression" })
	public void loadConfig()
	{
			ExtentManager.setExtent();
			DOMConfigurator.configure("log4j.xml");
		try {
			prop = new Properties();
			String proj_pathis=System.getProperty("user.dir");
			FileInputStream ip = new FileInputStream(proj_pathis+"\\Configuration\\config.properties");
			prop.load(ip);
			
			System.out.println("current proj directory.."+System.getProperty("user.dir"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void launchApp(String browserName) {
		// String browserName = prop.getProperty("browser");
		System.out.println("Brower Name .."+browserName);
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			// Set Browser to ThreadLocalMap
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver.set(new InternetExplorerDriver());
		}
		//Maximize the screen
		getDriver().manage().window().maximize();
		//Delete all the cookies
		getDriver().manage().deleteAllCookies();
		
		//Launching the URL
		System.out.println("url is..."+prop.getProperty("url"));
		getDriver().get(prop.getProperty("url"));
		Action.implicitWait(getDriver(), 10);
	}
	
	@AfterSuite(groups = { "Smoke", "Regression","Sanity" })
	public void afterSuite() {
		ExtentManager.endReport();
	}
}

