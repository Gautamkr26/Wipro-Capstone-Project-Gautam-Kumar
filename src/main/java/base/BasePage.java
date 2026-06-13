package base;
import factory.DriverFactory;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public BasePage() { PageFactory.initElements(DriverFactory.getDriver(), this); }
}