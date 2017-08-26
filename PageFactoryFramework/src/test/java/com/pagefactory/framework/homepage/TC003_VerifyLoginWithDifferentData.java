package com.pagefactory.framework.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pagefactory.framework.testBase.TestBase;
import com.pagefactory.framework.uiActions.CreateAccount;
import com.pagefactory.framework.uiActions.HomePage;

public class TC003_VerifyLoginWithDifferentData extends TestBase {

	public static final Logger log = Logger.getLogger(TC003_VerifyLoginWithDifferentData.class.getName());
	// WebDriver driver;
	HomePage homepage;
	CreateAccount createaccount;

	@BeforeClass
	public void setup() throws IOException {
		init();
	}

	@DataProvider(name = "createAccountData")
	public String[][] getTestData() {
		String[][] testRecords = getData("CreateAccount", "TestData.xlsx");
		return testRecords;
	}

	@Test(dataProvider = "createAccountData")
	public void createAccountDifferentRecordsTest(String strName, String stremail, String strpass, String runmode) {
		if (runmode.equalsIgnoreCase("n")) {
			throw new SkipException("This record is marked this as no Run ");
		}
		homepage = new HomePage(driver);
		homepage.clickSignInbutton();
		createaccount = new CreateAccount(driver);
		log("------------------- Starting createAccountDifferentRecordsTest Test -------------------");
		homepage.clickSignInbutton();
		String strexpectedPhoneNumber = "+2 95 01 88 821";

		createaccount.createLogin(strName, stremail, strpass);
		String actualPhoneNumber = createaccount.getPhoneNumber();

		getScreenCapture("createAccountDifferentRecordsTest" + strName);
		Assert.assertEquals(actualPhoneNumber, strexpectedPhoneNumber);

		log("------------------- Ending createAccountDifferentRecordsTest Test -------------------");

	}

	// @AfterClass
	// public void endTest() {
	// driver.quit();
	// }

}
