package com.demoblaze.drivers;

import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.dataReader.PropertyReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;


public class ChromeFactory extends AbstractDriver{

    private ChromeOptions GetOptions()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--Start-Maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addExtensions(haramBlurExtension);
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        switch (PropertyReader.GetProperty("executionType"))
        {
            case "LocalHeadless"->options.addArguments("--headless=new");
            case "Remote"->
            {
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                options.addArguments("--headless=new");

            }
        }
        return options;
    }

    @Override
    public WebDriver CreateDriver() {

        if (PropertyReader.GetProperty("executionType").equalsIgnoreCase("LocalHeadless") ||
                PropertyReader.GetProperty("executionType").equalsIgnoreCase("Local"))
        {
            return new ChromeDriver(GetOptions());
        }
        else if (PropertyReader.GetProperty("executionType").equalsIgnoreCase("remote"))
        {

            try {
                return new RemoteWebDriver(new URI("http://"+ remoteHost + ":" + remotePort + "/wd/hub").toURL(), GetOptions());
            }
            catch (Exception e)
            {
                LogsManager.Error("Failed to create remote WebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to create remote WebDriver: " + e.getMessage());
            }


        }
        else {
            LogsManager.Error("Invalid execution type: " + PropertyReader.GetProperty("executionType"));
            throw new IllegalArgumentException("Invalid execution type: " + PropertyReader.GetProperty("executionType"));
        }


    }
}
