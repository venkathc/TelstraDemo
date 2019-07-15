package com.telstra.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.telstra.pageObjects.CheckOutPage;
import com.telstra.pageObjects.FlipkartHomePage;
import com.telstra.setup.BaseTestPage;
import com.telstra.utility.CommonMethods;
import com.telstra.utility.ReadDataExcel;
import com.telstra.utility.Synchronization;
import com.telstra.utility.TelstraVerification;

public class FlipkartHomePageTests extends BaseTestPage{
	
	@Test(description = "Checkout camera")
	public void tc_buyCamera() throws Exception {
		ReadDataExcel readDataExcel = new ReadDataExcel();
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		Synchronization synchronization = new Synchronization();
		flipkartHomePage.textBoxEnterEmailMobileNumber.sendKeys(readDataExcel.readExcel("Products", "userName"));
		flipkartHomePage.textBoxEnterPassword.sendKeys(readDataExcel.readExcel("Products", "password"));
		flipkartHomePage.buttonLogin.click();
		synchronization.waitForPageToBeReady(driver);
		TelstraVerification telstraVerification = new TelstraVerification(driver);
		String actURL = driver.getCurrentUrl();
		telstraVerification.verifyString(actURL, flipkartHomePage.expURL);
		Thread.sleep(4000);
		synchronization.waitElement(driver, flipkartHomePage.textBoxSearchForProducts);
		flipkartHomePage.textBoxSearchForProducts.sendKeys(readDataExcel.readExcel("Products", "Cam"));
		flipkartHomePage.textBoxSearchForProducts.sendKeys(Keys.RETURN);
		Thread.sleep(4000);
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.scrollToWebElement(driver, flipkartHomePage.selectCamera("Sony CyberShot DSC-W800/SC IN5"));
		commonMethods.clickUsingJavaScript(driver,flipkartHomePage.selectCamera("Sony CyberShot DSC-W800/SC IN5"));
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
		
		//checkOutPage.inputTextEmail.sendKeys("venkateshkumarhc@gmail.com");
		checkOutPage.buttonCONTINUE.click();
		telstraVerification.verifyText(checkOutPage.headerPaymentOptions, checkOutPage.expPaymentOptions);
		checkOutPage.linkFlipKart.click();
		commonMethods.moveToElement(driver, flipkartHomePage.dropDownVenkatesh);
		
	}

}
