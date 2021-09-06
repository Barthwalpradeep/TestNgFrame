package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtils;

public class Cartpage {
	private WebDriver driver;
	private ElementUtils elementutils;
	private By searchButton = By.cssSelector("div#search button");
	private By searchField = By.name("search");
	
	
	public Cartpage(WebDriver driver) {
		this.driver = driver;
		elementutils = new ElementUtils(driver);	
			
	}
	public ResultsPage doSearch(String productName) {
		System.out.println("searching the product: " + productName);
		elementutils.doSendKeys(searchField, productName);
		elementutils.doClick(searchButton);
		return new ResultsPage(driver);
	}

}
