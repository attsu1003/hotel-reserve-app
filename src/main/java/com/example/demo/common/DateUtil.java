package com.example.demo.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	private static Calendar calendar = Calendar.getInstance();

	public static Date date(String day) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(day);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static long diffDate(Date before, Date after) {
		final long oneDay = 24 * 60 * 60 * 1000;
		long diff = (after.getTime() - before.getTime()) / oneDay;
		return diff;
	}

	public static synchronized Date getNextDate(Date date) {
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static synchronized Date getAddMonth(Date date, int amount) {
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amount);
		return calendar.getTime();
	}

	public static synchronized Date getDate(int year, int month, int day) {
		
		calendar.set(year, month - 1, day);
		return calendar.getTime();
	}
	
	public static synchronized Date getDate2(String year, String month, String day) {
		calendar.set(Integer.parseInt(year), Integer.parseInt(year), Integer.parseInt(day));
		return calendar.getTime();
		
	}

	public static int countMonthDays(Date date) {
		Calendar c = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(date)),
				Integer.parseInt(new SimpleDateFormat("MM").format(date)), 1);

		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static Date firstDate(Date date) {
		return new Date(Integer.parseInt(new SimpleDateFormat("yyyy").format(date)),
				Integer.parseInt(new SimpleDateFormat("MM").format(date)), 1);
	}

	public static Date lastDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return new Date(Integer.parseInt(new SimpleDateFormat("yyyy").format(date)),
				Integer.parseInt(new SimpleDateFormat("MM").format(date)), calendar.getActualMaximum(Calendar.DATE));

	}
}
