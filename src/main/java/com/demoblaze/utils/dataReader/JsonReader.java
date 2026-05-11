package com.demoblaze.utils.dataReader;

import com.demoblaze.utils.Logs.LogsManager;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {
    private final String TEST_DATA_PATH ="src/test/resources/test-data/";
    String JsonReader;
    String jsonFileName;

    public JsonReader (String jsonFileName){
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));
            JsonReader = data.toJSONString();
        }catch (Exception e){
            LogsManager.Error("Error loading json file: " , jsonFileName , e.getMessage());
            JsonReader = "{}";
        }
    }

    public String GetJsonData(String jsonPath){
        try {
            return JsonPath.read(JsonReader,jsonPath);
        }catch (Exception e){
            LogsManager.Error("Error getting json data for key: " , jsonPath , e.getMessage());
            return "";
        }
    }

}
