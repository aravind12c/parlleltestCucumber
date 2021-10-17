package com.flink.pageObjects;

import java.util.List;
import com.flink.driverInit.DriverManager;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class BasePage {
	
	/**
	 * All common functions are associated here
	 */

	private static String waitstrategyvisible = "Visible";
	private static String waitstrategyclickable = "Clickable";

	protected static void clickOn(By by) {
		WebElement element = performExplicitWait(waitstrategyclickable, by);
		element.click();
	}

	protected static boolean isElementDisplayed(By by) {
		boolean flag = false;
		WebElement element = performExplicitWait(waitstrategyvisible, by);
		if (element.isDisplayed()) {
			flag = true;
		}
		return flag;
	}

	protected static String getTextOn(By by) {
		WebElement element = performExplicitWait(waitstrategyvisible, by);
		return element.getText();
	}

	protected static String getTextOnEle(WebElement element) {
		return element.getText();
	}

	protected static String getAttributeOn(By by, String key) {
		WebElement element = performExplicitWait(waitstrategyvisible, by);
		return element.getAttribute(key);
	}

	protected static void sendKeysOn(By by, String value) {
		WebElement element = performExplicitWait(waitstrategyclickable, by);
		element.sendKeys(value);
	}

	protected static void sendKeysOnJS(By by, String value) {
		WebElement element = performExplicitWait(waitstrategyclickable, by);
		JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
		jse.executeScript("arguments[0].value='" + value + "';", element);
	}

	protected static void switchToFrame(By by) {
		WebElement element = performExplicitWait(waitstrategyclickable, by);
		DriverManager.getDriver().switchTo().frame(element);
	}

	protected static void switchToDefault() {
		DriverManager.getDriver().switchTo().defaultContent();
	}

	protected static void pause(int secs) {
		try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	protected static List<WebElement> listOfElements(By by) {
		List<WebElement> ele = DriverManager.getDriver().findElements(by);
		return ele;
	}

	protected static WebElement performExplicitWait(String waitstrategy, By by) {
		WebElement element = null;
		if (waitstrategy.equalsIgnoreCase("Clickable")) {
			element = new WebDriverWait(DriverManager.getDriver(), 50)
					.until(ExpectedConditions.elementToBeClickable(by));
		} else if (waitstrategy.equalsIgnoreCase("Visible")) {
			element = new WebDriverWait(DriverManager.getDriver(), 50)
					.until(ExpectedConditions.presenceOfElementLocated(by));
		} else if (waitstrategy.equalsIgnoreCase("None")) {
			element = DriverManager.getDriver().findElement(by);
		}
		return element;
	}

}
