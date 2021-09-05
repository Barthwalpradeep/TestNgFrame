package com.qa.democart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ConstantsUtils;
import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtils elementutils;
	
	private By accSections = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo h1 a");
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementutils = new ElementUtils(driver);	
			
	}
	public String getAccPageTitle() {
		return elementutils.waitForTitleIs(ConstantsUtils.ACCOUNT_PAGE_TITLE, 5);
	}
	
	public String getAccpageUrl() {
		return elementutils.waitForUrlContains(ConstantsUtils.ACCOUNT_PAGE_URL_FRACTION, 5);
	}
	
	public String getAccPageHeader() {
		return elementutils.doGetText(header);
	}

	
	public List<String> getAccountSectionsList() {
		List<String> accSecValueList = new ArrayList<String>();
		List<WebElement> accSecList = elementutils.getElements(accSections);
		for(WebElement e : accSecList) {
			accSecValueList.add(e.getText());
		}
		//Collections.sort(accSecValueList);
		return accSecValueList;
	}
	
	public boolean isLogoutLinkExist() {
		return elementutils.doIsDisplayed(logoutLink);
	}
	
	
	public ResultsPage doSearch(String productName) {
		System.out.println("searching the product: " + productName);
		elementutils.doSendKeys(searchField, productName);
		elementutils.doClick(searchButton);
		return new ResultsPage(driver);
	}
	

}
