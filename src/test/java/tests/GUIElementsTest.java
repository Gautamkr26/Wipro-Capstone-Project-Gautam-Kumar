package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GUIElementsPage;

public class GUIElementsTest extends BaseTest {

    private GUIElementsPage guiPage;

    @BeforeMethod
    public void pageSetup() {
        guiPage = new GUIElementsPage();
    }

    @Test(priority = 9, groups = {"GUI", "Assignment"}, description = "Validate jQuery Slider movement")
    public void testSlider() {
        // 🚨 FIX: Extract before/after state to mathematically prove movement
        String initialStyle = guiPage.getSliderStyleAttribute();
        guiPage.moveSlider();
        String finalStyle = guiPage.getSliderStyleAttribute();
        
        Assert.assertNotEquals(initialStyle, finalStyle, "Slider failed to move! CSS Style remained unchanged.");
    }

    @Test(priority = 10, groups = {"GUI", "Assignment"}, description = "Validate Simple Alert Handling")
    public void testSimpleAlert() {
        Assert.assertTrue(guiPage.handleSimpleAlert().toLowerCase().contains("alert box"), "Simple Alert failed");
    }

    @Test(priority = 11, groups = {"GUI", "Assignment"}, description = "Validate Confirmation Alert Acceptance")
    public void testConfirmationAlert() {
        Assert.assertTrue(guiPage.handleConfirmAlert(true).toLowerCase().contains("pressed ok"), "Confirm Alert failed");
    }

    @Test(priority = 12, groups = {"GUI", "Assignment"}, description = "Validate Prompt Alert Text Injection")
    public void testPromptAlert() {
        Assert.assertTrue(guiPage.handlePromptAlert("SDET Test").contains("SDET Test"), "Prompt Alert failed");
    }

    @Test(priority = 13, groups = {"GUI", "Assignment"}, description = "Validate Mouse Hover Action")
    public void testMouseHover() {
        guiPage.performHover();
    }

    @Test(priority = 14, groups = {"GUI", "Assignment"}, description = "Validate Double Click Action")
    public void testDoubleClick() {
        guiPage.performDoubleClick();
        Assert.assertTrue(guiPage.getDoubleClickFieldValue().length() > 0, "Double Click failed");
    }

    @Test(priority = 15, groups = {"GUI", "Assignment"}, description = "Validate Context / Right Click Action")
    public void testRightClick() {
        guiPage.performRightClick();
    }

    @Test(priority = 16, groups = {"GUI", "Assignment"}, description = "Validate Drag and Drop Action")
    public void testDragAndDrop() {
        guiPage.performDragAndDrop();
        Assert.assertEquals(guiPage.getDropTargetText(), "Dropped!", "Drag and drop failed");
    }
}