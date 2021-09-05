package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ConstantsUtils;
import com.qa.opencart.utils.ElementUtils;

public class RegisterationPage {
	private WebDriver driver;
	private ElementUtils elementutils;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	public RegisterationPage(WebDriver driver) {
		this.driver = driver;
		elementutils = new ElementUtils(driver);
	}

	
	public boolean accountRegistration(String firstName, String lastName, String email, String telephone,
			String password, String subscribe) {
		elementutils.doSendKeys(this.firstName, firstName);
		elementutils.doSendKeys(this.lastName, lastName);
		elementutils.doSendKeys(this.email, email);
		elementutils.doSendKeys(this.telephone, telephone);
		elementutils.doSendKeys(this.password, password);
		elementutils.doSendKeys(this.confirmpassword, password);
		if(subscribe.equalsIgnoreCase("Yes")) {
			elementutils.doClick(subscribeYes);
		}else 
		{
			elementutils.doClick(subscribeNo);
		}
		elementutils.doClick(agreeCheckBox);
		elementutils.doClick(continueButton);
		
		String mesg = elementutils.waitForElementPresence(sucessMessg, ConstantsUtils.DEFAULT_TIME_OUT).getText();
		if(mesg.contains(ConstantsUtils.REGISTER_SUCCESS_MESSG)) {
			elementutils.doClick(logoutLink);
			elementutils.doClick(registerLink);
			return true;			
		}
		return false;
		
		
	}
}
