package com.wipro.telstra.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	/**
	 * @return
	 */
	public String getLoginURL(){
		
		return driver.getCurrentUrl();

    }
    
	//Method to click on the links 
	
	/**
	 * @param linkName
	 * @return
	 */
	public WebElement clickLink(String linkName) {
		return driver.findElement(By.xpath("//span[text()='"+linkName+"']"));
				
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

	
	public WebElement getUsername(String user) {
		return driver.findElement(By.xpath("//div[@class='_2aUbKa' and text()='" + user + "']"));

	}
	
	//We will use this boolean for assertion. To check if page is opened
	
	   /**
	 * @return
	 */
	public boolean isPageOpened(){
	       return textLinkElectronics.getText().toString().contains("Electronics");
	   }	
}
