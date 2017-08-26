package com.pagefactory.framework.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.pagefactory.framework.customListener.Listener;
import com.pagefactory.framework.customListener.WebEventListener;
import com.pagefactory.framework.excelReader.ExcelReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public WebDriver dr;
	public EventFiringWebDriver driver;
	public WebEventListener eventListener;
	
	ExcelReader excel;
	Listener listener;
	Properties or = new Properties();

	public static ExtentReports extent;
	public static ExtentTest test;

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	//Reading all the properties in or from the config.properties
	public void loadconfigurations() throws IOException {
		File file = new File(
				System.getProperty("user.dir") + "/src/main/java/com/pagefactory/framework/config/config.properties");
		FileInputStream fis = new FileInputStream(file);
		or.load(fis);
	}

	//We have written this static method, so that it is loaded only once in the memory
	//the Extent report will be created only once, no matter wherever we do initialization
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/main/java/com/pagefactory/framework/report/"
				+ formater.format(calendar.getTime()) + ".html", false);
	}

	//This method needs to be called from all the Tests so that the browser is initialized with
	//the URL. Also the Log4J is initialized
	public void init() throws IOException {
		loadconfigurations();
		selectBrowser(or.getProperty("browser"));
		// listener = new Listener(driver);
		getURL(or.getProperty("url"));
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
	}


	//Select Browser is required for selecting the browser based on the argument passed.
	//This method can be extended by adding further if conditions
	public void selectBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			log("Creating the object of " + browser);
			dr = new ChromeDriver();

			driver = new EventFiringWebDriver(dr);
			eventListener = new WebEventListener();
			driver.register(eventListener);
		}
	}

	//The URL will be used to open in the browser of driver(WebDriver)
	public void getURL(String url) {
		log("Navigating to the URL:" + url);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	//Load all the data in the String[][] using the getDataFromSheet method of ExcelReader class
	public String[][] getData(String sheetname, String excelname) {
		String path = System.getProperty("user.dir") + "/src/main/java/com/pagefactory/framework/data/" + excelname;
		excel = new ExcelReader(path);
		String[][] data = excel.getDataFromSheet(sheetname, excelname);
		return data;

	}

	//Wait for the visibility of the element for a certain amount of time
	public void waitforElement(long timeoutseconds, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, timeoutseconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	//Method used for getting the screen capture with the name in a particular format
	public void getScreenCapture(String name) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "/src/main/java/com/pagefactory/framework/screenshot/";
			File destFile = new File(
					(String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(srcFile, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Method retrieves all the windows open for a driver in a iterator
	public Iterator<String> getAllWindows() {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		return itr;
	};

	//Logging method so that the same log is added in logger as well as syso
	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

	
	//Method to be used by After Method, for getting the status of the test execution
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is PASSING");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is SKIP reson is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is FAILED " + result.getThrowable());
			String screen = captureScreen("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
			// getScreenCapture(result.getName())
		}
	}

	//Captures the screen capture and returns the path of the created file
	public String captureScreen(String filename) {
		if (filename == "") {
			filename = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "/src/main/java/com/pagefactory/framework/screenshot/";
			destFile = new File(
					(String) reportDirectory + filename + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(srcFile, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();

	}

	@BeforeMethod
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		getResult(result);
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		closeBrowser();
	}

	private void closeBrowser() {
		driver.quit();
		log("Browser Closed");
		extent.endTest(test);
		extent.flush();
	}

}
