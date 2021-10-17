package com.flink.driverInit;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

	/**
	 * Manage driver using getter and setter methods
	 */
	
	private static final ThreadLocal<WebDriver> dr = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return dr.get();
	}

	static void setDriver(WebDriver driverref) {
		dr.set(driverref);
	}

	static void unload() {
		dr.remove();
	}

}
