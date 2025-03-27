package com.qa.opencart.Factory;


import java.io.File;




import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	 BrowserOptions browserOptions;
	 private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	public WebDriver init_driver(Properties prop) {
		String browserName=prop.getProperty("browser").trim();
		System.out.println("browser name is :"+browserName);
		highlight=prop.getProperty("highlight");
		 browserOptions=new  BrowserOptions(prop);
		switch (browserName.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vijay\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
            // driver = new ChromeDriver(browserOptions.getChromeOptions());
			if(Boolean.parseBoolean(prop.getProperty("remote"))){
				init_remotewebdriver("chrome");
				
			}else {
				tlDriver.set(new ChromeDriver(browserOptions.getChromeOptions()));
			}
			 
             break;
			
		 case "firefox":
             System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
           // driver = new FirefoxDriver(browserOptions.getFirefoxOptions());
             tlDriver.set(new FirefoxDriver(browserOptions.getFirefoxOptions()));
             break;

         case "edge":
             WebDriverManager.edgedriver().setup();
            // driver = new EdgeDriver(browserOptions.getEdgeOptions());
             tlDriver.set(new EdgeDriver(browserOptions.getEdgeOptions()));
             break;

         default:
             throw new IllegalArgumentException("Unsupported browser: " + browserName);
     }
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		//getDriver().get(prop.getProperty("url"));
		launchUrl(prop.getProperty("url"));
	  //launchUrl("https://naveenautomationlabs.com/opencart/index.php?route=account/login", "Expected Page Title");
	   //  launchUrl("https://naveenautomationlabs.com/opencart/index.php?route=account/login", "Expected Page Title", "https://naveenautomationlabs.com/opencart/index.php?route=account/login");
	    //  launchUrl("https://naveenautomationlabs.com/opencart/index.php?route=account/login", 10);
	     // launchUrl("https://naveenautomationlabs.com/opencart/index.php?route=account/login", "Expected Page Title", "https://naveenautomationlabs.com/opencart/index.php?route=account/login", 10);

	        return getDriver();
	    }
		
	private void init_remotewebdriver(String browser) {
	    System.out.println("Running test on remote grid server: " + browser);
	    
	    if (browser.equalsIgnoreCase("chrome")) {
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--remote-allow-origins=*");
	        // Add any additional Chrome options if needed
	        // options.addArguments("--headless"); // Example

	        try {
	            tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), options));
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }
	    } else if (browser.equalsIgnoreCase("firefox")) {
	        FirefoxOptions options = new FirefoxOptions();
	        // Add any additional Firefox options if needed
	        // options.setHeadless(true); // Example

	        try {
	            tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), options));
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }
	    } else {
	        throw new IllegalArgumentException("Browser not supported: " + browser);
	    }
	}


	public static synchronized WebDriver getDriver() {
	        return tlDriver.get();
	    }
	
	 public Properties init_prop() {
		    prop = new Properties();
		    FileInputStream ip = null;
		    String envName = System.getProperty("env"); // it could be qa, dev, uat, stage

		    try {
		        if (envName == null) {
		            System.out.println("Running on PROD environment");
		            ip = new FileInputStream(".\\src\\test\\resource\\config\\config.properties");
		        } else {
		            System.out.println("Running on environment: " + envName);
		            switch (envName.toLowerCase()) {
		                case "qa":
		                    ip = new FileInputStream(".\\src\\test\\resource\\config\\qa.config.properties");
		                    break;

		                case "dev":
		                    ip = new FileInputStream(".\\src\\test\\resource\\config\\dev.config.properties");
		                    break;

		                case "stage":
		                    ip = new FileInputStream(".\\src\\test\\resource\\config\\stage.config.properties");
		                    break;

		                default:
		                    System.out.println("Invalid environment specified. Please pass the correct environment name.");
		                    return prop; // Return empty properties if the environment is invalid
		            }
		        }

		        if (ip != null) {
		            prop.load(ip);
		        }
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    return prop;
		}
	 
	 public String getScreenshot() {
			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
			File destination = new File(path);
			try {
				FileUtils.copyFile(src, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return path;
		}

	 // Method 1: Launch URL with only the URL string
	    public void launchUrl(String url) {
	        if (url != null && !url.isEmpty()) {
	        	 getDriver().get(url);
	        } else {
	            System.err.println("URL is either null or empty.");
	        }
	    }

	    // Method 2: Launch URL with URL string and expected title verification
	    public void launchUrl(String url, String expectedTitle) {
	        launchUrl(url);
	        if (getDriver().getTitle().equals(expectedTitle)) {
	            System.out.println("Page title matches the expected title.");
	        } else {
	            System.err.println("Page title does not match the expected title.");
	        }
	    }

	    // Method 3: Launch URL with URL string, expected title, and expected URL verification
	    public void launchUrl(String url, String expectedTitle, String expectedUrl) {
	        launchUrl(url, expectedTitle);
	        if (getDriver().getCurrentUrl().equals(expectedUrl)) {
	            System.out.println("Page URL matches the expected URL.");
	        } else {
	            System.err.println("Page URL does not match the expected URL.");
	        }
	    }

	    // Method 4: Launch URL with URL string and timeout (in seconds)
	    public void launchUrl(String url, int timeoutInSeconds) {
	        launchUrl(url);
	        try {
	            Thread.sleep(timeoutInSeconds * 1000);
	        } catch (InterruptedException e) {
	            System.err.println("Interrupted while waiting for the timeout.");
	            Thread.currentThread().interrupt();
	        }
	    }

	    // Method 5: Launch URL with URL string, expected title, expected URL, and timeout
	    public void launchUrl(String url, String expectedTitle, String expectedUrl, int timeoutInSeconds) {
	        launchUrl(url, expectedTitle, expectedUrl);
	        try {
	            Thread.sleep(timeoutInSeconds * 1000);
	        } catch (InterruptedException e) {
	            System.err.println("Interrupted while waiting for the timeout.");
	            Thread.currentThread().interrupt();
	        }
	    }

}
			
			

	
	
		
	


