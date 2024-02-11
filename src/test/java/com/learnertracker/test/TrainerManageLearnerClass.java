package com.learnertracker.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.pages.LoginPage;
import com.learnertracker.pages.TrainerManageLearner;
import com.learnertracker.utilities.ExcelUtility;

public class TrainerManageLearnerClass extends TestBase {
	// Test trainer user can edit and delete learner

	LoginPage loginPage = null;
	TrainerManageLearner trainerManagelearner = null;

	@BeforeTest
	public void trainerLogin() {
		String username = ExcelUtility.getCellData(7, 0);
		String password = ExcelUtility.getCellData(7, 1);

		loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
	}

	@Test
	public void editLearner() throws InterruptedException {
		Thread.sleep(3000);
		trainerManagelearner = new TrainerManageLearner(driver);
		trainerManagelearner.clickEditButton();

		String newLearnerId = ExcelUtility.getCellData(36, 0);
		String newName = ExcelUtility.getCellData(36, 1);
		String newCourse = ExcelUtility.getCellData(36, 2);
		String newProject = ExcelUtility.getCellData(36, 3);
		String newBatch = ExcelUtility.getCellData(36, 4);
		String newCourseStatus = ExcelUtility.getCellData(36, 5);

		trainerManagelearner.enterLearnerId(newLearnerId);
		trainerManagelearner.enterName(newName);
		trainerManagelearner.setCourse(newCourse);
		trainerManagelearner.setProject(newProject);
		trainerManagelearner.setBatch(newBatch);
		trainerManagelearner.setCourseStatus(newCourseStatus);

		trainerManagelearner.clickSubmitBtn();

	}

	@Test
	public void deleteLearner() throws InterruptedException {
		Thread.sleep(3000);
		trainerManagelearner = new TrainerManageLearner(driver);
		trainerManagelearner.clickDeleteButton();
	}
}
