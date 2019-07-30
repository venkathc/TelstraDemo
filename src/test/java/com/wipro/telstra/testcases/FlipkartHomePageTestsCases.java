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
	
	//Test to verify login
	@Test(description = "Verify user successfully logged in")
	public void tc_loginFlipkart() throws IOException, InterruptedException {
		CommonUtilities commonUtilities = new CommonUtilities();
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		commonUtilities.login(driver);
		//Check if page is opened
		Assert.assertTrue(flipkartHomePage.isPageOpened());
		TelstraReporting telstraVerification = new TelstraReporting(driver);
		telstraVerification.verifyString(flipkartHomePage.getLoginURL(), flipkartHomePage.expURL);
		
	}

	@Test(description = "Select camera and  add to cart")
	public void tc_buyCamera() throws Exception {
		CommonUtilities commonUtilities = new CommonUtilities();
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		AjaxControl synchronization = new AjaxControl(driver);
		commonUtilities.login(driver);
		//Check if page is opened
	    Assert.assertTrue(flipkartHomePage.isPageOpened());
		TelstraReporting telstraReporting = new TelstraReporting(driver);
		synchronization.waitElementForVisible(driver, flipkartHomePage.textBoxSearchForProducts);
		//Pass the parameter to search from excel file
		flipkartHomePage.textBoxSearchForProducts.sendKeys(commonUtilities.readExcel("Products", "Cam"));
		flipkartHomePage.textBoxSearchForProducts.sendKeys(Keys.RETURN);
		synchronization.waitForPageToBeReady(driver);
		CommonUtilities commonMethods = new CommonUtilities();
		commonMethods.scrollToWebElement(driver,
				flipkartHomePage.selectCamera(commonUtilities.readExcel("Products", "CameraModel")));
		commonMethods.clickUsingJavaScript(driver,
				flipkartHomePage.selectCamera(commonUtilities.readExcel("Products", "CameraModel")));
		String parentTab = driver.getWindowHandle();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(parentTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		flipkartHomePage.buttonAddToCart.click();
		synchronization.waitElementForVisible(driver, flipkartHomePage.buttonPlaceOrder);
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		telstraReporting.verifyText(checkOutPage.textSonyCyberShotDSC, checkOutPage.expSonyCyberShotDSC);
		
		

	}

	@Test(description = " Navigate to payment options then logout")
	public void tc_checkOutCart() throws InterruptedException, IOException {
		
		CommonUtilities commonUtilities = new CommonUtilities();
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		AjaxControl synchronization = new AjaxControl(driver);
		commonUtilities.login(driver);
		
		flipkartHomePage.clickLink("Cart");
		synchronization.waitElementForVisible(driver, flipkartHomePage.buttonPlaceOrder);
		flipkartHomePage.buttonPlaceOrder.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		synchronization.waitElementForVisible(driver, checkOutPage.buttonCONTINUE);
		checkOutPage.buttonCONTINUE.click();
		synchronization.waitElementForVisible(driver, checkOutPage.headerPaymentOptions);
		TelstraReporting telstraReporting = new TelstraReporting(driver);
		telstraReporting.verifyText(checkOutPage.headerPaymentOptions, checkOutPage.expPaymentOptions);

		synchronization.waitElementForVisible(driver, checkOutPage.linkFlipKart);
		checkOutPage.linkFlipKart.click();
		synchronization.waitElementForVisible(driver,
				flipkartHomePage.getUsername(commonUtilities.readExcel("Products", "Name")));
	
		commonUtilities.moveToElement(driver, flipkartHomePage.getUsername(commonUtilities.readExcel("Products", "Name")));
		checkOutPage.linkLogout.click();
		
	}
}
