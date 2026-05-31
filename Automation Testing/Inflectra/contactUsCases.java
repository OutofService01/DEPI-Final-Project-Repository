package DEPI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class contactUsCases {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testContactUs() {
        driver.get("https://www.inflectra.com/Company/Contact-Us.aspx");

        By firstName = By.id("txtFirstName");
        By lastName = By.id("txtLastName");
        By email = By.id("txtEmailAddress");
        By subject = By.id("txtSubject");
        By description = By.id("txtDescription");
        By industry = By.id("cplMainContent_cplMainContent_ddlIndustry");
        By country = By.id("cplMainContent_cplMainContent_ddlCountry");
        By organization = By.id("txtOrganization");
        By phoneNumber = By.id("txtPhoneNumber");
        By checkBox = By.id("chkOptIn");
        By buttonSubmit = By.id("btnSubmit");

        driver.findElement(firstName).sendKeys("Kirolos");
        driver.findElement(lastName).sendKeys("Karam");
        driver.findElement(email).sendKeys("kiroloskaram055@gmail.com");
        driver.findElement(subject).sendKeys("Test Subject");
        driver.findElement(description).sendKeys("Test Description for make full test automation and cover all the fields in the contact us page");

        WebElement industryElement = driver.findElement(industry);
        Select selectIndustry = new Select(industryElement);
        selectIndustry.selectByVisibleText("Automotive Systems");

        WebElement countryElement = driver.findElement(country);
        Select selectCountry = new Select(countryElement);
        selectCountry.selectByVisibleText("Egypt");
        driver.findElement(organization).sendKeys("DEPI Testing Company");
        driver.findElement(phoneNumber).sendKeys("01090807012");
        driver.findElement(checkBox).click();
        driver.findElement(buttonSubmit).click();
    }
}





















