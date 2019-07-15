package com.telstra.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.telstra.setup.BasePage;

public class CheckOutPage extends BasePage{

	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String expSonyCyberShotDSC = "Sony CyberShot DSC-W800/SC IN5";
	public String expPaymentOptions = "Payment Options";
	
	@FindBy(xpath="//input[@type='email']")
	public WebElement inputTextEmail;
	
	@FindBy(xpath="//button[text()='CONTINUE']")
	public WebElement buttonCONTINUE;
	
	@FindBy(xpath="//span[text()='Payment Options']")
	public WebElement textPaymentOptions;
	
	@FindBy(xpath="//a[text()='Sony CyberShot DSC-W800/SC IN5']")
	public WebElement textSonyCyberShotDSC;
	
	@FindBy(xpath = "//a[@href='/plus']")
	public WebElement linkFlipKart;
	
	@FindBy(xpath = "//h3/span[text()='Payment Options']")
	public WebElement headerPaymentOptions;
	
	@FindBy(xpath = "//div[text()='Logout']")
	public WebElement linkLogout;
	
}
