package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GUIElementsPage;
import utils.TestDataGenerator;

public class SmokeTest extends BaseTest {

    private GUIElementsPage guiPage;

    @BeforeMethod
    public void pageSetup() {
        guiPage = new GUIElementsPage();
    }

    @Test(priority = 1, groups = {"Smoke", "Assignment"})
    public void testTextFields() {
        guiPage.fillUserDetails(TestDataGenerator.getFullName(), TestDataGenerator.getEmail(), TestDataGenerator.getPhone(), TestDataGenerator.getAddress());
        Assert.assertEquals(guiPage.getEnteredName(), TestDataGenerator.getFullName(), "Name mismatch");
    }

    @Test(priority = 2, groups = {"Smoke", "Assignment"})
    public void testGenderRadioButton() {
        guiPage.selectGenderMale();
        Assert.assertTrue(guiPage.isMaleSelected(), "Male radio button not selected");
    }

    @Test(priority = 3, groups = {"Smoke", "Assignment"})
    public void testDaysCheckboxes() {
        guiPage.selectDays();
        Assert.assertTrue(guiPage.areDaysSelected(), "Days not selected");
    }

    @Test(priority = 4, groups = {"Smoke", "Assignment"})
    public void testCountryDropdown() {
        guiPage.selectCountry("India");
        Assert.assertEquals(guiPage.getSelectedCountry(), "India", "Country Dropdown failed");
    }

    @Test(priority = 5, groups = {"Smoke", "Assignment"})
    public void testColorsDropdown() {
        guiPage.selectColor("Blue");
    }

    @Test(priority = 6, groups = {"Smoke", "Assignment"})
    public void testSortedListDropdown() {
        guiPage.selectSortedList("Cat");
    }

    @Test(priority = 7, groups = {"Smoke", "Assignment"})
    public void testDatePicker() {
        guiPage.selectDate("12/31/2026");
    }

    @Test(priority = 8, groups = {"Smoke", "Assignment"})
    public void testFileUpload() {
        guiPage.uploadFile();
        Assert.assertTrue(guiPage.getUploadStatus().contains("pom.xml"), "Upload failed");
    }
}