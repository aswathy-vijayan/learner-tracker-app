package com.learnertracker.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.pages.AdminManageUsers;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.utilities.ExcelUtility;

public class AdminManageUsersClass extends TestBase {
	// Test Admin user can edit and delete user

	AdminManageUsers adminUpdatePage = null;
	LoginPage lp = null;

	@BeforeTest
	public void secondaAdminLogin() {
		String username = ExcelUtility.getCellData(6, 0);
		String password = ExcelUtility.getCellData(6, 1);

		lp = new LoginPage(driver);
		lp.setUsername(username);
		lp.setPassword(password);
		lp.clickLogin();
	}

	@Test
	public void adminEditUser() throws InterruptedException {
		// adminUpdate
		adminUpdatePage = new AdminManageUsers(driver);
		adminUpdatePage.clickUpdateButtonUsers();

		String newName = ExcelUtility.getCellData(32, 0);
		String newEmail = ExcelUtility.getCellData(32, 1);
		String newUname = ExcelUtility.getCellData(32, 2);
		String newPassword = ExcelUtility.getCellData(32, 3);
		String newRole = ExcelUtility.getCellData(32, 4);
		adminUpdatePage.enterName(newName);
		adminUpdatePage.enterEmail(newEmail);
		adminUpdatePage.enterUsername(newUname);
		adminUpdatePage.enterPassword(newPassword);
		adminUpdatePage.setRole(newRole);
		adminUpdatePage.clickSubmitButton();

	}

	@Test
	public void adminDeleteUser() throws InterruptedException {
		adminUpdatePage = new AdminManageUsers(driver);
		adminUpdatePage.clickDeleteBtn();
	}
}
