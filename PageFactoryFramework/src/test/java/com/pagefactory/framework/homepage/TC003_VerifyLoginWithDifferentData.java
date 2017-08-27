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

	/*Dataprovider annotation parameter will help us execute the test with all the data in the 
	 * TestData.xlsx Excel sheet. If runmode = y, then only the data will be considered for execution 
	 * otherwise will be skipped*/
	//This is a basic test to test the dataprovider
	@Test(dataProvider = "createAccountData")
	public void createAccountDifferentRecordsTest(String strName, String stremail, String strpass, String runmode) {
		if (runmode.equalsIgnoreCase("n")) {
			//This execption that is raised will be displayed in Log as well as the Extent Reports
			throw new SkipException("This record is marked this as no Run ");
		}
		homepage = new HomePage(driver);
		homepage.clickSignInbutton();
		createaccount = new CreateAccount(driver);
		log("------------------- Starting createAccountDifferentRecordsTest Test -------------------");
		homepage.clickSignInbutton();
		//To fail this test change the below phone number
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
