package com.pagefactory.framework.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pagefactory.framework.testBase.TestBase;

public class CartPage extends TestBase {
	
	public static final Logger log = Logger.getLogger(CartPage.class.getName());
	
	WebDriver driver;
	
	@FindBy(xpath="//*[@id='footer']/div[3]/div/div/p[2]/span/a")
	WebElement webSiteLink;
	
	@FindBy(xpath="//*[contains(text(),'We’ve launched a new corporate/business WordPress theme')]")
	WebElement webSiteMessage;
	
	public CartPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	public void clickOnFooterLink(){
		webSiteLink.click();
		log("Website Link clicked and object is :"+webSiteLink.toString());
	}
	
	public boolean verifyExternalLink(){
		try {
			webSiteMessage.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
