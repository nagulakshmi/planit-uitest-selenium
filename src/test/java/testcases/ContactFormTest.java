package testcases;

import driver.PlanitDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

@Test
public class ContactFormTest {

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Properties properties;

    @BeforeTest
    public void setUp() throws IOException {
        properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("planit.properties"));
    }

    @BeforeMethod(description = "Execute before all test case")
    public void beforeAllTestCase() throws Exception {
        driver = PlanitDriverManager.getWebDriverByBrowerName(properties.getProperty("test.browsername"), Long.parseLong(properties.getProperty("timeout.page")));
        driver.get(properties.getProperty("server.url"));
        driver.findElement(By.id("nav-contact")).click();
        webDriverWait = new WebDriverWait(driver, Long.parseLong(properties.getProperty("timeout.explicit")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary")));
        driver.findElement(By.className("btn-primary")).click();
    }


    @Test(description = "Test Case 1 - Verify form validation messages", priority = 1)
    public void contactFormValidation() throws InterruptedException {
        Assert.assertEquals(driver.findElement(By.id("forename-err")).getText(), "Forename is required");
        Assert.assertEquals(driver.findElement(By.id("email-err")).getText(), "Email is required");
        Assert.assertEquals(driver.findElement(By.id("message-err")).getText(), "Message is required");
        driver.findElement(By.id("forename")).sendKeys("Nagalakshmi");
        driver.findElement(By.id("email")).sendKeys("lakshmi.padumanabhan@gmail.com");
        driver.findElement(By.id("message")).sendKeys("planit automation testing");
        Assert.assertEquals(driver.findElements(By.className("help-inline")).size(), 0);
        driver.close();
    }

    @Test(description = "Test Case 2 - Verify form validation and successful submission message", priority = 2)
    public void contactFormSubmission() throws InterruptedException {
        Assert.assertEquals(driver.findElement(By.id("forename-err")).getText(), "Forename is required");
        Assert.assertEquals(driver.findElement(By.id("email-err")).getText(), "Email is required");
        Assert.assertEquals(driver.findElement(By.id("message-err")).getText(), "Message is required");
        driver.findElement(By.id("forename")).sendKeys("Nagalakshmi");
        driver.findElement(By.id("email")).sendKeys("lakshmi.padumanabhan@gmail.com");
        driver.findElement(By.id("message")).sendKeys("planit automation testing");
        driver.findElement(By.className("btn-primary")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        Assert.assertEquals(driver.findElement(By.className("alert-success")).getText(), "Thanks Nagalakshmi, we appreciate your feedback.");
        driver.close();
    }

    @Test(description = "Test Case 3 - Verify form validation with invalid data field", priority = 3)
    public void contactFormErrorMsgValidate() throws InterruptedException {
        Assert.assertEquals(driver.findElement(By.id("forename-err")).getText(), "Forename is required");
        Assert.assertEquals(driver.findElement(By.id("email-err")).getText(), "Email is required");
        Assert.assertEquals(driver.findElement(By.id("message-err")).getText(), "Message is required");
        driver.findElement(By.id("forename")).sendKeys("Nagalakshmi");
        driver.findElement(By.id("email")).sendKeys("lakshmi.padumanabhan");
        driver.findElement(By.id("message")).sendKeys("planit automation testing");
        Assert.assertEquals(driver.findElement(By.id("email-err")).getText(), "Please enter a valid email");
        driver.close();
    }


}
