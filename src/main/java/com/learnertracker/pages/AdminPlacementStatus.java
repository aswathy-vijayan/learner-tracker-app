package com.learnertracker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AdminPlacementStatus {
	WebDriver driver;

	public AdminPlacementStatus(WebDriver d) {
		this.driver = d;
	}

	public void clickPlacement() {
		WebElement clickPlacementBtn = driver.findElement(By.xpath("//a[@href='/phome']"));
		clickPlacementBtn.click();
	}

	public void clickPlacementUpdateBtn() {
		WebElement clickPlacementUpdateBtnEl = driver
				.findElement(By.xpath("(//button[@class='btn btn-success btn btn-primary'])[2]"));
		clickPlacementUpdateBtnEl.click();
	}

	public void updatePlacementStatus(int placementIndex) {
		Select placementStatusDropdown = new Select(driver.findElement(By.name("pstatus")));
		placementStatusDropdown.selectByIndex(placementIndex);
	}

	public void clickSubmitBtn() {
		WebElement submitBtn = driver.findElement(By.xpath("//button[@class='btn btn-success']"));
		submitBtn.click();
	}

	public void clickOkBtn() {
		WebElement okButton = driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']"));
		okButton.click();
	}
}
