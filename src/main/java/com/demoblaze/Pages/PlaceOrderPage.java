package com.demoblaze.Pages;

import com.demoblaze.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PlaceOrderPage {
    private GUIDriver driver;
    public PlaceOrderPage(GUIDriver driver) {
    this.driver=driver;
    }

    //Locator
        private final By name_Input = By.id("name");
        private final By country_Input = By.id("country");
        private final By city_Input = By.id("city");
        private final By creditCard_Input = By.id("card");
        private final By month_Input = By.id("month");
        private final By year_Input = By.id("year");
        private final By purchase_Button =By.xpath("//button[.='Purchase']");
        private final By successMessage_Label =By.xpath( "//h2[contains(text(),'Thank you for your purchase!')]");
        private final By Close_Button = By.xpath("//button[.='Purchase']/preceding-sibling::button");
        private final By Total_price = By.cssSelector("form> label");
        private final By OK_Button = By.xpath("//*[.='OK']");

        //Actions
        @Step("Enter name: {name}")
        public PlaceOrderPage enterName(String name){
            driver.element().type(name_Input,name);
            return this;
        }
        @Step("Enter country: {country}")
        public PlaceOrderPage enterCountry(String country){
            driver.element().type(country_Input,country);
            return this;
        }
        @Step("Enter city: {city}")
        public PlaceOrderPage enterCity(String city){
            driver.element().type(city_Input,city);
            return this;
        }
        @Step("Enter credit card: {creditCard}")
        public PlaceOrderPage enterCreditCard(String creditCard){
            driver.element().type(creditCard_Input,creditCard);
            return this;
        }
        @Step("Enter month: {month}")
        public PlaceOrderPage enterMonth(String month){
            driver.element().type(month_Input,month);
            return this;
        }
        @Step("Enter year: {year}")
        public PlaceOrderPage enterYear(String year){
            driver.element().type(year_Input,year);
            return this;
        }
        @Step("Clicking purchase button")
        public PlaceOrderPage clickPurchaseButton(){
            driver.element().click(purchase_Button);
            return this;
        }
         @Step("Clicking close button")
         public PlaceOrderPage clickCloseButton(){
        driver.element().click(Close_Button);
        return this;
        }
         @Step("Clicking OK button")
         public PlaceOrderPage clickOKButton(){
            driver.element().click(OK_Button);
            return this;
         }



    //Validations
        @Step("Validate success PlaceOrder message after purchase")
        public PlaceOrderPage validateSuccessPlaceOrderMessage(String expectedMessage){
            String actualMessage = driver.element().gettextFromElement(successMessage_Label);
            driver.verification().isEquals(actualMessage,expectedMessage,"Success message is DisMatched");
            return this;
        }
        @Step("Validate Total price in place order pop up")
        public PlaceOrderPage validateTotalPrice(String expectedTotalPrice){
                String actualTotalPrice = driver.element().gettextFromElement(Total_price).split(":")[1].trim();
                driver.verification().isEquals(actualTotalPrice,expectedTotalPrice,"Total price is DisMatched the Expected is : "+expectedTotalPrice +" Tha Actual is : "+actualTotalPrice);
                return this;
        }
        @Step("Validate place Order Alert Empty Data is Displayed")
        public PlaceOrderPage validatePlaceOrderAlertEmptyData(String expectedMessage){
            String actualMessage = driver.alert().getTextFromAlert();
            driver.verification().isEquals(actualMessage,expectedMessage,"Alert Empty Data message is DisMatched The Expected : "+expectedMessage +"The Actual : "+actualMessage);
            return this;
        }


}
