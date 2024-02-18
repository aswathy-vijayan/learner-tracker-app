package com.learnertracker.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.constants.AutomationConstants;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.pages.TrainerManageLearner;
import com.learnertracker.utilities.CustomLogger;
import com.learnertracker.utilities.ExcelUtility;

public class TrainerAddLearnerClass extends TestBase {
	// Test trainer user can add learner

	LoginPage loginPage = null;
	TrainerManageLearner trainerManageLearner = null;
	CustomLogger logger = new CustomLogger(TrainerAddLearnerClass.class.getName());

	@BeforeTest
	public void loginTrainer() {
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
	public void trainerAddLearner() {
		logger.logInfo("Start testing trainer can add learner");
		String learnerId = ExcelUtility.getCellData(20, 0);
		String name = ExcelUtility.getCellData(20, 1);
		String course = ExcelUtility.getCellData(20, 2);
		String project = ExcelUtility.getCellData(20, 3);
		String batch = ExcelUtility.getCellData(20, 4);
		String courseStatus = ExcelUtility.getCellData(20, 5);
		logger.logInfo("Collected form data from excel sheet");

		trainerManageLearner = new TrainerManageLearner(driver);
		trainerManageLearner.clickAddLearner();
		trainerManageLearner.enterLearnerId(learnerId);
		trainerManageLearner.enterName(name);

		trainerManageLearner.setCourse(course);
		trainerManageLearner.setProject(project);
		trainerManageLearner.setBatch(batch);
		trainerManageLearner.setCourseStatus(courseStatus);
		trainerManageLearner.clickSubmitBtn();
		logger.logInfo("Submitted learner create form");
		String successMsg = trainerManageLearner.getAddLearnerSuccessMessage();
		logger.logInfo("Got message: " + successMsg);
		trainerManageLearner.clickOkBtn();
		Assert.assertEquals(successMsg, AutomationConstants.AddLearnerSuccessMessage);
	}

	@Test(priority = 2)
	public void trainerAddLearnersViaCsv() {
		// Test trainer user can add learners via CSV file
		logger.logInfo("Test trainer user can add learners via CSV file");
		trainerManageLearner = new TrainerManageLearner(driver);
		trainerManageLearner.clickFileUploadBtn();
		trainerManageLearner.chooseCsvFile();
		trainerManageLearner.submitCsvUpload();
		logger.logInfo("Submitted csv file upload form");
		String successMsg = trainerManageLearner.getCsvUploadSuccessMessage();
		logger.logInfo("Got message: " + successMsg);
		trainerManageLearner.clickReturnToDashboardBtn();
		trainerManageLearner.clickOkBtn();
		Assert.assertEquals(successMsg, AutomationConstants.AddBulkLearnerSuccessMessage);
	}

}
