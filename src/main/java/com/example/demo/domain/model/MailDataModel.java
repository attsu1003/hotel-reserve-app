package com.example.demo.domain.model;

public class MailDataModel {

	/** 送信者 */
	private String fromAddr;

	/** 受信者アドレス */
	private String toAddr;

	/** 件名 */
	private String subject;

	/** 本文 */
	private String body;

	/** 「パスワード設定画面」のURL */
	private String passwordSettingUrl;

	/** パスワード再設定依頼画面URL */
	private String repwdUrl;
	
	

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFromAddr() {
		return fromAddr;
	}

	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}

	public String getToAddr() {
		return toAddr;
	}

	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}

	public String getPasswordSettingUrl() {
		return passwordSettingUrl;
	}

	public void setPasswordSettingUrl(String passwordSettingUrl) {
		this.passwordSettingUrl = passwordSettingUrl;
	}

	public String getRepwdUrl() {
		return repwdUrl;
	}

	public void setRepwdUrl(String repwdUrl) {
		this.repwdUrl = repwdUrl;
	}
}