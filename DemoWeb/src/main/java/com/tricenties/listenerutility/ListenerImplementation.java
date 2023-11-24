package com.tricenties.listenerutility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.tricenties.basetest.BaseClass;

public class ListenerImplementation extends BaseClass implements ITestListener{

	@Override
	public void onTestFailure(ITestResult result) {
		TakesScreenshot ts=(TakesScreenshot) driver;
		test.addScreenCaptureFromBase64String(ts.getScreenshotAs(OutputType.BASE64),"error image");
		test.log(Status.FAIL, result.getName()+" is failed");
	}
	
}
