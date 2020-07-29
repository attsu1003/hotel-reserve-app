package com.example.demo.common;

/**
 * メール送信時のログ出力用定数.
 * 
 */
public interface MailFunctionBase {

	/**
	 * RESULT_ERR_CNT
	 */
	String RESULT_ERR_CNT = "err_count";

	/**
	 * RESULT_ERR_INFO
	 */
	String RESULT_ERR_INFO = "err_info";

	/**
	 * MAIL_SEPERATOR
	 */
	String MAIL_SEPERATOR = "%------------------------------------------------------------%".replaceAll("%", System.lineSeparator());

	/**
	 * MAIL_EXCEPTION_MSG
	 */
	String MAIL_EXCEPTION_MSG = "[ExceptionMessage]:";

	/**
	 * MAIL_FROM
	 */
	String MAIL_FROM = "[From]:";

	/**
	 * MAIL_TO
	 */
	String MAIL_TO = "[To]:";

	/**
	 * MAIL_SUBJECT
	 */
	String MAIL_SUBJECT = "[Subject]:";

	/**
	 * MAIL_BODY
	 */
	String MAIL_BODY = "[Body]:";

	/**
	 * MAIL_RESULT_CNT
	 */
	String MAIL_RESULT_CNT = "[Total count / Error count]:";

	/**
	 * LINE_SEPAREATOR
	 */
	String LINE_SEPAREATOR = System.lineSeparator();

	/**
	 * MAIL_FROM_ADDRESS
	 */
	String MAIL_FROM_ADDRESS = "fromaddress";

	/**
	 * MAIL_FROM_SEMINAR_ADRESS
	 */
	String MAIL_FROM_SEMINAR_ADDRESS = "fromseminaraddress";

	/**
	 * MAIL_TO_ADDRESS
	 */
	String MAIL_TO_ADDRESS = "toaddress";

	/**
	 *  MAIL_TO_ADDRESS_FOR_REQUEST_RESEND
	 */
	String MAIL_TO_ADDRESS_FOR_REQUEST_RESEND = "toAddressForRequestResend";

	/**
	 * MAIL_PASSWORD_SETTEING_URL
	 */
	String MAIL_PASSWORD_SETTEING_URL = "passwordSettingUrl";

	/**
	 * CUSTOMER_MAIL_PASSWORD_SETTEING_URL
	 */
	String CUSTOMER_MAIL_PASSWORD_SETTEING_URL = "customerPasswordSettingUrl";
	
	/**
	 * RE_PASSWORD_REQUEST_URL
	 */
	String RE_PASSWORD_REQUEST_URL = "rePasswordRequestUrl";

	/**
	 * CUSTOMER_RE_PASSWORD_REQUEST_URL
	 */
	String CUSTOMER_RE_PASSWORD_REQUEST_URL = "customerRePasswordRequestUrl";

	/**
	 * EXTERNAL_CUSTOMER_INFO_URL
	 */
	String EXTERNAL_CUSTOMER_INFO_URL = "externalCustomerInfoUrl";

	/**
	 * PERSONAL_INFO_CONF_DEST_ADDRESS
	 */
	String PERSONAL_INFO_CONF_DEST_ADDRESS = "personalInfoConfDestAddress";
	
	/**
	 * MAIL_IMAP_HOST
	 */
	String MAIL_IMAP_HOST = "mail.imap.host";
	
	/**
	 * MAIL_IMAP_PORT
	 */
	String MAIL_IMAP_PORT = "mail.imap.port";
	
	/**
	 * MAIL_IMAP_USERID
	 */
	String MAIL_IMAP_USERID = "mail.imap.userid";
	
	/**
	 * MAIL_IMAP_PASSWORD
	 */
	String MAIL_IMAP_PASSWORD = "mail.imap.password";
	
	/**
	 * MAIL_DELETE
	 */
	String MAIL_DELETE = "mail.delete";
	
	/**
	 * MAIL_LATEST
	 */
	String MAIL_LATEST = "mail.latest";
	
	/**
	 * BP_PERSON_CONCERNED_ADDRESS
	 */
	String BP_PERSON_CONCERNED_ADDRESS = "bpPersonConcernedAddress";
	
	/**
	 * TEMPLATEインターフェース
	 * 
	 * @author yohei.hasumi
	 *
	 */
	interface TEMPLATE {
		String RESOURCE_PATH = "mail.path.template";
	}

	/**
	 * ERRORインターフェース
	 * 
	 *
	 */
	interface ERROR {
		String OUTPUT_PATH = "mail.error.path.output";
	}

	/**
	 * DEBUGインターフェース
	 * 
	 * @author yohei.hasumi
	 *
	 */
	interface DEBUG {
		String MODE = "mode";
		String Debug = "debug";
		String Normal = "normal";
	}

	/**
	 * SMTPインターフェース
	 * 
	 * @author yohei.hasumi
	 *
	 */
	interface SMTP {
		String DEFUALT_ENCODE = "encoding";
		String HOST = "host";
		String PORT = "port";
		String USER_NAME = "userName";
		String PASSWORD = "password";
		String JAVA_MAIL_PROP = "javaMailProperties";
	}
}