package com.learnertracker.pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AdminManageLearner {
	WebDriver driver;

	public AdminManageLearner(WebDriver d) {
		this.driver = d;
	}

	public void clickLearner() {
		WebElement clickLearnerElement = driver.findElement(By.xpath("//a[@href='/thome']"));
		clickLearnerElement.click();
	}

	public void addLearner() {
		WebElement addLearnerElement = driver.findElement(By.xpath("//a[@href='/tadd']"));
		addLearnerElement.click();
	}

	public void enterLearnerId(String id) {
		WebElement LearnerIdElement = driver.findElement(By.id("learnerid"));
		LearnerIdElement.clear();
		LearnerIdElement.sendKeys(String.valueOf(id));
	}

	public void enterName(String name) {
		WebElement LearnerNameElement = driver.findElement(By.id("name"));
		LearnerNameElement.clear();
		LearnerNameElement.sendKeys(name);
	}

	public void setCourse(String course) {
		Select courseDropdown = new Select(driver.findElement(By.name("course")));
		courseDropdown.selectByVisibleText(course);
	}

	public void setProject(String project) {
		Select projectDropdown = new Select(driver.findElement(By.name("project")));
		projectDropdown.selectByVisibleText(project);
	}

	public void setBatch(String batch) {
		Select batchDropdown = new Select(driver.findElement(By.name("batch")));
		batchDropdown.selectByVisibleText(batch);
	}

	public void setCourseStatus(String batch) {
		Select courseStatusDropdown = new Select(driver.findElement(By.name("cstatus")));
		courseStatusDropdown.selectByVisibleText(batch);
	}

	public void clickSubmitBtn() {
		WebElement submitBtn = driver.findElement(By.xpath("//button[@class='btn btn-success']"));
		submitBtn.click();
	}

	public String successMessage() {
		WebElement successMsg = driver.findElement(By.id("swal2-html-container"));
		return successMsg.getText();
	}

	public void clickOkBtn() {
		WebElement okButton = driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']"));
		okButton.click();
	}

	public void backToDashboard() {
		WebElement backToDashboardEl = driver.findElement(By.xpath("(//a[@href='/thome'])[2]"));
		backToDashboardEl.click();

	}

	public void clickEditButton() {
		WebElement editButton = driver.findElement(By.xpath("(//button[@class='btn btn-success'])[1]"));
		editButton.click();
	}

	public void clickDeleteButton() {
		WebElement deleteBtn = driver.findElement(By.xpath("(//button[@class='btn btn-danger'])[2]"));
		deleteBtn.click();
	}

	public String invalidLearnerIdWarningMsg() {
		WebElement invalidIdWarningMsgEl = driver
				.findElement(By.xpath("//input[@id='learnerid']/following-sibling::p"));
		return invalidIdWarningMsgEl.getText();
	}

	public String invalidNameWarningMsg() {
		WebElement namefailedMsgEl = driver.findElement(By.xpath("//input[@id='name']/following-sibling::p"));
		return namefailedMsgEl.getText();
	}

	public String blankCoursefieldWarningMsg() {
		WebElement blankCoursefieldWarningMsgEl = driver
				.findElement(By.xpath("//select[@name='course']/following-sibling::p"));
		return blankCoursefieldWarningMsgEl.getText();
	}

	public String blankProjectfieldWarningMsg() {
		WebElement blankCoursefieldWarningMsgEl = driver
				.findElement(By.xpath("//select[@name='project']/following-sibling::p"));
		return blankCoursefieldWarningMsgEl.getText();
	}

	public String blankBatchfieldWarningMsg() {
		WebElement blankBatchfieldWarningMsgEl = driver
				.findElement(By.xpath("//select[@name='batch']/following-sibling::p"));
		return blankBatchfieldWarningMsgEl.getText();
	}

	public String blankCourseStatusfieldWarningMsg() {
		WebElement blankCourseStatusWarningMsgEl = driver
				.findElement(By.xpath("//select[@name='cstatus']/following-sibling::p"));
		return blankCourseStatusWarningMsgEl.getText();
	}

	public void clickFileUploadBtn() {
		WebElement fileUploadBtn = driver.findElement(By.xpath("//a[@href='/upload']"));
		fileUploadBtn.click();
	}

	public void chooseCsvFile() {
		WebElement chooseFileBtn = driver.findElement(By.xpath("//input[@type='file']"));
		File csvFile = new File("src/main/resources/learners-list-test-data.csv");
		chooseFileBtn.sendKeys(csvFile.getAbsolutePath());
	}

	public void submitCsvUpload() {
		WebElement submitCsvBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		submitCsvBtn.click();
	}

	public void clickReturnToDashboardBtn() {
		WebElement returnToDashboardBtn = driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']"));
		returnToDashboardBtn.click();
	}

	public String getCsvUploadSuccessMessage() {
		try {
			return driver.findElement(By.id("swal2-title")).getText();
		} catch (Exception e) {
			return null;
		}
	}

}
