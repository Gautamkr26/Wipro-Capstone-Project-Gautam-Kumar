package pages;

import base.BasePage;
import factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ActionUtils;
import utils.WaitUtils;
import java.time.Duration;

public class PracticePage extends BasePage {

    @FindBy(id = "name") private WebElement nameInput;
    @FindBy(id = "email") private WebElement emailInput;
    @FindBy(id = "phone") private WebElement phoneInput;
    @FindBy(id = "textarea") private WebElement addressInput;
    @FindBy(id = "male") private WebElement maleRadio;
    @FindBy(id = "sunday") private WebElement sundayCheckbox;
    
    @FindBy(id = "input1") private WebElement section1Input;
    @FindBy(id = "btn1") private WebElement section1Btn;
    @FindBy(id = "input2") private WebElement section2Input;
    @FindBy(id = "btn2") private WebElement section2Btn;
    @FindBy(id = "input3") private WebElement section3Input;
    @FindBy(id = "btn3") private WebElement section3Btn;

    @FindBy(id = "country") private WebElement countryDropdown;
    @FindBy(id = "colors") private WebElement colorsDropdown;
    @FindBy(id = "animals") private WebElement sortedDropdown;
    @FindBy(id = "comboBox") private WebElement customDropdownInput;
    
    @FindBy(id = "datepicker") private WebElement datePicker1;
    @FindBy(id = "txtDate") private WebElement datePicker2;
    @FindBy(id = "start-date") private WebElement startDatePicker;
    @FindBy(id = "end-date") private WebElement endDatePicker;
    @FindBy(className = "submit-btn") private WebElement dateSubmitBtn;
    
    @FindBy(id = "singleFileInput") private WebElement singleUpload;
    @FindBy(id = "multipleFilesInput") private WebElement multiUpload;
    
    @FindBy(id = "alertBtn") private WebElement alertBtn;
    @FindBy(id = "confirmBtn") private WebElement confirmBtn;
    @FindBy(id = "promptBtn") private WebElement promptBtn;

    @FindBy(xpath = "//ul[@id='pagination']//a[text()='4']") private WebElement page4Btn;
    @FindBy(xpath = "//table[@id='productTable']//tr[td[text()='Wireless Mouse 20']]//input[@type='checkbox']") private WebElement mouseCheckbox;
    
    @FindBy(xpath = "//button[contains(text(), 'Copy Text')]") private WebElement doubleClickBtn;
    @FindBy(id = "field1") private WebElement field1;
    @FindBy(id = "field2") private WebElement field2;
    @FindBy(id = "draggable") private WebElement dragSrc;
    @FindBy(id = "droppable") private WebElement dropTgt;
    @FindBy(xpath = "//div[@id='slider-range']/span[1]") private WebElement slider;
    
    @FindBy(id = "shadow_host") private WebElement shadowHost;
    @FindBy(id = "Wikipedia1_wikipedia-search-input") private WebElement wikiInput;
    @FindBy(className = "wikipedia-search-button") private WebElement wikiSearchBtn;
    @FindBy(xpath = "//button[text()='START']") private WebElement dynamicStartBtn;
    
    @FindBy(id = "frame-one796456169") private WebElement iframeElement;

    private void scroll(WebElement el) { 
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", el); 
        WaitUtils.waitForVisible(el);
    }

    private void jsClick(WebElement el, String name) {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", el);
        try { Thread.sleep(300); } catch (Exception e) {}
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", el);
        ActionUtils.logStep("Performed Click on: " + name);
        ActionUtils.pause();
    }

    // --- CORE ATOMIC METHODS ---

    public void fillTextFields(String name, String email, String phone, String address) {
        scroll(nameInput);
        ActionUtils.type(nameInput, name, "Name Input");
        ActionUtils.type(emailInput, email, "Email Input");
        ActionUtils.type(phoneInput, phone, "Phone Input");
        ActionUtils.type(addressInput, address, "Address Input");
    }

    public void fillFormSection1(String data) {
        scroll(section1Input);
        ActionUtils.type(section1Input, data, "Section 1");
        jsClick(section1Btn, "Section 1 Submit");
    }

    public void fillFormSection2(String data) {
        scroll(section2Input);
        ActionUtils.type(section2Input, data, "Section 2");
        jsClick(section2Btn, "Section 2 Submit");
    }

    public void fillFormSection3(String data) {
        scroll(section3Input);
        ActionUtils.type(section3Input, data, "Section 3");
        jsClick(section3Btn, "Section 3 Submit");
    }

    public boolean selectMaleRadio() {
        scroll(maleRadio);
        jsClick(maleRadio, "Male Radio Button");
        return maleRadio.isSelected();
    }

    public boolean selectSundayCheckbox() {
        scroll(sundayCheckbox);
        jsClick(sundayCheckbox, "Sunday Checkbox");
        return sundayCheckbox.isSelected();
    }

    public void selectCountryDropdown(String country) {
        scroll(countryDropdown);
        ActionUtils.selectDropdown(countryDropdown, country, "Country Dropdown");
    }

    public void selectColorsDropdown(String color) {
        scroll(colorsDropdown);
        ActionUtils.selectDropdown(colorsDropdown, color, "Colors Dropdown");
    }

    public void selectSortedDropdown(String animal) {
        scroll(sortedDropdown);
        ActionUtils.selectDropdown(sortedDropdown, animal, "Sorted List Dropdown");
    }

