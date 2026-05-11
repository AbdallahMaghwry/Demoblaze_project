package com.demoblaze.validations;

import com.demoblaze.utils.Actions.ElementActions;
import com.demoblaze.utils.FileUtils;
import com.demoblaze.utils.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class  BaseAssertion {

    protected  WebDriver driver;
    protected  WaitManager waitManager;
    protected  ElementActions elementActions;

    protected BaseAssertion(){

    }
    protected BaseAssertion(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        this.elementActions = new ElementActions(driver);

    }

    protected abstract void AssertEquals(String actual, String expected, String message);

    protected abstract void AssertTrue(boolean condition, String message);

    protected abstract void AssertFalse(boolean condition, String message);


    //check equals
    public BaseAssertion isEquals(String actual, String expected, String message) {
        AssertEquals(actual, expected, message);
        return this;
    }
    //check true
    public void isTrue(boolean condition, String message) {
        AssertTrue(condition, message);
    }
    //check false
    public void isFalse(boolean condition, String message) {
        AssertFalse(condition, message);
    }

    //check element is enabled
    public void isElementEnabled(By locator) {
        boolean flag = waitManager.FluentWait().until(driver ->
                {
                    try {
                        elementActions.findElement(locator).isEnabled();
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
        AssertTrue(flag, "Element is not enabled: " + locator);
    }


    //check element is visible
    public void isElementVisible(By locator) {

        boolean flag = waitManager.FluentWait().until(driver ->
                {
                    try {
                        elementActions.findElement(locator).isDisplayed();
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
        AssertTrue(flag, "Element is not visible: " + locator);
    }

    //verify page Url
    public void AssertPageUrlEquals(String expectedUrl) {
        String CurrentUrl = driver.getCurrentUrl();
        AssertEquals(CurrentUrl, expectedUrl, "Page URL does not match Expected: " +expectedUrl + "  CurrentUrl: " + CurrentUrl  );
    }

    //verify page title
    public void AssertPageTitleEquals(String expectedTitle) {
        String CurrentTitle = driver.getTitle();
        AssertEquals(CurrentTitle, expectedTitle, "Page Title does not match Expected: " +expectedTitle + "  CurrentTitle: " + CurrentTitle  );
    }


    //check elment is selected
    public void isElementSelected(By locator) {
        boolean flag = waitManager.FluentWait().until(driver ->
        {
            try {
                elementActions.findElement(locator).isSelected();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        AssertTrue(flag, "Element is not selected: " + locator);
    }

    //verify that file exists
    public void assertFileExists(String fileName ,String message) {

        waitManager.FluentWait().until(
                d -> FileUtils.isFileExists(fileName)
        );
        AssertTrue(FileUtils.isFileExists(fileName), message);
    }



}