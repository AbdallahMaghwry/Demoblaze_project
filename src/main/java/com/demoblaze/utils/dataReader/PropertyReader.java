package com.demoblaze.utils.dataReader;

import com.demoblaze.utils.Logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

public class PropertyReader {
    public static Properties LoadPropertiesFiles() {
        try {
            Properties property = new Properties();
            Collection<File> propertiesFile;
            propertiesFile = FileUtils.listFiles(new File("src/main/resources/"), new String[]{"properties"}, true);
            propertiesFile.forEach(file -> {
                try {
                    property.load(FileUtils.openInputStream(file));
                } catch (Exception e) {
                    LogsManager.Error("Error loading properties file:" , file.getName() , e.getMessage());
                }
                property.putAll(System.getProperties());
                System.getProperties().putAll(property);
            });
            return property;
        } catch (Exception e) {
            LogsManager.Error("Error loading properties files: " , e.getMessage());
        return null;
        }
    }

    public static String GetProperty(String key)
    {
        try {
            if (System.getProperty(key) == null) {
                LoadPropertiesFiles();
            }
            return System.getProperty(key);
        }catch (Exception e){
            LogsManager.Error("Error getting property: " , key , e.getMessage());
        return "";

        }
    }
}
