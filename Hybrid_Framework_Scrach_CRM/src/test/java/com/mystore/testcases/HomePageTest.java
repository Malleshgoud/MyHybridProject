/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;

/**
 * @author Malli
 *
 */
public class HomePageTest extends BaseClass {

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
	
	
	@Test(priority=1,groups = "Smoke")
	
	public void wishListTest() throws Throwable
	{
		indexPage=new IndexPage();
		loginPage=indexPage.clickOnSignin();
		Action.implicitWait(getDriver(),10);
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		boolean actual_results=homePage.validateMyWishList();
		Assert.assertTrue(actual_results);
		
		
	}
	@Test(priority=2,groups = "Smoke")
	public void orderhistory_orderDetails() throws Throwable
	{
		indexPage=new IndexPage();
		loginPage=indexPage.clickOnSignin();
		Action.implicitWait(getDriver(),10);
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	
		boolean actual_orderhistory=homePage.validateOrderHistory();
		Assert.assertTrue(actual_orderhistory);
	}
}
