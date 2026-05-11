package com.demoblaze.utils;

import com.demoblaze.utils.Logs.LogsManager;
import com.demoblaze.utils.dataReader.PropertyReader;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.copyFile;

public class FileUtils {
private static final String DIR_PATH = PropertyReader.GetProperty("user.dir")+File.separator;

    private FileUtils(){
        //prevent instantiation
    }

    // Renaming
    public static void renameFile(String oldName, String newName) {
        try {
            var targetFile = new File(oldName);
            String targetDirectory = targetFile.getParentFile().getAbsolutePath();
            File newFile = new File(targetDirectory + File.separator + newName);
            if (!targetFile.getPath().equals(newFile.getPath())) {
                copyFile(targetFile, newFile);
                org.apache.commons.io.FileUtils.deleteQuietly(targetFile);
                LogsManager.Info("Target File Path: \"" + oldName + "\", file was renamed to \"" + newName + "\".");
            } else {
                LogsManager.Info(("Target File Path: \"" + oldName + "\", already has the desired name \"" + newName + "\"."));
            }
        } catch (IOException e) {
            LogsManager.Error("Error Rename :"+e.getMessage());
        }
    }

    //creating Directory
    public static void createDirectory(String path){
        try {
            File file = new File(DIR_PATH + path);
            if (!file.exists()) {
                file.mkdirs();
                LogsManager.Info("Directory created: " + path);
            }
        }catch (Exception e){
            LogsManager.Error("Failed to create directory: " + path + " - " + e.getMessage());
        }
    }
    //Force delete
    public static void forceDeleteDirectory(File file){
        try {
            org.apache.commons.io.FileUtils.forceDeleteOnExit(file);
            LogsManager.Info("File deleted: " + file.getAbsolutePath());

        }
        catch (Exception e){
            LogsManager.Error("Failed to cleaning directory: " + file.getAbsolutePath() + " - " + e.getMessage());
        }
    }


    //cleaning Directory
    public static void cleanDirectory(File file){
        try {
            org.apache.commons.io.FileUtils.deleteQuietly(file);

        }
        catch (Exception e){
            LogsManager.Error("Failed to cleaning directory: " + file.getAbsolutePath() + " - " + e.getMessage());
        }
    }

    //check if the file exists
    public static boolean isFileExists(String filename){
        String filePath = System.getProperty("user.dir")+File.separator + "/src/test/resources/downloads/" ;
        File file = new File(filePath+ filename);
        return file.exists();
    }

}
