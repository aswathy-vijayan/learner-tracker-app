package com.learnertracker.pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TrainerManageLearner {
	WebDriver driver;

	public TrainerManageLearner(WebDriver d) {
		this.driver = d;
	}

	public void clickAddLearner() {
		WebElement clickAddLearnerElement = driver.findElement(By.xpath("//a[@href='/tadd']"));
		clickAddLearnerElement.click();
	}

	public void enterLearnerId(String learnerId) {
		WebElement learnerIdElement = driver.findElement(By.id("learnerid"));
		learnerIdElement.clear();
		learnerIdElement.sendKeys(String.valueOf(learnerId));
	}

	public void enterName(String name) {
		WebElement learnerNameElement = driver.findElement(By.id("name"));
		learnerNameElement.clear();
		learnerNameElement.sendKeys(name);
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

	public void setCourseStatus(String courseStatus) {
		Select courseStatusDropdown = new Select(driver.findElement(By.name("cstatus")));
		courseStatusDropdown.selectByVisibleText(courseStatus);
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

	public void clickEditButton() {
		WebElement editButton = driver.findElement(By.xpath("(//button[@class='btn btn-success'])[1]"));
		editButton.click();
	}

	public void clickDeleteButton() {
		WebElement deleteBtn = driver.findElement(By.xpath("(//button[@class='btn btn-danger'])[2]"));
		deleteBtn.click();
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

	public String getAddLearnerSuccessMessage() {
		try {
			return driver.findElement(By.id("swal2-html-container")).getText();
		} catch (Exception e) {
			return null;
		}
	}

	public String getCsvUploadSuccessMessage() {
		try {
			return driver.findElement(By.id("swal2-title")).getText();
		} catch (Exception e) {
			return null;
		}
	}

}
