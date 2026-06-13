package utils;
import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.util.Set;

public class WindowUtils {
    private static final Logger log = LogManager.getLogger(WindowUtils.class);

    public static void switchToNewWindow(String originalWindow) {
        WebDriver driver = DriverFactory.getDriver();
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                log.info("Switched to new window/tab.");
                break;
            }
        }
    }

    public static void closeCurrentAndSwitchToOriginal(String originalWindow) {
        DriverFactory.getDriver().close();
        DriverFactory.getDriver().switchTo().window(originalWindow);
    }
}