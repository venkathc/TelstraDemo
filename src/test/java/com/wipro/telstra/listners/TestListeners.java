package com.wipro.telstra.listners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.wipro.telstra.driver.DriverSetupPage;

/**
 * @author Venkatesh Kumar
 *
 */
public class TestListeners implements ITestListener{
	
	private WebDriver driver ;
	String currnetLocation = System.getProperty("user.dir");
	public String filePath = currnetLocation + "\\ScreenShots\\";

	
	public void onTestStart(ITestResult result) {
		System.out.println("Started Test Method--->"+ result.getInstanceName().toString()+ "-> "+result.getMethod().getMethodName() + " at:" + result.getStartMillis());
		
	}
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	public void onTestFailure(ITestResult result) {
		System.out.println("Error " + result.getName() + " test has failed");
		String methodName = result.getName().toString().trim();
		takeScreenShot(methodName);
		
		System.out.println(result.getMethod().getMethodName() + "test is Failed");
	}

	public void takeScreenShot(String methodName) {
		DriverSetupPage base = new DriverSetupPage();
	//	driver=base.getDriver();
		String fileName = new Date().getTime() + ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath + methodName + fileName));
			System.out.println("Placed screen shot in " + filePath );
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
		// TODO Auto-generated method stub
		
	}

	
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
