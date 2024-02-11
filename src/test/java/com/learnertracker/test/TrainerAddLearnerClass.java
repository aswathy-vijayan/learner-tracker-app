package com.learnertracker.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.constants.AutomationConstants;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.pages.TrainerManageLearner;
import com.learnertracker.utilities.ExcelUtility;

public class TrainerAddLearnerClass extends TestBase {
	// Test trainer user can add learner

	LoginPage loginPage = null;
	TrainerManageLearner trainerManageLearner = null;

	@BeforeTest
	public void loginTrainer() {
		String username = ExcelUtility.getCellData(7, 0);
		String password = ExcelUtility.getCellData(7, 1);

		loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
	}

	@Test
	public void trainerAddLearner() {
		String learnerId = ExcelUtility.getCellData(20, 0);
		String name = ExcelUtility.getCellData(20, 1);
		String course = ExcelUtility.getCellData(20, 2);
		String project = ExcelUtility.getCellData(20, 3);
		String batch = ExcelUtility.getCellData(20, 4);
		String courseStatus = ExcelUtility.getCellData(20, 5);
		trainerManageLearner = new TrainerManageLearner(driver);
		trainerManageLearner.clickAddLearner();
		trainerManageLearner.enterLearnerId(learnerId);
		trainerManageLearner.enterName(name);

		trainerManageLearner.setCourse(course);
		trainerManageLearner.setProject(project);
		trainerManageLearner.setBatch(batch);
		trainerManageLearner.setCourseStatus(courseStatus);
		trainerManageLearner.clickSubmitBtn();
		String successMsg = trainerManageLearner.getAddLearnerSuccessMessage();
		trainerManageLearner.clickOkBtn();
		Assert.assertEquals(successMsg, AutomationConstants.AddLearnerSuccessMessage);
	}

	@Test
	public void trainerAddLearnerViaCsv() {
		trainerManageLearner = new TrainerManageLearner(driver);
		trainerManageLearner.clickFileUploadBtn();
		trainerManageLearner.chooseCsvFile();
		trainerManageLearner.submitCsvUpload();
		String successMsg = trainerManageLearner.getCsvUploadSuccessMessage();
		trainerManageLearner.clickReturnToDashboardBtn();
		trainerManageLearner.clickOkBtn();
		Assert.assertEquals(successMsg, AutomationConstants.AddBulkLearnerSuccessMessage);
	}

}
