package factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static void initDriver(String browser, boolean isHeadless) {
        WebDriver webDriver;
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) options.addArguments("--headless=new", "--window-size=1920,1080");
            webDriver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) options.addArguments("--headless");
            webDriver = new FirefoxDriver(options);
        } else {
            webDriver = new EdgeDriver();
        }
        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }
    public static WebDriver getDriver() { return driver.get(); }
    public static void quitDriver() { if (driver.get() != null) { driver.get().quit(); driver.remove(); } }
}