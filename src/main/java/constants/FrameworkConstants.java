package constants;

import java.io.File;

public class FrameworkConstants {
    private FrameworkConstants() {} // Prevent instantiation

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String CONFIG_FILE_PATH = PROJECT_PATH + "/src/test/resources/config/config.properties";
    public static final String REPORT_PATH = PROJECT_PATH + "/reports/";
    public static final String SCREENSHOT_PATH = PROJECT_PATH + "/screenshots/";
    public static final String EXCEL_DATA_PATH = PROJECT_PATH + "/src/test/resources/testdata/TestData.xlsx";
    public static final String JSON_DATA_PATH = PROJECT_PATH + "/src/test/resources/testdata/TestData.json";
    public static final String CSV_DATA_PATH = PROJECT_PATH + "/src/test/resources/testdata/TestData.csv";
    public static final String UPLOAD_FILE_PATH = PROJECT_PATH + "/pom.xml"; // using pom as sample file
    public static final int EXPLICIT_WAIT = 15;
}