package com.learnertracker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver d) {
		this.driver = d;
	}

	public void setUsername(String uname) {
		WebElement unameElement = driver.findElement(By.id("username"));
		unameElement.sendKeys(uname);
	}

	public void setPassword(String password) {
		WebElement passwd = driver.findElement(By.id("password"));
		passwd.sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(By.xpath("//button[text()='Login']")).click();
	}

	public void clickName() {
		WebElement name = driver.findElement(By.id("basic-nav-dropdown"));
		name.click();
	}

	public void logout() {
		WebElement logoutButton = driver.findElement(By.xpath("//a[text()='Logout']"));
		logoutButton.click();
	}

	public String LoginStatusMessage() {
		WebElement messageElement = driver.findElement(By.xpath("//button[@class='btn-close']/parent::div"));
		return messageElement.getText();
	}

}
