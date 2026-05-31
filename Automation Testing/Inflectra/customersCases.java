package DEPI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class customersCases {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void scrollCases() {
        driver.get("https://www.inflectra.com/Company/Customers.aspx");

        By itSectionLocator = By.xpath("//a[text()='Information Technology']");
        WebElement itsectionTitle = driver.findElement(itSectionLocator);

        Actions actions = new Actions(driver);
        actions.moveToElement(itsectionTitle).perform();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }
}
