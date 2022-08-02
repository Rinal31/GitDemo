package Academy.E2EProject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNg;
import resources.base;


public class Listeners extends base implements ITestListener{
	ExtentReports extent=ExtentReporterNg.getReoprterObject();
	ExtentTest test;
	
	//to overcome the issue of overriding the result of test object in parallel test runs make it thread safe
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		// this will execute test start. this code is to create index.html customized report
		
		 test=extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS, "Test passed"); // this can be used for only sequential execution
		extentTest.get().log(Status.PASS, "Test passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		extentTest.get().fail(result.getThrowable());
		//give the testcase name to take screenshot
		String testMethodName=result.getMethod().getMethodName();
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		 try {
			getScreenShotPath(testMethodName, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//add screenshot to the index.html extent report
		 try {
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver), testMethodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
			}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	

}
