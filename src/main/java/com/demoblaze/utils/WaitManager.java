package com.demoblaze.utils;

import com.demoblaze.utils.dataReader.PropertyReader;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class WaitManager {

    private WebDriver driver;

    public WaitManager(WebDriver driver) {
        this.driver = driver;
    }


    public FluentWait<WebDriver> FluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(java.time.Duration.ofSeconds(Long.parseLong(PropertyReader.GetProperty("DEFAULT_WAIT"))))
                .pollingEvery(java.time.Duration.ofMillis(100))
                .ignoreAll(ignoredExceptions());
    }


    private ArrayList<Class<? extends Exception>> ignoredExceptions()
    {
        ArrayList<Class<? extends Exception>> exceptions = new ArrayList<>();
        exceptions.add(NoSuchElementException.class);
        exceptions.add(StaleElementReferenceException.class);
        exceptions.add(ElementNotInteractableException.class);
        exceptions.add(ElementClickInterceptedException.class);
        return exceptions;
    }




}

