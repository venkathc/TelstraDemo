package com.wipro.telstra.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.wipro.telstra.driver.DriverSetupPage;
import com.wipro.telstra.pageObjects.FlipkartHomePage;


public class CommonUtilities extends DriverSetupPage {

	public static String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	
	
	public void login(WebDriver driver) throws IOException, InterruptedException {
		
		CommonUtilities commonUtilities = new CommonUtilities();
		FlipkartHomePage flipkartHomePage = new FlipkartHomePage(driver);
		AjaxControl synchronization = new AjaxControl(driver);
		flipkartHomePage.textBoxEnterEmailMobileNumber.sendKeys(commonUtilities.readExcel("Products", "userName"));
		flipkartHomePage.textBoxEnterPassword.sendKeys(commonUtilities.readExcel("Products", "password"));
		flipkartHomePage.clickOnLogin();
		
		synchronization.waitForPageToBeReady(driver);
		synchronization.waitElementForVisible(driver,
				flipkartHomePage.getUsername(commonUtilities.readExcel("Products", "Name")));
	}
	
	
	/**
	 * @param sheetName
	 * @param variable
	 * @return
	 * @throws IOException
	 */
	public String readExcel(String sheetName, String variable) throws IOException{
		DataFormatter formatter = new DataFormatter();
	  	String filePath = System.getProperty("user.dir");
	    File file = new File(filePath+"/src/test/resources/TestData.xlsx");
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook testDataUIWorkbook = new XSSFWorkbook(inputStream);
	    Sheet testDataUISheet = testDataUIWorkbook.getSheet(sheetName);
	   
	    int rowCount = testDataUISheet.getLastRowNum()-testDataUISheet.getFirstRowNum();

	    for (int i = 0; i < rowCount+1; i++) {
	        Row row = testDataUISheet.getRow(i);
	        if(row.getCell(0).getStringCellValue().equals(variable))
	        {
	        	String inputText;
	        	if(row.getCell(1).getCellType()== Cell.CELL_TYPE_NUMERIC)
	        	{
	        		inputText = formatter.formatCellValue(row.getCell(1));
	        		
	        	}
	        	else 
	        	{
	        		inputText = row.getCell(1).getStringCellValue();
	        		
	        	}
	            return inputText;
	        }
	        else
	        	continue;   
	    }

	    return "default";

	}

// Method to click element using java script
	
	/**
	 * @param driver
	 * @param locator
	 * @throws Exception
	 */
	public void clickUsingJavaScript(WebDriver driver, WebElement locator) throws Exception {
		try {
			if (locator.isEnabled() && locator.isDisplayed()) {
				System.out.println("Clicking on element  using java script" + locator.getText());

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", locator);
			} else {
				System.out.println("Unable to click on element");
				driver.quit();

			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			throw new ElementNotVisibleException("Element not Visible to click");

		} catch (Exception e) {
			System.out.println("Unable to click on element " + e.getStackTrace());
		}
	}

	// Scroll down to bottom of the page
	
	/**
	 * @param driver
	 * @param url
	 */
	public void scrollToBottomOfPage(WebDriver driver, String url) {
		try {
			driver.navigate().to(url);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Scroll to top of the page
	
	/**
	 * @param driver
	 * @param url
	 */
	public void scrollToTopOfPage(WebDriver driver, String url) {
		try {
			driver.navigate().to(url);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -250)", "");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
// Method to move element
	
	/**
	 * @param driver
	 * @param locator
	 */
	public void moveToElement(WebDriver driver, WebElement locator) {
		Actions action = new Actions(driver);
		action.moveToElement(locator).perform();

	}
//Method to move cursor to visible element
	
	/**
	 * @param driver
	 * @param locator
	 */
	public void scrollToWebElement(WebDriver driver, WebElement locator) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", locator);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
// Method to select element from Dropdown box by visible text
	
	/**
	 * @param element
	 * @param elementToSelect
	 */
	public void selectFromDropDownByVisibleText(WebElement element, String elementToSelect) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(elementToSelect);

		} catch (NoSuchElementException e) {
			System.out.println("Option value not find in dropdown");

		}
	}

	// Method to select element from Dropdown box by visible value
	
	/**
	 * @param element
	 * @param elementToSelect
	 */
	public void selectFromDropDownByValue(WebElement element, String elementToSelect) {
		try {
			Select select = new Select(element);
			select.selectByValue(elementToSelect);
		} catch (NoSuchElementException e) {
			System.out.println("Option value not find in dropdown");

		}
	}

}
