package com.pagefactory.framework.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pagefactory.framework.testBase.TestBase;

public class CreateAccount extends TestBase {

	public static final Logger log = Logger.getLogger(CreateAccount.class.getName());

	WebDriver driver;

	@FindBy(xpath = "//*[@id='form']/div/div/div[3]/div/form/input[1]")
	WebElement firstName;

	@FindBy(xpath = "//*[@id='form']/div/div/div[3]/div/form/input[2]")
	WebElement email;

	@FindBy(xpath = "//*[@id='form']/div/div/div[3]/div/form/input[3]")
	WebElement password;

	@FindBy(xpath = "//*[@id='form']/div/div/div[3]/div/form/button")
	WebElement signUpButton;

	@FindBy(xpath = "//*[@id='header']/div[1]/div/div/div[1]/div/ul/li[1]/a")
	WebElement phoneNumber;

	public CreateAccount(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void createLogin(String firstName, String email, String password) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
		log("Clicked on Sign In and object is:" + firstName.toString());
		this.email.clear();
		this.email.sendKeys(email);
		log("Clicked on Sign In and object is:" + email.toString());
		this.password.clear();
		this.password.sendKeys(password);
		log("Clicked on Sign In and object is:" + password.toString());
	}

	public String getPhoneNumber() {
		return (phoneNumber.getText());
	}

//Below three methods will explain the concept of decendant/child, siblings for getting the exact xpath
	
	public void gotoNavigation(String menuname) {

		driver.findElement(By
				.xpath("//*[@id='accordian']/descendant::div[@class='panel panel-default']/descendant::div[@class='panel-heading']/descendant::a[@href='#"
						+ menuname + "']"))
				.click();
		log("Clicked on " + menuname + "Navigation menu item");
	}

	public void clickonSportswearProduct(String product) {

		driver.findElement(By
				.xpath("//*[@id='accordian']/descendant::div[@class='panel panel-default']/descendant::div[@id='sportswear']/descendant::div[@class='panel-body']/descendant::ul/child::li/child::a[contains(text(),'"
						+ product + "')]")).click();
		log("Clicked on "+product+" of Sportswear");
	}
	
	public void chickonMensProducts(String product){
		driver.findElement(By
				.xpath("//*[@id='accordian']/descendant::div[@class='panel panel-default']/descendant::div[@id='mens']/descendant::div[@class='panel-body']/descendant::ul/child::li/child::a[contains(text(),'"
						+ product + "')]")).click();
		log("Clicked on "+product+" of Mens");
	}
}
