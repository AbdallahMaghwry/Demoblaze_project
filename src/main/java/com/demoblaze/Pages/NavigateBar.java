package com.demoblaze.Pages;

import com.demoblaze.drivers.GUIDriver;
import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigateBar {
    private GUIDriver driver;

    public NavigateBar(GUIDriver driver) {
        this.driver = driver;

    }


    //Locator
   private final By homeButton = By.cssSelector("li >a[href='index.html']");
    private final By ContactButton = By.xpath("//a[.=\"Contact\"]");
   private final By AboutUsButton = By.xpath("//a[.=\"About us\"]");
   private final By CartButton = By.cssSelector("a[id='cartur']");
   private final By LoginButton = By.cssSelector("a[id='login2']");
   private final By SignUpButton = By.cssSelector("a[id='signin2']");

    //Actions
    @Step("Navigate to Base Url")
    public NavigateBar navigateToBaseUrl(){
        driver.browser().navigateToURL(PropertyReader.GetProperty("baseUrlWeb"));
        return this;
    }
    @Step("Home Button Clicked")
    public NavigateBar clickHomeButton(){
        driver.element().click(homeButton);
        return this;
    }
    @Step("Contact Button Clicked")
    public NavigateBar clickContactButton(){
        driver.element().click(ContactButton);
        return this;
    }
    @Step("AboutUS Button Clicked")
    public NavigateBar clickAboutUsButton(){
        driver.element().click(AboutUsButton);
        return this;
    }
    @Step("Cart Button Clicked")
    public CartPage clickCartButton(){
        driver.element().click(CartButton);
        return new CartPage(driver);
    }
    @Step("Login Button Clicked")
    public LoginPage clickLoginButton(){
        driver.element().click(LoginButton);
        return new LoginPage(driver);
    }
    @Step("SignUp Button Clicked")
    public SignupPage clickSignUpButton(){
        driver.element().click(SignUpButton);
        return new SignupPage(driver);
    }




}
