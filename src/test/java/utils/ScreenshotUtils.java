package utils;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String capture(WebDriver driver, String testName) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // ✅ Correct dynamic project path
        String projectPath = System.getProperty("user.dir");

        // ✅ Your required folder
        String dirPath = projectPath + "/screenshots/";

        // ✅ Create folder if not exists
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // ✅ Final file path
        String filePath = dirPath + testName + "_" + timestamp + ".png";

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File(filePath);

            FileUtils.copyFile(src, dest);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return filePath;
    }
}