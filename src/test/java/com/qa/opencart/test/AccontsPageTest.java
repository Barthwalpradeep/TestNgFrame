package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.ConstantsUtils;

public class AccontsPageTest extends BaseTest{

	@BeforeClass
	public void accPagesetup() {
		//do login method is returning the account page object
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority = 1)
	public void accPageTitleTest() {
		String Title = accPage.getAccPageTitle();
		Assert.assertEquals(Title,ConstantsUtils.ACCOUNT_PAGE_TITLE);
	}
	@Test (priority = 2)
	public void accPageHeaderTest() {
		String pageHeader = accPage.getAccPageHeader();
		Assert.assertEquals(pageHeader,ConstantsUtils.LOGIN_PAGE_HEADER);
	}
	@Test(priority = 3)
	public void accSectionsListTest() {
		List<String> actAccSecList = accPage.getAccountSectionsList();
		System.out.println("actual sections: " + actAccSecList);
		Assert.assertEquals(actAccSecList, ConstantsUtils.EXPECTED_ACC_SEC_LIST);
	}
	@Test(priority = 4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@DataProvider
	public Object[][]getSearchData(){
		return new Object[][] {
			{"MacBook Pro"},
			{"MacBoo Air"},
			{"Apple"}
		};
	}
	
	@Test(priority = 5, dataProvider = "getSearchData")	
	public void searchTest(String productName) {
		resultsPage = accPage.doSearch(productName);
		String resultHeader = resultsPage.getSearchPageHeader();
		System.out.println("result header is : " + resultHeader);
		Assert.assertTrue(resultHeader.contains(productName));
	}
	
	//This test return productinfopage hence we gave product page info reference
	@DataProvider
	public Object[][] getProductSelectData(){
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"Apple","Apple Cinema 30\""}
			};
		
	}
	
	@Test(priority = 6 , dataProvider = "getProductSelectData")
	public void selectProductTest(String productname ,String mainProductName) {
		resultsPage = accPage.doSearch(productname);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		String header = productInfoPage.getProductHeaderText();
		//String Price = productInfoPage.getProductPrice();
		System.out.println("product header : " + header);
		//System.out.println("product Price : " + Price);
		Assert.assertEquals(header, mainProductName);
		//Assert.assertEquals(Price, "$2,000.00");
		
	}
	
	
}
