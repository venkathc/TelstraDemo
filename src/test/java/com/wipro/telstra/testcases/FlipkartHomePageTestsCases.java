package com.wipro.telstra.testcases;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.wipro.telstra.driver.DriverSetupPage;
import com.wipro.telstra.pageObjects.CheckOutPage;
import com.wipro.telstra.pageObjects.FlipkartHomePage;
import com.wipro.telstra.utility.AjaxControl;
import com.wipro.telstra.utility.CommonUtilities;
import com.wipro.telstra.utility.TelstraReporting;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


public class FlipkartHomePageTestsCases extends DriverSetupPage {
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify home page title")
	@Story("Test Verify user successfully logged in")
	@Test(priority = 1)
	public void tc_loginFlipkart() throws IOException, InterruptedException {
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		flipkartHomePage.login(driver);
		Assert.assertTrue(flipkartHomePage.isPageOpened());
		TelstraReporting.verifyString(flipkartHomePage.getLoginURL(), flipkartHomePage.expURL);
		
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("Verify user selects camera and  add to cart")
	@Story("Test User added camera to cart")
	@Test(priority = 2)
	public void tc_buyCamera() throws Exception {
		
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		flipkartHomePage.login(driver);
		Assert.assertTrue(flipkartHomePage.isPageOpened());
	    AjaxControl.waitElementForVisible(driver, flipkartHomePage.textBoxSearchForProducts);
		//Pass the parameter to search from excel file
		flipkartHomePage.textBoxSearchForProducts.sendKeys(CommonUtilities.readExcel("Products", "Cam"));
		flipkartHomePage.textBoxSearchForProducts.sendKeys(Keys.RETURN);
		Thread.sleep(3000);
		//AjaxControl.waitElementForVisible(driver, flipkartHomePage.selectCamera(CommonUtilities.readExcel("Products", "CameraModel")));
		System.out.println(flipkartHomePage.selectCamera(CommonUtilities.readExcel("Products", "CameraModel")).toString());
		CommonUtilities.scrollToWebElement(driver,flipkartHomePage.selectCamera(CommonUtilities.readExcel("Products", "CameraModel")));
		CommonUtilities.clickUsingJavaScript(driver,flipkartHomePage.selectCamera(CommonUtilities.readExcel("Products", "CameraModel")));
		String parentTab = driver.getWindowHandle();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(parentTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		flipkartHomePage.buttonAddToCart.click();
		AjaxControl.waitElementForVisible(driver, flipkartHomePage.buttonPlaceOrder);
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		TelstraReporting.verifyText(flipkartHomePage.buttonPlaceOrder,flipkartHomePage.expPlaceOrder);
		
		

	}

	@Severity(SeverityLevel.NORMAL)
	@Description("Verify user navigates to payment options then logout")
	@Story("Test User navigates to payment page")
	@Test(priority = 3)
	public void tc_checkOutCart() throws Exception {
		
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		flipkartHomePage.login(driver);
		AjaxControl.waitForElementClickable(driver, flipkartHomePage.selectLink("Cart"));
		
		//Pass the link name to click as parameter
		CommonUtilities.clickUsingJavaScript(driver,flipkartHomePage.selectLink("Cart"));
		AjaxControl.waitForElementClickable(driver, flipkartHomePage.buttonPlaceOrder);
		CommonUtilities.clickUsingJavaScript(driver,flipkartHomePage.buttonPlaceOrder);
		
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		AjaxControl.waitElementForVisible(driver, checkOutPage.buttonCONTINUE);
		checkOutPage.buttonCONTINUE.click();
		AjaxControl.waitElementForVisible(driver, checkOutPage.headerPaymentOptions);
		TelstraReporting.verifyText(checkOutPage.headerPaymentOptions, checkOutPage.expPaymentOptions);
		AjaxControl.waitElementForVisible(driver, checkOutPage.linkFlipKart);
		checkOutPage.linkFlipKart.click();
		AjaxControl.waitElementForVisible(driver,flipkartHomePage.getUsername(CommonUtilities.readExcel("Products", "Name")));
		CommonUtilities.moveToElement(driver, flipkartHomePage.getUsername(CommonUtilities.readExcel("Products", "Name")));
		checkOutPage.linkLogout.click();
		
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Remove the added product from cart")
	@Story("Test User removes the added product from cart")
	@Test(priority = 4)
	public void tc_removeItem() throws Exception {
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		flipkartHomePage.login(driver);
		AjaxControl.waitForElementClickable(driver, flipkartHomePage.selectLink("Cart"));
		//Pass the link name to click as parameter
		CommonUtilities.clickUsingJavaScript(driver,flipkartHomePage.selectLink("Cart"));
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		Thread.sleep(2000);
		AjaxControl.waitForElementClickable(driver, checkOutPage.linkRemove);
		checkOutPage.linkRemove.click();
		AjaxControl.waitForElementClickable(driver,checkOutPage.popupLinkRemove);
		checkOutPage.popupLinkRemove.click();
	}
	
}
