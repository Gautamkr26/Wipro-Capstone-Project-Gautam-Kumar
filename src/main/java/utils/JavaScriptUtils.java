package utils;

import factory.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {
    private static JavascriptExecutor getJs() {
        return (JavascriptExecutor) DriverFactory.getDriver();
    }

    public static void clickElementByJS(WebElement element) {
        getJs().executeScript("arguments[0].click();", element);
    }

    public static void scrollSmoothlyToElement(WebElement element) {
        getJs().executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public static void highlightElement(WebElement element) {
        // Flash element with yellow background and red border for high visibility
        getJs().executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);
    }
    
    public static String getElementValueByJS(WebElement element) {
        return getJs().executeScript("return arguments[0].value;", element).toString();
    }
}