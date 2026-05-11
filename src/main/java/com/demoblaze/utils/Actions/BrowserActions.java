package com.demoblaze.utils.Actions;

import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.WaitManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class BrowserActions {
    private final WebDriver driver ;
    private WaitManager waitManager;
    public BrowserActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    //maximize window
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    //minimize window
    public void minimizeWindow() {
        driver.manage().window().minimize();
}

    //navigate to url
    public void navigateToURL(String URL) {
        LogsManager.Info(" Navigated to URL: " + URL);
        driver.get(URL);
    }

    //get page title
    public String getPageTitle() {
        return driver.getTitle();
    }

    //get current url
    public String getCurrentURL() {
        String currentURL = driver.getCurrentUrl();
        LogsManager.Info("Current URL: " + currentURL);
        return currentURL;

    }
    //Refresh page
    public void refreshPage() {
        driver.navigate().refresh();
    }
    //CLOSE WINDOW
    public void closeCurrentWindow() {
        driver.close();
    }
    //quit browser
    public void quitBrowser() {
        driver.quit();
    }
    //open new window
    public void openNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
    }
    //close extension tab
    public void closeExtensionTab() {
        String currentWindowHandle = driver.getWindowHandle(); //0 1
        waitManager.FluentWait().until(
                d ->
                {
                    return d.getWindowHandles().size() > 1; //wait until extension tab is opened
                }
        );
        for (String windowHandle : driver.getWindowHandles()) //extension tab is opened
        {
            if (!windowHandle.equals(currentWindowHandle))
                driver.switchTo().window(windowHandle).close(); //close the extension tab
        }
        driver.switchTo().window(currentWindowHandle); //switch back to the main window
        LogsManager.Info("Extension tab closed");
    }


}
