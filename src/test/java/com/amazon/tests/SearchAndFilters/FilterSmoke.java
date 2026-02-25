package com.amazon.tests.SearchAndFilters;

import com.amazon.base.BaseTest;
import com.amazon.pages.searchAndFilters;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FilterSmoke  extends BaseTest {

    private static String testQuery = "iphone";

    @BeforeMethod
    public void filterSetup(){
        searchAndFilters sf= new searchAndFilters(driver);
        sf.enterSearchQuery(testQuery);
        sf.clickSearchButton();
    }

@DataProvider(name="priceRange")

    public Object[][] priceRange () {

        return new Object[][]{

                {50000, 53000},
                {40000, 41000}
        };
    }

    @Test(dataProvider="priceRange")

    public void verifyPriceFilterFunctionality(int lr, int hr) throws InterruptedException {
        searchAndFilters sf = new searchAndFilters(driver);
        int settable_lr = sf.setLowerPriceRange(lr);
        int settable_hr =sf.setHigherPriceRange(hr);
        Assert.assertTrue(sf.isPriceWithinTheSetRange(settable_lr,settable_hr),"price filter not working");
        //added price filter test validation
    }

    @DataProvider(name="brandSelector")

    public Object[][] brandSelector () {

        return new Object[][]{

                {"Apple"},
                {"Samsung"},
                {"micromax"}
        };
    }

    @Test(dataProvider = "brandSelector")
    public void verifyBrandFilterFunctionality(String brand) throws InterruptedException {
        searchAndFilters sf = new searchAndFilters(driver);
        sf.selectBrandFilter(brand);
        Assert.assertTrue(sf.isBrandFilterSelected(brand),"Brand filter not functioning");
      //  Thread.sleep(4000);
    }

}






