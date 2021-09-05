package com.qa.democart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtils;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtils elementutils;

	private By searchHeader = By.cssSelector("div#content h1");
	private By productResults = By.cssSelector("div.caption a");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		elementutils = new ElementUtils(driver);
	}

	public String getSearchPageHeader() {
		return elementutils.doGetText(searchHeader);
	}

	public int getSearchProductListsCount() {
		return elementutils.getElements(productResults).size();
	}


	public ProductInfoPage selectProduct(String mainProductName) {
		List<WebElement> searchList = elementutils.getElements(productResults);
		for(WebElement e : searchList) {
			if(e.getText().trim().equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}