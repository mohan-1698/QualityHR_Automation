package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.JsonUtils;

public class LoginTest extends BaseTest {

    @DataProvider(name = "jsonLoginData")
    public Object[][] getData() {

        return JsonUtils.getLoginData(
            "/src/test/resources/loginData.json"
        );
    }

    @Test(dataProvider = "jsonLoginData")
    public void loginTest(String username, String password, String type) {

        LoginPage lp = new LoginPage(driver, config.getTimeout());

        lp.login(username, password);

        if (type.equals("valid")) {

            Assert.assertTrue(lp.isDashboardVisible(), "Login failed for valid user");
            lp.logout();
        }

        else if (type.equals("invalid")) {

            Assert.assertTrue(lp.getError().contains("Invalid"), "Error not shown");
        }

        else if (type.equals("empty")) {

            Assert.assertTrue(lp.getError().length() > 0, "Validation not shown");
        }
    }

    @Test
    public void logoutTest() {

        LoginPage lp = new LoginPage(driver, config.getTimeout());

        lp.login("Admin", "admin123");
        lp.logout();

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Logout failed");
    }
}