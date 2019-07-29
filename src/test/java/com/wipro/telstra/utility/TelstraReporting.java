package com.wipro.telstra.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class TelstraReporting  {
	private WebDriver driver;
	public TelstraReporting(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static SoftAssert softAssert = new SoftAssert();
	
	// Method to verify whether the element present on page
			public  void verifyElementPresent(WebDriver driver, By locator) throws Exception {
				try {

						driver.findElement(locator).isDisplayed() ;
						softAssert.assertTrue(true);
						Reporter.log("PASS " + locator.toString()+"Element Present on Page");
						System.out.println("PASS " + locator.toString()+"Element Present on Page");
					
					} catch (NoSuchElementException error) {
						softAssert.assertTrue(false);
						Reporter.log("FAIL Element is not Displayed");
						throw new Exception("Element is not Displayed");

					
				}

			}	
		
	
	public  void verifyText(WebElement locator, String expectedText) throws NoSuchElementException {
		String actualText;
		try {
			actualText = locator.getText();
			if (actualText.equals(expectedText)) {
				Reporter.log("Passed Actual Text:" + actualText + " Expected Text:" + expectedText );
				softAssert.assertEquals(actualText, expectedText);
			} else {
				Reporter.log("Fail Actual Text:" + actualText + " Expected Text:" + expectedText );
				softAssert.assertEquals(actualText, expectedText);
				softAssert.assertAll();
				throw new Exception("Expected string not matched" + actualText);
			}
		} catch (Exception error) {
			error.printStackTrace();
			Reporter.log(error.getMessage());
		}
	}

	
	public void verifyString(String actualText, String expectedText) {

		try {
			if (actualText.equals(expectedText)) {
				softAssert.assertTrue(true);
				Reporter.log("Passed Actual Text:" + actualText + " Expected Text:" + expectedText );
			} else {
				Reporter.log("Failed Actual Text:" + actualText + " Expected Text:" + expectedText );softAssert.assertTrue(false);
				
				throw new Exception("Expected string not matched" + actualText);
			}
		} catch (Exception error) {
			error.printStackTrace();
			Reporter.log(error.getMessage());
		}
		softAssert.assertAll();
	}
	
	

}
