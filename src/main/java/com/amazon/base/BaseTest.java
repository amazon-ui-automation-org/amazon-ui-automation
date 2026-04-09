package com.amazon.base;

import com.amazon.Listeners.Listener;
import com.amazon.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

    @Listeners({AllureTestNg.class, Listener.class})
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {

        // ✅ Get environment from Maven (default = qa)
        String env = System.getProperty("env", "qa");
        String browser = System.getProperty("browser", "chrome");
        // ✅ Get URL from config file
        String baseUrl = ConfigReader.get(env + ".url");

        System.out.println("Running on ENV: " + env);
        System.out.println("Base URL: " + baseUrl);


      /*  // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Headless mode for CI
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");


        driver = new ChromeDriver(options);*/
        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Invalid browser: " + browser);
        }
//        driver = new ChromeDriver();

        driver.manage().window().maximize();

        // ✅ Use dynamic URL
        driver.get(baseUrl);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}