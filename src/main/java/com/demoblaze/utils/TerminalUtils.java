package com.demoblaze.utils;

import com.demoblaze.utils.Logs.LogsManager;

import java.io.IOException;

public class TerminalUtils {
    public static void executeTerminalCommand(String... commandParts) {
        try {

            Process process = Runtime.getRuntime().exec(commandParts);
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                LogsManager.Error("Command failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            LogsManager.Error("Failed to execute terminal command: " + String.join(" ", commandParts), e.getMessage());
        }
    }
}


