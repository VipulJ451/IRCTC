package com.selenium.p1;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Testlisteners implements ITestListener{

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("Shit! Test Failed");
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy@MM@dd_HH-mm-ss");
		  Date date = new Date();
		  String format= dateFormat.format(date);
		 File f = ((TakesScreenshot)Searchpage.driver).getScreenshotAs(OutputType.FILE);
		  try {
		   org.apache.commons.io.FileUtils.copyFile(f, new File("E:\\abc_"+format+ ".png"));
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		System.out.println("shit"+"failed");
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("wow"+"passed");
	}

}
