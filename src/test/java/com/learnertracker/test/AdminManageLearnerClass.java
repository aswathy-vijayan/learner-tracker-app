package com.learnertracker.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.pages.AdminManageLearner;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.utilities.ExcelUtility;

public class AdminManageLearnerClass extends TestBase {
	// / Test Admin user can edit and delete learner

	LoginPage loginPage = null;
	AdminManageLearner adminpage = null;

	@BeforeTest
	public void secondAdminLogin() {
		String username = ExcelUtility.getCellData(6, 0);
		String password = ExcelUtility.getCellData(6, 1);
		loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
	}

	@Test
	public void adminEditLearner() throws InterruptedException {
		adminpage = new AdminManageLearner(driver);
		adminpage.clickLearner();
		adminpage.clickEditButton();

		String newLearnerId = ExcelUtility.getCellData(36, 0);
		String newName = ExcelUtility.getCellData(36, 1);
		String newCourse = ExcelUtility.getCellData(36, 2);
		String newProject = ExcelUtility.getCellData(36, 3);
		String newBatch = ExcelUtility.getCellData(36, 4);
		String newCourseStatus = ExcelUtility.getCellData(36, 5);

		adminpage.enterLearnerId(newLearnerId);
		adminpage.enterName(newName);
		adminpage.setCourse(newCourse);
		adminpage.setProject(newProject);
		adminpage.setBatch(newBatch);
		adminpage.setCourseStatus(newCourseStatus);

		adminpage.clickSubmitBtn();
		Thread.sleep(1000);
	}

	@Test
	public void adminDeleteLearner() throws InterruptedException {
		adminpage = new AdminManageLearner(driver);
		adminpage.clickLearner();
		Thread.sleep(1000);
		adminpage.clickDeleteButton();
		Thread.sleep(1000);
	}

}
