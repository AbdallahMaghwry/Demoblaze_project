package com.demoblaze.utils.Actions;

import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.WaitManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class ElementActions {
    private final WebDriver driver;
    private WaitManager waitManager;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    //Clicking
    public ElementActions click(By locator) {
        waitManager.FluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                        // Wait until the element is stable (not moving)
                        Point initialLocation = element.getLocation();
                        LogsManager.Info("initialLocation: " + initialLocation);
                        Point finalLocation = element.getLocation();
                        LogsManager.Info("finalLocation: " + finalLocation);
                        if (!initialLocation.equals(finalLocation)) {
                            return false; // still moving, wait longer
                        }
                        element.click();
                        LogsManager.Info("Clicked on element: " + locator);
                        return true;
                    } catch (Exception e) {
                        LogsManager.Error("Can't Clicked on element: " + locator);
                        return false;
                    }
                }
        );
        return this;
    }

    //Typing
    public ElementActions type(By locator, String text) {
        waitManager.FluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJS(locator);
                       element.clear();
                        element.sendKeys(text);
                        LogsManager.Info("Typed text '" + text + "' into element: " + locator);
                        return true;
                    } catch (Exception e) {
                        LogsManager.Info("Failed to type into element: " + locator + " with text: '" + text + "' - " + e.getMessage());
                        return false;
                    }
                }
        );
        return this;
    }
    public String gettextFromElement(By locator) {

        return waitManager.FluentWait().until
                (d ->
                        {
                            try {
                                WebElement element = findElement(locator);
                                scrollToElementJS(locator);
                               String MSG =element.getText();
                               LogsManager.Info("Got Text : '" + MSG + " 'from element : " +locator);
                                return !MSG.isEmpty() ? MSG : null;
                            }catch (Exception e) {
                               // LogsManager.Error("Failed to get text from element: " , e.getMessage());
                                return null;
                            }
                        }
                );
    }

    //find element
    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }
    //function to scroll to an element using js
    public void scrollToElementJS(By locator) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(""" 
                        arguments[0].scrollIntoView({behaviour:"auto",block:"center",inline:"center"});""", findElement(locator));
    }

    public void uploadFile(By locator ,String filepath){
        String absolutePath = System.getProperty("user.dir")+ File.separator + filepath;
        waitManager.FluentWait().until
                (d ->
                        {
                            try {
                                WebElement element = d.findElement(locator);
                                scrollToElementJS(locator);
                                element.clear();
                                element.sendKeys(absolutePath);
                                LogsManager.Info("Uploaded file: '" + absolutePath + " 'to element : " +locator);
                                return true;
                            }catch (Exception e) {
                               // LogsManager.Error("Failed to upload file: " , e.getMessage());
                                return false;
                            }
                        }
                );
    }

    //Dropdowm menu using value
    public void selectFromDropdownByVisibleText(By locator ,String value){
        waitManager.FluentWait().until
                (d ->
                        {
                            try {
                                WebElement dropdown = d.findElement(locator);
                                scrollToElementJS(locator);
                                Select select= new Select(dropdown);
                                select.selectByVisibleText(value);
                                LogsManager.Info("Selected value: '" + value + " 'from dropdown : " +locator);
                                return true;
                            }catch (Exception e) {
                                return false;
                            }
                        }
                );
    }
    //Hover Elment
    public ElementActions hoverElement(By locator ){
        waitManager.FluentWait().until
                (d ->
                        {
                            try {
                                WebElement element = d.findElement(locator);
                                scrollToElementJS(locator);
                                 new Actions(d).moveToElement(element).perform();
                                LogsManager.Info("Hover to element "+locator);
                                return true;
                            }catch (Exception e) {
                                return false;
                            }
                        }
                );
        return this;
    }

    public ElementActions Ex_wait(By element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> d.findElement(element).isDisplayed());
        return this;
    }
    //is element displayed

    public boolean IsElementNotDisplayed(By locator) {
        try {
            waitManager.FluentWait().until(d -> {
                try {
                    WebElement element = d.findElement(locator);
                    scrollToElementJS(locator);
                    boolean isNotDisplayed = !element.isDisplayed();
                    LogsManager.Info("Element " + locator + " is displayed: " + !isNotDisplayed);
                    return isNotDisplayed;
                } catch (NoSuchElementException e) {
                    LogsManager.Info("Element " + locator + " is not found: " + e.getMessage());
                    return true; // مش موجود = مش ظاهر ✅
                }
            });
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
