package com.demoblaze.Pages;

import com.demoblaze.drivers.GUIDriver;
import com.demoblaze.utils.Logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage {
    private GUIDriver driver;
    public LoginPage(GUIDriver driver) {
        this.driver = driver;
    }

   //locators
   private final By Username_Field = By.cssSelector(".modal-body > form > .form-group >[id=\"loginusername\"]");
    private final By Password_Field = By.cssSelector(".modal-body > form > .form-group >[id=\"loginpassword\"]");
    private final By Login_Button = By.cssSelector(".modal-footer button[onclick='logIn()']");
    private final By Close_Button = By.xpath("//div[@class='modal-footer']//button[@onclick='logIn()']//preceding-sibling::button");
    private final By UserLabel= By.cssSelector("li>a[id='nameofuser']") ;



    //Actions
    @Step("Login Username is Entered")
    public LoginPage enterUsername(String username) {
        driver.element().type(Username_Field, username);
        return this;
    }
    @Step("Login Password is Entered")
    public LoginPage enterPassword(String password) {
        driver.element().type(Password_Field, password);
        return this;
    }
    @Step("Login Button Is Clicked")
    public LoginPage clickLoginButton() {
        driver.element().click(Login_Button);
        return this;
    }

    @Step("Accept alert")
    public LoginPage acceptAlert() {
        driver.alert().acceptAlert();
        return this;
    }

    //Validations
    @Step("verify that username label is matched")
    public LoginPage   verifyUsernameVisible(String ExpectedName) {
        String actualUsername = driver.element().gettextFromElement(UserLabel);
        LogsManager.Info("verifying User Label:  " + actualUsername);
        driver.verification().isEquals(actualUsername,ExpectedName,"Username is not matched Actual: " + actualUsername + " Expected: " + ExpectedName);
        return this;
    }
    @Step("Validate Alert Empty Data is Displayed")
    public LoginPage validateAlertEmptyData(String expectedMessage) {
        String actualMessage = driver.alert().getTextFromAlert();
        driver.verification().isEquals(actualMessage, expectedMessage, "Alert Empty Data message is not as expected The Actual Message :"+actualMessage +" The Expected Message :"+expectedMessage);
        return this;
    }
    @Step("Validate Alert Error Data is Displayed")
    public LoginPage validateAlertErrorData(String expectedMessage) {
        String actualMessage = driver.alert().getTextFromAlert();
        driver.verification().isEquals(actualMessage, expectedMessage, "Alert Error Data message is not as expected The Actual Message :"+actualMessage +" The Expected Message :"+expectedMessage);
        return this;
    }




}
