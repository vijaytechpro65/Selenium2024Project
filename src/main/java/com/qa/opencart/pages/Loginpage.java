package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class Loginpage {
//	declare private driver
	private WebDriver driver;
	private ElementUtil eleutil;
	
//	page constructor
	public Loginpage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	// Locators
    private final By emailField = By.id("input-email");
    private final By passwordField = By.id("input-password");
    private final By loginButton = By.xpath("//input[@value='Login']");
    private final By registerLink = By.linkText("Register");
    private final By forgottenPasswordLink = By.linkText("Forgotten Password");
    private final By wishListLink = By.partialLinkText("Wish List");
    private final By NewCoustomerLink =By.xpath("//h2[normalize-space()='New Customer']");

// page actions
   @Step("Register Link is there or not.....")
    public boolean RegisterLinkExists() {
        return eleutil.waitForLinkToBeDisplayed(registerLink, Constants.DEFAULT_TIME_OUT);
        
    }
    
    public Boolean getTitle() {
    	return eleutil.waitForPageTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
    }
    
    public boolean getLoginpageUrl() {
    	return eleutil.waitForUrlToContain(Constants.LOGIN_PAGE_URL, Constants.DEFAULT_TIME_OUT);
    }

    public boolean isForgottenPasswordLink() {
       return eleutil.waitForLinkToBeDisplayed(forgottenPasswordLink, 15);
        
    }
        

    public boolean isWishListLink() {
        return eleutil.doIsDisplayed(wishListLink);
        
    }
    public String IsNewCoustomer() {
    	return eleutil.doGetText(NewCoustomerLink);
    }

   
    public AccountsPage dologin(String un,String pwd) {
    	eleutil.SendKeys(emailField, un);
    	eleutil.SendKeys(passwordField, pwd);
    	eleutil.doClick(loginButton);
    	return new AccountsPage(driver);
    }
    public RegistrationPage GoClickRegistration() {
    	eleutil.doClick(registerLink);
    	return new RegistrationPage(driver);
    	
    }
}
    

