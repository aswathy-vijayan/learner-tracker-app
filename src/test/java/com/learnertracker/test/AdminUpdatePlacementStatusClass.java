package com.learnertracker.test;

import org.testng.annotations.Test;

import com.learnertracker.pages.AdminPlacementStatus;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.utilities.ExcelUtility;

public class AdminUpdatePlacementStatusClass extends TestBase {
	// Test Admin user can update placement status of learner

	LoginPage loginPage = null;
	AdminPlacementStatus adminPlacementPage = null;

	@Test
	public void adminPlacementStatusUpdate() {
		String username = ExcelUtility.getCellData(6, 0);
		String password = ExcelUtility.getCellData(6, 1);

		loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();

		adminPlacementPage = new AdminPlacementStatus(driver);
		adminPlacementPage.clickPlacement();
		adminPlacementPage.clickPlacementUpdateBtn();

		adminPlacementPage.updatePlacementStatus(3);
		adminPlacementPage.clickSubmitBtn();
	}
}
