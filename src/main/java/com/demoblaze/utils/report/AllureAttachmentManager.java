package com.demoblaze.utils.report;

import com.demoblaze.media.ScreenRecordManager;
import com.demoblaze.utils.Logs.LogsManager;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.demoblaze.utils.dataReader.PropertyReader.GetProperty;

public class AllureAttachmentManager {
    // attachScreenshot, attachLogs, attachRecords methods would go here
    public static void attachScreenshot(String name, String path) {
        try {
            Path screenshot = Path.of(path);
            if (Files.exists(screenshot)) {
                Allure.addAttachment(name, Files.newInputStream(screenshot));
            } else {
                LogsManager.Error("Screenshot not found: " + path);
            }
        } catch (Exception e) {
            LogsManager.Error("Error attaching screenshot", e.getMessage());
        }
    }

    public static void attachLogs() {
        try {
            LogManager.shutdown();
            File logFile = new File(LogsManager.LOGS_PATH +"logs.log");
            ((LoggerContext) LogManager.getContext(false)).reconfigure();
            if (logFile.exists()) {
                Allure.attachment("logs.log", Files.readString(logFile.toPath()));
            }
        } catch (Exception e) {
            LogsManager.Error("Error attaching logs", e.getMessage());
        }
    }

    public static void attachRecords(String testMethodName) {
        if (GetProperty("recordTests").equalsIgnoreCase("true")) {
            try {
                File record = new File(ScreenRecordManager.RECORDINGS_PATH + testMethodName);
                if (record != null && record.getName().endsWith(".mp4")) {
                    Allure.addAttachment(testMethodName, "video/mp4", Files.newInputStream(record.toPath()), ".mp4");
                }
            } catch (Exception e) {
                LogsManager.Error("Error attaching records", e.getMessage());
            }
        }
    }

}
