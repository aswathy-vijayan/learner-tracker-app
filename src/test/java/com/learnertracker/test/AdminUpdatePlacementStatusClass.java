package com.learnertracker.test;

import org.testng.annotations.Test;

import com.learnertracker.pages.AdminPlacementStatus;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.utilities.CustomLogger;
import com.learnertracker.utilities.ExcelUtility;

public class AdminUpdatePlacementStatusClass extends TestBase {
	// Test Admin user can update placement status of learner

	LoginPage loginPage = null;
	AdminPlacementStatus adminPlacementPage = null;
	CustomLogger logger = new CustomLogger(AdminAddLearnerClass.class.getName());

	@Test(priority = 1)
	public void adminPlacementStatusUpdate() {
		logger.logInfo("Logging in with admin user credentials");
		String username = ExcelUtility.getCellData(6, 0);
		String password = ExcelUtility.getCellData(6, 1);

		loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		logger.logInfo("Logged in to admin account");

		adminPlacementPage = new AdminPlacementStatus(driver);
		adminPlacementPage.clickPlacement();
		adminPlacementPage.clickPlacementUpdateBtn();

		adminPlacementPage.updatePlacementStatus(3);
		adminPlacementPage.clickSubmitBtn();
		logger.logInfo("Completed admin placement status update test");
	}
}
