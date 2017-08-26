package com.pagefactory.framework.homepage;

import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pagefactory.framework.testBase.TestBase;
import com.pagefactory.framework.uiActions.CartPage;
import com.pagefactory.framework.uiActions.CreateAccount;
import com.pagefactory.framework.uiActions.HomePage;

public class TC004_VerifyExternalLink extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC004_VerifyExternalLink.class.getName());
	
	HomePage homepage;
	CreateAccount createAccount;
	CartPage cartPage;
	
	@BeforeClass
	public void setup() throws IOException{
		init();
	}

	@Test
	public void verifyExternalWebsiteLink(){
		log("------------------- Starting verifyExternalWebsiteLink Test -------------------");
		createAccount=new CreateAccount(driver);
		createAccount.gotoNavigation("mens");
		createAccount.chickonMensProducts("Valentino");
		cartPage = new CartPage(driver);
		cartPage.clickOnFooterLink();
		//This code will get all windows ID and switch to the child window to verify the website link
		Iterator<String> itr = getAllWindows();
		
		String parentWindow = itr.next();
		String childWindow = itr.next();
		
		driver.switchTo().window(childWindow);
		boolean status=cartPage.verifyExternalLink();
		driver.switchTo().window(parentWindow);
		Assert.assertEquals(status, true);
		log("------------------- Ending verifyExternalWebsiteLink Test -------------------");
	}
	
}
