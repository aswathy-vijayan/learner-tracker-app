package com.learnertracker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AdminManageUsers {
	WebDriver driver;

	public AdminManageUsers(WebDriver d) {
		this.driver = d;
	}

	public void clickUpdateButtonUsers() {
		System.out.println("111");
		WebElement updateUsersBtn = driver
				.findElement(By.xpath("(//button[@class='btn btn-success btn btn-primary'])[1]"));
		System.out.println("111");
		updateUsersBtn.click();
		System.out.println("111");
	}

	public void enterName(String name) {
		WebElement nameEl = driver.findElement(By.id("name"));
		nameEl.clear();
		nameEl.sendKeys(name);
	}

	public void enterEmail(String email) {
		WebElement userEmail = driver.findElement(By.id("email"));
		userEmail.clear();
		userEmail.sendKeys(email);
	}

	public void enterUsername(String username) {
		WebElement uNameEl = driver.findElement(By.xpath("//input[@id='username']"));
		uNameEl.clear();
		uNameEl.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement passwordEl = driver.findElement(By.id("password"));
		passwordEl.clear();
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

	public void clickDeleteBtn() {
		WebElement deleteBtn = driver.findElement(By.xpath("(//button[@class='btn btn-danger btn btn-primary'])[2]"));
		deleteBtn.click();
	}
}
