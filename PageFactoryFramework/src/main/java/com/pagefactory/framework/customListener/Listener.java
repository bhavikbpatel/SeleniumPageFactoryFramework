package com.pagefactory.framework.customListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.pagefactory.framework.testBase.TestBase;

public class Listener extends TestBase implements ITestListener {
	/*WebDriver driver;
	public Listener(WebDriver driver){
		this.driver=driver;
	}*/
	

	public void onTestStart(ITestResult result) {
				Reporter.log("Test Started: "+result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String methodName = result.getName();

		if (result.isSuccess())
			Reporter.log("Test Successful: "+result.getMethod().getMethodName());
			try {
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
						+ "/src/main/java/com/pagefactory/framework/success_screencapture";
				File destFile = new File((String) reportDirectory + "Success_ScreenCaptures" + "_" + methodName + "_"
						+ formater.format(calendar.getTime()) + ".png");
				FileUtils.copyFile(srcFile, destFile);
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
						+ "' height='100' width='100'/> </a");
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

	public void onTestFailure(ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String methodName = result.getName();

		if (!result.isSuccess())
			try {
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
						+ "/src/main/java/com/pagefactory/framework/failure_screencaptures";
				File destFile = new File((String) reportDirectory + "Failure_ScreenCaptures" + "_" + methodName + "_"
						+ formater.format(calendar.getTime()) + ".png");
				FileUtils.copyFile(srcFile, destFile);
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
						+ "' height='100' width='100'/> </a");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}

}
