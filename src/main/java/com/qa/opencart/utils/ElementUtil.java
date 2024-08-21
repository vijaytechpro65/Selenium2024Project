package com.qa.opencart.utils;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.Factory.DriverFactory;

public class ElementUtil {
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		 
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	public void doClear(By locator) {
		getElement(locator).clear();
	}
	public void doClick(By locator) {
		getElement(locator).click();
	}
    public void SendKeys(By locator,String value) {
    	doClear(locator);
        getElement(locator).sendKeys(value);
	}
    public String doGetText(By locator) {
		return getElement(locator).getText();
	}
    public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
    public boolean waitForLinkToBeDisplayed(By linkLocator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            WebElement linkElement = driver.findElement(linkLocator); // Find the element using the locator
            wait.until(ExpectedConditions.visibilityOf(linkElement));
            return true; // Element is visible
        } catch (TimeoutException e) {
            return false; // Element is not visible within the timeout
        }
    }
//    naveen
     public boolean waitForURLToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlToBe(url));
	}
   public Boolean waitForPageTitle(String title, int timeout) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	   return wait.until(ExpectedConditions.titleIs(title));
	}

   public boolean waitForUrlToContain(String partialUrl, int timeout) {
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	        return wait.until(ExpectedConditions.urlContains(partialUrl));
 }
   public List<WebElement> waitForElementsToBeVisible(By locator, int timeout) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    List<WebElement> headers = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	   return headers;
	}
   
   public String waitForTitleToBe(String title, int timeout) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    if (wait.until(ExpectedConditions.titleIs(title))) {
	        return driver.getTitle(); // Return the actual page title
	    }
	    return null; // Or handle this case differently
	}
   public WebElement openDropdown(By locator) {
	    WebElement dropdownToggle = driver.findElement(locator); // Adjust selector
	    dropdownToggle.click();
		return dropdownToggle;
	}
   public void doSelectByVisibleText(By locator, String value) {
       Select select = new Select(getElement(locator));
       select.selectByVisibleText(value);
   }
   public WebElement waitForElementToBeClickable(By locator, long timeout) {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
       return wait.until(ExpectedConditions.elementToBeClickable(locator));
   }
   public WebElement waitForElementToBeVisible(By locator, long timeout) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
// Method to get a WebElement and highlight it
   public WebElement getElement(By locator) {
       WebElement element = driver.findElement(locator);
       if(DriverFactory.highlight.equalsIgnoreCase("true"));
       flash(element);  // Highlight the element
       return element;
   }

   // Flash method to highlight an element by changing its background color
   public void flash(WebElement element) {
       JavascriptExecutor js = (JavascriptExecutor) driver;
       String originalColor = element.getCssValue("backgroundColor");
       for (int i = 0; i < 10; i++) {
           changeColor("rgb(0,200,0)", element); // Highlight color
           changeColor(originalColor, element);  // Revert to original color
       }
   }

   // Helper method to change color
   private void changeColor(String color, WebElement element) {
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
       try {
           Thread.sleep(20);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }

   }

