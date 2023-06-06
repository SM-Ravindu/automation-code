package com.nimi.ui.report;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.nimi.Browser;
import com.nimi.api.utility.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

public class TestListener implements ITestListener {

    protected static Log logger = LogFactory.getLog(ExtentManager.class);
    Date date = new Date();
    private String dateTimeFormat = "ddMMyyyymmss";

    public void onStart(ITestContext context) {
        logger.info("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        logger.info(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        logger.info(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        logger.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        logger.info((result.getMethod().getMethodName() + " failed!"));

        String targetLocation = null;

        String timeStamp = DateUtil.getDateAndTime(date, dateTimeFormat);
        String testMethodName = result.getName().trim();
        String screenShotName = testMethodName + timeStamp + ".png";
        String fileSeperator = System.getProperty("file.separator");
        String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator + "screenshots";
        logger.info("Screen shots reports path - " + reportsPath);
        try {
            File file = new File(reportsPath);
            FileHandler.delete(file);

            if (!file.exists()) {
                if (file.mkdirs()) {
                    logger.info("Directory: " + file.getAbsolutePath() + " is created!");
                } else {
                    logger.info("Failed to create directory: " + file.getAbsolutePath());
                }
            }

            File screenshotFile = Browser.captureScreenshot().getScreenshotAs(OutputType.FILE);
            targetLocation = reportsPath + fileSeperator + screenShotName;

            File targetFile = new File(targetLocation);
            logger.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
            logger.info("Target File location - " + targetFile.getAbsolutePath());
            FileHandler.copy(screenshotFile, targetFile);

        } catch (FileNotFoundException e) {
            logger.info("File not found exception occurred while taking screenshot " + e.getMessage());
        } catch (Exception e) {
            logger.info("An exception occurred while taking screenshot " + e.getCause());
        }

        ExtentTestManager.getTest().fail("Test Failed. ScreenshotName: " + screenShotName );
        ExtentTestManager.getTest().log(Status.FAIL, "Screenshot: " + MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
    }
}
