package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import base.BasePage;

public class AdminPage extends BasePage {

    public AdminPage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    // 🔹 Navigation
    private By adminMenu = By.xpath("//span[text()='Admin']");
    private By addBtn = By.xpath("//button[normalize-space()='Add']");

    // 🔹 Form
    private By roleDropdown = By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");
    private By statusDropdown = By.xpath("(//div[contains(@class,'oxd-select-text')])[2]");
    private By options = By.xpath("//div[@role='listbox']//span");

    private By empName = By.xpath("//input[@placeholder='Type for hints...']");
    private By suggestions = By.xpath("//div[@role='option']");

    private By usernameInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private By passwordInput = By.xpath("(//input[@type='password'])[1]");
    private By confirmPassword = By.xpath("(//input[@type='password'])[2]");

    private By saveBtn = By.xpath("//button[@type='submit']");

    // 🔹 Search
    private By searchUser = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private By searchBtn = By.xpath("//button[@type='submit']");

    // 🔹 Table
    private By rows = By.xpath("//div[@class='oxd-table-card']");
    private By deleteBtn = By.xpath(".//button[1]");
    private By confirmDelete = By.xpath("//button[normalize-space()='Yes, Delete']");

    // ================= METHODS =================

    public void goToAdmin() {
        click(adminMenu);
    }

    public void clickAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    private void selectDropdown(By dropdown, String value) {
        click(dropdown);

        List<WebElement> list = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(options)
        );

        for (WebElement e : list) {
            if (e.getText().contains(value)) {
                e.click();
                break;
            }
        }
    }

    public void selectRole(String role) {
        selectDropdown(roleDropdown, role);
    }

    public void selectStatus(String status) {
        selectDropdown(statusDropdown, status);
    }

    // ✅ Correct Employee Selection
    public void enterEmployeeName(String name) {
        sendKeys(empName, name);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(suggestions, 0));

        for (WebElement option : driver.findElements(suggestions)) {
            if (option.getText().contains(name)) {
                option.click();
                break;
            }
        }
    }

    public void enterCredentials(String username, String password) {
        sendKeys(usernameInput, username);
        sendKeys(passwordInput, password);
        sendKeys(confirmPassword, password);
    }

    public void clickSave() {
        click(saveBtn);
    }

    public void searchUser(String username) {
        sendKeys(searchUser, username);
        click(searchBtn);

        wait.until(ExpectedConditions.visibilityOfElementLocated(rows));
    }

    public boolean isUserPresent() {
        return driver.findElements(rows).size() > 0;
    }

    public void deleteUser() {
        WebElement row = driver.findElements(rows).get(0);
        row.findElement(deleteBtn).click();

        wait.until(ExpectedConditions.elementToBeClickable(confirmDelete)).click();
    }
}