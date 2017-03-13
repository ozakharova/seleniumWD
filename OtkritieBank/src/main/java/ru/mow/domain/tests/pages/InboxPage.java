package ru.mow.domain.tests.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InboxPage extends Page {

	public static final String MESSAGE_WAS_SENT_TEXT = "Письмо отправлено.";
	public static final String NO_INBOX_MESSAGES_TEXT = "В папке «Входящие» нет писем.";

	public InboxPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a[class*='button-compose-go']")
	private WebElement writeButton;

	@FindBy(css = "div[class*='button-delete']")
	private WebElement deleteButton;

	@FindBy(css = "button[class*='nb-action-button']")
	private WebElement smallSendButton;

	@FindBy(name = "subj")
	private WebElement subject;

	@FindBy(name = "to")
	private WebElement destination;

	@FindBy(css = "a[href*='#trash']")
	private WebElement trashFolder;

	@FindBy(css = "a[href*='#inbox']")
	private WebElement inboxFolder;

	@FindBy(css = "div[class*='mail-Done-Title']")
	private WebElement messageWasSentTitle;

	@FindBy(css = "div[class*='b-messages__placeholder-item']")
	private WebElement inboxEmpty;

	@FindBy(css = "span[title*='text']")
	private WebElement subjectOfMessage;

	@FindBy(css = "span[class='checkbox_view']")
	private WebElement checkboxView;

	@FindBy(css = "input[class='_nb-checkbox-input']")
	private List<WebElement> listCheckboxes;

	public void createMail(String subject, String email) {
		this.clickWriteButton()
				.setSubject(subject)
				.setDestination(email)
				.clickSendButton()
				.openInboxFolder();
	}

	public InboxPage selectFirstMessage() {
		getFirstMessage().click();
		return this;
	}

	public InboxPage openTrashFolder() {
		trashFolder.click();
		waitAjax();
		return this;
	}

	public InboxPage openInboxFolder() {
		inboxFolder.click();
		waitAjax();
		return this;
	}

	public void cleanInboxFolder() {
		this.openInboxFolder()
				.cleanFolder();
	}

	public void cleanTrashFolder() {
		this.openTrashFolder()
				.cleanFolder();
	}

	/**
	 * Click
	 */

	public InboxPage clickWriteButton() {
		writeButton.click();
		return this;
	}

	public InboxPage clickSendButton() {
		smallSendButton.click();
		return this;
	}

	public InboxPage clickDeleteButton() {
		deleteButton.click();
		waitAjax();
		return this;
	}

	/**
	 * Set Value
	 */

	public InboxPage setSubject(String value) {
		super.setValueInField(subject, value);
		return this;
	}

	public InboxPage setDestination(String value) {
		super.setValueInField(destination, value);
		return this;
	}

	/**
	 * Get
	 */

	public String getMessageWasSentText() {
		String maessageWasSentText = null;
		if (isElementPresent(messageWasSentTitle)) {
			maessageWasSentText = messageWasSentTitle.getText();
		}
		return maessageWasSentText;
	}

	public String getInboxEmptyText() {
		String inboxEmptyText = null;
		if (isElementPresent(inboxEmpty)) {
			inboxEmptyText = inboxEmpty.getText();
		}
		return inboxEmptyText;
	}

	public String getSubjectOfMessage() {
		String subjectOfMessageText = null;
		if (isElementPresent(subjectOfMessage)) {
			subjectOfMessageText = subjectOfMessage.getText();
		}
		return subjectOfMessageText;
	}

	// private

	private void cleanFolder() {
		selectHeadCheckbox();
		clickDeleteButton();
	}

	private void selectHeadCheckbox() {
		checkboxView.click();
		waitAjax();
	}

	private WebElement getFirstMessage() {
		return listCheckboxes.get(0);
	}

}
