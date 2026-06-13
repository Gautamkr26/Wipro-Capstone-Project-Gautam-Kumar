package pages;

import base.BasePage;
import constants.FrameworkConstants;
import factory.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.ActionUtils;
import utils.ConfigReader;
import utils.DropdownUtils;
import utils.JavaScriptUtils;
import utils.ScreenshotUtils;
import utils.WaitUtils;

import java.io.File;
import java.util.List;

public class GUIElementsPage extends BasePage {

    // --- FORM LOCATORS ---
    @FindBy(id = "name") private WebElement nameInput;
    @FindBy(id = "email") private WebElement emailInput;
    @FindBy(id = "phone") private WebElement phoneInput;
    @FindBy(id = "textarea") private WebElement addressInput;
    @FindBy(id = "male") private WebElement maleRadioBtn;
    @FindBy(id = "sunday") private WebElement sundayCheckbox;
    @FindBy(id = "monday") private WebElement mondayCheckbox;
    
    // --- DROPDOWNS & DATES ---
    @FindBy(id = "country") private WebElement countryDropdown;
    @FindBy(id = "colors") private WebElement colorsDropdown;
    @FindBy(id = "animals") private WebElement sortedDropdown;
    @FindBy(id = "datepicker") private WebElement datePickerInput;

    // --- ALERTS & UPLOADS ---
    @FindBy(id = "singleFileInput") private WebElement fileUploadBtn;
    @FindBy(id = "alertBtn") private WebElement alertBtn;
    @FindBy(id = "confirmBtn") private WebElement confirmBtn;
    @FindBy(id = "promptBtn") private WebElement promptBtn;
    @FindBy(id = "demo") private WebElement alertStatusText;

    // --- COMPLEX MOUSE ACTIONS ---
    @FindBy(id = "draggable") private WebElement sourceElement;
    @FindBy(id = "droppable") private WebElement targetElement;
    
    // 🚨 FIX: Resilient CSS Locator for jQuery Slider
    @FindBy(css = "#slider span.ui-slider-handle") private WebElement slider;
    
    @FindBy(xpath = "//button[contains(text(), 'Copy Text')]") private WebElement doubleClickBtn;
    @FindBy(id = "field2") private WebElement doubleClickField;
    @FindBy(className = "dropbtn") private WebElement hoverElement;
    
    // --- TABLES ---
    @FindBy(xpath = "//table[@name='BookTable']") private WebElement bookTable;
    @FindBy(xpath = "//table[@name='BookTable']//tr") private List<WebElement> tableRows;

    // ==========================================
    // --- ACTIONS ---
    // ==========================================

    public GUIElementsPage fillUserDetails(String name, String email, String phone, String address) {
        WaitUtils.waitForElementVisible(nameInput);
        ActionUtils.slowType(nameInput, name, "Name Textbox");
        ActionUtils.slowType(emailInput, email, "Email Textbox");
        ActionUtils.slowType(phoneInput, phone, "Phone Textbox");
        ActionUtils.slowType(addressInput, address, "Address Textarea");
        return this;
    }

    public String getEnteredName() { return nameInput.getAttribute("value"); }

    public GUIElementsPage selectGenderMale() {
        ActionUtils.slowClick(maleRadioBtn, "Male Radio Button");
        return this;
    }
    public boolean isMaleSelected() { return maleRadioBtn.isSelected(); }

    public GUIElementsPage selectDays() {
        ActionUtils.slowClick(sundayCheckbox, "Sunday Checkbox");
        ActionUtils.slowClick(mondayCheckbox, "Monday Checkbox");
        return this;
    }
    public boolean areDaysSelected() { return sundayCheckbox.isSelected() && mondayCheckbox.isSelected(); }

    public GUIElementsPage selectCountry(String countryName) {
        WaitUtils.waitForElementVisible(countryDropdown);
        DropdownUtils.selectByVisibleText(countryDropdown, countryName);
        ActionUtils.observeExecution();
        return this;
    }
    public String getSelectedCountry() { return DropdownUtils.getFirstSelectedOption(countryDropdown); }

