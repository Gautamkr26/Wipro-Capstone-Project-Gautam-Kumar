package utils;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();
    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties")) {
            properties.load(fis);
        } catch (Exception e) { throw new RuntimeException("Config file not found!"); }
    }
    public static String get(String key) { return System.getProperty(key) != null ? System.getProperty(key) : properties.getProperty(key); }
    public static boolean isDemoMode() { return Boolean.parseBoolean(get("demo.mode")); }
}