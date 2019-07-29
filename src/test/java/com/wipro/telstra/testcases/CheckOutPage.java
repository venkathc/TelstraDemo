package com.wipro.telstra.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

	private WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		//super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String expSonyCyberShotDSC = "Sony CyberShot DSC-W800/SC IN5";
	public String expPaymentOptions = "PAYMENT OPTIONS";
	
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
