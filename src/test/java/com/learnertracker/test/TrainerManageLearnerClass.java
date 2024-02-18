package com.learnertracker.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.pages.LoginPage;
import com.learnertracker.pages.TrainerManageLearner;
import com.learnertracker.utilities.CustomLogger;
import com.learnertracker.utilities.ExcelUtility;

public class TrainerManageLearnerClass extends TestBase {
	// Test trainer user can edit and delete learner

	LoginPage loginPage = null;
	TrainerManageLearner trainerManagelearner = null;
	CustomLogger logger = new CustomLogger(AdminAddLearnerClass.class.getName());

	@BeforeTest
	public void trainerLogin() {
		logger.logInfo("Logging in with trainer user credentials");
		String username = ExcelUtility.getCellData(7, 0);
		String password = ExcelUtility.getCellData(7, 1);

		loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		logger.logInfo("Logged in to trainer account");
	}

	@Test(priority = 1)
	public void editLearner() throws InterruptedException {
		// Test Trainer can edit learner
		logger.logInfo("Start testing trainer can edit learner");
		Thread.sleep(3000);
		trainerManagelearner = new TrainerManageLearner(driver);
		trainerManagelearner.clickEditButton();

		String newLearnerId = ExcelUtility.getCellData(36, 0);
		String newName = ExcelUtility.getCellData(36, 1);
		String newCourse = ExcelUtility.getCellData(36, 2);
		String newProject = ExcelUtility.getCellData(36, 3);
		String newBatch = ExcelUtility.getCellData(36, 4);
		String newCourseStatus = ExcelUtility.getCellData(36, 5);
		logger.logInfo("Collected form data from excel sheet");
		trainerManagelearner.enterLearnerId(newLearnerId);
		trainerManagelearner.enterName(newName);
		trainerManagelearner.setCourse(newCourse);
		trainerManagelearner.setProject(newProject);
		trainerManagelearner.setBatch(newBatch);
		trainerManagelearner.setCourseStatus(newCourseStatus);

		trainerManagelearner.clickSubmitBtn();
		logger.logInfo("Submitted learner edit form");

	}

	@Test(priority = 2)
	public void deleteLearner() throws InterruptedException {
		// Test Trainer can delete learner
		logger.logInfo("Test Trainer can delete learner");
		Thread.sleep(3000);
		trainerManagelearner = new TrainerManageLearner(driver);
		trainerManagelearner.clickDeleteButton();
		logger.logInfo("Deleted learner");
	}
}
