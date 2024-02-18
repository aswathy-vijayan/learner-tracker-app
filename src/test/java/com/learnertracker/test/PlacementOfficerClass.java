package com.learnertracker.test;

import org.testng.annotations.Test;

import com.learnertracker.pages.LoginPage;
import com.learnertracker.pages.PlacementOfficerPage;
import com.learnertracker.utilities.CustomLogger;
import com.learnertracker.utilities.ExcelUtility;

public class PlacementOfficerClass extends TestBase {
	// Test placement officer can update placement status of learner

	LoginPage loginPage = null;
	PlacementOfficerPage placementStatusUpdate = null;
	CustomLogger logger = new CustomLogger(AdminAddLearnerClass.class.getName());

	@Test(priority = 1)
	public void learnerPlacementStatusUpdate() {
		logger.logInfo("Logging in with placement officer user credentials");
		String username = ExcelUtility.getCellData(8, 0);
		String password = ExcelUtility.getCellData(8, 1);

		loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		logger.logInfo("Logged in to placement officer account");

		placementStatusUpdate = new PlacementOfficerPage(driver);
		placementStatusUpdate.clickPlacementUpdateBtn();
		placementStatusUpdate.updatePlacementStatus(3);
		placementStatusUpdate.clickSubmitBtn();
		logger.logInfo("Completed placement officer can update status");
	}
}
