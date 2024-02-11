package com.learnertracker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AdminUsers {
	WebDriver driver;

	public AdminUsers(WebDriver d) {
		this.driver = d;
	}

	public void clickAddUsers() {
		WebElement addUsersBtn = driver.findElement(By.xpath("//a[@href='/aadd']"));
		addUsersBtn.click();
	}

	public void enterName(String name) {
		WebElement nameEl = driver.findElement(By.id("name"));
		nameEl.sendKeys(name);
	}

	public void enterEmail(String email) {
		WebElement UserEmail = driver.findElement(By.id("email"));
		UserEmail.sendKeys(email);
	}

	public void enterUsername(String username) {
		WebElement uNameEl = driver.findElement(By.id("username"));
		uNameEl.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement passwordEl = driver.findElement(By.id("password"));
		passwordEl.sendKeys(password);
	}

	public void setRole(String role) {
		Select roleDropdown = new Select(driver.findElement(By.name("roleInputs")));
		roleDropdown.selectByVisibleText(role);
	}

	public void clickSubmitButton() {
		WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
		submitButton.click();
	}

	public void clickOkBtn() {
		WebElement okButton = driver.findElement(By.xpath("//button[text()='OK']"));
		okButton.click();
	}

	public String successMessage() {
		WebElement successMsg = driver.findElement(By.id("swal2-html-container"));
		return successMsg.getText();
	}

	public String invalidNameWarningMsg() {
		WebElement namefailedMsgEl = driver.findElement(By.xpath("//input[@id='name']/following-sibling::p"));
		return namefailedMsgEl.getText();
	}

	public void clickBackToDashboard() {
		WebElement backToDashboard = driver.findElement(By.xpath("(//a[@href='/ahome'])[2]"));
		backToDashboard.click();
	}

	public String invalidEmailWarningMsg() {
		try {
			WebElement invalidEmailMsgEl = driver.findElement(By.xpath("//input[@id='email']/following-sibling::p"));
			return invalidEmailMsgEl.getText();
		} catch (Exception e) {
			return null;
		}
	}

	public String invalidUsernameWarningMsg() {
		WebElement invalidUsernameMsgEl = driver.findElement(By.xpath("//input[@id='username']/following-sibling::p"));
		return invalidUsernameMsgEl.getText();
	}

	public String invalidPasswordWarningMsg() {
		WebElement invalidPasswordMsgEl = driver.findElement(By.xpath("//input[@id='password']/following-sibling::p"));
		return invalidPasswordMsgEl.getText();
	}

}
