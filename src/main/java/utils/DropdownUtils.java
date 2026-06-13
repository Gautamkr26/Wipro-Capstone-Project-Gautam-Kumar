package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class DropdownUtils {
    public static void selectByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    public static String getFirstSelectedOption(WebElement element) {
        return new Select(element).getFirstSelectedOption().getText();
    }

    public static int getOptionsCount(WebElement element) {
        return new Select(element).getOptions().size();
    }
    
    public static List<WebElement> getAllOptions(WebElement element) {
        return new Select(element).getOptions();
    }
}