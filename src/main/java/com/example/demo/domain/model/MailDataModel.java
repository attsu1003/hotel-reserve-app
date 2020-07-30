package com.example.demo.domain.model;

public class MailDataModel {

	/** 送信者 */
	private String fromAddr;
	
	/** 受信者アドレス */
	private String toAddr;

	/** 「パスワード設定画面」のURL */
	private String passwordSettingUrl;

	/** パスワード再設定依頼画面URL */
	private String repwdUrl;

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