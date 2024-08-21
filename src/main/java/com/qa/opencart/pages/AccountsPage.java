package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
//	declare private driver
	private WebDriver driver;
	private ElementUtil eleutil;
	
//	account page constructor
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	//Locators
	private By Accountspageheader =By.xpath("//img[@title='naveenopencart']");
	private By Mainheaders =By.xpath("//h2[text()='My Account']//parent::div/h2");
	private By Testlinks =By.xpath("//a[text()='MP3 Players']/../div[@class='dropdown-menu']//ul//li//a");
	private By Logoutlink =By.xpath("//a[@class='list-group-item' and text()='Logout']");
	private By TermsAndConditionslink =By.xpath("//a[contains(text(), 'Terms & Conditions')]");
	private By SearchField=By.xpath("//input[@name='search']");
	private By button =By.xpath("//button[@type='button' and @class='btn btn-default btn-lg']");
	private By Mp3players=By.xpath("//a[text()='MP3 Players']");
	
	//page Actions
	public String APgetTitle() {
		return eleutil.waitForTitleToBe(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
		
	public boolean isAccountPageHeaderDisplayed() {
	    return eleutil.doIsDisplayed(Accountspageheader);
	}
	public String isTermsAndConditionslink() {
	    return eleutil.doGetText(TermsAndConditionslink);
	}
	
	public boolean islogoutlinkDisplayed() {
	    return eleutil.doIsDisplayed(Logoutlink);
	}
	public void logout() {
		if(islogoutlinkDisplayed()) {
			eleutil.doClick(Logoutlink);
		}
	}
	public boolean isSearchFieldDisplayed() {
	    return eleutil.doIsDisplayed(SearchField);
	}
	public List<String> getMainHeadersText() {
	    List<WebElement> headers = eleutil.waitForElementsToBeVisible(Mainheaders, 10);
	    List<String> headersText = new ArrayList<>();
	    for (WebElement e : headers) {
	       String text= e.getText();
	       headersText.add(text);
	    }
	    return headersText;
	}
	
	
	public List<String> getTestLinksText() {
	    WebElement mp3Dropdown = eleutil.getElement(Mp3players);
	    
	    // Click to open the dropdown
	    Actions actions = new Actions(driver);
	    actions.moveToElement(mp3Dropdown).click().perform();
	    
	    // Wait for the elements to be visible
	    List<WebElement> testLinks = eleutil.waitForElementsToBeVisible(Testlinks, 10);

	    // Get the text of each link
	    List<String> testLinksText = new ArrayList<>();
	    for (WebElement link : testLinks) {
	        testLinksText.add(link.getText());
	    }

	    return testLinksText;
	}

	public SearchResultPage doSearch(String prodName) {
		eleutil.SendKeys(SearchField, prodName);
		eleutil.doClick(button);
		return new SearchResultPage(driver);
		
	
}

}
