package ru.mow.domain.tests.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.mow.domain.tests.utils.ConfigProperties;

public abstract class Page {
	protected WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	public abstract void open();

	protected void setValueInField(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	public boolean isElementPresent(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void waitForJob() {
		driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperties("impl.wait")),
				TimeUnit.SECONDS);
	}
}
