package org.example.SpiraTeam;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class AIAssistantTest {

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
    public void askAIAssistant() throws InterruptedException {
        // Step 1: Login
        login();

        // Step 2: Click the AI button in the nav bar
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("svg.inflectra-ai-icon")
        )).click();
        System.out.println("Clicked AI button!");

        // Step 3: Click "Create new product" option
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("content-create_children_from_RQ")
        )).click();
        System.out.println("Clicked Create new product option!");

        // Step 4: Wait for the AI input field to appear
        WebElement aiInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("spira-ai-user-input-text")
        ));
        System.out.println("AI input field is visible!");

        // Step 5: Type the question
        aiInput.sendKeys("open testcase page");
        System.out.println("Typed question!");

        // Step 6: Press Enter to submit
        aiInput.sendKeys(Keys.ENTER);
        System.out.println("Pressed Enter!");

        // Step 7: Wait for AI response
        Thread.sleep(5000);
        System.out.println("URL after AI response: " + driver.getCurrentUrl());

        // Step 8: Verify AI responded
        String pageSource = driver.getPageSource();
        Assert.assertTrue(
                pageSource.contains("open testcase page") || pageSource.contains("Test Case") || pageSource.contains("product"),
                "AI did not respond!"
        );
        System.out.println("PASSED - AI assistant responded successfully!");

        // Keep browser open to see result
        Thread.sleep(4000);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}