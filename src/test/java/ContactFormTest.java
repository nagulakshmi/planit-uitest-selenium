import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test
public class ContactFormTest {

    @Test(description = "Test Case 1 - Verify form validation messages", priority = 1)
    public void contactFormValidation() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        chromeDriver.get("https://jupiter.cloud.planittesting.com/");
        chromeDriver.findElement(By.id("nav-contact")).click();
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary")));
        chromeDriver.findElement(By.className("btn-primary")).click();
        Assert.assertEquals(chromeDriver.findElement(By.id("forename-err")).getText(),"Forename is required");
        Assert.assertEquals(chromeDriver.findElement(By.id("email-err")).getText(),"Email is required");
        Assert.assertEquals(chromeDriver.findElement(By.id("message-err")).getText(),"Message is required");
        chromeDriver.findElement(By.id("forename")).sendKeys("Nagalakshmi");
        chromeDriver.findElement(By.id("email")).sendKeys("lakshmi.padumanabhan@gmail.com");
        chromeDriver.findElement(By.id("message")).sendKeys("planit automation testing");
        Assert.assertEquals(chromeDriver.findElements(By.className("help-inline")).size(),0);
        Thread.sleep(2000);
        chromeDriver.close();
    }

    @Test(description = "Test Case 2 - Verify form validation and successful submission message", priority = 2)
    public void contactFormSubmission() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        chromeDriver.get("https://jupiter.cloud.planittesting.com/");
        chromeDriver.findElement(By.id("nav-contact")).click();
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 20);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary")));
        chromeDriver.findElement(By.className("btn-primary")).click();
        Assert.assertEquals(chromeDriver.findElement(By.id("forename-err")).getText(),"Forename is required");
        Assert.assertEquals(chromeDriver.findElement(By.id("email-err")).getText(),"Email is required");
        Assert.assertEquals(chromeDriver.findElement(By.id("message-err")).getText(),"Message is required");
        chromeDriver.findElement(By.id("forename")).sendKeys("Nagalakshmi");
        chromeDriver.findElement(By.id("email")).sendKeys("lakshmi.padumanabhan@gmail.com");
        chromeDriver.findElement(By.id("message")).sendKeys("planit automation testing");
        chromeDriver.findElement(By.className("btn-primary")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        Assert.assertEquals(chromeDriver.findElement(By.className("alert-success")).getText(),"Thanks Nagalakshmi, we appreciate your feedback.");
        Thread.sleep(2000);
        chromeDriver.close();
    }

    @Test(description = "Test Case 3 - Verify form validation with invalid data field", priority = 3)
    public void contactFormErrorMsgValidate() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        chromeDriver.get("https://jupiter.cloud.planittesting.com/");
        chromeDriver.findElement(By.id("nav-contact")).click();
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, 20);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary")));
        chromeDriver.findElement(By.className("btn-primary")).click();
        Assert.assertEquals(chromeDriver.findElement(By.id("forename-err")).getText(),"Forename is required");
        Assert.assertEquals(chromeDriver.findElement(By.id("email-err")).getText(),"Email is required");
        Assert.assertEquals(chromeDriver.findElement(By.id("message-err")).getText(),"Message is required");
        chromeDriver.findElement(By.id("forename")).sendKeys("Nagalakshmi");
        chromeDriver.findElement(By.id("email")).sendKeys("lakshmi.padumanabhan");
        chromeDriver.findElement(By.id("message")).sendKeys("planit automation testing");
        Assert.assertEquals(chromeDriver.findElement(By.id("email-err")).getText(),"Please enter a valid email");
        Thread.sleep(2000);
        chromeDriver.close();
    }

}
