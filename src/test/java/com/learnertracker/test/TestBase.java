package com.learnertracker.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.learnertracker.utilities.ExcelUtility;

public class TestBase {
	// Test base

	WebDriver driver;
	public static Properties prop = null;

	@BeforeTest
	public void firstSetup() throws IOException {
		prop = new Properties();
		FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
		prop.load(ip);

		if (prop.getProperty("browser").equals("Chrome"))
			driver = new ChromeDriver();
		else if (prop.getProperty("browser").equals("Firefox"))
			driver = new FirefoxDriver();

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
