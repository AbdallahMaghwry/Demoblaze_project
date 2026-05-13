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
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Abdallah Mohammed")
@UITest
public class LoginTest extends BaseTest {

    @Description("Verify user can login with valid credentials")
    @Test
    public void ValidLoginTC(){
        String timestamp = TimeManager.GetCurrentTimeStamp();
        new NavigateBar(driver).clickSignUpButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("password"))
                .clickSignupButton()
                .validateAlertSuccessSignup(testData.GetJsonData("messages.AlertSuccess"))
                .acceptAlert();
        new NavigateBar(driver).clickLoginButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("password"))
                .clickLoginButton()
                .verifyUsernameVisible("Welcome "+testData.GetJsonData("username") + timestamp);
    }
    @Description("Verify user can't login with invalid Username")
    @Test
    public void InValidUsernameLoginTC(){
        String timestamp = TimeManager.GetCurrentTimeStamp();
        new NavigateBar(driver).clickSignUpButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("password"))
                .clickSignupButton()
                .validateAlertSuccessSignup(testData.GetJsonData("messages.AlertSuccess"))
                .acceptAlert();
        new NavigateBar(driver).clickLoginButton()
                .enterUsername(testData.GetJsonData("Invalidusername") + timestamp)
                .enterPassword(testData.GetJsonData("password"))
                .clickLoginButton()
                .validateAlertErrorData(testData.GetJsonData("messages.AlertErrorUsernameData"))
                .acceptAlert();
    }
    @Description("Verify user can't login with invalid Password")
    @Test
    public void InValidPasswordLoginTC(){
        String timestamp = TimeManager.GetCurrentTimeStamp();
        new NavigateBar(driver).clickSignUpButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("password"))
                .clickSignupButton()
                .validateAlertSuccessSignup(testData.GetJsonData("messages.AlertSuccess"))
                .acceptAlert();
        new NavigateBar(driver).clickLoginButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("Invalidpassword"))
                .clickLoginButton()
                .validateAlertErrorData(testData.GetJsonData("messages.AlertErrorPasswordData"))
                .acceptAlert();
    }
    @Description("Verify user can't login with Empty Password")
    @Test
    public void InValidLoginEmptyPasswordTC(){
        String timestamp = TimeManager.GetCurrentTimeStamp();
        new NavigateBar(driver).clickSignUpButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("password"))
                .clickSignupButton()
                .validateAlertSuccessSignup(testData.GetJsonData("messages.AlertSuccess"))
                .acceptAlert();
        new NavigateBar(driver).clickLoginButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("emptypassword"))
                .clickLoginButton()
                .validateAlertEmptyData(testData.GetJsonData("messages.AlertEmptyData"))
                .acceptAlert();
    }
    @Description("Verify user can't login with Empty Username")
    @Test
    public void InValidLoginEmptyUsernameTC(){
        String timestamp = TimeManager.GetCurrentTimeStamp();
        new NavigateBar(driver).clickSignUpButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("password"))
                .clickSignupButton()
                .validateAlertSuccessSignup(testData.GetJsonData("messages.AlertSuccess"))
                .acceptAlert();
        new NavigateBar(driver).clickLoginButton()
                .enterUsername(testData.GetJsonData("emptyusername"))
                .enterPassword(testData.GetJsonData("password"))
                .clickLoginButton()
                .validateAlertEmptyData(testData.GetJsonData("messages.AlertEmptyData"))
                .acceptAlert();
    }

    @Description("Verify user can Logout After Login")
    @Test
    public void ValidLogoutTC(){
        String timestamp = TimeManager.GetCurrentTimeStamp();
        new NavigateBar(driver).clickSignUpButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("password"))
                .clickSignupButton()
                .validateAlertSuccessSignup(testData.GetJsonData("messages.AlertSuccess"))
                .acceptAlert();
        new NavigateBar(driver).clickLoginButton()
                .enterUsername(testData.GetJsonData("username") + timestamp)
                .enterPassword(testData.GetJsonData("password"))
                .clickLoginButton()
                .verifyUsernameVisible("Welcome "+testData.GetJsonData("username") + timestamp)
                .navigateBar
                .clickLogoutButton()
                .verifyUsernameNotVisibleAfterLogout();
    }









    //configuration
    @BeforeClass
    protected void precondition(){
        testData = new JsonReader("login-data");
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
