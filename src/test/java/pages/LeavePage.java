package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import base.BasePage;

public class LeavePage extends BasePage {

    public LeavePage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    // Navigation
    By leaveMenu = By.xpath("//span[text()='Leave']");
    By applyTab = By.xpath("//a[text()='Apply']");
    By leaveListTab = By.xpath("//a[text()='Leave List']");

    // Page check
    By applyHeader = By.xpath("//h6[text()='Apply Leave']");

    // Leave form
    By leaveTypeDropdown = By.xpath("//div[contains(@class,'oxd-select-text')]");
    By leaveTypeOptions = By.xpath("//div[@role='listbox']//span");

    By fromDate = By.xpath("(//input[@placeholder='yyyy-mm-dd'])[1]");
    By toDate = By.xpath("(//input[@placeholder='yyyy-mm-dd'])[2]");
    By applyBtn = By.xpath("//button[@type='submit']");

    // Messages
    By successMsg = By.xpath("//p[contains(text(),'Successfully')]");
    By errorMsg = By.xpath("//span[contains(@class,'error')]");

    // Table
    By rows = By.xpath("//div[@class='oxd-table-card']");
    By status = By.xpath(".//div[6]");

    // ================= METHODS =================

    public void goToLeave() {
        click(leaveMenu);
    }

    public void openApplyPage() {
        click(applyTab);
    }

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(applyHeader)).isDisplayed();
    }

    public void selectLeaveType(String type) {
        click(leaveTypeDropdown);
        List<WebElement> options = driver.findElements(leaveTypeOptions);

        for (WebElement opt : options) {
            if (opt.getText().contains(type)) {
                opt.click();
                break;
            }
        }
    }

    public void enterDates(String from, String to) {
        sendKeys(fromDate, from);
        sendKeys(toDate, to);
    }

    public void clickApply() {
        click(applyBtn);
    }

    public boolean isSuccessDisplayed() {
        return driver.findElements(successMsg).size() > 0;
    }

    public void openLeaveList() {
        click(leaveListTab);
    }

    public boolean isLeavePresent() {
        return driver.findElements(rows).size() > 0;
    }

    public String getStatus() {
        return driver.findElements(rows).get(0).findElement(status).getText();
    }

    public boolean isErrorDisplayed() {
        return driver.findElements(errorMsg).size() > 0;
    }
}