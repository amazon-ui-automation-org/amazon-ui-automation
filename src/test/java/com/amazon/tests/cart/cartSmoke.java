package com.amazon.tests.cart;

import com.amazon.base.BaseTest;
import com.amazon.pages.Cart;
import com.amazon.pages.searchAndFilters;
import org.testng.Assert;
import org.testng.annotations.Test;

public class cartSmoke extends BaseTest {
   @Test
    public void verifyUserCanAddProductTOCart() {
        searchAndFilters sf = new searchAndFilters(driver);
        Cart c = new Cart(driver);
        sf.enterSearchQuery("iphone");
        sf.clickSearchButton();
        c.addOneItemTOCart();
        c.openCartPage();
        Assert.assertTrue(c.isCartPageNonEmpty(),"cart functionality broken");

// added comment
    }
}