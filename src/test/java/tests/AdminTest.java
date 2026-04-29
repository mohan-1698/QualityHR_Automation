package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.AdminPage;

public class AdminTest extends BaseTest {

    @Test
    public void userManagementTest() {

        // 🔥 LOGIN
        LoginPage lp = new LoginPage(driver, config.getTimeout());
        lp.login(config.getAdminUsername(), config.getAdminPassword());

        Assert.assertTrue(lp.isDashboardVisible(), "Login failed");

        // 🔥 NAVIGATE TO ADMIN
        AdminPage admin = new AdminPage(driver, config.getTimeout());
        admin.goToAdmin();

        // 🔥 ADD USER
        admin.clickAdd();

        String username = "user" + System.currentTimeMillis();
        String password = config.getUserPassword();

        admin.selectRole(config.getUserRole());
        admin.selectStatus(config.getUserStatus());

        // 🔥 IMPORTANT → VALID employee
        admin.enterEmployeeName(config.getEmployeeName());

        admin.enterCredentials(username, password);
        admin.clickSave();

        // 🔥 VERIFY CREATED
        admin.searchUser(username);
        Assert.assertTrue(admin.isUserPresent(), "User not created");

        // 🔥 DELETE USER
        admin.deleteUser();

        // 🔥 VERIFY DELETED
        admin.searchUser(username);
        Assert.assertFalse(admin.isUserPresent(), "User not deleted");
    }
}