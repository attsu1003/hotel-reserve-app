package com.example.demo.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static int diffDate(Date before, Date after) {
		final long oneDay = 24 * 60 * 60 * 1000;
		long diff = (after.getTime() - before.getTime()) / oneDay;
		return (int)diff;
	}

	public static synchronized Date getNextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static synchronized Date getDateFrom(String year, String month, String day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Integer.parseInt(year), Integer.parseInt(day));
		return calendar.getTime();

	}

	public static int countMonthDays(Date date) {
		Calendar c = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(date)),
				Integer.parseInt(new SimpleDateFormat("MM").format(date)) - 1, 1);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}
