package com.qa.opencart.tests;



import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountPageTest extends BaseTest {
	@BeforeClass
	public void AccountpageSetup() {
		accountspage = loginpage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	}
	 @Test
	    public void verifyAccountsPageTitleTest() {
	        String actualTitle = accountspage.APgetTitle();
	        Assert.assertEquals(actualTitle, Constants.ACCOUNTS_PAGE_TITLE);
	    }
	 @Test
	    public void verifyAccountPageHeaderTest() {
	        boolean actualHeader = accountspage.isAccountPageHeaderDisplayed();
	        Assert.assertTrue(actualHeader);
	    }
	 @Test
	    public void verifyAccountLinkWithNormalizeSpace() {
		Assert.assertTrue(accountspage.getAccountLinkNormalized());
	 }
	 @Test
	    public void verifyTermsAndConditionsLinkTextTest() {
	        String actualLinkText = accountspage.isTermsAndConditionslink();
	        Assert.assertEquals(actualLinkText, "Terms & Conditions");
	    }
	 @Test
	    public void verifyLogoutLinkDisplayedTest() {
	        boolean isDisplayed = accountspage.islogoutlinkDisplayed();
	        Assert.assertTrue(isDisplayed);
	    }
	 @Test
	    public void verifySearchFieldDisplayedTest() {
	        boolean isDisplayed = accountspage.isSearchFieldDisplayed();
	        Assert.assertTrue(isDisplayed);
	    }
	 @Test
	    public void verifyMainHeadersTextTest() {
	        List<String> headersText = accountspage.getMainHeadersText();
	        // You can use a list to store expected header texts and compare them
	        List<String> expectedHeaders = List.of("My Account", "My Orders", "My Affiliate Account","Newsletter");
	        Assert.assertEquals(headersText, expectedHeaders, "Main headers text is incorrect");
	    }
	 
	 @Test
	 public void verifyTestLinksTextTest() {
	     List<String> actualTestLinksText = accountspage.getTestLinksText();
	     // Expected list with the exact text of the link
	     List<String> expectedTestLinksText = Constants.EXPECTED_TEST_LINKS_TEXT;
	     Assert.assertEquals(actualTestLinksText, expectedTestLinksText);
	 }
	 @DataProvider
	    public Object[][] getSearchData() {
	        return new Object[][] {
	            {"MacBook"},
	           // {"Apple"},
	            //{"Samsung"},
	        };
	    }
	  @Test(dataProvider="getSearchData")
	    public void verifySearchFunctionalityTest(String productName) {
	      searchresultpage=  accountspage.doSearch(productName);
	     Assert.assertTrue(searchresultpage.isShowingPageDisplayed());
	    
	        
	  }
	  

}
