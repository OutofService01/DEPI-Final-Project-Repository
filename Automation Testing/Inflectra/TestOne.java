import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class TestOne {

    @Test
    public void testNavigateToSpiraPlanPage() throws InterruptedException {

        // 1. فتح المتصفح وتكبير الشاشة وتظبيط الوقت
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 2. فتح الموقع
        driver.get("https://www.inflectra.com");

        Thread.sleep(3000);

        // 3. الوقوف بالماوس (Hover) على  Products
        WebElement productsMenu = driver.findElement(By.xpath("//a[contains(text(),'Products')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(productsMenu).perform();

        Thread.sleep(3000);

        // 4. الضغط على كلمة SpiraPlan
        WebElement spiraPlanLink = driver.findElement(By.xpath("//a[contains(text(),'SpiraPlan')]"));
        spiraPlanLink.click();

        Thread.sleep(3000);

        //##########################################

        // 5.  Assertion
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains("SpiraPlan"), "Error: Redirected to the wrong page! Current URL is: " + actualUrl);

        System.out.println("Result: Test Case Passed Successfully!");

        driver.quit();
    }
}