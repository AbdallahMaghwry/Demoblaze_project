package com.demoblaze.utils.Actions;

import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.WaitManager;
import org.openqa.selenium.WebDriver;

public class AlertActions {
    private final WaitManager waitManager;
    private final WebDriver driver;

    public AlertActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    /**
Accept alert
     */
    public void acceptAlert() {
        waitManager.FluentWait().until(d -> {
            try {
                d.switchTo().alert().accept();
                return true;
            } catch (Exception e) {
                LogsManager.Error("Failed to accept alert: " , e.getMessage());
                return false;
            }
        });
    }

    /**
Dismiss alert
     */
    public void dismissAlert() {
        waitManager.FluentWait().until(d -> {
            try {
                d.switchTo().alert().dismiss();
                return true;
            } catch (Exception e) {
                LogsManager.Error("Failed to dismiss alert: " , e.getMessage());
                return null;
            }
        });

    }

    /**
get text from alert
@return the text from the alert
     */
    public String getTextFromAlert() {
        return waitManager.FluentWait().until(d -> {
            try {
                String text = d.switchTo().alert().getText();
                return !text.isEmpty() ? text : null;
            } catch (Exception e) {
                //LogsManager.Error("Failed to get text from alert: " , e.getMessage());
                return null;
            }
        });
    }
/**
send text to alert
 param text the text to be sent to the alert
*/
    //
    public void sendTextFromAlert(String text) {
        waitManager.FluentWait().until(d -> {
            try {
                d.switchTo().alert().sendKeys(text);
                return true;
            } catch (Exception e) {
                LogsManager.Error("Failed to send text to alert: " , e.getMessage());
                return false;
            }
        });
    }






}