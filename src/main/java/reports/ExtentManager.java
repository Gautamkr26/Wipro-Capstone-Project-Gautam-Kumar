package reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import utils.ConfigReader;

public class ExtentManager {
    private static ExtentReports extent;
    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/AutomationReport.html");
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", ConfigReader.get("browser"));
            extent.setSystemInfo("Mode", ConfigReader.isDemoMode() ? "DEMO" : "FAST CI/CD");
        }
        return extent;
    }
}