package com.qa.opencart.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
//	declare private driver
	private WebDriver driver;
	private ElementUtil eleutil;
	
//	account page constructor
	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
// locators
	private By AddToCartBtn=By.xpath("//a[text()='MacBook']/../../following-sibling::div[@class='button-group']/button[@type='button']");
	private By ShortByDropdown=	By.xpath("//select[@id='input-sort']");
	private By ShowingPage=	By.xpath("//div[contains(text(), 'Showing 1 to 3 of 3 (1 Pages)')]");		
	private By SearchpageTitle=By.xpath("//h1[text()='Search - macbook']");
	private By prodResults=By.xpath("//div[@class='product-thumb']//h4/a");
//pageActions	
	
	public int getAddToCartButtonCount() {
	    return eleutil.getElements(AddToCartBtn).size();
	}
	public List<WebElement> getAllSortOptions() {
	    WebElement dropdownElement = eleutil.waitForElementToBeClickable(ShortByDropdown, Constants.DEFAULT_TIME_OUT);
	    Select select = new Select(dropdownElement);
	    return select.getOptions(); // Get all options as a list
		}
	
	public boolean isShowingPageDisplayed() {
	     return eleutil.doIsDisplayed(ShowingPage);
	    
	}

	public String getSearchPageTitle() {
	    WebElement titleElement = eleutil.waitForElementToBeVisible(SearchpageTitle, Constants.DEFAULT_TIME_OUT);
	    return titleElement.getText();
	}
	public ProductInfoPage selectProduct(String productName) {
	    List<WebElement> prodList = eleutil.waitForElementsToBeVisible(prodResults, 10);
	    
	    for (WebElement e : prodList) {
	        String text = e.getText().trim();  // Trim to avoid whitespace issues
	        System.out.println("Product found: " + text);
	        
	        if (text.equalsIgnoreCase(productName)) {  // Case insensitive comparison
	            try {
	                e.click();  // Attempt to click the product element
	            } catch (Exception clickException) {
	                System.out.println("Click failed, attempting JavaScript click");
	                // Use JavaScript executor as a fallback
	                JavascriptExecutor js = (JavascriptExecutor) driver;
	                js.executeScript("arguments[0].click();", e);
	            }
	            break;
	        }
	    }
	    return new ProductInfoPage(driver);
	}

	}


	


