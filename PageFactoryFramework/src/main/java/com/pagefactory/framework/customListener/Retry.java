package com.pagefactory.framework.customListener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Retry implements IRetryAnalyzer {

	public static final Logger log = Logger.getLogger(Retry.class.getName());
	
	private int retrycount = 0;
	private int maxretrycount = 2;

	//Logic for retrying the test with a particular result status.
	public boolean retry(ITestResult result) {
		if (retrycount < maxretrycount) {
			log("Retrying test " + result.getName() + " with status "
					+ getResultStatusName(result.getStatus()) + " for the " + (retrycount + 1) + " time(s).");
			retrycount++;
			return true;
		}
		return false;
	}

	private String getResultStatusName(int status) {
		String resultName=null;
		if (status==1)
			resultName="SUCCESS";
		if (status==2)
			resultName="FAILURE";
		if (status==3)
			resultName="SKIP";
		return resultName;
	}
	
	public void log(String data){
		log.info(data);
		Reporter.log(data);
	}
}
