package utils;

import factory.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {

    private static WebDriverWait getWait() {
        int timeout = 15;
        try { timeout = Integer.parseInt(ConfigReader.get("timeout")); } catch (Exception e) {}
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeout));
    }

    public static void waitForVisible(WebElement element) { getWait().until(ExpectedConditions.visibilityOf(element)); }
    public static void waitForClickable(WebElement element) { getWait().until(ExpectedConditions.elementToBeClickable(element)); }
    public static void waitForAlert() { getWait().until(ExpectedConditions.alertIsPresent()); }
    
    // 🔥 FIX: Restored for older test compatibility
    public static void waitForPageLoad() {
        getWait().until((ExpectedCondition<Boolean>) wd -> 
            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    // 🔥 FIX: Restored for older test compatibility
    public static boolean isDisplayed(WebElement element) {
        try { waitForVisible(element); return element.isDisplayed(); } catch (Exception e) { return false; }
    }

    // --- Backward Compatibility Aliases (Important for your old files) ---
    public static void waitForElementVisible(WebElement element) { waitForVisible(element); }
    public static void waitForElementClickable(WebElement element) { waitForClickable(element); }
    public static boolean isElementDisplayed(WebElement element) { return isDisplayed(element); }
}