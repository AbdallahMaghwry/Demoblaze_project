package com.demoblaze.Pages;

import com.demoblaze.drivers.GUIDriver;
import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.WaitManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartPage {
    private  GUIDriver driver;
    public NavigateBar navigateBar;

    public CartPage(GUIDriver driver) {
           this.driver=driver;
           this.navigateBar=new NavigateBar(driver);

    }

    //Locators
    private final By AddToCart_Button = By.xpath("//a[.='Add to cart']");
    private final By NameOfProduct_Header = By.tagName("h2");
    private final By TotalPriceOfProducts_Label = By.xpath("//h3");
    private final By PlaceOrder_Button = By.xpath("//*[.='Place Order']");
    private final By homeButton = By.cssSelector("li >a[href='index.html']");

    //Dynamic Locator
    private By productName(String productName) {
        return By.xpath("//h4/*[.='"+productName+"']");
    }
    private By Categories(String CategoryName) {
        return By.xpath("//*[.='"+CategoryName+"']");
    }
    private By DeleteProduct(String ProductName)
    {
        return By.xpath("//div/table/tbody/tr//td[.='"+ProductName+"']/following-sibling::td[2]/a");

    }
    private By PriceProduct(String ProductName)
    {
        return By.xpath("//div/table/tbody/tr//td[.='"+ProductName+"']/following-sibling::td[1]");

    }


    //Actions

    @Step("Click on product name: {productName}")
        public CartPage ClickProductName(String productName){
             driver.element().click(productName(productName));
            return this;
    }
    @Step("Click on category: {CategoryName}")
        public CartPage clickCategory(String CategoryName){
            driver.element().click(Categories(CategoryName));
            return this;
        }
    @Step("click on Add to Cart")
    public CartPage addProductToCart()
    {
        driver.element().click(AddToCart_Button);
        return this;
    }

    @Step("Click on Delete Product From Cart")
    public CartPage deleteProductFromCart(String ProductName)
    {
        try {
            driver.element().click(DeleteProduct(ProductName));
            Thread.sleep(1000);
            return this;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Step("Place Order Button Is Clicked")
    public PlaceOrderPage clickPlaceOrderButton() {
        driver.element().click(PlaceOrder_Button);
        return new PlaceOrderPage(driver);
    }

    @Step("Accept alert : (Product added) Message")
    public CartPage acceptAlert() {
        driver.alert().acceptAlert();
        return this;
    }

    //Validations
    @Step("Validate Product Name in Cart Page is: {expectedProductName}")
    public CartPage ValidateProductNameInCartPage(String expectedProductName) {
        driver.element().Ex_wait(NameOfProduct_Header);
        String actualProductName = driver.element().findElement(NameOfProduct_Header).getText();
        driver.verification().isEquals(actualProductName, expectedProductName, "Product Name in Cart Page is not as expected The Actual Product Name :" + actualProductName + " The Expected Product Name :" + expectedProductName);
        return this;
    }
    @Step("Validate Alert Product Added is Displayed")
    public CartPage validateAlertProductAdded(String expectedMessage) {
        String actualMessage = driver.alert().getTextFromAlert();
        driver.verification().isEquals(actualMessage, expectedMessage, "Alert Product Added message is not as expected The Actual Message :"+actualMessage +" The Expected Message :"+expectedMessage);
        return this;
    }



    @Step("Validate Total Price is Matched with the sum of product prices")
    public CartPage validateTotalPrice(String product1, String product2, String product3) {
        driver.element().Ex_wait(TotalPriceOfProducts_Label);
        int price_product1 = Integer.parseInt(driver.element().findElement(PriceProduct(product1)).getText().trim());
        int price_product2  = Integer.parseInt(driver.element().findElement(PriceProduct(product2)).getText().trim());
        int price_product3  = Integer.parseInt(driver.element().findElement(PriceProduct(product3)).getText().trim());

        int expectedTotal = price_product1 + price_product2 + price_product3;

        int actualTotal = Integer.parseInt(driver.element().findElement(TotalPriceOfProducts_Label).getText().trim());

        driver.verification().isNUMEquals(
                actualTotal,
                expectedTotal,
                "message: Total price is not as expected"
        );
        LogsManager.Info("Validating total price: " + actualTotal + " Expected total price: " + expectedTotal);
        return this;
    }


    public void validateEmptyCartMessage() {
        boolean actualMessage = driver.element().IsElementNotDisplayed(TotalPriceOfProducts_Label);
        driver.verification().isTrue(actualMessage, "message: Total price label is displayed, cart is Not empty");
    }
}
