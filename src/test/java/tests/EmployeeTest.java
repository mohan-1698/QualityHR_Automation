package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.DashboardPage;
import pages.EmployeePage;

public class EmployeeTest extends BaseTest {

    // 🔥 1. Add Employee + Verify using ID
    @Test
    public void addEmployeeVerifyById() {

        LoginPage lp = new LoginPage(driver, config.getTimeout());
        lp.login(config.getAdminUsername(), config.getAdminPassword());

        DashboardPage dp = new DashboardPage(driver, config.getTimeout());
        dp.goToPIM();

        EmployeePage ep = new EmployeePage(driver, config.getTimeout());

        String firstName = config.getEmployeeFirstName() + System.currentTimeMillis();

        ep.clickAddEmployee();
        ep.addEmployee(firstName, config.getEmployeeMiddleName(), config.getEmployeeLastName());

        ep.waitForPersonalDetails();

        String empId = ep.getEmployeeId();

        ep.goToEmployeeList();
        ep.searchById(empId);

        Assert.assertTrue(ep.isEmployeePresentInTable(firstName),
                "Employee not found by ID");
    }

    // 🔥 2. Search by Name
    @Test
    public void searchEmployeeByName() {

        LoginPage lp = new LoginPage(driver, config.getTimeout());
        lp.login(config.getAdminUsername(), config.getAdminPassword());

        DashboardPage dp = new DashboardPage(driver, config.getTimeout());
        dp.goToPIM();

        EmployeePage ep = new EmployeePage(driver, config.getTimeout());

        String name = config.getEmployeeFirstName();

        ep.goToEmployeeList();
        ep.searchByName(name);

        Assert.assertTrue(ep.isEmployeePresentInTable(name),
                "Employee not found in search");
    }

    // 🔥 3. Open Employee Record & Verify
    @Test
    public void openEmployeeAndVerifyDetails() {

        LoginPage lp = new LoginPage(driver, config.getTimeout());
        lp.login(config.getAdminUsername(), config.getAdminPassword());

        DashboardPage dp = new DashboardPage(driver, config.getTimeout());
        dp.goToPIM();

        EmployeePage ep = new EmployeePage(driver, config.getTimeout());

        String name = config.getEmployeeFirstName();

        ep.goToEmployeeList();
        ep.searchByName(name);

        ep.clickFirstRow();

        ep.waitForPersonalDetails();

        String actualName = ep.getFirstNameFromDetails();

        Assert.assertTrue(actualName.contains(name),
                "Employee name mismatch in details page");
    }

    // 🔥 4. Invalid Search
    @Test
    public void invalidSearchTest() {

        LoginPage lp = new LoginPage(driver, config.getTimeout());
        lp.login(config.getAdminUsername(), config.getAdminPassword());

        DashboardPage dp = new DashboardPage(driver, config.getTimeout());
        dp.goToPIM();

        EmployeePage ep = new EmployeePage(driver, config.getTimeout());

        ep.goToEmployeeList();
        ep.searchByName(config.getInvalidEmployee());

        Assert.assertTrue(ep.isNoRecordDisplayed(),
                "Invalid search did not show No Records");
    }
}