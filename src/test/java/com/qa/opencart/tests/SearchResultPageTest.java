package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class SearchResultPageTest extends BaseTest {
	@BeforeClass
	public void SearchResultPageSetup() {
		accountspage = loginpage.dologin(prop.getProperty("username"), prop.getProperty("password"));
		searchresultpage = accountspage.doSearch("macbook");
	}
	
	@Test(priority=1)
	public void verifyAddToCartButtonCount() {
	    int actualButtonCount = searchresultpage.getAddToCartButtonCount();
	    int expectedButtonCount = 3;
	    Assert.assertEquals(actualButtonCount, expectedButtonCount);
	}
	
	@Test(priority=2)
	public void verifyAllSortOptionsTest() {
	    // Retrieve all dropdown options
	    List<WebElement> allOptions = searchresultpage.getAllSortOptions();
	    int expectedOptionCount =Constants.DROPDOWN_COUNT;
	    int actualOptionCount = allOptions.size();
	    Assert.assertEquals(actualOptionCount, expectedOptionCount, "The number of dropdown options is incorrect.");
	}
	@Test(priority=3)
	public void verifyShowingPageIsDisplayed() {
	    // Check if the Showing Page element is displayed
	    boolean isDisplayed = searchresultpage.isShowingPageDisplayed();
	    Assert.assertTrue(isDisplayed, "The 'Showing Page' element is not displayed.");
	}
	@Test(priority=4)
	public void verifySearchPageTitle() {
	    // Define the expected title text
	    String expectedTitle =Constants.SEARCH_PAGE_EXP_TITLE;
	    String actualTitle = searchresultpage.getSearchPageTitle();
	    Assert.assertEquals(actualTitle, expectedTitle, "The search page title is not as expected.");
	}
//	@DataProvider
//    public Object[][] getSearchData() {
//        return new Object[][] {
//            {"MacBook","MacBook Pro"},
//          {"Apple","Apple Cinema 30\""},
//        {"Samsung","Samsung SyncMaster 941BW"},
//        };
  //  }
	 @Test(priority=5)
	    public void SelectprodTest() {
	        productinfopage = searchresultpage.selectProduct("MacBook Pro");
	       Assert.assertTrue(productinfopage.ReviewTab());
	        
	    }
	 
	
   }

