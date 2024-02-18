package com.learnertracker.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.pages.AdminManageUsers;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.utilities.CustomLogger;
import com.learnertracker.utilities.ExcelUtility;

public class AdminManageUsersClass extends TestBase {
	// Test Admin user can edit and delete user

	AdminManageUsers adminUpdatePage = null;
	LoginPage lp = null;
	CustomLogger logger = new CustomLogger(AdminManageUsersClass.class.getName());

	@BeforeTest
	public void secondaAdminLogin() {
		logger.logInfo("Logging in with admin user credentials");
		String username = ExcelUtility.getCellData(6, 0);
		String password = ExcelUtility.getCellData(6, 1);

		lp = new LoginPage(driver);
		lp.setUsername(username);
		lp.setPassword(password);
		lp.clickLogin();
		logger.logInfo("Logged in to admin account");
	}

	@Test(priority = 1)
	public void adminEditUser() throws InterruptedException {
		// Test admin can edit user
		logger.logInfo("Test admin can edit user");
		adminUpdatePage = new AdminManageUsers(driver);
		adminUpdatePage.clickUpdateButtonUsers();

		String newName = ExcelUtility.getCellData(32, 0);
		String newEmail = ExcelUtility.getCellData(32, 1);
		String newUname = ExcelUtility.getCellData(32, 2);
		String newPassword = ExcelUtility.getCellData(32, 3);
		String newRole = ExcelUtility.getCellData(32, 4);
		logger.logInfo("Collected form data from excel sheet");

		adminUpdatePage.enterName(newName);
		adminUpdatePage.enterEmail(newEmail);
		adminUpdatePage.enterUsername(newUname);
		adminUpdatePage.enterPassword(newPassword);
		adminUpdatePage.setRole(newRole);
		adminUpdatePage.clickSubmitButton();
		logger.logInfo("Submitted user edit form");

	}

	@Test(priority = 2)
	public void adminDeleteUser() throws InterruptedException {
		// Test admin can delete user
		logger.logInfo("Start testing admin can delete user");
		adminUpdatePage = new AdminManageUsers(driver);
		adminUpdatePage.clickDeleteBtn();
		logger.logInfo("Deleted user");
	}
}
