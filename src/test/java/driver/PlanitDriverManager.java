package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class PlanitDriverManager {

    public static WebDriver driver;
    public static final String CHROME_BROWSER = "CHROME";
    public static final String FIREFOX_BROWSER = "FIREFOX";
    public static final String EDGE_BROWSER = "EDGE";

    public static WebDriver getWebDriverByBrowerName(String browserName, long pageTimeout) throws Exception {
        switch (browserName.toUpperCase()) {
            case CHROME_BROWSER:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX_BROWSER:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE_BROWSER:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new Exception("Browser name not defined");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(pageTimeout, TimeUnit.SECONDS);
        return driver;
    }
}
