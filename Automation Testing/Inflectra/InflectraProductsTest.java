import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class InflectraProductsTest {
    public static void main(String[] args) {

        // Initialize the Chrome driver instance
        WebDriver driver = new ChromeDriver();

        // Set implicit wait and maximize the browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            // ----------------------------------------------------
            // [Test Case 1]: Navigate to Products page and verify title
            // ----------------------------------------------------
            System.out.println("Starting Test Case 1...");

            // Navigate directly to the products page to bypass dropdown issues
            driver.get("https://www.inflectra.com/Products/");

            String actualTitle = driver.getTitle();
            System.out.println("Actual page title is: " + actualTitle);

            // Assertion: Check if the title contains the word "Products"
            if (actualTitle.contains("Products")) {
                System.out.println(">> [PASS]: Test Case 1 passed! Successfully accessed the Products page.");
            } else {
                System.out.println(">> [FAIL]: Test Case 1 failed.");
            }

            // ----------------------------------------------------
            // [Test Case 2]: Verify SpiraTest link is available and working
            // ----------------------------------------------------
            System.out.println("\nStarting Test Case 2...");

            // Locate the SpiraTest link on the products page and click it
            WebElement spiraTestLink = driver.findElement(By.linkText("SpiraTest"));
            spiraTestLink.click();

            // Assertion: Verify the current URL after the click
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL after click is: " + currentUrl);

            if (currentUrl.toLowerCase().contains("spiratest")) {
                System.out.println(">> [PASS]: Test Case 2 passed! SpiraTest page loaded successfully.");
            } else {
                System.out.println(">> [FAIL]: Test Case 2 failed.");
            }

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close the browser and clean up the session
            driver.quit();
            System.out.println("\nBrowser closed. Test execution finished.");
        }
    }
}