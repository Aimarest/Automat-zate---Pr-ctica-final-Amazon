package com.selenium.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/drivers/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://google.es");
        driver.manage().window().maximize();
        return driver;
    }
}

