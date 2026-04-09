package com.amazon.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement waitForElementToBeVisible(By locator) {
       return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public WebElement waitForElementToBeClickable(WebElement element) {
       return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public WebElement waitForElementToBePresent(By locator) {
       return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public boolean waitForURLToBe(String URL) {
          return  wait.until(ExpectedConditions.urlToBe(URL));
    }



}

