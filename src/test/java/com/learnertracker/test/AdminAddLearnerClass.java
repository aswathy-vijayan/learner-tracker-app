package com.learnertracker.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.constants.AutomationConstants;
import com.learnertracker.pages.AdminManageLearner;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.utilities.ExcelUtility;

public class AdminAddLearnerClass extends TestBase {
	// Test Admin user can add learner

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

	@BeforeMethod
	public void LearnerForm() {
		adminpage = new AdminManageLearner(driver);
		adminpage.clickLearner();
	}

	public void runLearnerCreate(int row) throws InterruptedException {
		adminpage.addLearner();

		String learnerId = ExcelUtility.getCellData(row, 0);
		String name = ExcelUtility.getCellData(row, 1);
		String course = ExcelUtility.getCellData(row, 2);
		String project = ExcelUtility.getCellData(row, 3);
		String batch = ExcelUtility.getCellData(row, 4);
		String courseStatus = ExcelUtility.getCellData(row, 5);

		if (learnerId != null)
			adminpage.enterLearnerId(learnerId);
		if (name != null)
			adminpage.enterName(name);
		if (course != null)
			adminpage.setCourse(course);
		if (project != null)
			adminpage.setProject(project);
		if (batch != null)
			adminpage.setBatch(batch);
		if (courseStatus != null)
			adminpage.setCourseStatus(courseStatus);

		adminpage.clickSubmitBtn();
		Thread.sleep(1000);
	}

	@Test
	public void addLearnersWithValidData() throws InterruptedException {
		runLearnerCreate(20);
		String acutalSuccessMsg = adminpage.successMessage();
		adminpage.clickOkBtn();
		Assert.assertEquals(acutalSuccessMsg, AutomationConstants.AddLearnerSuccessMessage);

	}

	@Test
	public void addLearnersWithInvalidLearnerId() throws InterruptedException {
		runLearnerCreate(21);
		String acutalinvalidLearnerIdWarningMsg = adminpage.invalidLearnerIdWarningMsg();
		adminpage.backToDashboard();
		Assert.assertEquals(acutalinvalidLearnerIdWarningMsg, AutomationConstants.InvalidLearnerIdWarningMessage);
	}

	@Test
	public void addLearnersWithInvalidName() throws InterruptedException {
		runLearnerCreate(22);
		String acutalinvalidNameWarningMsg = adminpage.invalidNameWarningMsg();
		adminpage.backToDashboard();
		Assert.assertEquals(acutalinvalidNameWarningMsg, AutomationConstants.InvalidNameWarningMessage);
	}

	@Test
	public void addLearnersWithBlankLearnerId() throws InterruptedException {
		runLearnerCreate(27);
		String acutalBlankLearnerIdWarningMsg = adminpage.invalidLearnerIdWarningMsg();
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankLearnerIdWarningMsg, AutomationConstants.InvalidLearnerIdWarningMessage);
	}

	@Test
	public void addLearnersWithBlankName() throws InterruptedException {
		runLearnerCreate(28);
		String acutalBlankNameWarningMsg = adminpage.invalidNameWarningMsg();
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankNameWarningMsg, AutomationConstants.InvalidNameWarningMessage);
	}

	@Test
	public void addLearnersWithBlankCourseField() throws InterruptedException {
		runLearnerCreate(23);
		String acutalBlankCourseFieldWarningMsg = adminpage.blankCoursefieldWarningMsg();
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankCourseFieldWarningMsg, AutomationConstants.BlankCourseFieldMessage);
	}

	@Test
	public void addLearnersWithBlankProjectField() throws InterruptedException {
		runLearnerCreate(24);
		String acutalBlankProjectFieldWarningMsg = adminpage.blankProjectfieldWarningMsg();
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankProjectFieldWarningMsg, AutomationConstants.BlankProjectFieldMessage);
	}

	@Test
	public void addLearnersWithBlankBatchField() throws InterruptedException {
		runLearnerCreate(25);
		String acutalBlankBatchFieldWarningMsg = adminpage.blankBatchfieldWarningMsg();
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankBatchFieldWarningMsg, AutomationConstants.BlankBatchFieldMessage);
	}

	@Test
	public void addLearnersWithBlankCourseStatusField() throws InterruptedException {
		runLearnerCreate(26);
		String acutalBlankCourseStatusFieldWarningMsg = adminpage.blankCourseStatusfieldWarningMsg();
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankCourseStatusFieldWarningMsg, AutomationConstants.BlankCourseStatusFieldMessage);
	}

	@Test
	public void trainerAddLearnersViaCsv() {
		// Test admin user can add learners via CSV file
		adminpage.clickFileUploadBtn();
		adminpage.chooseCsvFile();
		adminpage.submitCsvUpload();
		String successMsg = adminpage.getCsvUploadSuccessMessage();
		adminpage.clickReturnToDashboardBtn();
		adminpage.clickOkBtn();
		Assert.assertEquals(successMsg, AutomationConstants.AddBulkLearnerSuccessMessage);
	}

}
