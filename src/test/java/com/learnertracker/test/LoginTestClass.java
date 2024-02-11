package com.learnertracker.test;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.learnertracker.constants.AutomationConstants;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.utilities.ExcelUtility;

public class LoginTestClass extends TestBase {
	// Test users can login

	LoginPage obj = null;

	@BeforeMethod
	public void refresh() {
		driver.navigate().refresh();
	}

	@Test
	public void testcase1() {
		// Test case using invalid username and valid password
		String username = ExcelUtility.getCellData(1, 0);
		String password = ExcelUtility.getCellData(1, 1);
		obj = new LoginPage(driver);
		obj.setUsername(username);
		obj.setPassword(password);
		obj.clickLogin();
		Assert.assertEquals(obj.LoginStatusMessage(), AutomationConstants.InvalidUsernameExpError);
	}

	@Test
	public void testcase2() {
		// Test case using valid username and invalid password
		String username = ExcelUtility.getCellData(2, 0);
		String password = ExcelUtility.getCellData(2, 1);
		obj = new LoginPage(driver);
		obj.setUsername(username);
		obj.setPassword(password);
		obj.clickLogin();
		Assert.assertEquals(obj.LoginStatusMessage(), AutomationConstants.InvalidPasswordExpError);
	}

	@Test
	public void testcase3() {
		// Test case using invalid username and invalid password
		String username = ExcelUtility.getCellData(3, 0);
		String password = ExcelUtility.getCellData(3, 1);
		obj = new LoginPage(driver);
		obj.setUsername(username);
		obj.setPassword(password);
		obj.clickLogin();
		Assert.assertEquals(obj.LoginStatusMessage(), AutomationConstants.InvalidUsernameExpError);
	}

	@Test
	public void testcase4() {
		// Test case using blank username and valid password
		String password = ExcelUtility.getCellData(4, 1);
		obj = new LoginPage(driver);
		obj.setPassword(password);
		obj.clickLogin();
		Assert.assertEquals(obj.LoginStatusMessage(), AutomationConstants.InvalidUsernameExpError);
	}

	@Test
	public void testcase5() {
		// Test case using valid username and blank password
		String username = ExcelUtility.getCellData(5, 0);
		obj = new LoginPage(driver);
		obj.setUsername(username);
		obj.clickLogin();
		Assert.assertEquals(AutomationConstants.InvalidPasswordExpError, obj.LoginStatusMessage());
	}

	@Test
	public void testcase6() throws InterruptedException {
		// Get valid credentials of Admin from excel file
		String username = ExcelUtility.getCellData(6, 0);
		String password = ExcelUtility.getCellData(6, 1);
		obj = new LoginPage(driver);
		obj.setUsername(username);
		obj.setPassword(password);
		obj.clickLogin();
		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, AutomationConstants.ExpUrlAdmin);
		obj.clickName();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		obj.logout();
	}

	@Test
	public void testcase7() throws InterruptedException {
		// Get valid credentials of Training Head from excel file
		String username = ExcelUtility.getCellData(7, 0);
		String password = ExcelUtility.getCellData(7, 1);
		obj = new LoginPage(driver);
		obj.setUsername(username);
		obj.setPassword(password);
		obj.clickLogin();
		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, AutomationConstants.ExpUrlTrainingHead);
		obj.clickName();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		obj.logout();
	}

	@Test
	public void testcase8() throws InterruptedException {
		// Get valid credentials of Placement Officer from excel file
		String username = ExcelUtility.getCellData(8, 0);
		String password = ExcelUtility.getCellData(8, 1);
		obj = new LoginPage(driver);
		obj.setUsername(username);
		obj.setPassword(password);
		obj.clickLogin();
		Thread.sleep(1000);
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, AutomationConstants.ExpUrlPlacementOfficer);
		obj.clickName();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		obj.logout();
	}
}
