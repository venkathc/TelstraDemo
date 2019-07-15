package com.telstra.utility;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;


public class Synchronization {
	
	private static final long DEFAULT_TIMEOUT = 60;

	//Method to wait page until element visible
	public  void waitElementForVisible(WebDriver driver, WebElement locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
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
	
	
	//Method to wait until element clickable
	public  void waitisElementClickable(WebDriver driver, WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {

		}

	}

	public void waitElement(WebDriver driver, WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(locator));

		} catch (Exception  e) {

			System.out.println(locator.toString() + "Element not found");
		}

	}
	
	//Method to wait until element visible
	public  void waitIsElementVisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception  e) {

		}

	}

	
	
}
