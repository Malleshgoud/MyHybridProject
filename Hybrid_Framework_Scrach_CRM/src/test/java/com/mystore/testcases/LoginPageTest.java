package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

public class LoginPageTest extends BaseClass
{

	IndexPage indexPage;
	LoginPage loginPage;
	HomePage homePage;
	
@Parameters("browser")
@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser)
	{
		launchApp(browser);
	}
	

	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	
	public void tearDown()
	{
		getDriver().quit();
	}
	
	@Test(groups = {"Smoke","Sanity"},dataProvider="credentials",dataProviderClass=DataProviders.class)
	
	public void logintest(String uname,String upwd)
	{
		Log.startTestCase("logintest");
		indexPage=new IndexPage();
		Log.info("User is going to click on SignIn button");
		loginPage=indexPage.clickOnSignin();
		Log.info("User is clicked on SignIn button");
		Action.implicitWait(getDriver(),10);
		//homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage=loginPage.login(uname,upwd);
		String actualurl=homePage.getCurrUrl();
		String expectedUrl="http://automationpractice.com/index.php?controller=my-account";
		Log.info("verifying user is able to login");
		Assert.assertEquals(actualurl, expectedUrl);
		Log.info("Login is Successfull... ");
		Log.endTestCase("logintest");
	}
	
	
	
}
