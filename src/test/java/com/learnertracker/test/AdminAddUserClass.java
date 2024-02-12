package com.learnertracker.test;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.constants.AutomationConstants;
import com.learnertracker.pages.AdminAddUsers;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.utilities.ExcelUtility;

public class AdminAddUserClass extends TestBase {
	// Test Admin user can add user

	AdminAddUsers adminPage = null;
	LoginPage loginPage = null;

	@BeforeTest
	public void secondAdminLogin() {
		String username = ExcelUtility.getCellData(6, 0);
		String password = ExcelUtility.getCellData(6, 1);
		loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
	}

	@BeforeMethod
	public void addUserForm() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		adminPage = new AdminAddUsers(driver);
		adminPage.clickAddUsers();
	}

	public void runUserCreate(int row) throws InterruptedException {
		String name = ExcelUtility.getCellData(row, 0);
		String email = ExcelUtility.getCellData(row, 1);
		String uname = ExcelUtility.getCellData(row, 2);
		String password = ExcelUtility.getCellData(row, 3);
		String role = ExcelUtility.getCellData(row, 4);
		adminPage.enterName(name);
		adminPage.enterEmail(email);
		adminPage.enterUsername(uname);
		adminPage.enterPassword(password);
		adminPage.setRole(role);
		adminPage.clickSubmitButton();
		Thread.sleep(1000);
	}

	@Test
	public void addUserWithValidCredentials() throws InterruptedException {
		runUserCreate(12);
		String actualSuccessMsg = adminPage.successMessage();
		adminPage.clickOkBtn();
		Assert.assertEquals(actualSuccessMsg, AutomationConstants.AddUserSuccessMessage);
	}

	@Test
	public void addUserWithInvalidName() throws InterruptedException {
		runUserCreate(13);
		String actualSuccessMsg = adminPage.invalidNameWarningMsg();
		adminPage.clickBackToDashboard();
		Assert.assertEquals(actualSuccessMsg, AutomationConstants.InvalidNameWarningMessage);
	}

	@Test
	public void addUserWithInvalidEmail() throws InterruptedException {
		runUserCreate(14);
		String invalidEmailActualMsg = adminPage.invalidEmailWarningMsg();
		adminPage.clickOkBtn();
		Assert.assertEquals(invalidEmailActualMsg, AutomationConstants.InvalidEmailWarningMessage);
	}

	@Test
	public void addUserWithInvalidUsername() throws InterruptedException {
		runUserCreate(15);
		String invalidUsernameActualMsg = adminPage.invalidUsernameWarningMsg();
		adminPage.clickBackToDashboard();
		Assert.assertEquals(invalidUsernameActualMsg, AutomationConstants.InvalidUsernameWarningMessage);
	}

	@Test
	public void addUsersWithInvalidPassword() throws InterruptedException {
		runUserCreate(16);
		String invalidPasswordActualMsg = adminPage.invalidPasswordWarningMsg();
		adminPage.clickBackToDashboard();
		Assert.assertEquals(invalidPasswordActualMsg, AutomationConstants.InvalidPasswordWarningMessage);
	}
}
