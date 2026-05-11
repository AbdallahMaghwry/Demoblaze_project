package com.demoblaze.utils.report;

import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.OSUtils;
import com.demoblaze.utils.TerminalUtils;
import com.demoblaze.utils.TimeManager;
import org.apache.commons.io.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.demoblaze.utils.FileUtils.renameFile;
import static com.demoblaze.utils.dataReader.PropertyReader.GetProperty;
import static com.demoblaze.utils.report.AllureConstants.HISTORY_FOLDER;
import static com.demoblaze.utils.report.AllureConstants.RESULTS_HISTORY_FOLDER;

public class AllureReportGenerator {
    //Generate Allure report
    //--single-file - generate single file report
    public static void generateReports(boolean isSingleFile) {
        Path outputFolder = isSingleFile ? AllureConstants.REPORT_PATH : AllureConstants.FULL_REPORT_PATH;
        List<String> command = new ArrayList<>(List.of(
                AllureBinaryManager.getExecutable().toString(),
                "generate",
                AllureConstants.RESULTS_FOLDER.toString(),
                "-o", outputFolder.toString(),
                "--clean"
        ));
        if (isSingleFile) command.add("--single-file");
        TerminalUtils.executeTerminalCommand(command.toArray(new String[0]));

    }

    //rename report file to AllureReport_timestamp.html
    public static String renameReport() {
        String newFileName = AllureConstants.REPORT_PREFIX + TimeManager.GetCurrentTimeStamp() + AllureConstants.REPORT_EXTENSION; // AllureReport_20250720_211230.html
       renameFile(AllureConstants.REPORT_PATH.resolve(AllureConstants.INDEX_HTML).toString(), newFileName);
        return newFileName;
    }

    //open Allure report in browser
    public static void openReport(String reportFileName) {
        if (!GetProperty("executionType").toLowerCase().contains("local")) return;

        Path reportPath = AllureConstants.REPORT_PATH.resolve(reportFileName);
        switch (OSUtils.GetCurrentOS()) {
            case WINDOWS -> TerminalUtils.executeTerminalCommand(
                    "cmd.exe",
                    "/c",
                    "start",
                    "\"\"",
                    "\"" + reportPath.toString() + "\""
            );
            case MAC, LINUX -> TerminalUtils.executeTerminalCommand("open", reportPath.toString());
            default -> LogsManager.Warn("Opening Allure Report is not supported on this OS.");
        }
        LogsManager.Info("Opened Allure Report: " + reportPath.toString());
    }

    //copy history folder to results folder
    public static void copyHistory() {
        try {
            FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULTS_HISTORY_FOLDER.toFile());
        } catch (Exception e) {
            LogsManager.Error("Error copying history files", e.getMessage());
        }
    }

}
