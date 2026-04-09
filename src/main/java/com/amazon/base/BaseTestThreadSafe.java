package com.amazon.base;

import com.amazon.Listeners.Listener;
import com.amazon.factory.DriverFactory;
import com.amazon.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;


@Listeners({AllureTestNg.class, Listener.class})
public class  BaseTestThreadSafe {

    Logger log = LogManager.getLogger(BaseTestThreadSafe.class);





    @Parameters({"browser", "device"})
    @BeforeMethod
    public void setup(@Optional String browserParam,
                      @Optional String deviceParam) {

        String env = System.getProperty("env", "qa");
        log.info("Entering email: {}", browserParam);
        log.info("Entering device: {}", deviceParam);
        // Priority: XML param → System property → default
        String browser = browserParam != null && !browserParam.isEmpty() ? browserParam :
                System.getProperty("browser", "chrome");

        String device = deviceParam != null && !deviceParam.isEmpty()  ? deviceParam :
                System.getProperty("device", "desktop");

        String baseUrl = ConfigReader.get(env + ".url");

        WebDriver driver;
        DriverFactory.setDevice(device);

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                if (device.equalsIgnoreCase("mobile")) {
                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", "Samsung Galaxy A51/71");

                    options.setExperimentalOption("mobileEmulation", mobileEmulation);
                    log.info("Running on CHROME MOBILE");
                } else {
                    log.info("Running on CHROME DESKTOP");
                }
//                options.addArguments("--headless=new");
//                options.addArguments("--no-sandbox");
//                options.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(options);
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

        // ✅ ThreadLocal storage
        DriverFactory.setDriver(driver);

        if (!device.equalsIgnoreCase("mobile")) {
            DriverFactory.getDriver().manage().window().maximize();
        }

        DriverFactory.getDriver().get(baseUrl);
    }

        @AfterMethod
        public void teardown() {
            if (DriverFactory.getDriver() != null) {
                DriverFactory.getDriver().quit();
                DriverFactory.remove();
                DriverFactory.removeDevice();
            }
        }


        public static boolean isMobile() {
            return "mobile".equalsIgnoreCase(DriverFactory.getDevice());
        }
    }


