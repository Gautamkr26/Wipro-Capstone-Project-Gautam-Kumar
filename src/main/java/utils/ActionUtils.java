package utils;

import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ActionUtils {
    private static final Logger log = LogManager.getLogger(ActionUtils.class);
    private static Actions getActions() { return new Actions(DriverFactory.getDriver()); }

    // 🔥 FIX: Insano jaisi speed ke liye 1.2 second ka aaram (Delay)
    public static void slowDownForObservation() {
        try { Thread.sleep(1200); } catch (Exception e) {} 
    }

    public static void type(WebElement element, String text, String fieldName) {
        logStep("Entering text into: " + fieldName);
        WaitUtils.waitForVisible(element);
        applyDemoHighlight(element);
        element.clear();
        element.sendKeys(text);
        slowDownForObservation(); // Pause
    }

    public static void click(WebElement element, String elementName) {
        logStep("Clicking: " + elementName);
        WaitUtils.waitForClickable(element);
        applyDemoHighlight(element);
        element.click();
        slowDownForObservation(); // Pause
    }

    public static void selectDropdown(WebElement element, String text, String name) {
        logStep("Selecting '" + text + "' from " + name);
        WaitUtils.waitForVisible(element);
        applyDemoHighlight(element);
        new Select(element).selectByVisibleText(text);
        slowDownForObservation(); // Pause
    }

    public static void uploadFile(WebElement element, String path, String name) {
        logStep("Uploading single file to: " + name);
        applyDemoHighlight(element);
        element.sendKeys(path);
        slowDownForObservation(); // Pause
    }

    public static void uploadMultipleFiles(WebElement element, String[] filePaths, String name) {
        logStep("Uploading multiple files to: " + name);
        element.sendKeys(String.join("\n", filePaths));
        slowDownForObservation(); // Pause
    }

    public static void hoverOverElement(WebElement element, String elementName) {
        logStep("Hovering over: " + elementName);
        applyDemoHighlight(element);
        getActions().moveToElement(element).perform();
        slowDownForObservation(); // Pause
    }

    public static void doubleClick(WebElement element, String elementName) {
        logStep("Double Clicking on: " + elementName);
        applyDemoHighlight(element);
        getActions().doubleClick(element).perform();
        slowDownForObservation(); // Pause
    }

    public static void rightClick(WebElement element, String elementName) {
        logStep("Right Clicking on: " + elementName);
        applyDemoHighlight(element);
        getActions().contextClick(element).perform();
    }

    public static void dragAndDrop(WebElement src, WebElement tgt) {
        logStep("Dragging element to target");
        applyDemoHighlight(tgt);
        getActions().dragAndDrop(src, tgt).perform();
        slowDownForObservation(); // Pause
    }

    public static void moveSlider(WebElement slider, int x) {
        logStep("Moving slider by offset: " + x);
        applyDemoHighlight(slider);
        getActions().dragAndDropBy(slider, x, 0).perform();
        slowDownForObservation(); // Pause
    }

    public static void clickSVG(WebElement element, String name) {
        logStep("Clicking SVG: " + name);
        try { element.click(); } catch (Exception e) { ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", element); }
        slowDownForObservation(); // Pause
    }

    public static SearchContext getShadowRoot(WebElement shadowHost) {
        logStep("Piercing Shadow DOM");
        return shadowHost.getShadowRoot();
    }

    public static void logStep(String msg) {
        log.info(msg);
        if (listeners.TestListener.getTest() != null) listeners.TestListener.getTest().info(msg);
    }

    public static void applyDemoHighlight(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            Thread.sleep(300); 
            js.executeScript("arguments[0].style.border=''", element);
        } catch (Exception ignored) {}
    }

    public static void observeExecution() { slowDownForObservation(); }

    // --- Backward Compatibility Aliases ---
    public static void typeLikeHuman(WebElement element, String text, String fieldName) { type(element, text, fieldName); }
    public static void slowType(WebElement element, String text, String fieldName) { type(element, text, fieldName); }
    public static void slowClick(WebElement element, String elementName) { click(element, elementName); }
    public static void clickTriggersAlert(WebElement element, String elementName) { click(element, elementName); }
    public static void selectByText(WebElement element, String text, String name) { selectDropdown(element, text, name); }
}