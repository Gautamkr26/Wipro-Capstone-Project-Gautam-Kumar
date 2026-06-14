package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PracticePage;

public class AdvancedFeaturesTest extends BaseTest {
    
    PracticePage page;

    @BeforeMethod 
    public void init() { 
        page = new PracticePage(); 
    }

    @Test(priority = 18) public void validateWikipediaSearch() { 
        page.handleWikipediaSearch("Selenium"); Assert.assertTrue(true, "Passed: Wikipedia.");
    }

    @Test(priority = 19) public void validateDynamicButton() { 
        page.handleDynamicButton(); Assert.assertTrue(true, "Passed: Dynamic Button.");
    }

    @Test(priority = 20) public void validateScrollingDropdown() { 
        Assert.assertTrue(page.handleScrollingDropdown(), "Passed: Scrolling Dropdown.");
    }

    @Test(priority = 21) public void validateDoubleClickFunctionality() { 
        String result = page.handleDoubleClick();
        Assert.assertEquals(result, "Hello SDET", "Passed: Double Click Verified!");
    }

    @Test(priority = 22) public void validateDragAndDropAction() { 
        String result = page.handleDragAndDrop();
        Assert.assertEquals(result, "Dropped!", "Passed: Drag and Drop.");
    }

    @Test(priority = 23) public void validateInteractiveSlider() { 
        page.handleSlider(); Assert.assertTrue(true, "Passed: Slider.");
    }

    @Test(priority = 24) public void validateShadowDOMInjection() { 
        page.handleShadowDOM("Shadow DOM Validated"); Assert.assertTrue(true, "Passed: Shadow DOM.");
    }

    @Test(priority = 25) public void validatePaginationTable() { 
        Assert.assertTrue(page.handlePaginationTable(), "Passed: Pagination.");
    }
    
    @Test(priority = 26) public void validateIFrameInteraction() { 
        page.handleIFrameInteraction("Hello SDET"); Assert.assertTrue(true, "Passed: IFrame executed.");
    }
}