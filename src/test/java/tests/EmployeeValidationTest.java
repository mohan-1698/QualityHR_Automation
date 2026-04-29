package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.DashboardPage;
import pages.EmployeePage;

public class EmployeeValidationTest extends BaseTest {

    // 🔥 1. EMPTY FORM VALIDATION
    @Test
    public void emptyFieldValidationTest() {

        LoginPage lp = new LoginPage(driver, config.getTimeout());
        lp.login(config.getAdminUsername(), config.getAdminPassword());

        DashboardPage dp = new DashboardPage(driver, config.getTimeout());
        dp.goToPIM();

        EmployeePage emp = new EmployeePage(driver, config.getTimeout());

        emp.clickAddEmployee();

        emp.clickSaveWithoutData();

        Assert.assertTrue(emp.isFirstNameErrorDisplayed(), "First name error not shown");
        Assert.assertTrue(emp.isLastNameErrorDisplayed(), "Last name error not shown");
    }

    // 🔥 2. INVALID DATE VALIDATION
    @Test
    public void invalidDateValidationTest() {

        LoginPage lp = new LoginPage(driver, config.getTimeout());
        lp.login(config.getAdminUsername(), config.getAdminPassword());

        DashboardPage dp = new DashboardPage(driver, config.getTimeout());
        dp.goToPIM();

        EmployeePage emp = new EmployeePage(driver, config.getTimeout());

        emp.clickAddEmployee();

        emp.enterInvalidDate("32-13-2025"); // invalid format

        emp.clickSaveWithoutData();

        Assert.assertTrue(emp.isDateErrorDisplayed(), "Invalid date error not shown");
    }

    // 🔥 3. DROPDOWN VALIDATION
    @Test
    public void dropdownValidationTest() {

        LoginPage lp = new LoginPage(driver, config.getTimeout());
        lp.login(config.getAdminUsername(), config.getAdminPassword());

        DashboardPage dp = new DashboardPage(driver, config.getTimeout());
        dp.goToPIM();

        EmployeePage emp = new EmployeePage(driver, config.getTimeout());

        emp.clickAddEmployee();

        boolean result = emp.selectGenderDropdown();

        Assert.assertTrue(result, "Dropdown not working properly");
    }
}