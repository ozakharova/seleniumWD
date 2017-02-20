package ru.mow.domain.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ru.mow.domain.tests.utils.ConfigProperties;

public class HomePage extends Page {

	public static final String ERROR_LABEL_TEXT = "Проверьте правильность ввода логина и пароля.";

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".ePanelLinks_link.mPopupArrow.jsQuickPanelUserMenu")
	private WebElement userMenuLink;

	@FindBy(css = ".ePanelLinks_term.jsOption.jsClearTilesFromStorage.jsLogOff.jsBottomPart")
	private WebElement logoutLink;

	@FindBy(xpath = "//iframe[contains(@class,'eUserLoginMenu_frame')]")
	private WebElement frame;

	@FindBy(id = "jsLogin")
	private WebElement loginField;

	@FindBy(id = "jsPassword")
	private WebElement passwordField;

	@FindBy(name = "Answer")
	private WebElement imageCodeField;

	@FindBy(id = "Authentication")
	private WebElement submitButton;

	@FindBy(css = ".eLoginUserForm_register.jsRegister")
	private WebElement registerLink;

	@FindBy(css = ".eFormLabel_helpLink>a")
	private WebElement fogotPasswordLink;

	@FindBy(id = "PageFooter_ctl01_NewCaptcha")
	private WebElement dontSeeCodeLink;

	@FindBy(css = "#PageFooter_ctl01_CaptchaUpdatePanel>div>img")
	private WebElement errorImage;

	@FindBy(id = "PageFooter_ctl01_ErrorLabel")
	private WebElement errorLabel;

	@FindBy(css = ".ePanelLinks_term.jsOption.jsClearTilesFromStorage.jsLoginPanel.jsBottomPart")
	private WebElement enterLink;

	@Override
	public void open() {
		driver.get(ConfigProperties.getProperties("home.url"));
	}

	public HomePage moveToUserMenuLink() {
		Actions actions = new Actions(driver);
		actions.moveToElement(userMenuLink).build().perform();
		return this;
	}

	public void loginAsUser(String login, String password) {
		this.setLogin(login)
				.setPassword(password)
				.clickSubmitButton();
	}

	/**
	 * Click
	 */

	public void clickEnterLink() throws InterruptedException {
		enterLink.click();
		super.waitForJob();
		driver.switchTo().frame(frame);
	}

	public void clickRegisterLink() {
		registerLink.click();
	}

	public FogotPasswordPage clickFogotPasswordLink() {
		fogotPasswordLink.click();
		waitForJob();
		return PageFactory.initElements(driver, FogotPasswordPage.class);
	}

	public void clickDontSeeCodeLink() {
		dontSeeCodeLink.click();
	}

	public void clickSubmitButton() {
		submitButton.submit();
	}

	public void clickLogoutLink() {
		logoutLink.click();
	}

	/**
	 * Is Element Present
	 */

	public boolean isErrorImagePresent() {
		return isElementPresent(errorImage);
	}

	public boolean isLogoutLinkPresent() {
		return isElementPresent(logoutLink);
	}

	public boolean isImageCodeFieldPresent() {
		return isElementPresent(imageCodeField);
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

	public HomePage setImageCode(String value) {
		super.setValueInField(imageCodeField, value);
		return this;
	}

	/**
	 * Get text
	 */

	public String getLoginValue() {
		return loginField.getAttribute("value");
	}

	public String getPasswordValue() {
		return passwordField.getAttribute("value");
	}

	public String getUserNameLinkText() {
		return userMenuLink.getText();
	}

	public HomePage getErrorImageText() {
		errorImage.getText();
		return this;
	}

	public String getErrorText() {
		String errorLabelText = null;
		if (isElementPresent(errorLabel)) {
			errorLabelText = errorLabel.getText();
		}
		return errorLabelText;
	}

	public void logout() {
		Actions actions = new Actions(driver);
		actions.moveToElement(userMenuLink).build().perform();
		clickLogoutLink();
	}
}
