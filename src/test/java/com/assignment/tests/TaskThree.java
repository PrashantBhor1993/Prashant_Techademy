package com.assignment.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class TaskThree {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void searchFlight() throws InterruptedException {
        driver.get("https://www.makemytrip.com/");

        // Handle login popup if visible
        try {
            WebElement popupClose = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@class='commonModal__close']")));
            popupClose.click();
        } catch (Exception e) {
            System.out.println("No popup displayed.");
        }

        // Click Flights tab
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Flights']"))).click();

        // Select Round Trip
        driver.findElement(By.xpath("//li[@data-cy='roundTrip']")).click();

        // Select From city
        WebElement fromCity = driver.findElement(By.id("fromCity"));
        fromCity.click();
        WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@id='fromCity']")));
        fromInput.sendKeys("HYD");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[text()='Hyderabad, India']"))).click();

        // Select To city
        WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@id='toCity']")));
        toInput.sendKeys("MAA");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[text()='Chennai, India']"))).click();

        // Select Departure date
        driver.findElement(By.xpath("//div[@aria-label='Fri Sep 26 2025']/div")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@aria-label='Sat Sep 27 2025']/div")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[text()='Search']")).click();
       // wait.until(ExpectedConditions.urlContains("flights"));
       // System.out.println("Search page displayed successfully!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
