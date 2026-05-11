package com.demoblaze.listeners;

import com.demoblaze.drivers.APITest;
import com.demoblaze.drivers.UITest;
import com.demoblaze.drivers.WebDriverProvider;
import com.demoblaze.media.ScreenRecordManager;
import com.demoblaze.media.ScreenShotManager;
import com.demoblaze.utils.FileUtils;
import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.dataReader.PropertyReader;
import com.demoblaze.utils.report.AllureAttachmentManager;
import com.demoblaze.utils.report.AllureConstants;
import com.demoblaze.utils.report.AllureEnvironmentManager;
import com.demoblaze.utils.report.AllureReportGenerator;
import com.demoblaze.validations.Validation;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements IExecutionListener, IInvokedMethodListener, ITestListener,ISuiteListener {
    public void onStart(ISuite suite) {
        suite.getXmlSuite().setName("Demo Blaze Test Suite");
    }


    public void onExecutionStart() {
        LogsManager.Info("Test Execution started");
        cleanTestOutputDirectories();
        LogsManager.Info("Test output directories cleaned successfully");
        createTestOutputDirectories();
        LogsManager.Info("Test output directories created successfully");
        PropertyReader.LoadPropertiesFiles();
        LogsManager.Info("Properties files loaded successfully");
        AllureEnvironmentManager.setEnvironmentVariables();
        LogsManager.Info("Allure environment information set successfully");
    }


    public void onExecutionFinish() {
        AllureReportGenerator.copyHistory();
        AllureReportGenerator.generateReports(false);
        LogsManager.Info("Allure report generated successfully");
       AllureReportGenerator.generateReports(true);
        LogsManager.Info("Allure single file report generated successfully");
        AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
        LogsManager.Info("Allure report opened successfully");
        LogsManager.Info("Test Execution finished");
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod())   {
            if (testResult.getInstance() instanceof UITest){
                ScreenRecordManager.startRecording();
            }
            LogsManager.Info("Starting execution of test method: " + method.getTestMethod().getMethodName());
        }

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = null;
        if (method.isTestMethod()) {
            if (testResult.getInstance().getClass().isAnnotationPresent(UITest.class)) {
                ScreenRecordManager.stopRecording(testResult.getName());
                if (testResult.getInstance() instanceof WebDriverProvider provider)
                    driver = provider.getWebDriver(); //initialize driver from WebDriverProvider
                switch (testResult.getStatus()) {
                    case ITestResult.SUCCESS ->
                            ScreenShotManager.takeFullPageScreenshot(driver, "passed-" + testResult.getName());
                    case ITestResult.FAILURE ->
                            ScreenShotManager.takeFullPageScreenshot(driver, "failed-" + testResult.getName());
                    case ITestResult.SKIP ->
                            ScreenShotManager.takeFullPageScreenshot(driver, "skipped-" + testResult.getName());
                }
                AllureAttachmentManager.attachRecords(testResult.getName());
            }

            Validation.AssertAll(testResult);

            AllureAttachmentManager.attachLogs();

        }
    }

    public void onTestSuccess(ITestResult result) {
        LogsManager.Info("Test method " + result.getName() + " Passed");
    }


    public void onTestFailure(ITestResult result) {
        LogsManager.Info("Test method " + result.getName() + " Failed");

    }


    public void onTestSkipped(ITestResult result) {
    LogsManager.Info("Test method " + result.getName() + " Skipped");
    }

    //clean and create directory
    //clean (logs - screenshots - records - reports)
    public void cleanTestOutputDirectories(){
        FileUtils.cleanDirectory(AllureConstants.RESULTS_FOLDER.toFile());
        FileUtils.cleanDirectory(new File(ScreenShotManager.SCREENSHOTS_PATH));
        FileUtils.cleanDirectory(new File(ScreenRecordManager.RECORDINGS_PATH));
        FileUtils.cleanDirectory(new File("src/test/resources/downloads"));
        FileUtils.forceDeleteDirectory(new File(LogsManager.LOGS_PATH+"logs.log"));
    }
    public void createTestOutputDirectories(){

        FileUtils.createDirectory(ScreenShotManager.SCREENSHOTS_PATH);
        FileUtils.createDirectory(ScreenRecordManager.RECORDINGS_PATH);
        FileUtils.createDirectory("src/test/resources/downloads");

    }
}
