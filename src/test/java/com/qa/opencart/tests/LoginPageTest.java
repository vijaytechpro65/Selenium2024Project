package com.qa.opencart.tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.qa.opencart.utils.CommonErrors;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic id in jira 100: Design opencart application- LoginPage")
@Story("There is a user story under this epic 101:open cart login design with multiple features")
//@Listeners( TestAllureListener.class)some times the screenshot is not coming you just add this listener above your test class
public class LoginPageTest extends BaseTest {
	@Description("LoinPage Title Test  ")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void gettitleTest() {
		Assert.assertTrue(loginpage.getTitle(),CommonErrors.NO_SUCH_ELEMENT_EXCEPTION);
		
	}
	@Test(priority=2)
	public void UrlTest() {
		Assert.assertTrue(loginpage.getLoginpageUrl());
	
	}

    @Test(priority=3)
    public void testRegisterLinkExists() {
        Assert.assertTrue(loginpage.RegisterLinkExists());
    }

    @Test(priority=4)
    public void testForgottenPasswordLink() {
        boolean isLinkVisible = loginpage.isForgottenPasswordLink();
        System.out.println("Forgotten Password link visibility: " + isLinkVisible);
        Assert.assertTrue(isLinkVisible, "Forgotten Password link is not displayed.");
    }
    @Test(priority=5)
    public void testWishListLink() {
        Assert.assertTrue(loginpage.isWishListLink());
    }
    @Test(priority=6)
    public void NewCoustomerLinkTest() {
    	String actval=loginpage.IsNewCoustomer();
    	Assert.assertEquals(actval, Constants.LOGIN_PAGE_NEW_CUSTOMER);
    	
    }

   
    @Test(priority=7)
    public void testDoLogin() {
       accountspage= loginpage.dologin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
      Assert.assertTrue(accountspage.isSearchFieldDisplayed());
}

}
