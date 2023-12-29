package allureReport;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureListner implements ITestListener
{

	private static String getTestMethodName(ITestResult iTestResult)
	{
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	@Attachment
	public byte[] saveFailureScreenshot(WebDriver driver)
	{
		return((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value= "{0}",type="text/plain")
	public static String saveTextLog(String message) {
		
		return message;
	
}
	@Override
	public void onStart(ITestContext iTestcontext) 
	{
	   System.out.println("I am in onStart method"+iTestcontext.getName());
	   
	   iTestcontext.setAttribute("WebDriver", BaseClass.getDriver());
	  }
	
	@Override
	public void onFinish(ITestContext iTestcontext) {

         System.out.println("I am in onStart method"+iTestcontext.getName());
		
	  }
	
	@Override
	public void onTestStart(ITestResult iTestResult) {

		System.out.println("I am in OnTestStart method"+getTestMethodName(iTestResult)+" start");
	}
	
	@Override
	public void onTestSuccess(ITestResult iTestResult)
	{
	
		System.out.println("I am in OnTestSuccess method"+getTestMethodName(iTestResult)+" Success");
	}
	
	@Override
	public void onTestFailure(ITestResult iTestResult)
	{
	
		System.out.println("I am in OnTestfailure method"+getTestMethodName(iTestResult)+" Failed");
	  
		Object testClass=iTestResult.getInstance();
	   WebDriver driver=BaseClass.getDriver();
	   //Allure screenshot and saveTestLog
	   
	   if(driver instanceof WebDriver) {
		   
		   System.out.println("Screenshot captured for test case: "+getTestMethodName(iTestResult));
		   saveFailureScreenshot(driver);
	   }
	     saveTextLog(getTestMethodName(iTestResult)+"failed and screenshot taken!");
	}
	
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		
		System.out.println("I am in OnTestSkipped method"+getTestMethodName(iTestResult)+" Skipped");
	}
	
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is defined success ratio "+getTestMethodName(iTestResult));
	}
}