    public GUIElementsPage selectColor(String color) {
        WaitUtils.waitForElementVisible(colorsDropdown);
        DropdownUtils.selectByVisibleText(colorsDropdown, color);
        ActionUtils.observeExecution();
        return this;
    }

    public GUIElementsPage selectSortedList(String animal) {
        WaitUtils.waitForElementVisible(sortedDropdown);
        DropdownUtils.selectByVisibleText(sortedDropdown, animal);
        ActionUtils.observeExecution();
        return this;
    }

    public GUIElementsPage selectDate(String date) {
        WaitUtils.waitForElementVisible(datePickerInput);
        ActionUtils.slowType(datePickerInput, date, "Date Picker Input");
        return this;
    }

    // 🚨 FIX: Safe Alert Handling Sequence (Extract -> Accept -> Screenshot)
    public String handleSimpleAlert() {
        ActionUtils.clickTriggersAlert(alertBtn, "Simple Alert Button");
        WaitUtils.waitForAlert();
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        String text = alert.getText();
        ActionUtils.observeExecution(); 
        alert.accept();
        ScreenshotUtils.captureDemoActionScreenshot("Handled_Simple_Alert");
        return text;
    }

    public String handleConfirmAlert(boolean accept) {
        ActionUtils.clickTriggersAlert(confirmBtn, "Confirm Alert Button");
        WaitUtils.waitForAlert();
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        ActionUtils.observeExecution();
        if (accept) alert.accept(); else alert.dismiss();
        ScreenshotUtils.captureDemoActionScreenshot("Handled_Confirm_Alert");
        return alertStatusText.getText();
    }

    public String handlePromptAlert(String input) {
        ActionUtils.clickTriggersAlert(promptBtn, "Prompt Alert Button");
        WaitUtils.waitForAlert();
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        ActionUtils.observeExecution();
        alert.sendKeys(input);
        alert.accept();
        ScreenshotUtils.captureDemoActionScreenshot("Handled_Prompt_Alert");
        return alertStatusText.getText();
    }

    // 🚨 FIX: Convert relative path to absolute OS path dynamically
    public GUIElementsPage uploadFile() {
        String absolutePath = new File(FrameworkConstants.UPLOAD_FILE_PATH).getAbsolutePath();
        ActionUtils.uploadFile(fileUploadBtn, absolutePath, "File Upload Input");
        return this;
    }

    public String getUploadStatus() {
        return fileUploadBtn.getAttribute("value");
    }

    public GUIElementsPage performDragAndDrop() {
        ActionUtils.dragAndDrop(sourceElement, targetElement);
        return this;
    }
    public String getDropTargetText() { return targetElement.getText(); }

    public GUIElementsPage moveSlider() {
        ActionUtils.moveSlider(slider, 50);
        return this;
    }
    
    // 🚨 FIX: Extract style attribute to validate the slider actually moved
    public String getSliderStyleAttribute() {
        return slider.getAttribute("style");
    }

    public GUIElementsPage performDoubleClick() {
        ActionUtils.doubleClick(doubleClickBtn, "Double Click Button");
        return this;
    }
    public String getDoubleClickFieldValue() { return doubleClickField.getAttribute("value"); }

    public GUIElementsPage performHover() {
        ActionUtils.hoverOverElement(hoverElement, "Hover Dropdown");
        return this;
    }

    public GUIElementsPage performRightClick() {
        ActionUtils.rightClick(doubleClickField, "Right Click Target Area");
        return this;
    }

    public GUIElementsPage performScrolling() {
        JavaScriptUtils.scrollSmoothlyToElement(bookTable);
        JavaScriptUtils.highlightElement(bookTable);
        ActionUtils.observeExecution();
        return this;
    }

    public boolean verifyDynamicElementsLoaded() {
        WaitUtils.waitForElementVisible(DriverFactory.getDriver().findElement(org.openqa.selenium.By.className("wikipedia-icon")));
        return true;
    }

    public int getTableRowsCount() {
        return tableRows.size();
    }
}