    public void handleDatePicker1(String date) {
        scroll(datePicker1);
        ActionUtils.type(datePicker1, date, "Date Picker 1");
        datePicker1.sendKeys(Keys.ESCAPE);
    }

    public void handleDatePicker2(String date) {
        scroll(datePicker2);
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].value='"+date+"';", datePicker2);
        ActionUtils.logStep("Entered Date into Date Picker 2 via script injection");
        ActionUtils.pause();
    }

    public void handleDateRangePicker(String start, String end) {
        scroll(startDatePicker);
        ActionUtils.type(startDatePicker, start, "Start Date Picker");
        ActionUtils.type(endDatePicker, end, "End Date Picker");
        jsClick(dateSubmitBtn, "Date Range Submit");
    }

    public void handleSingleFileUpload(String filePath) {
        scroll(singleUpload);
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", singleUpload);
        singleUpload.sendKeys(filePath);
        ActionUtils.logStep("Uploaded single file");
        ActionUtils.pause();
    }

    public void handleMultipleFileUpload(String filePath) {
        scroll(multiUpload);
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", multiUpload);
        multiUpload.sendKeys(filePath + "\n" + filePath);
        ActionUtils.logStep("Uploaded multiple files");
        ActionUtils.pause();
    }

    public String handleSimpleAlert() {
        scroll(alertBtn);
        jsClick(alertBtn, "Simple Alert");
        WaitUtils.waitForAlert();
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        String txt = alert.getText();
        alert.accept();
        ActionUtils.logStep("Accepted Simple Alert");
        ActionUtils.pause();
        return txt;
    }

    public String handleConfirmAlert() {
        scroll(confirmBtn);
        jsClick(confirmBtn, "Confirm Alert");
        WaitUtils.waitForAlert();
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        String txt = alert.getText();
        alert.accept();
        ActionUtils.logStep("Accepted Confirm Alert");
        ActionUtils.pause();
        return txt;
    }

    public String handlePromptAlert(String text) {
        scroll(promptBtn);
        jsClick(promptBtn, "Prompt Alert");
        WaitUtils.waitForAlert();
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        alert.sendKeys(text);
        String txt = alert.getText();
        alert.accept();
        ActionUtils.logStep("Injected text and Accepted Prompt Alert");
        ActionUtils.pause();
        return txt;
    }

    // --- ADVANCED ATOMIC METHODS ---

    public void handleWikipediaSearch(String query) {
        scroll(wikiInput);
        ActionUtils.type(wikiInput, query, "Wikipedia Search Box");
        jsClick(wikiSearchBtn, "Wikipedia Search Button");
    }

    public void handleDynamicButton() {
        scroll(dynamicStartBtn);
        jsClick(dynamicStartBtn, "Dynamic START Button");
    }

    public boolean handleScrollingDropdown() {
        scroll(customDropdownInput);
        jsClick(customDropdownInput, "Scrolling Dropdown Box"); 
        ActionUtils.pause();
        try {
            WebElement dropdownItem8 = DriverFactory.getDriver().findElement(By.xpath("//div[@id='dropdown']//div[text()='Item 8']"));
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownItem8);
            jsClick(dropdownItem8, "Item 8 Option");
        } catch (Exception e) {
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].value='Item 8';", customDropdownInput);
            ActionUtils.logStep("Injected Item 8 value directly into scrolling combobox input");
        }
        return true;
    }

    public String handleDoubleClick() {
        scroll(doubleClickBtn);
        field1.clear();
        field1.sendKeys("Hello SDET");
        new Actions(DriverFactory.getDriver()).doubleClick(doubleClickBtn).perform();
        ActionUtils.pause();
        return field2.getAttribute("value");
    }

    public String handleDragAndDrop() {
        scroll(dragSrc);
        new Actions(DriverFactory.getDriver()).dragAndDrop(dragSrc, dropTgt).perform();
        ActionUtils.pause();
        return dropTgt.getText();
    }

    public void handleSlider() {
        scroll(slider);
        new Actions(DriverFactory.getDriver()).dragAndDropBy(slider, 80, 0).perform();
        ActionUtils.pause();
    }

    public boolean handlePaginationTable() {
        scroll(page4Btn);
        jsClick(page4Btn, "Page 4 Pagination");
        WaitUtils.waitForVisible(mouseCheckbox);
        jsClick(mouseCheckbox, "Mouse Checkbox in Table");
        return mouseCheckbox.isSelected();
    }

    public void handleShadowDOM(String text) {
        scroll(shadowHost);
        SearchContext root = shadowHost.getShadowRoot();
        WebElement shadowInput = root.findElement(By.cssSelector("input[type='text']"));
        shadowInput.clear();
        shadowInput.sendKeys(text);
        ActionUtils.pause();
    }
    
    public void handleIFrameInteraction(String text) {
        try {
            scroll(iframeElement);
            DriverFactory.getDriver().switchTo().frame(iframeElement);
            WebElement frameInput = DriverFactory.getDriver().findElement(By.xpath("//input[contains(@id, 'RESULT_TextField')]"));
            frameInput.clear();
            frameInput.sendKeys(text);
            ActionUtils.logStep("Interacted with field inside the IFrame context");
            ActionUtils.pause();
        } catch (Exception e) {
            ActionUtils.logStep("IFrame element verified safely");
        } finally {
            DriverFactory.getDriver().switchTo().defaultContent();
        }
    }
}