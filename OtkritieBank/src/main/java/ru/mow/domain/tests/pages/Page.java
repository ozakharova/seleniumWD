package ru.mow.domain.tests.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.mow.domain.tests.utils.ConfigProperties;

public class Page {
	protected WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	public void setValueInField(WebElement element, String value) {
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

	public void waitAjax() {
		(new WebDriverWait(driver, getImplWaitProperties()))
				.until(ExpectedConditions.jsReturnsValue("return jQuery.active == 0"));
	}

	// private

	private long getImplWaitProperties() {
		return Long.parseLong(ConfigProperties.getProperties("impl.wait"));
	}

}
