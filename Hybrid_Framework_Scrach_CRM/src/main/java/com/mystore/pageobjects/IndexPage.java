package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class IndexPage extends BaseClass {
	
	
	
	@FindBy(xpath = "//a[@class='login']") 
	private WebElement signInBtn;
	
	
	@FindBy(xpath = "//img[@class='logo img-responsive']")
	private WebElement myStoreLogo;
	
	@FindBy(id="search_query_top")
	private WebElement searchProductBox;
	
	@FindBy(name="submit_search")
	private WebElement searchButton;
	
	public IndexPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public LoginPage clickOnSignin() {
		Action.click(getDriver(), signInBtn);
		return new LoginPage();
	}
	
	public boolean validateLogo() {
		return Action.isDisplayed(getDriver(), myStoreLogo);
	
	}
	
	public String getMyStoreTitle()
	{
		String myStoretitle=getDriver().getTitle();
		return myStoretitle; 
	}
	
	public SearchResultPage searchProduct(String productName)
	{
		Action.fluentWait(getDriver(), myStoreLogo, 10);
		Action.findElement(getDriver(), myStoreLogo);
		Action.type(searchProductBox, productName);
		Action.click(getDriver(),searchButton);
		return new SearchResultPage();
	}
	
	
	

}
