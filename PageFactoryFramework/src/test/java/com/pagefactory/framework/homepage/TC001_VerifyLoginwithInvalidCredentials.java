package com.pagefactory.framework.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pagefactory.framework.testBase.TestBase;
import com.pagefactory.framework.uiActions.HomePage;

public class TC001_VerifyLoginwithInvalidCredentials extends TestBase {

	HomePage homepage;

	public static final Logger log= Logger.getLogger(TC001_VerifyLoginwithInvalidCredentials.class.getName());
	
	@BeforeTest
	public void setup() throws IOException {
		// System.out.println(System.getProperty("user.dir")+"/drivers/geckodriver.exe");
		// System.setProperty("webdriver.gecko.driver",
		// System.getProperty("user.dir") + "/drivers/geckodriver.exe");
		//
		//
		// DesiredCapabilities caps = DesiredCapabilities.firefox();
		// caps.setCapability("acceptInsecureCerts", true);
		// driver = new FirefoxDriver(caps);
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		init();
		
		}

	@Test
	public void verifyLoginwithInvalidCredentials(){
		log("------------------- Starting verifyLoginwithInvalidCredentials Test -------------------");
		homepage = new HomePage(driver);
		homepage.loginToApplication("test@gmail.com", "password");
		Assert.assertEquals(homepage.getInvalidLoginText(),"Authentication failed.");
		log("------------------- Ending verifyLoginwithInvalidCredentials Test -------------------");
	}

//	@AfterClass
//	public void endTest() {
//		driver.quit();
//	}
}
