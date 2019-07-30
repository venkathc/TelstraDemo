package com.wipro.telstra.pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wipro.telstra.utility.AjaxControl;
import com.wipro.telstra.utility.CommonUtilities;
/**
 * @author Venkatesh Kumar
 *
 */

public class FlipkartHomePage  {
private WebDriver driver;

	public FlipkartHomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String expURL = "https://www.flipkart.com/";

	@FindBy(name = "q")
	public WebElement textBoxSearchForProducts;

	@FindBy(xpath = "//div[label[span[text()='Enter Email/Mobile number']]]//input")
	public WebElement textBoxEnterEmailMobileNumber;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement textBoxEnterPassword;

	@FindBy(xpath = "//button/span[text()='Login']")
	public WebElement buttonLogin;
	
	@FindBy(xpath = "//button[text()='ADD TO CART']")
	public WebElement buttonAddToCart;

	@FindBy(xpath = "//button/span[text()='Place Order']")
	public WebElement buttonPlaceOrder;

	@FindBy(xpath = "//span[text()='Electronics']")
	public WebElement textLinkElectronics;

	//Click on login button	
	public void clickOnLogin(){
		buttonLogin.click();
	   }

	 //Get the title of Login Home Page URL
	public String getLoginURL(){
		
		return driver.getCurrentUrl();

    }
    
// Login method	
/**
 * @param driver
 * @throws IOException
 * @throws InterruptedException
 */
public  void login(WebDriver driver) throws IOException, InterruptedException {
		textBoxEnterEmailMobileNumber.sendKeys(CommonUtilities.readExcel("Products", "userName"));
		textBoxEnterPassword.sendKeys(CommonUtilities.readExcel("Products", "password"));
		clickOnLogin();
		AjaxControl.waitForPageToBeReady(driver);
		AjaxControl.waitElementForVisible(driver,
		getUsername(CommonUtilities.readExcel("Products", "Name")));
	}

//Method to click on the links 
/**
 * @param linkName
 * @return
 */
	public void clickLink(String linkName) {
		 driver.findElement(By.xpath("//span[text()='"+linkName+"']")).click();;
				
	}
	
// Method to select common name elements
/**
 * @param camName
 * @return
 */
	public WebElement selectCamera(String camName) {
		return driver.findElement(By.xpath(
				"//div[@data-id='CAMDVGUGJ4G5FVDM']/div[@class='_1UoZlX']//div[contains(text(),'" + camName + "')]"));
	}

//Method to get logged user name	
	public WebElement getUsername(String user) {
		return driver.findElement(By.xpath("//div[@class='_2aUbKa' and text()='" + user + "']"));

	}
	
//We will use this boolean for assertion. To check if page is opened
	public boolean isPageOpened(){
	       return textLinkElectronics.getText().toString().contains("Electronics");
	   }	
}
