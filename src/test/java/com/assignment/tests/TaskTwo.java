package com.assignment.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class TaskTwo {

    WebDriver driver;
    WebDriverWait wait;
    @Test
    public void searchFlight() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.makemytrip.com/");
        try {
           Thread.sleep(5000);
            driver.findElement(By.xpath("//span[@class=\"commonModal__close\"]")).click();
            Thread.sleep(7000);
        } catch (Exception e) {
            System.out.println("No popup displayed.");
        }
        driver.findElement(By.xpath("//span[text()='Flights']")).click();
        driver.findElement(By.xpath("//li[@data-cy='roundTrip']")).click();

        driver.findElement(By.xpath("//input[@id='fromCity']")).sendKeys("HYD");
        driver.findElement(By.xpath("//p[text()='Hyderabad, India']")).click();
        driver.findElement(By.xpath("//input[@id='toCity']")).sendKeys("MAA");
        driver.findElement(By.xpath("//p[text()='Chennai, India']")).click();

        driver.findElement(By.xpath("//div[@aria-label='Fri Sep 26 2025']/div")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@aria-label='Sat Sep 27 2025']/div")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='Search']")).click();

        wait.until(ExpectedConditions.urlContains("flights"));

        System.out.println("Search page is displayed as expected");
    }
}
