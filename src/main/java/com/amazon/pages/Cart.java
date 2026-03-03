package com.amazon.pages;

import com.amazon.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Cart {
   private WebDriver driver;
  private WaitUtils wait;
   private By prodCard = By.xpath("//div[contains(@class,'puis-card-container')]");
    private By addToCartButtonLoc = By.name("submit.addToCart");
    private By cartPageLink = By.id("nav-cart-count-container");
    private By cartPageContainer= By.xpath("//ul[@data-name='Active Items']");
    public Cart(WebDriver driver){
       this.driver = driver;
        this.wait = new WaitUtils(driver);

    }

    public void addOneItemTOCart(){
        wait.waitForElementToBeVisible(prodCard);
        List<WebElement> prodCards = driver.findElements(prodCard);
        WebElement firstCard = prodCards.get(0);
        wait.waitForElementToBeClickable(firstCard.findElement(addToCartButtonLoc));
        //can improve later to check if add to cart button is present in card
        firstCard.findElement(addToCartButtonLoc).click();
//       WebElement addToCartButton = firstCard.findElement(addToCartButtonLoc);
//        wait.waitForElementToBeClickable(addToCartButton);
//          addToCartButton.click();


    }

    public void openCartPage(){

     wait.waitForElementToBeClickable(cartPageLink);
     driver.findElement(cartPageLink).click();

    }

    public boolean isCartPageNonEmpty(){
        wait.waitForElementToBeVisible(cartPageContainer);
        return driver.findElement(cartPageContainer).isDisplayed();
    }








}
