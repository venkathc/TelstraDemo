package com.wipro.telstra.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class AjaxControl {
	
	private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    protected WebDriver driver;
    private WebDriverWait wait;

    public AjaxControl(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Reporter.log(locator.toString() + ": Loaded successfully");
    }

    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        Reporter.log(locator.toString() + ": Loaded successfully");
    }

    protected void waitForTextToDisappear(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
        Reporter.log(locator.toString() + ": Loaded successfully");
    }
    
  //Method to wait page until element visible
  	public  void waitElementForVisible(WebDriver driver, WebElement locator) {
  		try {
  			WebDriverWait wait = new WebDriverWait(driver, 100);
  			wait.until(ExpectedConditions.visibilityOf(locator));
  			Reporter.log(locator.toString() + ": Loaded successfully");
  			
  		}catch (Exception  e) {

  		}
  		
  	}

  //Method to wait until page loads
  	public  boolean waitForPageToBeReady(WebDriver driver) {
  		WebDriverWait wait = new WebDriverWait(driver, 100);
  		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
  			public Boolean apply(WebDriver driver) {
  				try {
  					return((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
  				}catch(Exception e) {
  					return false;
  				}
  			}
  			};
  			return wait.until(jsLoad);
  		
  	}
  	

}
