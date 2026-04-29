package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver, int timeout) {
        super(driver, timeout);
    }

    By pimMenu = By.xpath("//span[text()='PIM']");

    public void goToPIM() {
        click(pimMenu);
    }
}