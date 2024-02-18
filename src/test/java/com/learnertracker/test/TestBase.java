package com.learnertracker.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.learnertracker.utilities.CaptureScreenshot;
import com.learnertracker.utilities.ExcelUtility;

public class TestBase {
	// Test base

	WebDriver driver;
	public static CaptureScreenshot screenshot = null;
	public static Properties prop = null;

	@BeforeTest
	public void firstSetup() throws IOException {
		// Create object of Properties
		prop = new Properties();

		// Create FileInputStream object with properties file path
		FileInputStream ip = new FileInputStream("src/test/resources/config.properties");

		// Load file to Properties object
		prop.load(ip);

		// Get browser from properties file
		if (prop.getProperty("browser").toLowerCase().equals("chrome")) {
			// Get configuration whether headless testing preferred, from properties file
			if (prop.getProperty("isHeadlessBrowser").toLowerCase().equals("true")) {
				// Create ChromeOptions object
				ChromeOptions chromeOptions = new ChromeOptions();
				// Set headless mode
				chromeOptions.addArguments("--headless");
				// Set browser window size
				chromeOptions.addArguments("--window-size=1920,1080");
				// Instantiate ChromeDriver with options
				driver = new ChromeDriver(chromeOptions);
			} else
				// Instantiate ChromeDriver without options
				driver = new ChromeDriver();

			// Get browser from properties file
		} else if (prop.getProperty("browser").toLowerCase().equals("firefox")) {
			// Get configuration whether headless testing preferred, from properties file
			if (prop.getProperty("isHeadlessBrowser").toLowerCase().equals("true")) {
				// Create FirefoxOptions object
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				// Set headless mode
				firefoxOptions.addArguments("-headless");
				// Set browser window size
				firefoxOptions.addArguments("--window-size=1920,1080");
				// Instantiate FirefoxDriver with options
				driver = new FirefoxDriver(firefoxOptions);
			} else
				// Instantiate FirefoxDriver without options
				driver = new FirefoxDriver();
		}

		// Create screenshot utility object
		screenshot = new CaptureScreenshot(driver);

		driver.manage().window().maximize();

		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@BeforeSuite
	public void excelSetup() throws IOException {
		String excelFilePath = "src/main/resources/TestData.xlsx";
		ExcelUtility.setExcelFile(excelFilePath, "Sheet1");
	}

	@AfterTest
	public void logout() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement name = driver.findElement(By.id("basic-nav-dropdown"));
		name.click();
		WebElement logoutButton = driver.findElement(By.xpath("//a[text()='Logout']"));
		logoutButton.click();
	}
}
