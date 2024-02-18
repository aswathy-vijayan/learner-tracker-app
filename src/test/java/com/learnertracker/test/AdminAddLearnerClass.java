package com.learnertracker.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learnertracker.constants.AutomationConstants;
import com.learnertracker.pages.AdminManageLearner;
import com.learnertracker.pages.LoginPage;
import com.learnertracker.utilities.CustomLogger;
import com.learnertracker.utilities.ExcelUtility;

public class AdminAddLearnerClass extends TestBase {
	// Test admin user can add learner

	LoginPage loginPage = null;
	AdminManageLearner adminpage = null;
	CustomLogger logger = new CustomLogger(AdminAddLearnerClass.class.getName());

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

	@BeforeMethod
	public void LearnerForm() {
		adminpage = new AdminManageLearner(driver);
		adminpage.clickLearner();
		logger.logInfo("Learners listing menu item clicked");
	}

	public void runLearnerCreate(int row, String testName) throws InterruptedException {
		logger.logInfo("Started " + testName + " test");
		adminpage.addLearner();
		String learnerId = ExcelUtility.getCellData(row, 0);
		String name = ExcelUtility.getCellData(row, 1);
		String course = ExcelUtility.getCellData(row, 2);
		String project = ExcelUtility.getCellData(row, 3);
		String batch = ExcelUtility.getCellData(row, 4);
		String courseStatus = ExcelUtility.getCellData(row, 5);
		logger.logInfo("Collected form data from excel sheet");

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
		logger.logInfo("Submit button clicked");
		Thread.sleep(1000);
	}

	@Test(priority = 1)
	public void addLearnersWithValidData() throws InterruptedException {
		// Test Admin can add learner with valid data
		runLearnerCreate(20, "addLearnersWithValidData");
		String acutalSuccessMsg = adminpage.successMessage();
		logger.logInfo("Got success message: " + acutalSuccessMsg);
		adminpage.clickOkBtn();
		Assert.assertEquals(acutalSuccessMsg, AutomationConstants.AddLearnerSuccessMessage);
		logger.logInfo("Completed addLearnersWithValidData test");
	}

	@Test(priority = 2)
	public void addLearnersWithInvalidLearnerId() throws InterruptedException {
		// Test Admin can add learner with invalid learnerId
		runLearnerCreate(21, "addLearnersWithInvalidLearnerId");
		String acutalinvalidLearnerIdWarningMsg = adminpage.invalidLearnerIdWarningMsg();
		logger.logInfo("Got message: " + acutalinvalidLearnerIdWarningMsg);
		adminpage.backToDashboard();
		Assert.assertEquals(acutalinvalidLearnerIdWarningMsg, AutomationConstants.InvalidLearnerIdWarningMessage);
		logger.logInfo("Completed addLearnersWithInvalidLearnerId test");
	}

	@Test(priority = 3)
	public void addLearnersWithInvalidName() throws InterruptedException {
		// Test Admin can add learner with invalid name
		runLearnerCreate(22, "addLearnersWithInvalidName");
		String acutalinvalidNameWarningMsg = adminpage.invalidNameWarningMsg();
		logger.logInfo("Got message: " + acutalinvalidNameWarningMsg);
		adminpage.backToDashboard();
		Assert.assertEquals(acutalinvalidNameWarningMsg, AutomationConstants.InvalidNameWarningMessage);
		logger.logInfo("Completed addLearnersWithInvalidName test");
	}

	@Test(priority = 4)
	public void addLearnersWithBlankLearnerId() throws InterruptedException {
		// Test Admin can add learner with blank learnerId

		runLearnerCreate(27, "addLearnersWithBlankLearnerId");
		String acutalBlankLearnerIdWarningMsg = adminpage.invalidLearnerIdWarningMsg();
		logger.logInfo("Got message: " + acutalBlankLearnerIdWarningMsg);
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankLearnerIdWarningMsg, AutomationConstants.InvalidLearnerIdWarningMessage);
		logger.logInfo("Completed addLearnersWithBlankLearnerId test");
	}

	@Test(priority = 5)
	public void addLearnersWithBlankName() throws InterruptedException {
		// Test Admin can add learner with blank name field
		runLearnerCreate(28, "addLearnersWithBlankName");
		String acutalBlankNameWarningMsg = adminpage.invalidNameWarningMsg();
		logger.logInfo("Got message: " + acutalBlankNameWarningMsg);
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankNameWarningMsg, AutomationConstants.InvalidNameWarningMessage);
		logger.logInfo("Completed addLearnersWithBlankName test");
	}

	@Test(priority = 6)
	public void addLearnersWithBlankCourseField() throws InterruptedException {
		// Test Admin can add learner with blank course field
		runLearnerCreate(23, "addLearnersWithBlankCourseField");
		String acutalBlankCourseFieldWarningMsg = adminpage.blankCoursefieldWarningMsg();
		logger.logInfo("Got message: " + acutalBlankCourseFieldWarningMsg);
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankCourseFieldWarningMsg, AutomationConstants.BlankCourseFieldMessage);
		logger.logInfo("Completed addLearnersWithBlankCourseField test");
	}

	@Test(priority = 7)
	public void addLearnersWithBlankProjectField() throws InterruptedException {
		// Test Admin can add learner with blank project field
		runLearnerCreate(24, "addLearnersWithBlankProjectField");
		String acutalBlankProjectFieldWarningMsg = adminpage.blankProjectfieldWarningMsg();
		logger.logInfo("Got message: " + acutalBlankProjectFieldWarningMsg);
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankProjectFieldWarningMsg, AutomationConstants.BlankProjectFieldMessage);
		logger.logInfo("Completed addLearnersWithBlankProjectField test");
	}

	@Test(priority = 8)
	public void addLearnersWithBlankBatchField() throws InterruptedException {
		// Test Admin can add learner with blank batch field
		runLearnerCreate(25, "addLearnersWithBlankBatchField");
		String acutalBlankBatchFieldWarningMsg = adminpage.blankBatchfieldWarningMsg();
		logger.logInfo("Got message: " + acutalBlankBatchFieldWarningMsg);
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankBatchFieldWarningMsg, AutomationConstants.BlankBatchFieldMessage);
		logger.logInfo("Completed addLearnersWithBlankBatchField test");
	}

	@Test(priority = 9)
	public void addLearnersWithBlankCourseStatusField() throws InterruptedException {
		// Test Admin can add learner with blank course status field
		runLearnerCreate(26, "addLearnersWithBlankCourseStatusField");
		String acutalBlankCourseStatusFieldWarningMsg = adminpage.blankCourseStatusfieldWarningMsg();
		logger.logInfo("Got message: " + acutalBlankCourseStatusFieldWarningMsg);
		adminpage.backToDashboard();
		Assert.assertEquals(acutalBlankCourseStatusFieldWarningMsg, AutomationConstants.BlankCourseStatusFieldMessage);
		logger.logInfo("Completed addLearnersWithBlankCourseStatusField test");
	}

	@Test(priority = 10)
	public void trainerAddLearnersViaCsv() {
		// Test admin user can add learners via CSV file
		adminpage.clickFileUploadBtn();
		logger.logInfo("Navigated to csv upload page ");
		adminpage.chooseCsvFile();
		adminpage.submitCsvUpload();
		logger.logInfo("Submit button clicked");
		String successMsg = adminpage.getCsvUploadSuccessMessage();
		adminpage.clickReturnToDashboardBtn();
		adminpage.clickOkBtn();
		Assert.assertEquals(successMsg, AutomationConstants.AddBulkLearnerSuccessMessage);
		logger.logInfo("Completed bulk addition of Learners using csv test");

	}

}
