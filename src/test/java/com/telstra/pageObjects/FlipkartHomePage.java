package com.telstra.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.telstra.setup.BasePage;

public class FlipkartHomePage extends BasePage {

	private WebDriver driver;
	
	public FlipkartHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String expURL = "https://www.flipkart.com/";
	

	@FindBy(name="q")
	public WebElement textBoxSearchForProducts;
	
	@FindBy(className="_2zrpKA")
	public WebElement textBoxEnterEmailMobileNumber;

	@FindBy(xpath="//input[@type='password']")
	public WebElement textBoxEnterPassword;

	@FindBy(xpath="//button/span[text()='Login']")
	public WebElement buttonLogin;

	@FindBy(xpath="//button[@class='_2AkmmA _29YdH8']")
	public WebElement buttonClose;

	@FindBy(xpath="//button[text()='ADD TO CART']")
	public WebElement buttonAddToCart;
	
	@FindBy(xpath="//button/span[text()='Place Order']")
	public WebElement buttonPlaceOrder;

	@FindBy(xpath="//div[@class='_2aUbKa' and text()='Venkatesh']")
	public WebElement dropDownVenkatesh;
	
	
	
	// Method to select common name elements
		public WebElement selectCamera(String camName) {
			////div[@data-id='CAMDVGUGJ4G5FVDM']/div[@class='_1UoZlX']//div[contains(text(),'Sony CyberShot DSC-W800/SC IN5')]
			return driver.findElement(By.xpath("//div[@data-id='CAMDVGUGJ4G5FVDM']/div[@class='_1UoZlX']//div[contains(text(),'" + camName + "')]"));
		}
		
		
	public void selectCheckbox(String skillName) {
		driver.findElement(By.xpath("//span[contains(text(),'" + skillName + "')]/..//i[@class='icon icon-checkbox']"))
				.click();
	}

	
}
