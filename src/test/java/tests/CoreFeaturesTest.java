package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PracticePage;
import java.io.File;

public class CoreFeaturesTest extends BaseTest {
    PracticePage page;

    @BeforeMethod 
    public void init() { 
        page = new PracticePage(); 
    }

    @Test(priority = 1) 
    public void formValidationTest() { 
        page.fillUserDetails("Gautam", "test@test.com", "9999999999", "Bangalore"); 
    }
    
    @Test(priority = 2) public void radioButtonAndCheckboxTest() { page.selectRadioAndCheckbox(); }
    @Test(priority = 3) public void dropdownsTest() { page.handleDropdowns(); }
    @Test(priority = 4) public void datePickerTest() { page.enterDate("12/31/2026"); }
    @Test(priority = 5) public void dateRangeTest() { page.enterDateRange("01/01/2026", "12/31/2026"); }
    
    @Test(priority = 6) 
    public void singleFileUploadTest() { 
        String validPath = new File("pom.xml").getAbsolutePath();
        page.uploadSingle(validPath); 
    }
    
    @Test(priority = 7) 
    public void multipleFileUploadTest() { 
        // 🔥 FIX: Using pom.xml twice ensures we don't get "File not found" from wrong testng.xml paths
        String validPath = new File("pom.xml").getAbsolutePath();
        page.uploadMultiple(new String[]{validPath, validPath}); 
    }
    
    @Test(priority = 8) public void alertTest() { Assert.assertTrue(page.handleAlert("simple", null).contains("alert box")); }
    @Test(priority = 9) public void confirmAlertTest() { Assert.assertTrue(page.handleAlert("confirm", null).contains("Press a button")); }
    @Test(priority = 10) public void promptAlertTest() { page.handleAlert("prompt", "SDET Automation"); }
}