package com.pagefactory.framework.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pagefactory.framework.testBase.TestBase;

public class HomePage extends TestBase {

	public static final Logger log= Logger.getLogger(HomePage.class.getName());
	
	WebDriver driver;

	@FindBy(xpath = "//*[@id='header']/div[2]/div/div/nav/div[1]/a")
	WebElement signIn;

	@FindBy(xpath="//*[@id='header']/div[2]/div/div/div[2]/div/ul/li[5]/a")
	WebElement localSignIn;
	
	@FindBy(xpath = "//*[@id='email']")
	WebElement email;

	@FindBy(xpath = "//*[@id='passwd']")
	WebElement password;

	@FindBy(id = "SubmitLogin")
	WebElement submitButton;

	@FindBy(xpath = "//*[@id='center_column']/div[1]/ol/li")
	WebElement authenticationFailed;

	public HomePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginToApplication(String emailaddress, String strpassword) {
		signIn.click();
		log("Clicked on Sign In and object is:" + signIn.toString());
		email.sendKeys(emailaddress);
		log("Entered email address " + emailaddress + " and object is:" + emailaddress.toString());
		password.sendKeys(strpassword);
		log("Entered email address " + strpassword + " and object is:" + password.toString());
		submitButton.click();
		log("Clicked on Submit button and object is:" + submitButton.toString());
	}

	public void clickSignInbutton(){
		localSignIn.click();
	}
	
	public String getInvalidLoginText(){
		log("Error message is:" + authenticationFailed.getText());
		return authenticationFailed.getText();
	}
}
