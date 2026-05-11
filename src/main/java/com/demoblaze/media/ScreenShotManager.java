package com.demoblaze.media;

import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.TimeManager;

import com.demoblaze.utils.report.AllureAttachmentManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;


public class ScreenShotManager {
    public static final String SCREENSHOTS_PATH = "test-output/screenshots/";

    //take full page screenshot
    public static void takeFullPageScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Capture screenshot using TakesScreenshot
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save screenshot to a file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + TimeManager.GetCurrentTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);


            AllureAttachmentManager.attachScreenshot(screenshotName,screenshotFile.getAbsolutePath());

            LogsManager.Info("Capturing Screenshot Succeeded");
        } catch (Exception e) {
            LogsManager.Error("Failed to Capture Screenshot " + e.getMessage());
        }
    }

    //take screenshot of a specific element
    public static void takeElementScreenshot(WebDriver driver, By elementSelector) {
        try {
            // Capture screenshot using TakesScreenshot
            String ariaName = driver.findElement(elementSelector).getAccessibleName();
            File screenshotSrc = driver.findElement(elementSelector).getScreenshotAs(OutputType.FILE);

            // Save screenshot to a file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + ariaName + "-" + TimeManager.GetCurrentTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);
            LogsManager.Info("Capturing Screenshot Succeeded");
        } catch (Exception e) {
            LogsManager.Error("Failed to Capture Element Screenshot" , e.getMessage());
        }
    }
}
