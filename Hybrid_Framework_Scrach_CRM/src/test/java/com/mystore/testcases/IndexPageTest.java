/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.utility.Log;

/**
 * @author Malli
 *
 */
public class IndexPageTest extends BaseClass {

	 IndexPage indexpage=new IndexPage();
	
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
	@Test(groups = "Smoke")
	
	public void verifyLogo() {
		Log.startTestCase("verifyLogo");
		Log.info("verifying Logo ");
		boolean result=indexpage.validateLogo();
		Assert.assertTrue(result);
		Log.endTestCase("verifyLogo");
	}
	
@Test(groups = "Smoke")
	
	public void verifiTitle() {
	Log.startTestCase("verifiTitle");
		String actTitle=indexpage.getMyStoreTitle();
		System.out.println("title..."+actTitle);
		Assert.assertEquals(actTitle,"My Store");
		Log.endTestCase("verifiTitle");
	}
	
	
}
