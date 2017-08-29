package com.pagefactory.framework.APHomePage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pagefactory.framework.testBase.APTestBase;
import com.pagefactory.framework.uiActions.APHomePage;

public class APTC001_VerifyHomePageLinks extends APTestBase {

	public static final Logger log = Logger.getLogger(APTC001_VerifyHomePageLinks.class.getName());
	APHomePage aphomepage;
	
	@BeforeTest
	public void setup() throws IOException{
		init();
	}
	
	@Test
	public void clickOnWomenTshirts() {
		aphomepage=new APHomePage(this);
		aphomepage.hoverMouseoverMenu("Women");
		aphomepage.clickonWomenTopsSubMenu("Blouses");
	}
}
