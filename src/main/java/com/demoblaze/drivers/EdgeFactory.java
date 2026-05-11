package com.demoblaze.drivers;

import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.dataReader.PropertyReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class EdgeFactory extends AbstractDriver{

    private EdgeOptions GetOptions()
    {
       EdgeOptions options = new EdgeOptions();
       options.addArguments("--Start-Maximized");
       options.addArguments("--remote-allow-origins=*");
       options.addArguments("--disable-notifications");
       options.addArguments("--disable-popup-blocking");
       options.addArguments("--disable-infobars");
        Map<String, Object> prefs = new HashMap<>();
        String userDir = System.getProperty("user.dir");
        String downloadPath = userDir + "\\src\\test\\resources\\downloads";
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory",downloadPath);
        options.setExperimentalOption("prefs", prefs);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.ENABLE_DOWNLOADS, true);
       //options.addExtensions(haramBlurExtension);
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
            return new EdgeDriver(GetOptions());
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
