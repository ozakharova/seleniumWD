package ru.mow.domain.tests.yandexmailtests;

import static ru.mow.domain.tests.pages.InboxPage.MESSAGE_WAS_SENT_TEXT;
import static ru.mow.domain.tests.pages.InboxPage.NO_INBOX_MESSAGES_TEXT;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ru.mow.domain.tests.data.Message;
import ru.mow.domain.tests.data.User;
import ru.mow.domain.tests.pages.HomePage;
import ru.mow.domain.tests.pages.InboxPage;

public class InboxMailTest extends BaseTest {
	public InboxPage inboxPage;
	public HomePage homePage;

	private User user = new User("test.user.17@yandex.ru", "D4g!wh12");
	private Message message = new Message("text", user.login);

	@Before
	public void setUp() throws Exception {
		homePage = HomePage.loadUsing(getWebDriver());
		inboxPage = homePage.loginAsUser(user.login, user.password);
		inboxPage.cleanInboxFolder();
		inboxPage.cleanTrashFolder();
	}

	@Test
	public void verifySendingMessage() {
		inboxPage.clickWriteButton()
				.setSubject(message.subject)
				.setDestination(message.email)
				.clickSendButton();

		Assert.assertEquals("The message was not sent", MESSAGE_WAS_SENT_TEXT,
				inboxPage.getMessageWasSentText());

		inboxPage.openInboxFolder();

		Assert.assertEquals("The message was not sent", message.subject,
				inboxPage.getSubjectOfMessage());
	}

	@Test
	public void verifyDeletingMessage() {
		inboxPage.createMail(message.subject, message.email);
		inboxPage.selectFirstMessage()
				.clickDeleteButton();

		Assert.assertEquals("The message was not deleted", NO_INBOX_MESSAGES_TEXT,
				inboxPage.getInboxEmptyText());

		inboxPage.openTrashFolder();

		Assert.assertEquals("The message was not deleted", message.subject,
				inboxPage.getSubjectOfMessage());

	}

	@Override
	@After
	public void tearDown() {
		inboxPage.cleanInboxFolder();
		inboxPage.cleanTrashFolder();
		super.tearDown();
	}

}
