package ru.mow.domain.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FogotPasswordPage extends Page {

	public static final String HEADER_TEXT = "Забыли пароль?";

	public FogotPasswordPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".bMainHeader_Header")
	private WebElement header;

	public String getHeaderText() {
		String headerText = null;
		if (isElementPresent(header)) {
			headerText = header.getText();
		}
		return headerText;
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
	}
}
