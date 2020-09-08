package com.example.demo.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

	public static boolean checkRegexp(String value, String regexp) {
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
}
