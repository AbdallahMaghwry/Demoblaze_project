package com.demoblaze.tests.ui;

import com.demoblaze.Pages.CartPage;
import com.demoblaze.Pages.NavigateBar;
import com.demoblaze.drivers.GUIDriver;
import com.demoblaze.drivers.UITest;
import com.demoblaze.tests.BaseTest;
import com.demoblaze.utils.TimeManager;
import com.demoblaze.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Demoblaze")
@Feature("UI User Management")
@Story("User Place Order")
@Severity(SeverityLevel.CRITICAL)
@Owner("Abdallah Mohammed")
@UITest
public class PlaceOrderTest extends BaseTest {


    @Description("Verify user Can Place Order With Login")
    @Test
    public void ValidPlaceOrderOneProductWithLoginTC(){
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
        new CartPage(driver)
                .ClickProductName(testData.GetJsonData("products.product_mobile"))
                .ValidateProductNameInCartPage(testData.GetJsonData("products.product_mobile"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAdded")
                )
                .acceptAlert()
                .navigateBar
                .clickCartButton()
                .clickPlaceOrderButton()
                .enterName(testData.GetJsonData("order.name"))
                .enterCountry(testData.GetJsonData("order.country"))
                .enterCity(testData.GetJsonData("order.city"))
                .enterCreditCard(testData.GetJsonData("order.creditCard"))
                .enterMonth(testData.GetJsonData("order.month"))
                .enterYear(testData.GetJsonData("order.year"))
                .clickPurchaseButton()
                .validateSuccessPlaceOrderMessage(testData.GetJsonData("order.successMessagePlaceOrder_Label"))
                .validateTotalPrice(testData.GetJsonData("products.Amount_mobile"))
                .clickOKButton();



    }
    @Description("Verify user Can Place Order One Product Without Login")
    @Test
    public void ValidPlaceOrderOneProductWithoutLoginTC(){
        new CartPage(driver)
                .ClickProductName(testData.GetJsonData("products.product_mobile"))
                .ValidateProductNameInCartPage(testData.GetJsonData("products.product_mobile"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAddedWithoutLogin")
                )
                .acceptAlert()
                .navigateBar
                .clickCartButton()
                .clickPlaceOrderButton()
                .enterName(testData.GetJsonData("order.name"))
                .enterCountry(testData.GetJsonData("order.country"))
                .enterCity(testData.GetJsonData("order.city"))
                .enterCreditCard(testData.GetJsonData("order.creditCard"))
                .enterMonth(testData.GetJsonData("order.month"))
                .enterYear(testData.GetJsonData("order.year"))
                .clickPurchaseButton()
                .validateSuccessPlaceOrderMessage(testData.GetJsonData("order.successMessagePlaceOrder_Label"))
                .validateTotalPrice(testData.GetJsonData("products.Amount_mobile"))
                .clickOKButton();

    }

    @Description("Verify user Can Place Order Without Login")
    @Test
    public void ValidPlaceOrderDifferentProductsWithLoginTC() {
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
        new CartPage(driver)
                .ClickProductName(testData.GetJsonData("products.product_mobile"))
                .ValidateProductNameInCartPage(testData.GetJsonData("products.product_mobile"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAdded")
                )
                .acceptAlert()
                .navigateBar
                .clickHomeButton();
        new CartPage(driver)
                .ClickProductName(testData.GetJsonData("products.product_laptop"))
                .ValidateProductNameInCartPage(testData.GetJsonData("products.product_laptop"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAdded")
                )
                .acceptAlert()
                .navigateBar
                .clickHomeButton();
        new CartPage(driver)
                .clickCategory(testData.GetJsonData("categories.category_monitor"))
                .ClickProductName(testData.GetJsonData("products.product_monitor"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAdded")
                )
                .acceptAlert()
                .navigateBar
                .clickCartButton().validateTotalPrice(
                        testData.GetJsonData("products.product_mobile"),
                        testData.GetJsonData("products.product_laptop"),
                        testData.GetJsonData("products.product_monitor")
                )
                .clickPlaceOrderButton()
                .enterName(testData.GetJsonData("order.name"))
                .enterCountry(testData.GetJsonData("order.country"))
                .enterCity(testData.GetJsonData("order.city"))
                .enterCreditCard(testData.GetJsonData("order.creditCard"))
                .enterMonth(testData.GetJsonData("order.month"))
                .enterYear(testData.GetJsonData("order.year"))
                .clickPurchaseButton()
                .validateSuccessPlaceOrderMessage(testData.GetJsonData("order.successMessagePlaceOrder_Label"))
                .validateTotalPrice(testData.GetJsonData("products.total_amount"))
                .clickOKButton();

    }

    @Description("Verify user Can't Place Order With Empty Data in All Fields")
    @Test
    public void InValidPlaceOrderWithEmptyData(){
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
        new CartPage(driver)
                .ClickProductName(testData.GetJsonData("products.product_mobile"))
                .ValidateProductNameInCartPage(testData.GetJsonData("products.product_mobile"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAdded")
                )
                .acceptAlert()
                .navigateBar
                .clickHomeButton();
        new CartPage(driver)
                .ClickProductName(testData.GetJsonData("products.product_laptop"))
                .ValidateProductNameInCartPage(testData.GetJsonData("products.product_laptop"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAdded")
                )
                .acceptAlert()
                .navigateBar
                .clickHomeButton();
        new CartPage(driver)
                .clickCategory(testData.GetJsonData("categories.category_monitor"))
                .ClickProductName(testData.GetJsonData("products.product_monitor"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAdded")
                )
                .acceptAlert()
                .navigateBar
                .clickCartButton().validateTotalPrice(
                        testData.GetJsonData("products.product_mobile"),
                        testData.GetJsonData("products.product_laptop"),
                        testData.GetJsonData("products.product_monitor")
                )
                .clickPlaceOrderButton()
                .clickPurchaseButton()
                .validatePlaceOrderAlertEmptyData(
                        testData.GetJsonData("messages.AlertEmptyDataPlaceOrder"));

    }













    //configuration
    @BeforeClass
    protected void precondition(){
        testData = new JsonReader("PlaceOrder-data");
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
