package com.demoblaze.validations;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
//hard Assertion
public class Verification extends BaseAssertion {
   public Verification(){
        super();
   }

    public Verification(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void AssertEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    @Override
    protected void AssertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    @Override
    protected void AssertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }
}
