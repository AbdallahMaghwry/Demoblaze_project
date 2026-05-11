package com.demoblaze.tests.ui;

import com.demoblaze.tests.BaseTest;
import com.demoblaze.drivers.GUIDriver;
import com.demoblaze.drivers.UITest;
import com.demoblaze.Pages.NavigateBar;
import com.demoblaze.utils.TimeManager;
import com.demoblaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Demoblaze")
@Feature("UI User Management")
@Story("User Registration")
@Severity(SeverityLevel.CRITICAL)
@Owner("Abdallah Mohammed")
@UITest
public class RegisterTest extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();
    @Description("Verify user can sign up with valid data")
    @Test
    public void validSignUpTC() {
        new NavigateBar(driver).clickSignUpButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("password"))
                .clickSignupButton()
                .validateAlertSuccessSignup(testData.GetJsonData("messages.AlertSuccess"))
                .acceptAlert();
    }
    @Description("Verify user can't sign up by existing username")
    @Test
    public void signUpWithExistingUsernameTC() {
        new NavigateBar(driver).clickSignUpButton()
                .enterUsername(testData.GetJsonData("username"))
                .enterPassword(testData.GetJsonData("password"))
                .clickSignupButton()
                .validateAlertUserAlreadyExists(testData.GetJsonData("messages.AlertUserAlreadyExists"))
                .acceptAlert();
    }
    @Description("Verify User can't sign up with empty Username Field")
    @Test
    public void signUpWithEmptyUsernameTC() {
        new NavigateBar(driver).clickSignUpButton()
                .enterUsername(testData.GetJsonData("emptyusername"))
                .enterPassword(testData.GetJsonData("password"))
                .clickSignupButton()
                .validateAlertEmptyData(testData.GetJsonData("messages.AlertEmptyData"))
                .acceptAlert();
    }
    @Description("Verify User can't sign up with empty Username Field")
    @Test
    public void signUpWithEmptyPasswordTC() {
        new NavigateBar(driver).clickSignUpButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("emptypassword"))
                .clickSignupButton()
                .validateAlertEmptyData(testData.GetJsonData("messages.AlertEmptyData"))
                .acceptAlert();
    }

    //configuration
    @BeforeClass
    protected void precondition(){
        testData = new JsonReader("Register-data");
    }

    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();
        new NavigateBar(driver).navigateToBaseUrl();


    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}
