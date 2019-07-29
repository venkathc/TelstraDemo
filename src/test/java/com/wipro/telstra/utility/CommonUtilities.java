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

import com.wipro.telstra.setup.BaseSetupPage;

public class CommonUtilities extends BaseSetupPage {

	Properties prop = new Properties();
	InputStream input = null;
	public String testUrl;

	public static String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	
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
	public void scrollToBottomOfPage(WebDriver driver, String url) {
		try {
			driver.navigate().to(url);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Scroll to top of the page
	public void scrollToTopOfPage(WebDriver driver, String url) {
		try {
			driver.navigate().to(url);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -250)", "");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void moveToElement(WebDriver driver, WebElement locator) {
		Actions action = new Actions(driver);
		action.moveToElement(locator).perform();

	}

	public void scrollToWebElement(WebDriver driver, WebElement locator) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", locator);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public void selectFromDropDownByVisibleText(WebElement element, String elementToSelect) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(elementToSelect);

		} catch (NoSuchElementException e) {
			System.out.println("Option value not find in dropdown");

		}
	}

	public void selectFromDropDownByValue(WebElement element, String elementToSelect) {
		try {
			Select select = new Select(element);
			select.selectByValue(elementToSelect);
		} catch (NoSuchElementException e) {
			System.out.println("Option value not find in dropdown");

		}
	}

}
