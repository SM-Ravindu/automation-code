package com.nimi.ui.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.nimi.ui.common.CommonConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportFileName = CommonConstants.REPORT_FILE_NAME;
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") +fileSeperator+ CommonConstants.REPORT_PATH;
    private static String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;
    protected static Log logger = LogFactory.getLog(ExtentManager.class);


    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static ExtentReports createInstance() {
        String fileName = getReportPath(reportFilepath);

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setChartVisibleOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //Set environment details
        extent.setSystemInfo("OS", CommonConstants.OS);
        extent.setSystemInfo("AUT", CommonConstants.ENV);

        return extent;
    }

    //Create the report path
    private static String getReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                logger.info("Directory: " + path + " is created!");
                return reportFileLocation;
            } else {
                logger.info("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            logger.info("Directory already exists: " + path);
        }
        return reportFileLocation;
    }
}
