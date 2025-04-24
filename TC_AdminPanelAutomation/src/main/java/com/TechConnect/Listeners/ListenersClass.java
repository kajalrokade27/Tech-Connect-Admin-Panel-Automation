package com.TechConnect.Listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.TechConnect.Base.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersClass extends BaseClass implements ITestListener
{
	 private ExtentReports report;
	    private ExtentTest test;

	    @Override
	    public void onStart(ITestContext context)
	    {
	        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	        String reportName = "TechConnect_Report_" + timestamp + ".html";

	        ExtentSparkReporter spark = new ExtentSparkReporter("./Reports/" + reportName);
	        spark.config().setDocumentTitle("Tech-Connect Testing Report");
	        spark.config().setReportName("Test Execution Summary");
	        spark.config().setTheme(Theme.DARK);
	        

	        report = new ExtentReports();
	        report.attachReporter(spark);
	        report.setSystemInfo("OS", System.getProperty("os.name"));
	        report.setSystemInfo("Browser", "Chrome Latest");
	        report.setSystemInfo("Test Engineer", "Kajal");
	        report.setSystemInfo("Execution Start Time", timestamp);

	        Reporter.log("🚀 Test Execution Started at: " + timestamp, true);
	    }

	    @Override
	    public void onTestStart(ITestResult result) 
	    {
	        String testName = result.getMethod().getMethodName();
	        test = report.createTest(testName)
	                .assignCategory(result.getMethod().getGroups()) // Assign category if test has groups
	                .assignAuthor("QA Team") // Assign test author
	                .info("🟢 Test Started: " + testName);

	        Reporter.log("🟢 Executing Test: " + testName, true);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) 
	    {
	        test.pass(MarkupHelper.createLabel("✅ Test Passed: " + result.getMethod().getMethodName(), ExtentColor.GREEN));
	        test.info("Execution Time: " + getExecutionTime(result) + " seconds");

	        Reporter.log("✅ Test Passed: " + result.getMethod().getMethodName(), true);
	    }

	    @Override
	    public void onTestFailure(ITestResult result)
	    {
	        String testName = result.getMethod().getMethodName();
	        String failureReason = result.getThrowable().getMessage();

	        test.fail(MarkupHelper.createLabel("❌ Test Failed: " + testName, ExtentColor.RED));
	        test.fail("❗ Reason: " + failureReason);
	        test.fail(result.getThrowable()); // Capture stack trace in report

	        captureScreenshot(test, testName);

	        Reporter.log("❌ Test Failed: " + testName + " | Reason: " + failureReason, true);
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) 
	    {
	        test.skip(MarkupHelper.createLabel("⚠️ Test Skipped: " + result.getMethod().getMethodName(), ExtentColor.ORANGE));
	        test.skip("Reason: " + result.getThrowable().getMessage());

	        captureScreenshot(test, result.getMethod().getMethodName());

	        Reporter.log("⚠️ Test Skipped: " + result.getMethod().getMethodName(), true);
	    }

	    @Override
	    public void onFinish(ITestContext context) 
	    {
	        report.setSystemInfo("Execution End Time", new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
	        report.flush();
	        Reporter.log("🏁 Test Execution Completed!", true);
	    }

	    // Method to capture screenshot and attach it to the report
	    private void captureScreenshot(ExtentTest test, String testName) 
	    {
	        try 
	        {
	            TakesScreenshot ts = (TakesScreenshot) driver;
	            String screenshot = ts.getScreenshotAs(OutputType.BASE64);
	            test.addScreenCaptureFromBase64String(screenshot, "Screenshot for: " + testName);
	        } 
	        catch (Exception e) 
	        {
	            test.warning("⚠️ Screenshot capture failed: " + e.getMessage());
	        }
	    }

	    // Method to calculate test execution time
	    private String getExecutionTime(ITestResult result) 
	    {
	        long duration = (result.getEndMillis() - result.getStartMillis()) / 1000;
	        return String.valueOf(duration);
	    }
} 
