package com.qa.opencart.test;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.democart.pages.AccountsPage;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.ConstantsUtils;
import com.qa.opencart.utils.ErrorsUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 100: Design Login page for open cart application....")
@Story("US 101: Login page with differnet features.....")
@Listeners(TestAllureListener.class)

public class LoginPageTest extends BaseTest {
	
	@Description("This is my login application scenario")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginpage.getLoginPageTitle();
		System.out.println("lp title is: " + title);
		Assert.assertEquals(title, ConstantsUtils.LOGIN_PAGE_HEADER,ErrorsUtils.TITLE_ERROR_MESSG);		
	}
	
	@Test(priority = 2)
	public void loginPageHeaderTest() {
		String header = loginpage.getPageHeaderText();
		System.out.println("lp header is: " + header);
		Assert.assertEquals(header, ConstantsUtils.LOGIN_PAGE_HEADER, ErrorsUtils.HEADER_ERROR_MESSG);
	}
	
	@Description("This is my validation scenario")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	
	@Test(priority = 4)
	public void doLogin() {
		AccountsPage accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
