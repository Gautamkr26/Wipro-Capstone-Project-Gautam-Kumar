package utils;

import factory.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ScreenshotUtils {

    public static String getBase64() { 
        return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64); 
    }

    // --- Backward Compatibility ---
    public static String getBase64Image() { 
        return getBase64(); 
    }

    public static void captureDemoActionScreenshot(String actionName) {
        if (listeners.TestListener.getTest() != null) {
            listeners.TestListener.getTest().info("Action Visual: " + actionName, 
                MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
        }
    }
}