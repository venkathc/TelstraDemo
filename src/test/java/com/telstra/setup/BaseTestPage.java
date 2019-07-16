package com.telstra.setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.telstra.utility.ReadFileData;

public class BaseTestPage extends ReadFileData {

	public RemoteWebDriver driver;
	public WebDriverWait wait;

	public String currnetlocation = System.getProperty("user.dir");

	@Parameters({ "browser" })
	@BeforeMethod
	public void openBrowser(@Optional("chrome") String browser) {

		String os = System.getProperty("os.name").toLowerCase();

		try {

			if (browser.equalsIgnoreCase("Firefox")) {
				System.out.println("Launching Firefox browser");
				System.setProperty("webdriver.gecko.driver", currnetlocation + "/Drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			} else if (browser.equalsIgnoreCase("chrome")) {

				if (os.contains("mac")) {
					System.setProperty("webdriver.chrome.driver", currnetlocation + "/Drivers/chromedrivermac");
					// driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
					driver = new ChromeDriver();
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.manage().window().maximize();
				} else {
					ChromeOptions options = new ChromeOptions();

					System.setProperty("webdriver.chrome.driver", currnetlocation + "/Drivers/chromedriver.exe");
					driver = new ChromeDriver(options);
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					driver.manage().window().maximize();

				}

			} else if (browser.equalsIgnoreCase("IE")) {
				System.out.println("Launching IE browser");
				System.setProperty("webdriver.ie.driver", currnetlocation + "/Drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			} else if (browser.equalsIgnoreCase("Edge")) {
				System.out.println("Launching Microsoft Edge browser");
				System.setProperty("webdriver.edge.driver", currnetlocation + "/Drivers/MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			}
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());

		}

		driver.get(getUrl());

	}

	@AfterMethod
	public void tear() {

		 driver.quit();

	}

	public WebDriver getDriver() {
		return driver;
	}

}
