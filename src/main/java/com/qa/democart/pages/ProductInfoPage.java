package com.qa.democart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtils elementutils;
	private By productHeader = By.cssSelector("div#content h1");	
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private Map<String ,String> productInfomap;
	
	public ProductInfoPage (WebDriver driver) {
		this.driver = driver;
		elementutils = new ElementUtils(driver);
		
	}
	
	public String getProductHeaderText() {
	 return elementutils.doGetText(productHeader);
	}
	
	public String getProductPrice() {
		 return elementutils.doGetText(productPriceData);
		}
	
	public int getProductImagesCount() {
		return elementutils.getElements(productImages).size();
	}
	
	public Map<String, String> getProductInfo() {		
		productInfomap = new HashMap<String ,String>();
		productInfomap.put("name", getProductHeaderText());
		
		List<WebElement> metaDataList = elementutils.getElements(productMetaData);
			for(WebElement e : metaDataList) {
			String meta[] = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfomap.put(metaKey, metaValue);
			}
			
			//Price data
			
			List<WebElement> priceList = elementutils.getElements(productPriceData);
			String price = priceList.get(0).getText().trim();
			String exTaxPrice = priceList.get(1).getText().trim();
			productInfomap.put("price", price);
			productInfomap.put("ExTaxPrice", exTaxPrice);
			
			return productInfomap;
			
			
		
		
	}
	
	

}
