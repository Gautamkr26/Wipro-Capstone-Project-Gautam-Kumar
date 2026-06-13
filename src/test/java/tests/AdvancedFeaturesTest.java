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

    @Test(priority = 1) 
    public void formSectionsTest() { 
        page.testForms(); 
    }

    @Test(priority = 2) 
    public void hoverTest() { 
        page.testMouseHover(); 
    }

    @Test(priority = 3) 
    public void doubleClickTest() { 
        String result = page.testDoubleClick();
        Assert.assertEquals(result, "Automation Architect", "Text was not copied successfully!");
    }

    @Test(priority = 4) 
    public void dragAndDropTest() { 
        page.testDragAndDrop(); 
    }

    @Test(priority = 5) 
    public void sliderTest() { 
        page.testSlider(); 
    }

    @Test(priority = 6) 
    public void svgTest() { 
        page.testSVGs(); 
    }

    @Test(priority = 7) 
    public void shadowDOMTest() { 
        page.testShadowDOM("Enterprise Framework"); 
    }

    @Test(priority = 8) 
    public void paginationTableTest() { 
        page.testPagination(); 
    }
}