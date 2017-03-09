package ru.mow.domain.tests;

import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

import ru.mow.domain.classes.Calendar;

public class CalendarTest {

	@Test
	public void isDateOfGregorianCalendar() {
		Calendar date = new Calendar(2016, 1, 29);

		Assert.assertTrue("Некорректная дата григорианского календаря.", isDateValid(date));
	}

	private boolean isDateValid(Calendar date) {
		try {
			GregorianCalendar calendar = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDay());
			calendar.setLenient(false);
			calendar.getTime();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
