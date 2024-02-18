package com.learnertracker.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.pages.AdminManageLearner;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.utilities.CustomLogger;
import com.learnertracker.utilities.ExcelUtility;

public class AdminManageLearnerClass extends TestBase {
	// / Test Admin user can edit and delete learner

	LoginPage loginPage = null;
	AdminManageLearner adminpage = null;
	CustomLogger logger = new CustomLogger(AdminManageLearnerClass.class.getName());

	@BeforeTest
	public void secondAdminLogin() {
		logger.logInfo("Logging in with admin user credentials");
		String username = ExcelUtility.getCellData(6, 0);
		String password = ExcelUtility.getCellData(6, 1);
		loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		logger.logInfo("Logged in to admin account");
	}

	@Test(priority = 1) // Test admin can edit learner
	public void adminEditLearner() throws InterruptedException {
		logger.logInfo("Start testing admin can edit learner");
		adminpage = new AdminManageLearner(driver);
		adminpage.clickLearner();
		adminpage.clickEditButton();

		String newLearnerId = ExcelUtility.getCellData(36, 0);
		String newName = ExcelUtility.getCellData(36, 1);
		String newCourse = ExcelUtility.getCellData(36, 2);
		String newProject = ExcelUtility.getCellData(36, 3);
		String newBatch = ExcelUtility.getCellData(36, 4);
		String newCourseStatus = ExcelUtility.getCellData(36, 5);
		logger.logInfo("Collected form data from excel sheet");

		adminpage.enterLearnerId(newLearnerId);
		adminpage.enterName(newName);
		adminpage.setCourse(newCourse);
		adminpage.setProject(newProject);
		adminpage.setBatch(newBatch);
		adminpage.setCourseStatus(newCourseStatus);

		adminpage.clickSubmitBtn();
		logger.logInfo("Submitted learner edit form");
		Thread.sleep(1000);
	}

	@Test(priority = 2) // Test admin can delete learner
	public void adminDeleteLearner() throws InterruptedException {
		logger.logInfo("Start testing admin can delete learner");
		adminpage = new AdminManageLearner(driver);
		adminpage.clickLearner();
		Thread.sleep(1000);
		adminpage.clickDeleteButton();
		Thread.sleep(1000);
		logger.logInfo("Deleted learner");
	}

}
