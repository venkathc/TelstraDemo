package com.wipro.telstra.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class IntiatePage {

	public IntiatePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
