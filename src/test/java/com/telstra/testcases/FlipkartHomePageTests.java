package com.telstra.testcases;

import java.util.ArrayList;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import com.telstra.pageObjects.CheckOutPage;
import com.telstra.pageObjects.FlipkartHomePage;
import com.telstra.setup.BaseTestPage;
import com.telstra.utility.CommonMethods;
import com.telstra.utility.ReadDataExcel;
import com.telstra.utility.Synchronization;
import com.telstra.utility.TelstraVerification;

public class FlipkartHomePageTests extends BaseTestPage {

	@Test(description = "Select camera add to cart navigate to payment options then logout")
	public void tc_buyCamera() throws Exception {
		ReadDataExcel readDataExcel = new ReadDataExcel();
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		Synchronization synchronization = new Synchronization();
		flipkartHomePage.textBoxEnterEmailMobileNumber.sendKeys(readDataExcel.readExcel("Products", "userName"));
		flipkartHomePage.textBoxEnterPassword.sendKeys(readDataExcel.readExcel("Products", "password"));
		flipkartHomePage.buttonLogin.click();
		synchronization.waitForPageToBeReady(driver);
		synchronization.waitElementForVisible(driver,
				flipkartHomePage.getUsername(readDataExcel.readExcel("Products", "Name")));
		TelstraVerification telstraVerification = new TelstraVerification(driver);
		String actURL = driver.getCurrentUrl();
		telstraVerification.verifyString(actURL, flipkartHomePage.expURL);

		synchronization.waitElementForVisible(driver, flipkartHomePage.textBoxSearchForProducts);
		flipkartHomePage.textBoxSearchForProducts.sendKeys(readDataExcel.readExcel("Products", "Cam"));
		flipkartHomePage.textBoxSearchForProducts.sendKeys(Keys.RETURN);
		synchronization.waitForPageToBeReady(driver);
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.scrollToWebElement(driver,
				flipkartHomePage.selectCamera(readDataExcel.readExcel("Products", "CameraModel")));
		commonMethods.clickUsingJavaScript(driver,
				flipkartHomePage.selectCamera(readDataExcel.readExcel("Products", "CameraModel")));
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
				flipkartHomePage.getUsername(readDataExcel.readExcel("Products", "Name")));
		commonMethods.moveToElement(driver, flipkartHomePage.getUsername(readDataExcel.readExcel("Products", "Name")));
		checkOutPage.linkLogout.click();

	}

}
