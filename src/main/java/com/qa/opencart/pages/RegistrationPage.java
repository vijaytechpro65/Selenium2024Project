package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	private ElementUtil eleutil;
	
	public RegistrationPage(WebDriver driver) {
		eleutil= new ElementUtil(driver);
	}
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public boolean accountRegistration(String firstName, String lastName, String email, String telphone,
			String password, String subscribe) {

		eleutil.SendKeys(this.firstName, firstName);
		eleutil.SendKeys(this.lastName, lastName);
		eleutil.SendKeys(this.email, email);
		eleutil.SendKeys(this.telephone, telphone);
		eleutil.SendKeys(this.password, password);
		eleutil.SendKeys(this.confirmpassword, password);

		if (subscribe.equals("yes")) {
			eleutil.doClick(subscribeYes);
		} else {
			eleutil.doClick(subscribeNo);
		}
		eleutil.doClick(agreeCheckBox);
		eleutil.doClick(continueButton);
		String mesg = eleutil.waitForElementToBeVisible(sucessMessg, 5).getText();

		if (mesg.contains(Constants.REGISTER_SUCCESS_MESSG)) {
			eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
			return true;
		}
		return false;

	}

}



