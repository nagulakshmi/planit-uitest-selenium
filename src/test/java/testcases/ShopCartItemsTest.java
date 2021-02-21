package testcases;

import driver.PlanitDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Test
public class ShopCartItemsTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeMethod(description = "Execute befor all test case")
    public void beforeAllTestCase() throws Exception {
        driver = PlanitDriverManager.getWebDriverByBrowerName("firefox");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get("https://jupiter.cloud.planittesting.com/");
        driver.findElement(By.id("nav-shop")).click();

    }

    @Test(description = "Test case 4- Verify the items are in the cart")
    public void shopCartItems() {
        webDriverWait = new WebDriverWait(driver, 20);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("products")));
        driver.findElement(By.xpath("//*[@id=\"product-6\"]/div/p/a")).click();
        driver.findElement(By.xpath("//*[@id=\"product-6\"]/div/p/a")).click();
        driver.findElement(By.xpath("//*[@id=\"product-4\"]/div/p/a")).click();
        driver.findElement(By.id("nav-cart")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-items"))).click();
        List<WebElement> elements = driver.findElements(By.className("cart-item"));
        Assert.assertEquals(elements.size(), 2);
        ArrayList<ArrayList<String>> expectedValue = new ArrayList<>();
        expectedValue.add(new ArrayList<>(Arrays.asList("Funny Cow", "$10.99", "2", "$21.98", "")));
        expectedValue.add(new ArrayList<>(Arrays.asList("Fluffy Bunny", "$9.99", "1", "$9.99", "")));
        ArrayList<ArrayList<String>> actualValue = new ArrayList<>();

        elements.forEach(webElement -> {
            ArrayList<String> row = new ArrayList<>();
            webElement.findElements(By.tagName("td")).forEach(element -> {
                List<WebElement> qty = element.findElements(By.tagName("input"));
                if (qty.size() != 0) {
                    row.add(qty.get(0).getAttribute("value"));
                } else {
                    row.add(element.getText());
                }
            });
            actualValue.add(row);
        });
        Assert.assertEquals(actualValue, expectedValue);
        driver.close();
    }

}






