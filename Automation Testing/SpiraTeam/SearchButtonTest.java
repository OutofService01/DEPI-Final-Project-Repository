package org.example.SpiraTeam;

import org.openqa.selenium.By;
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

public class SearchButtonTest {

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
    public void searchForSettings() throws InterruptedException {
        // Step 1: Login
        login();

        // Step 2: Click the search label to open search bar
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("btn-global-search")
        )).click();
        System.out.println("Clicked search icon!");

        // Step 3: Type "settings" in the search input
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("globalNav_search_input")
        ));
        searchInput.sendKeys("settings");
        System.out.println("Typed 'settings' in search box!");

        Thread.sleep(1000);

        // Step 4: Click the search button to submit
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("globalNav_search_button")
        )).click();
        System.out.println("Clicked search button to submit!");

        // Step 5: Wait for results to load
        Thread.sleep(3000);
        System.out.println("URL after search: " + driver.getCurrentUrl());

        // Step 6: Verify results are displayed
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("settings") || pageSource.contains("Settings"),
                "No search results found for 'settings'!");
        System.out.println("PASSED - Search results displayed successfully!");

        // Keep browser open to see results
        Thread.sleep(5000);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}