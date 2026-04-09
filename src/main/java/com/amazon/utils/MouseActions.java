package com.amazon.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseActions {
    private  Actions actions ;


    public MouseActions(WebDriver driver) {
       actions = new Actions(driver);
    }

    public  void mouseHover(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public  void mouseClick(WebElement element) {
       actions.doubleClick(element).perform();
    }

    public  void RightClick(WebElement element) {
        actions.contextClick(element).perform();
    }

    public  void dragAndDrop(WebElement src, WebElement dest) {
        actions.dragAndDrop(src,dest).perform();
    }




}
