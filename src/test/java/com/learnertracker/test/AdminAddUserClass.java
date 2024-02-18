package com.learnertracker.test;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.constants.AutomationConstants;
import com.learnertracker.pages.AdminAddUsers;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.utilities.CustomLogger;
import com.learnertracker.utilities.ExcelUtility;

public class AdminAddUserClass extends TestBase {
	// Test Admin user can add user

	AdminAddUsers adminPage = null;
	LoginPage loginPage = null;
	CustomLogger logger = new CustomLogger(AdminAddUserClass.class.getName());

	@BeforeTest
	public void secondAdminLogin() {
		logger.logInfo("Logging in with admin user credentials");
		String username = ExcelUtility.getCellData(6, 0);
		String password = ExcelUtility.getCellData(6, 1);
		loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		logger.logInfo("Logged in as admin user");
	}

	@BeforeMethod
	public void addUserForm() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		adminPage = new AdminAddUsers(driver);
		adminPage.clickAddUsers();
	}

	public void runUserCreate(int row, String testName) throws InterruptedException {
		logger.logInfo("Started " + testName + " test");
		String name = ExcelUtility.getCellData(row, 0);
		String email = ExcelUtility.getCellData(row, 1);
		String uname = ExcelUtility.getCellData(row, 2);
		String password = ExcelUtility.getCellData(row, 3);
		String role = ExcelUtility.getCellData(row, 4);
		logger.logInfo("Collected form data from excel sheet");
		adminPage.enterName(name);
		adminPage.enterEmail(email);
		adminPage.enterUsername(uname);
		adminPage.enterPassword(password);
		adminPage.setRole(role);
		adminPage.clickSubmitButton();
		Thread.sleep(1000);
		logger.logInfo("Submitted user create form");
	}

	@Test(priority = 1)
	public void addUserWithValidCredentials() throws InterruptedException {
		// Test Admin user can add user with valid credentials

		runUserCreate(12, "addUserWithValidCredentials");
		String actualSuccessMsg = adminPage.successMessage();
		logger.logInfo("Got message: " + actualSuccessMsg);
		adminPage.clickOkBtn();
		Assert.assertEquals(actualSuccessMsg, AutomationConstants.AddUserSuccessMessage);
		logger.logInfo("Completed testing addUserWithValidCredentials");
	}

	@Test(priority = 2)
	public void addUserWithInvalidName() throws InterruptedException {
		// Test Admin user can add user with invalid name

		runUserCreate(13, "addUserWithInvalidName");
		String actualSuccessMsg = adminPage.invalidNameWarningMsg();
		logger.logInfo("Got message: " + actualSuccessMsg);
		adminPage.clickBackToDashboard();
		Assert.assertEquals(actualSuccessMsg, AutomationConstants.InvalidNameWarningMessage);
		logger.logInfo("Completed testing addUserWithInvalidName");
	}

	@Test(priority = 3)
	public void addUserWithInvalidEmail() throws InterruptedException {
		// Test Admin user can add user with invalid email

		runUserCreate(14, "addUserWithInvalidEmail");
		String invalidEmailActualMsg = adminPage.invalidEmailWarningMsg();
		logger.logInfo("Got message: " + invalidEmailActualMsg);
		screenshot.captureScreenshot("addUserWithInvalidEmail");
		logger.logWarning("Screenshot taken");
		adminPage.clickOkBtn();
		Assert.assertEquals(invalidEmailActualMsg, AutomationConstants.InvalidEmailWarningMessage);
		logger.logError("Failed test addUserWithInvalidEmail");
	}

	@Test(priority = 4)
	public void addUserWithInvalidUsername() throws InterruptedException {
		// Test Admin user can add user with invalid username

		runUserCreate(15, "addUserWithInvalidUsername");
		String invalidUsernameActualMsg = adminPage.invalidUsernameWarningMsg();
		logger.logInfo("Got message: " + invalidUsernameActualMsg);
		adminPage.clickBackToDashboard();
		Assert.assertEquals(invalidUsernameActualMsg, AutomationConstants.InvalidUsernameWarningMessage);
		logger.logInfo("Completed testing addUserWithInvalidUsername");
	}

	@Test(priority = 5)
	public void addUsersWithInvalidPassword() throws InterruptedException {
		// Test Admin user can add user with invalid password

		runUserCreate(16, "addUsersWithInvalidPassword");
		String invalidPasswordActualMsg = adminPage.invalidPasswordWarningMsg();
		logger.logInfo("Got message: " + invalidPasswordActualMsg);
		adminPage.clickBackToDashboard();
		Assert.assertEquals(invalidPasswordActualMsg, AutomationConstants.InvalidPasswordWarningMessage);
		logger.logInfo("Completed testing addUsersWithInvalidPassword");
	}
}
