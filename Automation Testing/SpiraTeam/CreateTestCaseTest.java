package org.example.SpiraTeam;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class CreateTestCaseTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login() throws InterruptedException {
        driver.get("https://demo-eu.spiraservice.net/farah-walid/Login.aspx");
        driver.findElement(By.id("UserName")).sendKeys("administrator");
        driver.findElement(By.id("Password")).sendKeys("PleaseChange");
        driver.findElement(By.id("btnLogin")).click();

        Thread.sleep(3000);

        // Handle popup if it appears
        List<WebElement> signOffButton = driver.findElements(By.id("cplMainContent_btnSignOffOthers"));
        if (!signOffButton.isEmpty()) {
            System.out.println("Popup detected - clicking Sign Off Other Locations...");
            signOffButton.get(0).click();
            Thread.sleep(2000);
        }

        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("Login.aspx")
        ));
        System.out.println("Logged in! URL: " + driver.getCurrentUrl());
    }

    @Test
    public void createNewTestCase() throws InterruptedException {
        // Step 1: Login
        login();

        // Step 2: Click the Artifacts menu
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("globalNav_artifactDropdown")
        )).click();
        System.out.println("Clicked Artifacts menu!");

        // Step 3: Click Test Cases in the dropdown
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("globalNav_artifactDropdown_2")
        )).click();
        System.out.println("Clicked Test Cases!");

        // Step 4: Wait for Test Cases page to load
        wait.until(ExpectedConditions.urlContains("TestCase/List.aspx"));
        System.out.println("On Test Cases page! URL: " + driver.getCurrentUrl());

        // Step 5: Click New Test Case button
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(., 'New Test Case')]")
        )).click();
        System.out.println("Clicked New Test Case button!");

        // Step 6: Wait and see what happens
        Thread.sleep(3000);
        System.out.println("URL after clicking New Test Case: " + driver.getCurrentUrl());

        // Keep browser open so you can see the result
        Thread.sleep(3000);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}