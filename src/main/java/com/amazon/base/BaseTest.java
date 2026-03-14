package com.amazon.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void setup(){
    /* driver= new ChromeDriver();
     driver.manage().window().maximize();
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     driver.get("https://www.amazon.in/");*/
        // Automatically downloads matching ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Run browser in headless mode (no UI)
        options.addArguments("--headless=new");

        // Required for CI environments
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        driver.get("https://www.amazon.in/");

    }
    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
        }


    }

}