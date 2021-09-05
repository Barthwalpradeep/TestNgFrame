package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.qa.opencart.utils.ConstantsUtils;
import com.qa.opencart.utils.ExcelUtils;

public class RegisterpageTest extends BaseTest {
	
	@BeforeClass
	public void regSetup() {
		regPage = loginpage.navigateToRegisterPage();
				}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "Pradeep"+random.nextInt(5000)+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		return ExcelUtils.getTestData(ConstantsUtils.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getRegTestData")
	public void registrationTest(String firstname,String lastname ,String telephone, String password,String subscribe) {
		Assert.assertTrue(regPage.accountRegistration(firstname,lastname,getRandomEmail(),telephone,password,subscribe));
	}

}
