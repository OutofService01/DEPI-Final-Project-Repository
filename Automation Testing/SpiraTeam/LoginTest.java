package org.example.SpiraTeam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo-eu.spiraservice.net/farah-walid/Login.aspx");
    }

    @Test(priority = 1)
    public void validLogin() {
        driver.findElement(By.id("UserName")).sendKeys("administrator");
        driver.findElement(By.id("Password")).sendKeys("PleaseChange");
        driver.findElement(By.id("btnLogin")).click();

        // Wait 3 seconds for page to load
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Print actual URL so we can see where it lands
        String url = driver.getCurrentUrl();
        System.out.println("Current URL after login: " + url);

        // Handle popup if it appears
        List<WebElement> signOffButton = driver.findElements(By.id("cplMainContent_btnSignOffOthers"));
        if (!signOffButton.isEmpty()) {
            signOffButton.get(0).click();
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        // Just check we're no longer on the login page
        Assert.assertFalse(url.contains("Login.aspx"), "Still on login page - login may have failed");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}