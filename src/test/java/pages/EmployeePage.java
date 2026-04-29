package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import base.BasePage;

public class EmployeePage extends BasePage {

    public EmployeePage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    // 🔹 Add Employee
    By addEmployeeTab = By.xpath("//a[text()='Add Employee']");
    By firstName = By.name("firstName");
    By middleName = By.name("middleName");
    By lastName = By.name("lastName");
    By saveBtn = By.xpath("//button[@type='submit']");

    // 🔹 Personal Details
    By personalDetailsHeader = By.xpath("//h6[text()='Personal Details']");
    By empId = By.xpath("//input[@name='employeeId']");
    By firstNameField = By.name("firstName");

    // 🔹 Employee List
    By employeeListTab = By.xpath("//a[text()='Employee List']");
    By empIdSearch = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    By nameSearch = By.xpath("//input[@placeholder='Type for hints...']");
    By searchBtn = By.xpath("//button[@type='submit']");
    By nameSuggestion = By.xpath("//div[@role='option']");

    // 🔹 Table
    By rows = By.xpath("//div[@class='oxd-table-card']");
    By nameColumn = By.xpath(".//div[3]");

    // 🔹 No Records
    By noRecord = By.xpath("//span[text()='No Records Found']");

    // 🔹 VALIDATION LOCATORS
    By requiredError = By.xpath("//span[text()='Required']");
    By dateField = By.xpath("//input[@placeholder='yyyy-mm-dd']");
    By invalidDateError = By.xpath("//span[contains(text(),'Invalid')]");

    // 🔹 Dropdown (example: nationality / gender type)
    By dropdown = By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");
    By options = By.xpath("//div[@role='listbox']//span");

    // =========================================================
    // 🔥 EXISTING METHODS
    // =========================================================

    public void clickAddEmployee() {
        click(addEmployeeTab);
    }

    public void addEmployee(String f, String m, String l) {
        sendKeys(firstName, f);
        sendKeys(middleName, m);
        sendKeys(lastName, l);
        click(saveBtn);
    }

    public void waitForPersonalDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetailsHeader));
    }

    public String getEmployeeId() {
        return driver.findElement(empId).getAttribute("value");
    }

    public void goToEmployeeList() {
        click(employeeListTab);
    }

    public void searchById(String id) {
        sendKeys(empIdSearch, id);
        click(searchBtn);
    }

    public void searchByName(String name) {
        sendKeys(nameSearch, name);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(nameSuggestion, 0));
        driver.findElements(nameSuggestion).get(0).click();
        click(searchBtn);
    }

    public boolean isEmployeePresentInTable(String expectedName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(rows));

        List<WebElement> allRows = driver.findElements(rows);

        for (WebElement row : allRows) {
            String name = row.findElement(nameColumn).getText();
            if (name.contains(expectedName)) {
                return true;
            }
        }
        return false;
    }

    public void clickFirstRow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(rows));
        driver.findElements(rows).get(0).click();
    }

    public String getFirstNameFromDetails() {
        return driver.findElement(firstNameField).getAttribute("value");
    }

    public boolean isNoRecordDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noRecord))
                .getText().contains("No Records");
    }

    // =========================================================
    // 🔥 NEW VALIDATION METHODS
    // =========================================================

    // ✅ 1. EMPTY VALIDATION
    public void clickSaveWithoutData() {
        click(saveBtn);
    }

    public boolean isFirstNameErrorDisplayed() {
        return driver.findElements(requiredError).size() > 0;
    }

    public boolean isLastNameErrorDisplayed() {
        return driver.findElements(requiredError).size() > 0;
    }

    // ✅ 2. INVALID DATE VALIDATION
    public void enterInvalidDate(String date) {
        sendKeys(dateField, date);
    }

    public boolean isDateErrorDisplayed() {
        return driver.findElements(invalidDateError).size() > 0;
    }

    // ✅ 3. DROPDOWN VALIDATION
    public boolean selectDropdownOption() {

        try {
            click(dropdown);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@role='listbox']")
            ));

            List<WebElement> list = driver.findElements(options);

            if (list.size() > 0) {
                list.get(0).click();
                return true;
            }

        } catch (Exception e) {
            return false;
        }

        return false;
    }
    public boolean selectGenderDropdown() {
        return selectDropdownOption();
    }
}