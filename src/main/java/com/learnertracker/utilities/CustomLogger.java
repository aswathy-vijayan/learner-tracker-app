package com.learnertracker.utilities;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CustomLogger {
	private final Logger logger;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RED = "\u001B[31m";

	public CustomLogger(String loggerName) {
		this.logger = Logger.getLogger(loggerName);

		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		this.logger.addHandler(consoleHandler);

		// Create file handler with append option
		try {
			FileHandler fileHandler = new FileHandler("src/test/resources/tests.log", true);
			fileHandler.setFormatter(new SimpleFormatter());
			fileHandler.setLevel(Level.ALL);
			this.logger.addHandler(fileHandler);
//			fileHandler.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.logger.setLevel(Level.ALL);
		this.logger.setUseParentHandlers(false);

	}

	public void logInfo(String message) {
		System.out.println(ANSI_BLUE + "INFO: " + message + ANSI_RESET);
	}

	public void logWarning(String message) {
		System.out.println(ANSI_YELLOW + "WARNING: " + message + ANSI_RESET);
	}

	public void logError(String message) {
		System.out.println(ANSI_RED + "ERROR: " + message + ANSI_RESET);
	}
}
