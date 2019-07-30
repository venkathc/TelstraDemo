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


public class FlipkartHomePageTestsCases extends DriverSetupPage {
	
	
	@Test(description = "Verify user successfully logged in")
	public void tc_loginFlipkart() throws IOException, InterruptedException {
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		flipkartHomePage.login(driver);
		Assert.assertTrue(flipkartHomePage.isPageOpened());
		TelstraReporting.verifyString(flipkartHomePage.getLoginURL(), flipkartHomePage.expURL);
		
	}

	
	@Test(description = "Verify user selects camera and  add to cart")
	public void tc_buyCamera() throws Exception {
		
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		flipkartHomePage.login(driver);
		Assert.assertTrue(flipkartHomePage.isPageOpened());
	    AjaxControl.waitElementForVisible(driver, flipkartHomePage.textBoxSearchForProducts);
		//Pass the parameter to search from excel file
		flipkartHomePage.textBoxSearchForProducts.sendKeys(CommonUtilities.readExcel("Products", "Cam"));
		flipkartHomePage.textBoxSearchForProducts.sendKeys(Keys.RETURN);
		AjaxControl.waitForPageToBeReady(driver);
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
		TelstraReporting.verifyText(checkOutPage.textSonyCyberShotDSC, checkOutPage.expSonyCyberShotDSC);
		
		

	}

	@Test(description = "Verify user navigates to payment options then logout")
	public void tc_checkOutCart() throws InterruptedException, IOException {
		
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		flipkartHomePage.login(driver);
		//Pass the link name to click as parameter
		flipkartHomePage.clickLink("Cart");
		AjaxControl.waitElementForVisible(driver, flipkartHomePage.buttonPlaceOrder);
		flipkartHomePage.buttonPlaceOrder.click();
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
	
}
