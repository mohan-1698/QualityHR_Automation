package listeners;

import org.testng.*;
import com.aventstack.extentreports.*;
import org.openqa.selenium.WebDriver;

import utils.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);

        test.get().info("Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail("Test Failed ❌");
        test.get().fail(result.getThrowable());

        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

        if (driver != null) {

            String fullPath = ScreenshotUtils.capture(driver, result.getMethod().getMethodName());

            try {
                // 🔥 CONVERT TO RELATIVE PATH
                String relativePath = fullPath.replace(System.getProperty("user.dir") + "\\", "");

                test.get().addScreenCaptureFromPath(relativePath);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped ⚠️");
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}