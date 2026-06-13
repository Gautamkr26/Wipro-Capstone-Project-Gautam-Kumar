package base;
import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import utils.ConfigReader;

public class BaseTest {
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        log.info("Initializing Browser via ThreadLocal...");
        DriverFactory.initDriver(ConfigReader.get("browser"), Boolean.parseBoolean(ConfigReader.get("headless")));
        DriverFactory.getDriver().get(ConfigReader.get("url"));
    }

    @BeforeMethod(alwaysRun = true)
    public void cleanState() {
        log.debug("Cleaning DOM State for Test Isolation");
        DriverFactory.getDriver().manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        log.info("Tearing down WebDriver instance.");
        DriverFactory.quitDriver();
    }
}