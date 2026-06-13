package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int count = 0;
    int limit = 1; // Number of times to retry a failed test

    @Override
    public boolean retry(ITestResult result) {
        if (count < limit) {
            count++;
            return true;
        }
        return false;
    }
}