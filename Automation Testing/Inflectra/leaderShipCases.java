package DEPI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class leaderShipCases {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void CheckImages() {
        driver.get("https://www.inflectra.com/Company/Leadership-Team.aspx");
        List<WebElement> images = driver.findElements(By.xpath("//blockquote/img[@class='img-headshot']"));
        System.out.println("Total leadership image found: " + images.size());

        for (WebElement image : images) {
            String src = image.getAttribute("src");
            String naturalWidth = image.getDomProperty("naturalWidth");
            boolean isimageBroken = naturalWidth == null || naturalWidth.equals("0");
            Assert.assertFalse(isimageBroken, "Image with src '" + src + "' is broken.");
        }
        System.out.println("All leadership images are display successfully!");
    }

    @AfterMethod
    public void teardwon() {
        if (driver != null) {
            driver.quit();
        }
    }
}






























































































































