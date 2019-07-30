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

/**
 * @author Venkatesh Kumar
 *
 */
public class AjaxControl {
	
	public static final int TIMEOUT = 5;
    public static final int POLLING = 100;

    public static WebDriver driver;
    public static WebDriverWait wait;

    
    public static void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Reporter.log(locator.toString() + ": Loaded successfully");
    }

    public static void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        Reporter.log(locator.toString() + ": Loaded successfully");
    }

    
    public static void waitForTextToDisappear(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
        Reporter.log(locator.toString() + ": Loaded successfully");
    }
    
  //Method to wait page until element visible
  	public static void waitElementForVisible(WebDriver driver, WebElement locator) {
  		try {
  			wait.until(ExpectedConditions.visibilityOf(locator));
  			Reporter.log(locator.toString() + ": Loaded successfully");
  			
  		}catch (Exception  e) {

  		}
  		
  	}

//Method to wait until page loads
  	
/**
 * @param driver
 * @return
 */
  	public static  boolean waitForPageToBeReady(WebDriver driver) {
  		
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
