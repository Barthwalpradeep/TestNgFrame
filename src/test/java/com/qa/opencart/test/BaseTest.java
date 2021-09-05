package com.qa.opencart.test;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterTest;

import com.qa.democart.pages.AccountsPage;
import com.qa.democart.pages.LoginPage;
import com.qa.democart.pages.ProductInfoPage;
import com.qa.democart.pages.RegisterationPage;
import com.qa.democart.pages.ResultsPage;
import com.qa.opencart.factory.DriverFactory;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	Properties prop;
	
	LoginPage loginpage;
	AccountsPage accPage;
	ResultsPage resultsPage;
	ProductInfoPage productInfoPage;
	RegisterationPage regPage; 
	SoftAssert softAssert;
	
	
	@BeforeTest
	public void setUp() {
		softAssert = new SoftAssert();
		df = new DriverFactory();
		prop = df.initProperties();
		driver = df.initdriver(prop);
		loginpage = new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
