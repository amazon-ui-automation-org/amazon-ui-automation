package com.amazon.factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static  ThreadLocal<String> deviceType = new ThreadLocal<>();

    public static void setDevice(String device) {
        deviceType.set(device);
    }


//get device


    public static String getDevice() {
        return deviceType.get();
    }


    // Set driver
    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    // Get driver
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Quit + remove
    public static void remove() {
        driver.remove();
    }

    public static void removeDevice() {
        deviceType.remove();
    }


}