import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class TestTwo {

    @Test
    public void testProductFreeTrialNavigation() throws InterruptedException {

        // 1. تشغيل الكروم وتظبيط المقاس والانتظار
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 2. الدخول على صفحة المنتج (SpiraPlan)
        driver.get("https://www.inflectra.com/SpiraPlan/");
        Thread.sleep(3000);

        // 3.  Free Trial
        WebElement freeTrialButton = driver.findElement(By.xpath("//a[contains(@href,'Trial') or contains(text(),'Trial')]"));
        freeTrialButton.click();

        Thread.sleep(4000);

        // 4. الـ Assertion: نتأكد إننا دخلنا صفحة تسيجل المنتج التجريبي
        String actualUrl = driver.getCurrentUrl();

        // بنتأكد إن اللينك الجديد جواه كلمة trial أو register
        Assert.assertTrue(actualUrl.contains("trial") || actualUrl.contains("Register"), "Error: Product Trial page failed to open!");


        System.out.println("Result: Test Case 4 (Product Free Trial) Passed Successfully!");


        driver.quit();
    }
}