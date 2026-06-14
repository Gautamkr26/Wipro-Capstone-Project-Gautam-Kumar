package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    public static ExtentTest getTest() { 
        return test.get(); 
    }

    @Override
    public void onTestStart(ITestResult res) { 
        test.set(ExtentManager.getInstance().createTest(res.getMethod().getMethodName())); 
    }
    
    @Override
    public void onTestSuccess(ITestResult res) { 
        test.get().log(Status.PASS, "Test Passed");
        test.get().addScreenCaptureFromBase64String(ScreenshotUtils.getBase64(), "Pass Screenshot"); 
    }
    
    @Override
    public void onTestFailure(ITestResult res) { 
        test.get().fail(res.getThrowable()); 
        test.get().addScreenCaptureFromBase64String(ScreenshotUtils.getBase64(), "Failure Screenshot"); 
    }
    
    @Override
    public void onTestSkipped(ITestResult res) { 
        test.get().log(Status.SKIP, "Test Skipped"); 
    }
    
    @Override
    public void onFinish(ITestContext ctx) { 
        ExtentManager.getInstance().flush(); 
    }
}