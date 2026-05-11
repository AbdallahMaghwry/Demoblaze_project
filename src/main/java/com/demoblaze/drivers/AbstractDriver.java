package com.demoblaze.drivers;

import com.demoblaze.utils.dataReader.PropertyReader;
import org.openqa.selenium.WebDriver;

import java.io.File;

public abstract class AbstractDriver {
    protected final String remoteHost = PropertyReader.GetProperty("remoteHost");
    protected final String remotePort = PropertyReader.GetProperty("remotePort");
    protected File  haramBlurExtension= new File("src/main/resources/extensions/HaramBlur.crx");
    public abstract WebDriver CreateDriver();
}
