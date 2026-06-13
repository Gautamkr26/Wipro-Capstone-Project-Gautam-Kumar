package pages;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ActionUtils;
import utils.JavaScriptUtils;
import utils.WaitUtils;

import java.util.List;

public class AdvancedPracticePage extends BasePage {
    private static final Logger log = LogManager.getLogger(AdvancedPracticePage.class);

    // 1. Date Picker 3 (Date Range)
    @FindBy(id = "start-date") private WebElement startDate;
    @FindBy(id = "end-date") private WebElement endDate;
    @FindBy(xpath = "//button[text()='Submit Date']") private WebElement submitDateBtn;
    @FindBy(id = "result-date") private WebElement dateValidationText;

    // 2. Upload Files Section
    @FindBy(id = "multipleFilesInput") private WebElement multiUploadInput;
    @FindBy(xpath = "//button[text()='Upload Multiple']") private WebElement multiUploadBtn;
    @FindBy(id = "upload-status") private WebElement uploadStatusMsg;

    // 3. Pagination Web Table
    @FindBy(xpath = "//table[@id='productTable']//tr") private List<WebElement> tableRows;
    @FindBy(xpath = "//table[@id='productTable']//td//input[@type='checkbox']") private List<WebElement> tableCheckboxes;
    @FindBy(xpath = "//ul[@id='pagination']//li/a") private List<WebElement> paginationLinks;

    // 4. Double Click Section
    @FindBy(xpath = "//button[contains(text(), 'Copy Text')]") private WebElement doubleClickBtn;
    @FindBy(id = "field1") private WebElement field1;
    @FindBy(id = "field2") private WebElement field2;

    // 5. Slider Section
    @FindBy(css = "#slider span") private WebElement sliderHandle;
    @FindBy(id = "amount") private WebElement priceRangeText;

    // 6. SVG Elements
    @FindBy(xpath = "//*[local-name()='svg']//*[local-name()='circle']") private WebElement svgCircle;
    @FindBy(xpath = "//*[local-name()='svg']//*[local-name()='rect']") private WebElement svgRect;
    @FindBy(xpath = "//*[local-name()='svg']//*[local-name()='polygon']") private WebElement svgTriangle;

    // 7. Scrolling Dropdown
    @FindBy(id = "scrolling-dropdown") private WebElement scrollDropdownDiv;
    @FindBy(xpath = "//div[@id='scrolling-dropdown']//li") private List<WebElement> scrollingItems;

    // 8. Mobile Labels & 9. Laptop Links
    @FindBy(xpath = "//span[contains(text(), 'Samsung')]") private WebElement samsungLabel;
    @FindBy(xpath = "//a[contains(text(), 'Apple')]") private WebElement appleLink;
    @FindBy(xpath = "//a[contains(text(), 'Dell')]") private WebElement dellLink;

    // 10. Broken Links Section
    @FindBy(xpath = "//div[@id='broken-links']//a") private List<WebElement> allBrokenSectionLinks;


    // =====================================
    // --- 1. DATE PICKER ACTIONS ---
    // =====================================
    public String selectDateRange(String start, String end) {
        WaitUtils.waitForElementVisible(startDate);
        ActionUtils.type(startDate, start, "Start Date");
        ActionUtils.type(endDate, end, "End Date");
        ActionUtils.click(submitDateBtn, "Submit Date Button");
        return dateValidationText.getText();
    }

    // =====================================
    // --- 2. MULTI-FILE UPLOAD ACTIONS ---
    // =====================================
    public String performMultiUpload(String[] filePaths) {
        ActionUtils.uploadMultipleFiles(multiUploadInput, filePaths, "Multi File Upload");
        ActionUtils.click(multiUploadBtn, "Upload Button");
        return uploadStatusMsg.getText();
    }

    // =====================================
    // --- 3. PAGINATION ACTIONS ---
    // =====================================
    public AdvancedPracticePage navigateToPage(String pageNum) {
        JavaScriptUtils.scrollSmoothlyToElement(paginationLinks.get(0));
        for (WebElement link : paginationLinks) {
            if (link.getText().equals(pageNum)) {
                ActionUtils.click(link, "Page " + pageNum);
                break;
            }
        }
        return this;
    }
    public void selectAllCheckboxesOnCurrentPage() {
        for (WebElement checkbox : tableCheckboxes) {
            if (!checkbox.isSelected()) ActionUtils.click(checkbox, "Table Checkbox");
        }
    }
    public int getVisibleRowCount() { return tableRows.size() - 1; }

    // =====================================
    // --- 4. DOUBLE CLICK ACTIONS ---
    // =====================================
    public String validateDoubleClickCopy() {
        JavaScriptUtils.scrollSmoothlyToElement(doubleClickBtn);
        ActionUtils.doubleClick(doubleClickBtn, "Copy Text Button");
        return field2.getAttribute("value");
    }
    public void setField1Value(String text) { ActionUtils.type(field1, text, "Field 1"); }

    // =====================================
    // --- 5. SLIDER ACTIONS ---
    // =====================================
    public String getPriceRangeText() { return priceRangeText.getText(); }
    public AdvancedPracticePage moveSliderToRight(int offset) {
        JavaScriptUtils.scrollSmoothlyToElement(sliderHandle);
        ActionUtils.moveSlider(sliderHandle, offset);
        return this;
    }

    // =====================================
    // --- 6. SVG ACTIONS ---
    // =====================================
    public void interactWithSVGs() {
        JavaScriptUtils.scrollSmoothlyToElement(svgCircle);
        ActionUtils.clickSVG(svgCircle, "SVG Circle");
        ActionUtils.clickSVG(svgRect, "SVG Rectangle");
        ActionUtils.clickSVG(svgTriangle, "SVG Triangle");
    }

    // =====================================
    // --- 7. SCROLLING DROPDOWN ---
    // =====================================
    public String selectFromScrollingDropdown(String targetItem) {
        JavaScriptUtils.scrollSmoothlyToElement(scrollDropdownDiv);
        for (WebElement item : scrollingItems) {
            if (item.getText().equals(targetItem)) {
                JavaScriptUtils.scrollSmoothlyToElement(item); // Scrolls *inside* the div
                ActionUtils.click(item, "Scrolling Item: " + targetItem);
                return item.getText();
            }
        }
        return null;
    }

    // =====================================
    // --- 8 & 9. LABELS & LAPTOPS ---
    // =====================================
    public boolean areMobileLabelsVisible() { return WaitUtils.isElementDisplayed(samsungLabel); }
    public String clickAppleLink() {
        ActionUtils.click(appleLink, "Apple Laptop Link");
        return appleLink.getAttribute("href");
    }

    // =====================================
    // --- 10. BROKEN LINKS ---
    // =====================================
    public List<WebElement> getBrokenSectionLinks() {
        return allBrokenSectionLinks;
    }
}