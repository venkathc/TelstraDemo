package com.wipro.telstra.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

/**
 * @author Venkatesh Kumar
 *
 */
public class TelstraReporting  {
	private WebDriver driver;
	public TelstraReporting(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static SoftAssert softAssert = new SoftAssert();
	
	// Method to verify whether the element present on page
	
			/**
			 * @param driver
			 * @param locator
			 * @throws Exception
			 */
			public static void verifyElementPresent(WebDriver driver, By locator) throws Exception {
				try {

						driver.findElement(locator).isDisplayed() ;
						Assert.assertTrue(true);
						Reporter.log("PASS " + locator.toString()+"Element Present on Page");
						
					
					} catch (NoSuchElementException error) {
						
						Reporter.log("FAIL Element is not Displayed");
						throw new Exception("Element is not Displayed");

					
				}

			}	
		
	// Verify expected text on webPage
			
	/**
	 * @param locator
	 * @param expectedText
	 * @throws NoSuchElementException
	 */
	public static void verifyText(WebElement locator, String expectedText) throws NoSuchElementException {
		String actualText;
		try {
			actualText = locator.getText();
			
				Reporter.log("Pass Text present " + actualText);
				
			
		} catch (Exception error) {
			Reporter.log("Fail Actual Text:" );
			error.printStackTrace();
			Reporter.log(error.getMessage());
		}
	}

	//Verify string on webpage
	
	/**
	 * @param actualText
	 * @param expectedText
	 */
	public static void verifyString(String actualText, String expectedText) {

		try {
			if (actualText.equals(expectedText)) {
				
				Reporter.log("Passed Actual Text:" + actualText  );
			} else {
				Reporter.log("Failed expected text not present:"  );
				
				throw new Exception("Expected string not matched" );
			}
		} catch (Exception error) {
			error.printStackTrace();
			Reporter.log(error.getMessage());
		}
		
	}
	
	

}
