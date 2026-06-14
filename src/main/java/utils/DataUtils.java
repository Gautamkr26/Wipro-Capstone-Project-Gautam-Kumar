package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;

public class DataUtils {
    
    // Reads the TestData.json file and returns it as a JsonObject
    public static JsonObject getJsonData() {
        try {
            String filePath = "src/test/resources/testdata/TestData.json";
            FileReader reader = new FileReader(filePath);
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON test data file.", e);
        }
    }
}