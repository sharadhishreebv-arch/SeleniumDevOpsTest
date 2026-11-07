package tests;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeOptions;

public class WelcomeTest {

    WebDriver driver;

    // Browser setup before every test

@BeforeMethod
public void setUp() {
    ChromeOptions options = new ChromeOptions();

    options.addArguments("--headless=new");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--window-size=1920,1080");

    driver = new ChromeDriver(options);

    String page = new File("webpage/welcome.html")
            .getAbsoluteFile()
            .toURI()
            .toString();

    driver.get(page);
}


    // TC01: Verify page title
    @Test
    public void verifyPageTitle() {

        String title = driver.getTitle();

        Assert.assertEquals(title, "Welcome to DevOps");

        System.out.println("TC01: Page Title Verified");
    }

    // TC02: Verify heading
    @Test
    public void verifyHeading() {

        String heading = driver.findElement(
                By.id("heading")).getText();

        Assert.assertEquals(heading, "Welcome to DevOps");

        System.out.println("TC02: Heading Verified");
    }

    // TC03: Valid login
    @Test
    public void validLoginTest() {

        driver.findElement(By.id("username"))
                .sendKeys("admin");

        driver.findElement(By.id("password"))
                .sendKeys("admin123");

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(
                By.id("message")).getText();

        Assert.assertEquals(
                message,
                "Welcome to DevOps! Login Successful");

        System.out.println("TC03: Valid Login Passed");
    }

    // TC04: Invalid login
    @Test
    public void invalidLoginTest() {

        driver.findElement(By.id("username"))
                .sendKeys("wrong");

        driver.findElement(By.id("password"))
                .sendKeys("wrong");

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(
                By.id("message")).getText();

        Assert.assertEquals(
                message,
                "Invalid Username or Password");

        System.out.println("TC04: Invalid Login Passed");
    }

    // TC05: Verify username field
    @Test
    public void verifyUsernameField() {

        boolean result = driver.findElement(
                By.id("username")).isDisplayed();

        Assert.assertTrue(result);

        System.out.println("TC05: Username Field Verified");
    }

    // TC06: Verify password field
    @Test
    public void verifyPasswordField() {

        boolean result = driver.findElement(
                By.id("password")).isDisplayed();

        Assert.assertTrue(result);

        System.out.println("TC06: Password Field Verified");
    }

    // TC07: Verify login button
    @Test
    public void verifyLoginButton() {

        boolean result = driver.findElement(
                By.id("loginButton")).isDisplayed();

        Assert.assertTrue(result);

        System.out.println("TC07: Login Button Verified");
    }

    // TC08: Verify background color
    @Test
    public void verifyBackgroundColor() {

        String color = driver.findElement(
                By.id("welcomeBox"))
                .getCssValue("background-color");

        Assert.assertEquals(
                color,
                "rgba(173, 216, 230, 1)");

        System.out.println("TC08: Background Color Verified");
    }

    // Close browser after every test
    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}