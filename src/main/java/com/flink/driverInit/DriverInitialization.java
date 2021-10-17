package com.flink.driverInit;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.flink.webUtilities.PropertyUtils;

import io.cucumber.testng.AbstractTestNGCucumberTests;

public class DriverInitialization extends AbstractTestNGCucumberTests {

	@Parameters({ "browsername" })
	@BeforeMethod
	public static void driverInit(String browsername) throws MalformedURLException {
		DriverManager.setDriver(getBrowserDriver(browsername));
		DriverManager.getDriver().get(PropertyUtils.configProperty("URL"));
		DriverManager.getDriver().manage().window().maximize();
	}

	@SuppressWarnings("deprecation")
	public static WebDriver getBrowserDriver(String browser) {
		WebDriver browserdriver = null;
		if (browser.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_settings.popups", 0);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
			options.setCapability("platform", "Any");
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");
			browserdriver = new ChromeDriver(options);
		} else if (browser.equals("firefox")) {
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);
			System.setProperty("webdriver.gecko.driver", "./src/main/resources/driver/geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			browserdriver = new FirefoxDriver(capabilities);
		}
		return browserdriver;
	}

	@AfterMethod
	public static void teardown() {
		DriverManager.getDriver().quit();

	}
}
