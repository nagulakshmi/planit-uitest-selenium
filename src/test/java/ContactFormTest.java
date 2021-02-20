import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

@Test
public class ContactFormTest {

    @Test(description = "Test Case 1 - Verify form validation messages", priority = 1)
    public void contactFormValidation() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.get("https://jupiter.cloud.planittesting.com/");
        chromeDriver.close();

    }

}
