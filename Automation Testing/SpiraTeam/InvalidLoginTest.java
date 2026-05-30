package org.example.SpiraTeam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InvalidLoginTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo-eu.spiraservice.net/farah-walid/Login.aspx");
    }

    @Test
    public void invalidLogin() {
        driver.findElement(By.id("UserName")).sendKeys("administrator");
        driver.findElement(By.id("Password")).sendKeys("WrongPassword123");
        driver.findElement(By.id("btnLogin")).click();

        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }

        String url = driver.getCurrentUrl();
        System.out.println("Current URL after failed login: " + url);

        // We should still be on the login page
        Assert.assertTrue(url.contains("Login.aspx"), "Expected to stay on login page but was redirected!");
        System.out.println("PASSED - stayed on login page as expected");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}