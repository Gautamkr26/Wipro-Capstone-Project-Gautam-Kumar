package tests;

import base.BaseTest;
import listeners.TestListener;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdvancedPracticePage;
import utils.HttpUtils;
import java.io.File;

public class AdvancedPracticeTest extends BaseTest {
    private AdvancedPracticePage advancedPage;

    @BeforeMethod
    public void pageSetup() {
        advancedPage = new AdvancedPracticePage();
    }

    // =====================================
    // 1. DATE RANGE PICKER TESTS
    // =====================================
    @Test(priority = 1, groups = {"Advanced", "Positive"})
    public void testDateRangePositive() {
        TestListener.getTest().info("Validating Positive Date Range Selection");
        String result = advancedPage.selectDateRange("01/01/2026", "12/31/2026");
        Assert.assertTrue(result.contains("Success"), "Date Range should be accepted.");
    }

    @Test(priority = 2, groups = {"Advanced", "Negative"})
    public void testDateRangeNegative() {
        TestListener.getTest().info("Validating Negative Date Range (End before Start)");
        String result = advancedPage.selectDateRange("12/31/2026", "01/01/2026");
        Assert.assertTrue(result.contains("Error") || result.contains("Invalid"), "Date Range should be rejected.");
    }

    // =====================================
    // 2. MULTI-FILE UPLOAD TESTS
    // =====================================
    @Test(priority = 3, groups = {"Advanced", "Positive"})
    public void testMultipleFileUploadPositive() {
        TestListener.getTest().info("Validating Multiple File Upload functionality");
        String path1 = new File("pom.xml").getAbsolutePath();
        String path2 = new File("testng.xml").getAbsolutePath();
        
        String result = advancedPage.performMultiUpload(new String[]{path1, path2});
        Assert.assertTrue(result.contains("uploaded successfully"), "Upload confirmation missing.");
    }

    @Test(priority = 4, groups = {"Advanced", "Negative"})
    public void testMultipleFileUploadNegative() {
        TestListener.getTest().info("Validating Upload with empty selection");
        String result = advancedPage.performMultiUpload(new String[]{});
        Assert.assertTrue(result.contains("No files"), "System should warn about empty upload.");
    }

    // =====================================
    // 3. PAGINATION WEB TABLE TESTS
    // =====================================
    @Test(priority = 5, groups = {"Advanced", "Positive"})
    public void testPaginationAndCheckboxes() {
        TestListener.getTest().info("Navigating to Page 2 and interacting with Data Table");
        advancedPage.navigateToPage("2");
        advancedPage.selectAllCheckboxesOnCurrentPage();
        Assert.assertTrue(advancedPage.getVisibleRowCount() > 0, "Table should not be empty on Page 2");
    }

    // =====================================
    // 4. DOUBLE CLICK TESTS
    // =====================================
    @Test(priority = 6, groups = {"Advanced", "Positive"})
    public void testDoubleClickCopyAction() {
        TestListener.getTest().info("Validating Double Click Action copies Field1 to Field2");
        advancedPage.setField1Value("Enterprise Automation");
        String copiedText = advancedPage.validateDoubleClickCopy();
        Assert.assertEquals(copiedText, "Enterprise Automation", "Text was not copied successfully!");
    }

    // =====================================
    // 5. SLIDER TESTS
    // =====================================
    @Test(priority = 7, groups = {"Advanced", "Positive"})
    public void testDynamicSlider() {
        TestListener.getTest().info("Moving slider and capturing dynamic price change");
        String initialPrice = advancedPage.getPriceRangeText();
        advancedPage.moveSliderToRight(100);
        String updatedPrice = advancedPage.getPriceRangeText();
        Assert.assertNotEquals(initialPrice, updatedPrice, "Price should update when slider moves.");
    }

    // =====================================
    // 6. SVG TESTS
    // =====================================
    @Test(priority = 8, groups = {"Advanced", "Positive"})
    public void testSVGInteractions() {
        TestListener.getTest().info("Validating interactions with embedded Vector Graphics");
        advancedPage.interactWithSVGs();
        // Implicit assertion: If ActionUtils.clickSVG fails, TestNG will mark this test as failed.
    }

    // =====================================
    // 7. SCROLLING DROPDOWN TESTS
    // =====================================
    @Test(priority = 9, groups = {"Advanced", "Positive"})
    public void testScrollingDropdown() {
        TestListener.getTest().info("Validating selection inside an internal scrolling DIV");
        String selected = advancedPage.selectFromScrollingDropdown("Item 3");
        Assert.assertEquals(selected, "Item 3", "Failed to select item from internal scroll div.");
    }

    // =====================================
    // 8 & 9. LABELS AND LINK TESTS
    // =====================================
    @Test(priority = 10, groups = {"Advanced", "Positive"})
    public void testPeripheralElements() {
        Assert.assertTrue(advancedPage.areMobileLabelsVisible(), "Mobile labels failed to render.");
        
        String linkUrl = advancedPage.clickAppleLink();
        TestListener.getTest().info("Validating target URL HTTP status: " + linkUrl);
        Assert.assertFalse(HttpUtils.isLinkBroken(linkUrl), "Apple Laptop link is broken (404)!");
    }

    // =====================================
    // 10. BROKEN LINKS REPORTING
    // =====================================
    @Test(priority = 11, groups = {"Advanced", "Positive"})
    public void testBrokenLinksDetection() {
        TestListener.getTest().info("Scanning section for HTTP 400/500 broken links");
        int brokenCount = 0;
        for (WebElement link : advancedPage.getBrokenSectionLinks()) {
            String url = link.getAttribute("href");
            if (HttpUtils.isLinkBroken(url)) brokenCount++;
        }
        TestListener.getTest().info("Total Broken Links Detected: " + brokenCount);
        // Note: We don't fail the test if broken links exist, we just report them. 
        // Failing it would cause false-negatives in CI/CD since the test app intentionally hosts broken links.
    }
}