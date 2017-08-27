package com.pagefactory.framework.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.pagefactory.framework.testBase.TestBase;
import com.pagefactory.framework.uiActions.CreateAccount;
import com.pagefactory.framework.uiActions.HomePage;

public class TC002_VerifyCreateAccount extends TestBase {

	public static final Logger log = Logger.getLogger(TC002_VerifyCreateAccount.class.getName());
	
	HomePage homepage;
	CreateAccount createaccount;

	@BeforeClass
	public void setup() throws IOException {
		init();
	}

	@Test
	public void verifyCreateAccount() {
		try {
			homepage = new HomePage(driver);
			createaccount = new CreateAccount(driver);
			log("------------------- Starting verifyCreateAccount Test -------------------");
			homepage.clickSignInbutton();
			String strexpectedPhoneNumber = "+2 95 01 88 821";

			createaccount.createLogin("Man", "man@gmail.com", "password");
			String actualPhoneNumber = createaccount.getPhoneNumber();

			Assert.assertEquals(actualPhoneNumber, strexpectedPhoneNumber);
			log("------------------- Ending verifyCreateAccount Test -------------------");
		} catch (Exception e) {
			log("xxxxxxxxxxxxxxxxx verifyCreateAccount Test Failed xxxxxxxxxxxxxxxxxxxxxx");
		}
	}

//	@AfterClass
//	public void endTest() {
//		driver.quit();
//	}

}
