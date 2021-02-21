package testcases;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class BaseTest {
    public void takeScreenShot(WebDriver driver, String testCaseName) throws IOException {
        String workingDir = System.getProperty("user.dir");
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(String.format("%s/%s/%s.jpg", workingDir, testCaseName, UUID.randomUUID())));
    }
}
