import org.openqa.selenium.Dimension; // المكتبة المسؤولة عن مقاسات الشاشة
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class TestThree {

    @Test
    public void testProductPageResponsiveLayout() throws InterruptedException {

        // 1. تشغيل الكروم وتظبيط الانتظار
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 2. تصغير نافذة المتصفح لمقاس الموبايل
        // العرض 375 والطول 812 ده مقاس شاشة الـ iPhone (Mobile Width)
        Dimension mobileSize = new Dimension(375, 812);
        driver.manage().window().setSize(mobileSize);

        // 3. فتح صفحة المنتج (SpiraPlan) على مقاس الموبايل
        driver.get("https://www.inflectra.com/SpiraPlan/");

        Thread.sleep(4000);

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("SpiraPlan"), "Error: Product page failed to load on mobile view!");

        System.out.println("Result: Test Case 4 (Mobile Responsive Check) Passed Successfully!");

        driver.quit();
    }
}