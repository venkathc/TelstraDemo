package com.wipro.telstra.testcases;

import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.wipro.telstra.driver.DriverSetupPage;
import com.wipro.telstra.utility.AjaxControl;
import com.wipro.telstra.utility.CommonUtilities;
import com.wipro.telstra.utility.TelstraReporting;

public class FlipkartHomePageTestsCases extends DriverSetupPage {

	@Test(description = "Select camera add to cart navigate to payment options then logout")
	public void tc_buyCamera() throws Exception {
		CommonUtilities commonUtilities = new CommonUtilities();
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		AjaxControl synchronization = new AjaxControl();
		flipkartHomePage.textBoxEnterEmailMobileNumber.sendKeys(commonUtilities.readExcel("Products", "userName"));
		flipkartHomePage.textBoxEnterPassword.sendKeys(commonUtilities.readExcel("Products", "password"));
		flipkartHomePage.buttonLogin.click();
		synchronization.waitForPageToBeReady(driver);
		synchronization.waitElementForVisible(driver,
				flipkartHomePage.getUsername(commonUtilities.readExcel("Products", "Name")));
		TelstraReporting telstraVerification = new TelstraReporting(driver);
		String actURL = driver.getCurrentUrl();
		telstraVerification.verifyString(actURL, flipkartHomePage.expURL);

		synchronization.waitElementForVisible(driver, flipkartHomePage.textBoxSearchForProducts);
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
		telstraVerification.verifyText(checkOutPage.textSonyCyberShotDSC, checkOutPage.expSonyCyberShotDSC);
		flipkartHomePage.buttonPlaceOrder.click();
		synchronization.waitElementForVisible(driver, checkOutPage.buttonCONTINUE);
		checkOutPage.buttonCONTINUE.click();
		synchronization.waitElementForVisible(driver, checkOutPage.headerPaymentOptions);
		telstraVerification.verifyText(checkOutPage.headerPaymentOptions, checkOutPage.expPaymentOptions);

		synchronization.waitElementForVisible(driver, checkOutPage.linkFlipKart);
		checkOutPage.linkFlipKart.click();
		synchronization.waitElementForVisible(driver,
				flipkartHomePage.getUsername(commonUtilities.readExcel("Products", "Name")));
		commonMethods.moveToElement(driver, flipkartHomePage.getUsername(commonUtilities.readExcel("Products", "Name")));
		checkOutPage.linkLogout.click();

	}

}
