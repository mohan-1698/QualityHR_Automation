package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    By username = By.name("username");
    By password = By.name("password");
    By loginBtn = By.xpath("//button[@type='submit']");
    By errorMsg = By.xpath("//p[contains(@class,'alert-content-text')]");

    By dropdown = By.xpath("//i[contains(@class,'oxd-userdropdown-icon')]");
    By logout = By.linkText("Logout");

    public void login(String user, String pass) {
        sendKeys(username, user);
        sendKeys(password, pass);
        click(loginBtn);
    }

    public boolean isDashboardVisible() {
        return driver.getCurrentUrl().contains("dashboard");
    }

    public String getError() {
        return getText(errorMsg);
    }

    public void logout() {
        click(dropdown);
        click(logout);
    }
}