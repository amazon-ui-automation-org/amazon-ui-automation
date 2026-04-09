package com.amazon.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotUtil {


    public static String takeScreenshot(WebDriver driver, String testName) {


        // Timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Clean name
        String safeTestName = testName.replaceAll("[^a-zA-Z0-9]", "_");

        // File name
        String fileName = safeTestName + "_" + timestamp + ".png";

        // Path
        String projectPath = System.getProperty("user.dir");
        String directoryPath = projectPath + "/screenshots/";
       String fullPath  ;
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            fullPath = directoryPath + fileName;

            // Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);


            File destFile = new File(fullPath);
            FileHandler.copy(srcFile, destFile);


            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // ✅ 3. Attach to Allure (FINAL FIX)
            Allure.getLifecycle().addAttachment(
                    "Failure Screenshot",
                    "image/png",
                    "png",
                    screenshot
            );

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return fullPath;
    }
}