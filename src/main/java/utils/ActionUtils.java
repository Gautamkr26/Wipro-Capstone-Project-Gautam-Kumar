package utils;

import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class ActionUtils {
    
    private static final Logger log = LogManager.getLogger(ActionUtils.class);

    // 1.5 second pause for visual observation
    public static void pause() { 
        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); } 
    }

    public static void logStep(String msg) {
        System.out.println("INFO: " + msg);
        log.info(msg);
        // This pushes the exact step into the TestNG/Extent Report HTML
        Reporter.log("✅ " + msg + "<br>", true); 
    }

    private static void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);
        try { Thread.sleep(300); } catch (InterruptedException e) {}
        js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
    }

    public static void type(WebElement element, String text, String fieldName) {
        WaitUtils.waitForVisible(element);
        highlightElement(element);
        element.clear();
        element.sendKeys(text);
        logStep("Typed '" + text + "' into " + fieldName);
        pause();
    }

    public static void click(WebElement element, String elementName) {
        WaitUtils.waitForClickable(element);
        highlightElement(element);
        element.click();
        logStep("Clicked on " + elementName);
        pause();
    }

    public static void selectDropdown(WebElement element, String text, String dropdownName) {
        WaitUtils.waitForVisible(element);
        highlightElement(element);
        new Select(element).selectByVisibleText(text);
        logStep("Selected '" + text + "' from " + dropdownName);
        pause();
    }
    
}