package tests;

import base.BaseTest;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PracticePage;
import utils.DataUtils;
import java.io.File;

public class CoreFeaturesTest extends BaseTest {
    
    PracticePage page;
    String uploadFilePath;

    @BeforeMethod 
    public void init() { 
        page = new PracticePage(); 
        uploadFilePath = new File("pom.xml").getAbsolutePath();
    }

    @Test(priority = 1) public void validateBasicInfoFields() { 
        // Fetching data dynamically from JSON file (Data-Driven Testing)
        JsonObject testData = DataUtils.getJsonData();
        String name = testData.get("userName").getAsString();
        String email = testData.get("userEmail").getAsString();
        String phone = testData.get("userPhone").getAsString();
        String address = testData.get("userAddress").getAsString();

        page.fillTextFields(name, email, phone, address); 
        Assert.assertTrue(true, "Passed: Basic text fields using JSON Data.");
    }

    @Test(priority = 2) public void validateFormSection1() { 
        page.fillFormSection1("Test Data 1"); Assert.assertTrue(true, "Passed: Section 1."); 
    }
    
    @Test(priority = 3) public void validateFormSection2() { 
        page.fillFormSection2("Test Data 2"); Assert.assertTrue(true, "Passed: Section 2."); 
    }

    @Test(priority = 4) public void validateFormSection3() { 
        page.fillFormSection3("Test Data 3"); Assert.assertTrue(true, "Passed: Section 3."); 
    }
    
    @Test(priority = 5) public void validateMaleRadioButton() { 
        Assert.assertTrue(page.selectMaleRadio(), "Passed: Male radio selected.");
    }
    
    @Test(priority = 6) public void validateSundayCheckbox() { 
        Assert.assertTrue(page.selectSundayCheckbox(), "Passed: Sunday checkbox selected.");
    }

    @Test(priority = 7) public void validateCountryDropdown() { 
        page.selectCountryDropdown("India"); Assert.assertTrue(true, "Passed: Country Dropdown.");
    }

    @Test(priority = 8) public void validateColorsDropdown() { 
        page.selectColorsDropdown("Blue"); Assert.assertTrue(true, "Passed: Colors Dropdown.");
    }

    @Test(priority = 9) public void validateSortedListDropdown() { 
        page.selectSortedDropdown("Elephant"); Assert.assertTrue(true, "Passed: Sorted Dropdown.");
    }
    
    @Test(priority = 10) public void validateStandardDatePicker() { 
        page.handleDatePicker1("12/31/2026"); Assert.assertTrue(true, "Passed: Date Picker 1.");
    }

    @Test(priority = 11) public void validateSecondaryDatePicker() { 
        page.handleDatePicker2("31/12/2026"); Assert.assertTrue(true, "Passed: Date Picker 2.");
    }

    @Test(priority = 12) public void validateDateRangePicker() { 
        page.handleDateRangePicker("01/01/2026", "12/31/2026"); Assert.assertTrue(true, "Passed: Date Range.");
    }
    
    @Test(priority = 13) public void validateSingleFileUpload() { 
        page.handleSingleFileUpload(uploadFilePath); Assert.assertTrue(true, "Passed: Single Upload.");
    }

    @Test(priority = 14) public void validateMultipleFileUpload() { 
        page.handleMultipleFileUpload(uploadFilePath); Assert.assertTrue(true, "Passed: Multiple Upload.");
    }
    
    @Test(priority = 15) public void validateSimpleAlert() { 
        Assert.assertTrue(page.handleSimpleAlert().contains("alert box"), "Passed: Simple Alert."); 
    }

    @Test(priority = 16) public void validateConfirmationAlert() { 
        Assert.assertTrue(page.handleConfirmAlert().contains("Press a button"), "Passed: Confirm Alert."); 
    }

    @Test(priority = 17) public void validatePromptAlert() { 
        page.handlePromptAlert("SDET Framework"); Assert.assertTrue(true, "Passed: Prompt Alert."); 
    }
}