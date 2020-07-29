package com.example.demo.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MailDataModel {

	/** 送信者 */
	private String fromAddr;

	/** 宛先(受信者)ユーザ情報 */
	private Optional<List<DestinationModel>> destInfo = Optional.empty();

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

	public Optional<List<DestinationModel>> getDestInfo() {
		return destInfo;
	}

	public void setDestInfo(Optional<List<DestinationModel>> destInfo) {
		this.destInfo = destInfo;
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

	/**
	 * 宛先(受信者)情報リストに宛先(受信者)情報を追加する.
	 *
	 * @param dest 追加宛先(受信者)情報
	 */
	public void addMultiDestInfo(DestinationModel dest) {
		List<DestinationModel> destInfoList = this.destInfo.orElse(new ArrayList<>());
		destInfoList.add(dest);
		if (!this.destInfo.isPresent())
			this.destInfo = Optional.of(destInfoList);
	}
}