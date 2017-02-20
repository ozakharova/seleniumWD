package ru.mow.domain.tests.loginpagetests;

import static ru.mow.domain.tests.pages.HomePage.ERROR_LABEL_TEXT;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import ru.mow.domain.tests.data.User;
import ru.mow.domain.tests.pages.HomePage;

public class LoginTest extends BaseTest {
	private HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

	private User existingUser = new User("test.user17@mail.ru", "D4g!wh12", "John");

	@Before
	public void setUp() throws Exception {
		homePage.open();
		if (homePage.getUserNameLinkText() == existingUser.name) {
			homePage.logout();
		}
		homePage.moveToUserMenuLink()
				.clickEnterLink();
	}

	@Test
	public void verifyFailAuthentication() {
		String randomUserLogin = Long.toString(System.currentTimeMillis());
		homePage.setLogin(randomUserLogin)
				.setPassword(randomUserLogin)
				.clickSubmitButton();

		Assert.assertEquals(ERROR_LABEL_TEXT, homePage.getErrorText());
		Assert.assertEquals(randomUserLogin, homePage.getLoginValue());
		Assert.assertEquals("", homePage.getPasswordValue());
	}

	@Test
	public void failAuthenticationMoreThanThreeTimes() {
		String randomUserLogin = Long.toString(System.currentTimeMillis());
		for (int i = 1; i <= 4; i++) {
			homePage.setLogin(randomUserLogin)
					.setPassword(Integer.toString(i))
					.clickSubmitButton();
			homePage.waitForJob();
		}

		Assert.assertTrue(homePage.isImageCodeFieldPresent());
		Assert.assertTrue(homePage.isErrorImagePresent());
	}

	@Test
	public void verifySuccessAuthentication() {
		homePage.setLogin(existingUser.login)
				.setPassword(existingUser.password)
				.clickSubmitButton();

		Assert.assertEquals(existingUser.name, homePage.getUserNameLinkText());
		Assert.assertTrue(homePage.isLogoutLinkPresent());
	}
}
