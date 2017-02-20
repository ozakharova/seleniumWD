package ru.mow.domain.tests.loginpagetests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;

import ru.mow.domain.tests.utils.ConfigProperties;

public class BaseTest {
	protected WebDriver driver;

	protected WebDriver getWebDriver() {
		if (driver == null) {
			driver = getFirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperties("impl.wait")),
					TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		return driver;
	}

	private WebDriver getFirefoxDriver() {
		WebDriver firefoxDriver = null;
		try {
			firefoxDriver = new FirefoxDriver();
		} catch (WebDriverException e) {
			Assert.fail("Not connect to Firefox.");
		}
		return firefoxDriver;
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
