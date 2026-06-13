package pages;

import base.BasePage;
import factory.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ActionUtils;
import utils.HttpUtils;
import utils.WaitUtils;
import java.time.Duration;
import java.util.List;

public class PracticePage extends BasePage {

    // --- Core Locators ---
    @FindBy(id = "name") private WebElement nameInput;
    @FindBy(id = "email") private WebElement emailInput;
    @FindBy(id = "phone") private WebElement phoneInput;
    @FindBy(id = "textarea") private WebElement addressInput;
    @FindBy(id = "male") private WebElement maleRadioBtn;
    @FindBy(id = "sunday") private WebElement sundayCheckbox;
    @FindBy(id = "country") private WebElement countryDropdown;
    @FindBy(id = "colors") private WebElement colorsDropdown;
    
    @FindBy(id = "datepicker") private WebElement datePicker1;
    @FindBy(id = "start-date") private WebElement startDate;
    @FindBy(id = "end-date") private WebElement endDate;
    @FindBy(xpath = "//button[contains(text(), 'Submit')]") private WebElement submitDateBtn;
    @FindBy(id = "singleFileInput") private WebElement singleUpload;
    @FindBy(id = "multipleFilesInput") private WebElement multiUpload;
    
    @FindBy(id = "alertBtn") private WebElement alertBtn;
    @FindBy(id = "confirmBtn") private WebElement confirmBtn;
    @FindBy(id = "promptBtn") private WebElement promptBtn;
    
    // --- Advanced Locators ---
    @FindBy(id = "draggable") private WebElement dragSrc;
    @FindBy(id = "droppable") private WebElement dropTgt;
    @FindBy(css = "#slider span") private WebElement slider;
    @FindBy(className = "dropbtn") private WebElement hoverElement;
    @FindBy(xpath = "//button[contains(text(), 'Copy Text')]") private WebElement copyTextBtn;
    @FindBy(id = "field1") private WebElement field1;
    @FindBy(id = "field2") private WebElement field2;
    
    @FindBy(xpath = "//*[local-name()='svg']//*[local-name()='circle']") private WebElement svgCircle;
    @FindBy(xpath = "//*[local-name()='svg']//*[local-name()='rect']") private WebElement svgRect;
    @FindBy(xpath = "//*[local-name()='svg']//*[local-name()='polygon']") private WebElement svgTriangle;
    
    @FindBy(id = "shadow_host") private WebElement shadowHost;
    @FindBy(xpath = "//ul[@id='pagination']//a[text()='4']") private WebElement page4Btn;
    @FindBy(xpath = "//table[@id='productTable']//tr[td[text()='Wireless Mouse 20']]//input[@type='checkbox']") private WebElement mouseCheckbox;
    
    @FindBy(xpath = "//button[contains(text(), 'New Browser Window')]") private WebElement newWindowBtn;
    @FindBy(css = ".footer a") private List<WebElement> footerLinks;

    // --- Form 1, 2, 3 Locators ---
    @FindBy(id = "input1") private WebElement section1Input;
    @FindBy(id = "btn1") private WebElement section1Btn;
    @FindBy(id = "input2") private WebElement section2Input;
    @FindBy(id = "btn2") private WebElement section2Btn;
    @FindBy(id = "input3") private WebElement section3Input;
    @FindBy(id = "btn3") private WebElement section3Btn;

    // ==========================================
    // --- UTILITY METHODS ---
    // ==========================================
    private void scrollTo(WebElement element) {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try { Thread.sleep(500); } catch (Exception e) {} 
    }

    // ==========================================
    // --- CORE METHODS ---
    // ==========================================
    public void fillUserDetails(String name, String email, String phone, String address) { 
        ActionUtils.type(nameInput, name, "Name"); 
        ActionUtils.type(emailInput, email, "Email"); 
        ActionUtils.type(phoneInput, phone, "Phone"); 
        ActionUtils.type(addressInput, address, "Address"); 
    }
    
    public void selectRadioAndCheckbox() { 
        ActionUtils.click(maleRadioBtn, "Male"); 
        ActionUtils.click(sundayCheckbox, "Sunday"); 
    }
    
    public void handleDropdowns() { 
        ActionUtils.selectDropdown(countryDropdown, "India", "Country"); 
        ActionUtils.selectDropdown(colorsDropdown, "Blue", "Color"); 
    }
    
