package com.demoblaze.drivers;

import com.demoblaze.utils.Actions.AlertActions;
import com.demoblaze.utils.Actions.BrowserActions;
import com.demoblaze.utils.Actions.ElementActions;
import com.demoblaze.utils.Actions.FrameActions;
import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.dataReader.PropertyReader;
import com.demoblaze.validations.Validation;
import com.demoblaze.validations.Verification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIDriver {

    private final  String browser = PropertyReader.GetProperty("browserType");

    private  ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public GUIDriver () {
        Browsers BrowserType = Browsers.valueOf(browser.toUpperCase());
        LogsManager.Info("Browser Type: " + BrowserType);
        AbstractDriver abstractDriver = BrowserType.getDriverFactory();
        WebDriver driver = ThreadGuard.protect(abstractDriver.CreateDriver());
        driverThreadLocal.set(driver);

    }

    public  WebDriver Get()
     {
        return driverThreadLocal.get();
    }
    public ElementActions element(){
        return new ElementActions(Get());
    }
    public BrowserActions browser(){
        return new BrowserActions(Get());
    }
    public AlertActions alert(){
        return new AlertActions(Get());
    }
    public FrameActions frame(){
        return new FrameActions(Get());
    }
    public Validation validation(){
        return new Validation(Get());
    }
    public Verification verification(){
        return new Verification(Get());
    }

    public void quitDriver()
    {
        driverThreadLocal.get().quit();
    }
}


