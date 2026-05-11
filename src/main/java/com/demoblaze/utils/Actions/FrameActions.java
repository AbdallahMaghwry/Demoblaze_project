package com.demoblaze.utils.Actions;

import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrameActions {
    private final WebDriver driver;
    private final WaitManager waitManager;
    public FrameActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }
    /**
     * switch to frame by index
     * @param index the index of the frame to switch to
     * */
    public void switchToFrameByIndex(int index) {
        waitManager.FluentWait().until(d -> {
            try {
                d.switchTo().frame(index);
                LogsManager.Info("Switched to frame by index: " + index);
                return true;
            } catch (Exception e) {
                LogsManager.Error("Failed to switch to frame by index: " , e.getMessage());
                return false;
            }
        });
    }
    /**
     * switch to frame by name or id
     * @param nameOrId the name or id of the frame to switch to
     * */
    public void switchToFrameByNameOrId(String nameOrId) {
        waitManager.FluentWait().until(d -> {
            try {
                d.switchTo().frame(nameOrId);
                LogsManager.Info("Switched to frame by name or id: " + nameOrId);
                return true;
            } catch (Exception e) {
                LogsManager.Error("Failed to switch to frame by name or id: " , e.getMessage());
                return false;
            }
        });
    }
    /**
     * switch to frame by element
     * @param framelocator the locator of the frame element to switch to
     * */
    public void switchToFrameByElement(By framelocator) {
        waitManager.FluentWait().until(d -> {
            try {
                d.switchTo().frame(d.findElement(framelocator));
                LogsManager.Info("Switched to frame by element: " + framelocator);
                return true;
            } catch (Exception e) {
                LogsManager.Error("Failed to switch to frame by element: " , e.getMessage());
                return false;
            }
        });
    }
    /**
     * switch back to default content
     * */
    public void switchToDefaultContent(){
        waitManager.FluentWait().until(d -> {
            try {
                d.switchTo().defaultContent();
                LogsManager.Info("Switched to default content");
                return true;
            } catch (Exception e) {
                LogsManager.Error("Failed to switch to default content: " , e.getMessage());
                return false;
            }
        });
    }

}
