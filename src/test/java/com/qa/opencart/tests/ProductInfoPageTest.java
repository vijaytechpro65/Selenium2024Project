package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ProductInfoPageTest extends BaseTest {
	@BeforeClass
	public void SearchResultPageSetup() {
		accountspage = loginpage.dologin(prop.getProperty("username"), prop.getProperty("password"));
		searchresultpage = accountspage.doSearch("macbook");
        productinfopage = searchresultpage.selectProduct("MacBook Pro");
	}
	@Test
	public void verifyProdInfoTitleTest() {
	    String actualTitle = productinfopage.ProdInfoTitle();
	    String expectedTitle = "MacBook Pro";
	    Assert.assertEquals(actualTitle, expectedTitle, "Product title does not match!");
	}
	@Test
	public void verifySingleImageDisplayedTest() {
	    Assert.assertTrue(productinfopage.singleIMG(), "Product image is not displayed!");
	}
	@Test
	public void verifyProductPriceTest() {
	    String expectedPrice = "$2,000.00";
	    String actualPrice = productinfopage.getProductPrice();
	    Assert.assertEquals(actualPrice, expectedPrice, "Product price does not match!");
	}
	@Test
	public void verifyReviewTabDisplayedTest() {
	    Assert.assertTrue(productinfopage.ReviewTab(), "Review tab is not displayed!");
	}
	@Test
	public void verifyQualityFieldDisplayedTest() {
	    Assert.assertTrue(productinfopage.QualityField(), "Quantity field is not displayed!");
   }
	
	@Test
	public void productInfoTest() {
		Map<String, String> actProductInfoMap = productinfopage.getProductInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		// Soft AssertS
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();
	}
	

}




