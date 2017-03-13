package ru.mow.domain.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ru.mow.domain.tests.utils.ConfigProperties;

public class HomePage extends Page {

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public static HomePage loadUsing(WebDriver driver) {
		driver.get(ConfigProperties.getProperties("home.url"));
		return new HomePage(driver);
	}

	@FindBy(name = "login")
	private WebElement loginField;

	@FindBy(name = "passwd")
	private WebElement passwordField;

	@FindBy(css = "button[class*='auth-button']")
	private WebElement loginButton;

	public InboxPage loginAsUser(String login, String password) {
		this.setLogin(login)
				.setPassword(password)
				.clickLoginButton();
		return new InboxPage(driver);
	}

	/**
	 * Click
	 */

	public void clickLoginButton() {
		loginButton.click();
	}

	/**
	 * Set Value
	 */

	public HomePage setLogin(String value) {
		super.setValueInField(loginField, value);
		return this;
	}

	public HomePage setPassword(String value) {
		super.setValueInField(passwordField, value);
		return this;
	}

}
