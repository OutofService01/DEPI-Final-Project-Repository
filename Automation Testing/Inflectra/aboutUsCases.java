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

public class aboutUsCases {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.inflectra.com/Company/");
    }

    @Test
    public void TestAbout() {
//        By videoSectionLocator = By.cssSelector(".\"ytmCuedOverlayHost\"");
//        WebElement videosection = driver.findElement(videoSectionLocator);

        By missionTitle = By.xpath("//h2[text()='Our Mission']");
        WebElement missionTitleElement = driver.findElement(missionTitle);

        String missionText = missionTitleElement.getText();
        System.out.println("Mission Title: " + missionText);

        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, 800).perform();

        By iframLocator = By.id("video-iframe");
        WebElement iframe = driver.findElement(iframLocator);
        driver.switchTo().frame(iframe);


        By playButtonLocator = By.cssSelector("[title=\"Play video\"]");
        WebElement playButton = driver.findElement(playButtonLocator);
        playButton.click();

        driver.switchTo().defaultContent();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}











































