package com.learnertracker.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
	WebDriver driver = null;

	public CaptureScreenshot(WebDriver d) {
		this.driver = d;
	}

	public void captureScreenshot(String testName) {
		try {
			// Convert the WebDriver instance to TakesScreenshot
			TakesScreenshot screenshot = (TakesScreenshot) driver;

			// Capture the screenshot as a file
			File source = screenshot.getScreenshotAs(OutputType.FILE);

			// Define the destination path for the screenshot
			String destination = "./screenshots/" + testName + ".png";

			// Copy the screenshot to the destination path
			FileUtils.copyFile(source, new File(destination));
			System.out.println("Screenshot captured at: " + destination);
		} catch (Exception e) {
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}
	}
}
