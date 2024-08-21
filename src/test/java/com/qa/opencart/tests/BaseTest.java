package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.Loginpage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	Loginpage loginpage;
	AccountsPage accountspage;
	SearchResultPage searchresultpage;
	ProductInfoPage productinfopage;
	RegistrationPage registrationpage;
	SoftAssert softAssert;
	Properties prop;
	
	
	@BeforeTest
	public void setup() {
		df=new DriverFactory();
		prop= df.init_prop();
		driver=df.init_driver(prop);
		loginpage=new Loginpage(driver);
	    softAssert = new SoftAssert();
		
	}
	
    @AfterTest
    public void tearDown() {
    	driver.quit();
    	
    }
}
