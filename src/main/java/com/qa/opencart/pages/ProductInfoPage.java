package com.qa.opencart.pages;

import java.time.Duration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private ElementUtil eleutil;
	private WebDriverWait wait;
	
	public ProductInfoPage(WebDriver driver) {
		eleutil=new ElementUtil(driver);
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // You can adjust the timeout duration as needed
	} 
	private By productinfoTitle=By.xpath("//div[@id=\"content\"]/div/div[2]/h1");
	private By ReviewTab=By.xpath("	//a[text()='Reviews (0)']");
    private By price=By.xpath("//h2[text()='$2,000.00']");
    private By QualityField=By.xpath ("//input[@id='input-quantity']");
	private By singleimg=By.xpath("(//div[@class='col-sm-8']//img[@title='MacBook Pro'])[1]");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private Map<String, String> productInfoMap;

	
	  public String ProdInfoTitle() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(productinfoTitle));
	        return eleutil.doGetText(productinfoTitle);
	    }

	    public boolean singleIMG() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(singleimg));
	        return eleutil.doIsDisplayed(singleimg);
	    }

	    public String getProductPrice() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(price));
	        return eleutil.doGetText(price);
	    }

	    public boolean ReviewTab() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(ReviewTab));
	        return eleutil.doIsDisplayed(ReviewTab);
	    }

	    public boolean QualityField() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(QualityField));
	        return eleutil.doIsDisplayed(QualityField);
	    }
	    public Map<String, String> getProductInfo() {
			productInfoMap = new LinkedHashMap<String, String>();
			productInfoMap.put("name", ProdInfoTitle() );
			getProductMetaData();
			getProductPriceData();
			return productInfoMap;
		}

		private void getProductMetaData() {
			List<WebElement> metaDataList = eleutil.getElements(productMetaData);
//			Brand: Apple
//			Product Code: Product 18
//			Reward Points: 800
//			Availability: Out Of Stock

			for (WebElement e : metaDataList) {
				String text = e.getText();
				String meta[] = text.split(":");
				String metaKey = meta[0].trim();
				String metaValue = meta[1].trim();
				productInfoMap.put(metaKey, metaValue);
			}
		}

		private void getProductPriceData() {

			List<WebElement> metaPriceList = eleutil.getElements(productPriceData);
//			$2,000.00
//			Ex Tax: $2,000.00
			String price = metaPriceList.get(0).getText().trim();
			String exPrice = metaPriceList.get(1).getText().trim();
			productInfoMap.put("price", price);
			productInfoMap.put("ExTaxPrice", exPrice);

		}

	}



	   

