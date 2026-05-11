package com.demoblaze.Pages;

import com.demoblaze.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupPage {
    private GUIDriver driver;
    public SignupPage(GUIDriver driver) {
        this.driver = driver;
    }


//locators
    private final By Username_Field = By.cssSelector(".modal-body > form > .form-group >[id=\"sign-username\"]");
    private final By Password_Field = By.cssSelector(".modal-body > form > .form-group >[id=\"sign-password\"]");
    private final By Signup_Button = By.cssSelector(".modal-footer button[onclick='register()']");
    private final By Close_Button = By.xpath("//div[@class='modal-footer']//button[@onclick='register()']//preceding-sibling::button");


//Actions
    @Step("Signup Username is Entered")
    public SignupPage enterUsername(String username) {
        driver.element().type(Username_Field, username);
        return this;
    }
    @Step("Signup Password is Entered")
    public SignupPage enterPassword(String password) {
        driver.element().type(Password_Field, password);
        return this;
    }
    @Step("Signup Button Is Clicked")
    public SignupPage clickSignupButton() {
        driver.element().click(Signup_Button);
        return this;
    }

    @Step("Close Button Is Clicked")
    public SignupPage clickCloseButton() {
        driver.element().click(Close_Button);
        return this;
    }
    @Step("Accept alert")
    public SignupPage acceptAlert() {
        driver.alert().acceptAlert();
        return this;
    }

    //Validations
    @Step("validate Alert Message Success Signup is Displayed")
    public SignupPage validateAlertSuccessSignup(String expectedMessage) {
        String actualMessage = driver.alert().getTextFromAlert();
        driver.verification().isEquals(actualMessage, expectedMessage, "Alert Message Success Signup is not as expected The Actual Message :"+actualMessage +" The Expected Message :"+expectedMessage);
        return this;
    }
    @Step("Validate Alert Message user already exists is Displayed")
    public SignupPage validateAlertUserAlreadyExists(String expectedMessage) {
        String actualMessage = driver.alert().getTextFromAlert();
        driver.verification().isEquals(actualMessage, expectedMessage, "Alert User Already Exists message is not as expected The Actual Message :"+actualMessage +" The Expected Message :"+expectedMessage);
        return this;
    }

@Step("Validate Alert Empty Data is Displayed")
    public SignupPage validateAlertEmptyData(String expectedMessage) {
        String actualMessage = driver.alert().getTextFromAlert();
        driver.verification().isEquals(actualMessage, expectedMessage, "Alert Empty Data message is not as expected The Actual Message :"+actualMessage +" The Expected Message :"+expectedMessage);
        return this;
    }
}
