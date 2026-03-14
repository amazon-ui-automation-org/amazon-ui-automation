package com.amazon.tests.SearchAndFilters;

import com.amazon.base.BaseTest;
import com.amazon.pages.searchAndFilters;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchFunctionalityTest extends BaseTest {

    @DataProvider(name = "meaningfulQuery")

    public Object[][] meaningfulQuery(){
return new Object[][]{
        {"iPhone"},
        {"Nike shoes"},
        {"Roadster tshirts"}
};
    }



    @Test(dataProvider="meaningfulQuery")
    public void verifySearchResultsAreRelevantToMeaningfulSearchQuery(String query){
        searchAndFilters sf = new searchAndFilters(driver);
        sf.enterSearchQuery(query);
        sf.clickSearchButton();

        Assert.assertTrue(sf.isSearchQueryRetained(query),"search query is not retained");
        Assert.assertTrue(sf.isSearchQueryReflectedInResultsPage(query),"search query is not visible in results page");
        //Assert.assertTrue(sf.areResultsRelevant(query),"Results not relevant");
    }

    @Test
    public void verifyUserISRedirectedToHomePageOnBlankSearch() throws InterruptedException {
        searchAndFilters sf = new searchAndFilters(driver);
        sf.enterSearchQuery("");
        sf.clickSearchButton();
        Assert.assertTrue(sf.isHomePageURLLoaded(),"Home page not loaded on blank search");
    }


    @DataProvider(name="queryTypes")
    public Object[][] queryTypes(){
        return new Object[][]{

                {"123"},
                {"abc"},
                {"123abc"},
                {"@@@@"}
        };
    }



    @Test(dataProvider="queryTypes")
    public void verifySearchInputAcceptsDifferentInputTypesWithoutValidationError(String queryTypes) {
        searchAndFilters sf = new searchAndFilters(driver);
       String urlBeforeSearch = sf.getCurrentURL();
        sf.enterSearchQuery(queryTypes);
        sf.clickSearchButton();
        Assert.assertFalse(sf.isURLRefreshed(urlBeforeSearch),"Home page not loaded on blank search");
    }


@DataProvider(name = "specialCharacterQuery")
    public Object[][] specialCharacterQuery(){
        return new Object[][]{
                {"!@#$%^&*()"},
                {"<>?/:;\"'[]\\|`~"}
        };
    }
// comment
    @Test(dataProvider="specialCharacterQuery")
    public void verifySearchInputAcceptsSpecialCharacters(String query){
        searchAndFilters sf = new searchAndFilters(driver);
        String urlBeforeSearch = sf.getCurrentURL();
        sf.enterSearchQuery(query);
        sf.clickSearchButton();
        Assert.assertFalse(sf.isURLRefreshed(urlBeforeSearch),"Home page not loaded on blank search");
        // this assertion is to validate that search is performed and user is not redirected to home page on entering special characters in search input. We are not validating the relevance of search results for special character query as it may vary based on the search algorithm and product catalog.
    }







}
