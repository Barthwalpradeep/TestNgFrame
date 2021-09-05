package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public static String highlight;
	public static String headless;
	private OptionsManager optionsManager;
	public static  ThreadLocal<WebDriver> tlDriver = new  ThreadLocal<WebDriver>();
	
	public WebDriver initdriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		
		
		System.out.println("browser name is: " +browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Please Pass the right browser: " +browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
		
	}
	
	
	//This method is to initialize the properties/config file
	
	public Properties initProperties() {
		        Properties prop = null;
                FileInputStream ip = null;
                String env =  System.getProperty("env");
	            	try {
                     if(env == null){
                    	 System.out.println("Usig normal execution env file");
			             ip = new FileInputStream("./src/testResources/Config/Config.properties");
                                    }
                         else{   
                        	 System.out.println("Usig Env execution  file");                         
                          switch (env) {
                          case "qa":
                           ip = new FileInputStream("./src/testResources/Config/qa.Config.properties");
                           break;
                           case "dev":
                           ip = new FileInputStream("./src/testResources/Config/dev.Config.properties");
                           break;
                          default:
                             break;
                          }
                         }
	            	}
			
		                            
                           catch (FileNotFoundException e) {
			                 e.printStackTrace();
		}
                try{
                   prop = new Properties();
                   prop.load(ip);
                   }                
                catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	

}
