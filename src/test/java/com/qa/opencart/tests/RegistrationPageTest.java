package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	@BeforeClass
	public void regitrationpageSetUp() {
		registrationpage=loginpage.GoClickRegistration();
	}
	
	public String getRandomEmail() {
		Random randomGenerator = new Random();
		String email = "septautomation"+randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}

	
	 @DataProvider
	    public Object[][] getUserData() {
	        String sheetName = "registration"; // Replace with your actual sheet name
	        ExcelUtil.initExcel(sheetName);

	        // Fetch data as a 2D array
	        Object[][] userData = ExcelUtil.getDataAsArray();

	        // Close the workbook
	        ExcelUtil.close();

	        return userData;
	    }
	
	 @Test(dataProvider = "getUserData")
	    public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
	        Assert.assertTrue(registrationpage.accountRegistration(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	    }

	   
	}

