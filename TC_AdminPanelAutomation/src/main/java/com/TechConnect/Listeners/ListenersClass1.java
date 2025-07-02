package com.TechConnect.Listeners;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.TechConnect.Base.AdminBaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersClass1 extends AdminBaseClass implements ITestListener {
    
    private ExtentReports report;
    private ExtentTest test;
    private Map<String, Long> testStartTimes = new ConcurrentHashMap<>();
    private int totalTests, passedTests, failedTests, skippedTests;
    
    @Override
    public void onStart(ITestContext context) {
        initializeReport(context);
        setupSystemInfo(context);
        logSuiteStart(context);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        testStartTimes.put(testName, System.currentTimeMillis());
        
        test = report.createTest(testName)
                .assignCategory(result.getMethod().getGroups())
                .assignAuthor("QA Team");
        
        logTestDetails(result);
        Reporter.log("🟢 [Thread-" + Thread.currentThread().getId() + "] Starting: " + testName, true);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        long executionTime = getExecutionTime(testName);
        passedTests++;
        
        test.pass(MarkupHelper.createLabel("✅ PASSED", ExtentColor.GREEN));
        logSuccessMetrics(testName, executionTime);
        
        Reporter.log("✅ PASSED [" + executionTime + "ms]: " + testName, true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        long executionTime = getExecutionTime(testName);
        failedTests++;
        
        test.fail(MarkupHelper.createLabel("❌ FAILED", ExtentColor.RED));
        logFailureDetails(result, executionTime);
        captureFailureEvidence(testName);
        
        Reporter.log("❌ FAILED [" + executionTime + "ms]: " + testName, true);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        skippedTests++;
        
        test.skip(MarkupHelper.createLabel("⚠️ SKIPPED", ExtentColor.ORANGE));
        test.skip("Reason: " + (result.getThrowable() != null ? result.getThrowable().getMessage() : "Dependency failed"));
        
        captureScreenshot(testName);
        Reporter.log("⚠️ SKIPPED: " + testName, true);
    }

    @Override
    public void onFinish(ITestContext context) {
        generateExecutionSummary(context);
        report.flush();
        logFinalResults();
    }

    private void initializeReport(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        totalTests = context.getAllTestMethods().length;
        
        ExtentSparkReporter spark = new ExtentSparkReporter("./Reports/TechConnect_Report_" + timestamp + ".html");
        configureReportTheme(spark);
        
        report = new ExtentReports();
        report.attachReporter(spark);
    }
    
    private void configureReportTheme(ExtentSparkReporter spark) {
        spark.config().setDocumentTitle("🚀 TechConnect Test Execution Report");
        spark.config().setReportName("📊 Automated Testing Dashboard");
        spark.config().setTheme(Theme.DARK);
        spark.config().setEncoding("utf-8");
    }

    private void setupSystemInfo(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        
        report.setSystemInfo("🖥️ Operating System", System.getProperty("os.name") + " " + System.getProperty("os.version"));
        report.setSystemInfo("☕ Java Version", System.getProperty("java.version"));
        report.setSystemInfo("👤 User", System.getProperty("user.name"));
        report.setSystemInfo("🌐 Browser", "Chrome Latest");
        report.setSystemInfo("👨‍💻 Test Engineer", "Kajal");
        
        report.setSystemInfo("📋 Suite Name", context.getSuite().getName());
        report.setSystemInfo("🧪 Total Tests", String.valueOf(totalTests));
        report.setSystemInfo("⚡ Parallel Mode", context.getSuite().getParallel().toString());
        
        report.setSystemInfo("🕐 Start Time", timestamp);
        report.setSystemInfo("🏗️ Build Version", System.getProperty("build.version", "1.0.0"));
        
        String environment = context.getCurrentXmlTest().getParameter("environment");
        report.setSystemInfo("🌍 Environment", environment != null ? environment : "Not specified");
    }

    private void logTestDetails(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        
        test.info("📝 <b>Test Information</b>");
        test.info("🔹 Test Name: " + testName);
        test.info("🔹 Test Class: " + result.getTestClass().getName());
        test.info("🔹 Priority: " + result.getMethod().getPriority());
        test.info("🔹 Thread ID: " + Thread.currentThread().getId());
        test.info("🔹 Description: " + (result.getMethod().getDescription() != null ? result.getMethod().getDescription() : "No description"));
        test.info("🔹 Groups: " + String.join(", ", result.getMethod().getGroups()));
    }
    
    private void logSuccessMetrics(String testName, long executionTime) {
        int completedTests = passedTests + failedTests + skippedTests;
        double currentPassRate = completedTests > 0 ? (double) passedTests / completedTests * 100 : 0;
        
        test.info("📊 <b>Success Metrics</b>");
        test.info("⏱️ Execution Time: " + executionTime + "ms");
        test.info("📈 Progress: " + completedTests + "/" + totalTests);
        test.info("🎯 Current Pass Rate: " + String.format("%.1f%%", currentPassRate));
        test.info("✅ Status: PASSED");
    }
    
    private void logFailureDetails(ITestResult result, long executionTime) {
        test.info("💥 <b>Failure Analysis</b>");
        test.fail("🔴 Error Message: " + result.getThrowable().getMessage());
        test.fail("⏱️ Failed After: " + executionTime + "ms");
        test.fail("🏷️ Exception Type: " + result.getThrowable().getClass().getSimpleName());
        test.fail("📍 Location: " + result.getTestClass().getName() + "." + result.getMethod().getMethodName());
        
        addBrowserContext();
    }

    private void captureFailureEvidence(String testName) {
        captureScreenshot(testName);
        captureBrowserLogs();
    }
    
    private void captureScreenshot(String testName) {
        try {
            String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test.addScreenCaptureFromBase64String(screenshot, "📸 Screenshot: " + testName);
        } catch (Exception e) {
            test.warning("📷 Screenshot capture failed: " + e.getMessage());
        }
    }
    
    private void captureBrowserLogs() {
        try {
            if (driver != null) {
                test.info("🌐 Current URL: " + driver.getCurrentUrl());
                test.info("📄 Page Title: " + driver.getTitle());
                test.info("🔧 Browser: " + driver.getClass().getSimpleName());
            }
        } catch (Exception e) {
            test.warning("Browser info unavailable: " + e.getMessage());
        }
    }
    
    private void addBrowserContext() {
        try {
            if (driver != null) {
                test.info("🌐 <b>Browser Context</b>");
                test.info("🔗 URL: " + driver.getCurrentUrl());
                test.info("📄 Title: " + driver.getTitle());
                test.info("🖥️ Window Size: " + driver.manage().window().getSize());
            }
        } catch (Exception e) {
            test.warning("Browser context unavailable: " + e.getMessage());
        }
    }

    private void generateExecutionSummary(ITestContext context) {
        String endTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        long totalDuration = (context.getEndDate().getTime() - context.getStartDate().getTime()) / 1000;
        double passRate = totalTests > 0 ? (double) passedTests / totalTests * 100 : 0;
        
        report.setSystemInfo("🕐 End Time", endTime);
        report.setSystemInfo("⏱️ Total Duration", formatDuration(totalDuration));
        report.setSystemInfo("✅ Tests Passed", passedTests + " (" + String.format("%.1f%%", passRate) + ")");
        report.setSystemInfo("❌ Tests Failed", String.valueOf(failedTests));
        report.setSystemInfo("⚠️ Tests Skipped", String.valueOf(skippedTests));
        report.setSystemInfo("📊 Pass Rate", String.format("%.2f%%", passRate));
        
        String qualityGate = getQualityGate(passRate);
        report.setSystemInfo("🏆 Quality Gate", qualityGate);
        
        double avgTestTime = totalTests > 0 ? (double) totalDuration / totalTests : 0;
        report.setSystemInfo("⚡ Avg Test Time", String.format("%.2fs", avgTestTime));
        report.setSystemInfo("🚀 Tests/Minute", String.format("%.1f", totalTests > 0 ? (double) totalTests / (totalDuration / 60.0) : 0));
    }
    
    private String getQualityGate(double passRate) {
        if (passRate >= 95) return "🏆 EXCELLENT";
        if (passRate >= 85) return "✅ GOOD";
        if (passRate >= 70) return "⚠️ ACCEPTABLE";
        return "❌ NEEDS IMPROVEMENT";
    }
    
    private String formatDuration(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        
        if (hours > 0) return String.format("%dh %dm %ds", hours, minutes, secs);
        if (minutes > 0) return String.format("%dm %ds", minutes, secs);
        return String.format("%ds", secs);
    }

    private void logSuiteStart(ITestContext context) {
        Reporter.log("🚀 Test Suite Started: " + context.getSuite().getName(), true);
    }
    
    private void logFinalResults() {
        double passRate = totalTests > 0 ? (double) passedTests / totalTests * 100 : 0;
        String qualityGate = getQualityGate(passRate);
        
        Reporter.log("🏁 EXECUTION COMPLETED", true);
        Reporter.log("📊 Results: " + totalTests + " Total | ✅ " + passedTests + " Passed | ❌ " + failedTests + " Failed | ⚠️ " + skippedTests + " Skipped", true);
        Reporter.log("📈 Pass Rate: " + String.format("%.2f%%", passRate) + " | Quality: " + qualityGate, true);
    }
    
    private long getExecutionTime(String testName) {
        Long startTime = testStartTimes.get(testName);
        return startTime != null ? System.currentTimeMillis() - startTime : 0;
    }
    
    private String getExecutionTime(ITestResult result) {
        return String.valueOf((result.getEndMillis() - result.getStartMillis()) / 1000);
    }
}
