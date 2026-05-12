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
@Story("User Add product to cart")
@Severity(SeverityLevel.CRITICAL)
@Owner("Abdallah Mohammed")
@UITest
public class CartTest extends BaseTest {

    @Description("verify User Can Add Product To Cart Witout Login")
    @Test
    public void AddProductToCartWithoutLoginTC(){
        new CartPage(driver)
                .ClickProductName(testData.GetJsonData("products.product_mobile"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAddedWithoutLogin")
                )
                .acceptAlert();
    }

    @Description("verify User Can Add One Product To Cart With Login")
    @Test
    public void AddOneProductToCartWithLoginTC(){
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
                .acceptAlert();
    }
    @Description("Verify User Can Add Different Products To Cart Without Login")
    @Test
    public void AddDifferentProductToCartWithoutLoginTC(){
        new CartPage(driver)
                .ClickProductName(testData.GetJsonData("products.product_mobile"))
                .ValidateProductNameInCartPage(testData.GetJsonData("products.product_mobile"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAddedWithoutLogin")
                )
                .acceptAlert()
                .navigateBar
                .clickHomeButton();
        new CartPage(driver)
                .ClickProductName(testData.GetJsonData("products.product_laptop"))
                .ValidateProductNameInCartPage(testData.GetJsonData("products.product_laptop"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAddedWithoutLogin")
                )
                .acceptAlert()
                .navigateBar
                .clickHomeButton();
        new CartPage(driver)
                .clickCategory(testData.GetJsonData("categories.category_monitor"))
                .ClickProductName(testData.GetJsonData("products.product_monitor"))
                .addProductToCart()
                .validateAlertProductAdded(
                        testData.GetJsonData("messages.AlertProductAddedWithoutLogin")
                )
                .acceptAlert()
                .navigateBar
                .clickCartButton().validateTotalPrice(
                        testData.GetJsonData("products.product_mobile"),
                        testData.GetJsonData("products.product_laptop"),
                        testData.GetJsonData("products.product_monitor")
                );
    }
    @Description("Verify User Can Add Different Products To Cart With Login")
    @Test
    public void AddDifferentProductToCartWithLoginTC(){
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
                );
    }

    @Test
    public void DeleteProductFromCartTC(){
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
                .deleteProductFromCart(testData.GetJsonData("products.product_mobile"))
                .validateEmptyCartMessage();
    }


    //configuration
    @BeforeClass
    protected void precondition(){
        testData = new JsonReader("Cart-data");
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