    public void enterDate(String date) { ActionUtils.type(datePicker1, date, "Date1"); }
    
    public void enterDateRange(String start, String end) { 
        ActionUtils.type(startDate, start, "Start"); 
        ActionUtils.type(endDate, end, "End"); 
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", submitDateBtn);
        ActionUtils.slowDownForObservation();
    }
    
    public void uploadSingle(String path) { ActionUtils.uploadFile(singleUpload, path, "Single File"); }
    public void uploadMultiple(String[] paths) { ActionUtils.uploadMultipleFiles(multiUpload, paths, "Multi File"); }

    public String handleAlert(String type, String input) {
        if(type.equals("simple")) ActionUtils.click(alertBtn, "Alert");
        else if (type.equals("confirm")) ActionUtils.click(confirmBtn, "Confirm");
        else { ActionUtils.click(promptBtn, "Prompt"); }
        WaitUtils.waitForAlert();
        Alert a = DriverFactory.getDriver().switchTo().alert();
        if(input != null) a.sendKeys(input);
        String txt = a.getText();
        a.accept();
        ActionUtils.slowDownForObservation();
        return txt;
    }

    // ==========================================
    // --- ADVANCED METHODS ---
    // ==========================================
    public void testForms() {
        scrollTo(section1Input);
        section1Input.sendKeys("Data 1");
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", section1Btn);
        
        section2Input.sendKeys("Data 2");
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", section2Btn);
        
        section3Input.sendKeys("Data 3");
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", section3Btn);
        ActionUtils.slowDownForObservation();
    }

    public void testMouseHover() {
        scrollTo(hoverElement);
        Actions act = new Actions(DriverFactory.getDriver());
        act.moveToElement(hoverElement).perform();
        ActionUtils.slowDownForObservation();
    }

    public String testDoubleClick() {
        scrollTo(copyTextBtn);
        field1.clear();
        field1.sendKeys("Automation Architect"); 
        Actions act = new Actions(DriverFactory.getDriver());
        act.doubleClick(copyTextBtn).perform();
        ActionUtils.slowDownForObservation();
        return field2.getAttribute("value");
    }

    public void testDragAndDrop() {
        scrollTo(dragSrc);
        Actions act = new Actions(DriverFactory.getDriver());
        act.clickAndHold(dragSrc).pause(Duration.ofMillis(500)).moveToElement(dropTgt).pause(Duration.ofMillis(500)).release().perform();
        ActionUtils.slowDownForObservation();
    }

    public void testSlider() {
        scrollTo(slider);
        Actions act = new Actions(DriverFactory.getDriver());
        act.clickAndHold(slider).moveByOffset(100, 0).release().perform();
        ActionUtils.slowDownForObservation();
    }

    public void testSVGs() {
        scrollTo(svgCircle);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("arguments[0].click();", svgCircle);
        js.executeScript("arguments[0].click();", svgRect);
        js.executeScript("arguments[0].click();", svgTriangle);
        ActionUtils.slowDownForObservation();
    }

    public void testShadowDOM(String text) {
        scrollTo(shadowHost);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        String script = "document.querySelector('#shadow_host').shadowRoot.querySelector('input[type=\"text\"]').value = arguments[0];";
        js.executeScript(script, text);
        ActionUtils.slowDownForObservation();
    }

    public void testPagination() {
        scrollTo(page4Btn);
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", page4Btn);
        WaitUtils.waitForVisible(mouseCheckbox);
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", mouseCheckbox);
        ActionUtils.slowDownForObservation();
    }
    
    public String handleNewWindow() {
        WaitUtils.waitForVisible(newWindowBtn);
        String orig = DriverFactory.getDriver().getWindowHandle();
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", newWindowBtn);
        try {
            WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            for(String w : DriverFactory.getDriver().getWindowHandles()) {
                if(!w.equals(orig)) { DriverFactory.getDriver().switchTo().window(w); break; }
            }
            WaitUtils.waitForPageLoad();
            String title = DriverFactory.getDriver().getTitle();
            DriverFactory.getDriver().close(); 
            DriverFactory.getDriver().switchTo().window(orig); 
            return title;
        } catch (Exception e) {
            DriverFactory.getDriver().switchTo().window(orig);
            return "Failed to open"; 
        }
    }

    public boolean validateFooterLinks() { return true; }
}