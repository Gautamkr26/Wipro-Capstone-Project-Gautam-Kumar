package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GUIElementsPage;

public class RegressionTest extends BaseTest {

    private GUIElementsPage guiPage;

    @BeforeMethod
    public void pageSetup() {
        guiPage = new GUIElementsPage();
    }

    @Test(priority = 17, groups = {"Regression", "Assignment"})
    public void testWebTableValidation() {
        Assert.assertTrue(guiPage.getTableRowsCount() > 0, "Web Table is empty");
    }

    @Test(priority = 18, groups = {"Regression", "Assignment"})
    public void testDynamicElements() {
        Assert.assertTrue(guiPage.verifyDynamicElementsLoaded(), "Dynamic element sync failed");
    }

    @Test(priority = 19, groups = {"Regression", "Assignment"})
    public void testScrolling() {
        guiPage.performScrolling();
    }
}