package com.example.demo.common;

import java.util.ResourceBundle;

public class MailProperties {

	private static final String FROM_ADDRESS = "fromaddress";
	private static final String PASSWORD_SETTING_URL = "passwordSettingUrl";
	private static final String DELETE_MEMBER_URL = "deleteMemberUrl";

	private static final String MAIL_PROPERTIES_FILE = "mail";
	private static final String MAIL_TEMPLATE_PATH = "src/main/resources/mailtemplate";
	private static final ResourceBundle resourceBundle;

	static {
		resourceBundle = ResourceBundle.getBundle(MAIL_PROPERTIES_FILE);
	}

	public static String getFromAddr() {
		return resourceBundle.getString(FROM_ADDRESS);
	}

	public static String getPasswordSettingUrl() {
		return resourceBundle.getString(PASSWORD_SETTING_URL);
	}
	
	public static String getDeleteMemberUrl() {
		return resourceBundle.getString(DELETE_MEMBER_URL);
	}

	public static String getMailTemplateResourcePath() {
		return MAIL_TEMPLATE_PATH;
	}
}
