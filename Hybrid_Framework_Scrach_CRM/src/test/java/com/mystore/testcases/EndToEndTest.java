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
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.AddressPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.OrderConfirmationPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.OrderSummary;
import com.mystore.pageobjects.PaymentPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.pageobjects.ShippingPage;

/**
 * @author Malli
 *
 */
public class EndToEndTest extends BaseClass
{
	IndexPage indexPage;
	LoginPage loginPage;

	SearchResultPage searchResultPage;
	AddToCartPage addToCartPageTest;
	OrderPage orderPage;
	AddressPage addressPage;
	ShippingPage shippingPage;
	PaymentPage paymentpage;
	OrderSummary orderSummary;
	OrderConfirmationPage orderConfirmationPage;
	
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
	
	
	@Test(groups = "Regression")
	public void endToendTest() throws Throwable
	{
         indexPage=new IndexPage();
		
		searchResultPage=indexPage.searchProduct("t-shirt");
	
		
		addToCartPageTest=searchResultPage.clickOnProduct();
		
		addToCartPageTest.enterQuantity("2");
		addToCartPageTest.selectSize("M");
		addToCartPageTest.clickOnAddToCart();
		orderPage=addToCartPageTest.clickOnCheckOut();
		loginPage=orderPage.clickOnCheckOut();
				
				addressPage=loginPage.login1(prop.getProperty("username"), prop.getProperty("password"));
				shippingPage=addressPage.clickOnCheckOut();
				shippingPage.checkTheTerms();
				paymentpage=shippingPage.clickOnProceedToCheckOut();
				orderSummary=paymentpage.clickOnPaymentMethod();
				orderConfirmationPage=orderSummary.clickOnconfirmOrderBtn();
				String confirm_actul_message=orderConfirmationPage.validateConfirmMessage();
				String expected_message="Your order on My Store is complete.";
				Assert.assertEquals(confirm_actul_message, expected_message);
				
				
	}
	 

}
