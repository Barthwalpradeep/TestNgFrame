package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ConstantsUtils;
import com.qa.opencart.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage {
	
	//this driver is only used for this class  only that why its created as private
	private WebDriver driver;
	private ElementUtils elementutils;
	
	// private By locators:
		private By emailId = By.id("input-email");
		private By password = By.id("input-password");
		private By loginBtn = By.xpath("//input[@value='Login']");
		private By forgotPwdLink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
		private By header = By.cssSelector("div#logo h1 a");
		private By registerLink = By.linkText("Register");
		
		//Constructor of this class need to create
		public LoginPage(WebDriver driver) {
			this.driver = driver;
			elementutils = new ElementUtils(driver);
		}
     
		
		//page Method>> functionality of the page
		//No assertion is included
		
		@Step("Login  page Title testcase")
		public String getLoginPageTitle() {
			return elementutils.waitForTitleContains(ConstantsUtils.LOGIN_PAGE_TITLE, 5);
		}

		public String getPageHeaderText() {
			return elementutils.doGetText(header);
		}

		public boolean isForgotPwdLinkExist() {
			return elementutils.doIsDisplayed(forgotPwdLink);
		}

		public AccountsPage doLogin(String un, String pwd) {
			elementutils.doSendKeys(emailId, un);
			elementutils.doSendKeys(password, pwd);;
			elementutils.doClick(loginBtn);			
			return new AccountsPage(driver);
		}
		
		public RegisterationPage navigateToRegisterPage() {
			elementutils.doClick(registerLink);
			return new RegisterationPage(driver);
		}
}
