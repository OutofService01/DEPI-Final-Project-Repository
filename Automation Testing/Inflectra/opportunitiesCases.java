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

public class opportunitiesCases {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testHover() {
        driver.get("https://www.inflectra.com/Company/Opportunities.aspx");
        By hoverlocator = By.xpath("//img[@alt='World Map']");
        WebElement hoverElement = driver.findElement(hoverlocator);

        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).perform();

    }

    @AfterMethod
    public void tearDwon() {
        if (driver != null) {
            driver.quit();
        }
    }
}
