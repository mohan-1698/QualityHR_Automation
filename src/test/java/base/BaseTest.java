package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import utils.ConfigReader;
import utils.DriverFactory;

@Listeners(listeners.TestListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected ConfigReader config;

    @BeforeMethod
    public void setup(ITestContext context) {

        config = new ConfigReader();
        driver = DriverFactory.initDriver(config.getBrowser());
        driver.get(config.getUrl());

        // ✅ IMPORTANT: store driver for listener (screenshots)
        context.setAttribute("driver", driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}