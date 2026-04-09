
package com.amazon.Listeners;

import com.amazon.base.BaseTest;
import com.amazon.factory.DriverFactory;
import com.amazon.utils.ScreenShotUtil;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;

public class Listener implements ITestListener {

    private static final Logger log = LogManager.getLogger(Listener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        captureAndAttach(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        log.info("Test FAILED due to TIMEOUT: {}", result.getName());
        captureAndAttach(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test PASSED: {}", result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {

        log.info("Test STARTED: {}", result.getName());
    }

    // 🔥 MAIN METHOD
    private void captureAndAttach(ITestResult result) {
        try {
          //  WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
            WebDriver driver= DriverFactory.getDriver();

            if (driver == null) {
                log.error("Driver is null, cannot capture screenshot");
                return;
            }

            String testName = result.getName();

            // ✅ 1. Save locally (optional but useful)


            // ✅ 2. Capture screenshot as bytes

            log.info("Screenshot attached to Allure for: {}", testName);

            String path = ScreenShotUtil.takeScreenshot(driver, testName);
            log.info("Screenshot saved locally at: {}", path);

        } catch (Exception e) {
            log.error("Error while capturing screenshot", e);
        }



//    private static final Logger log = LogManager.getLogger(Listener.class);
//
//        @Override
//        public void onTestFailure(ITestResult result) {
//            System.out.println("🔥 Listener Triggered for: " + result.getName());
//
//            WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
//
//            if (driver != null) {
//
//                // ✅ keep local save
//                ScreenShotUtil.takeScreenshot(driver, result.getName());
//
//                // 🔥 BEST WAY → attach using annotation
//                ScreenShotUtil.attachScreenshot(driver, result.getName());
//
//                System.out.println("✅ Screenshot attached using @Attachment");
//
//            } else {
//                System.out.println("❌ Driver is NULL");
//            }
//        }
//
//        @Override
//        public void onTestSuccess(ITestResult result) {
//            log.info("Test PASSED: " + result.getName());
//        }
//
//        @Override
//        public void onTestStart(ITestResult result) {
//            log.info("Test STARTED: " + result.getName());
//        }
//    }

    }
}
