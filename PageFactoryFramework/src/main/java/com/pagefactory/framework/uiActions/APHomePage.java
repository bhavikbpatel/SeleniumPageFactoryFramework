package com.pagefactory.framework.uiActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.pagefactory.framework.testBase.APTestBase;

public class APHomePage {

	//WebDriver driver;
	APTestBase tBase;
	
	public APHomePage(APTestBase APtBase) {
		this.tBase = APtBase;
		
		//this.driver = APtBase.dr;
		PageFactory.initElements(this.tBase.dr, this);
	}

	public void hoverMouseoverMenu(String menuitem) {
		WebElement target = this.tBase.dr.findElement(
				By.xpath("//*[@id='block_top_menu']/child::ul/child::li/child::a[@title='" + menuitem + "']"));
		Actions action = new Actions(this.tBase.dr);
		action.moveToElement(target);
		action.perform();
	}

	public void clickonWomenTopsSubMenu(String submenuitem) {
		// .//*[@id='block_top_menu']/child::ul/child::li/child::a[@title='Women']/following-sibling::ul/child::li/child::ul/child::li/child::a[@title='T-shirts']
		WebElement target =this.tBase.dr.findElement(By
				.xpath("//*[@id='block_top_menu']/child::ul/child::li/child::ul/child::li/child::ul/child::li/child::a[@title='"
						+ submenuitem + "']"));
//		WebDriverWait wait = new WebDriverWait(driver, 5); 
//		wait.until(ExpectedConditions.elementToBeClickable(target));
		this.tBase.waitforClickable(target);
		target.click();
	}
}
