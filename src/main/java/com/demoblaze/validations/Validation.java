package com.demoblaze.validations;

import com.demoblaze.utils.Logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

//soft Assertion
public class Validation extends BaseAssertion {
     private static SoftAssert softAssert = new SoftAssert();
    private static boolean used = false;
   public Validation() {
        super();
    }

    public Validation(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void AssertEquals(String actual, String expected, String message) {
        used= true;
        Assert.assertEquals(actual, expected, message);
    }

    @Override
    protected void AssertTrue(boolean condition, String message) {
        used= true;
        Assert.assertTrue(condition, message);
    }

    @Override
    protected void AssertFalse(boolean condition, String message) {
        used = true;
        Assert.assertFalse(condition, message);
    }

    // all Soft Assertion
    public static void AssertAll(ITestResult result){
        if (!used)return;
        try {
            softAssert.assertAll();
        }
        catch (AssertionError e) {
            LogsManager.Error("Soft assertion failed: " + e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e); // set the exception as the cause of failure
           // throw e; // rethrow the assertion error to fail the test
        }
        finally {
           softAssert = new SoftAssert(); // reset the SoftAssert for reuse
        }
    }
}
