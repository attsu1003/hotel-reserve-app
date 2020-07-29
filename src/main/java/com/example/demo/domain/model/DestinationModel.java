package com.example.demo.domain.model;

import java.io.Serializable;

public class DestinationModel implements Serializable {

	/** シリアルNo */
	private static final long serialVersionUID = 8325353594077313264L;

	/** 受信者アドレス */
	private String toAddr;

	/** 受信者名 */
	private String toName;

	/** 受信者(利用者)会社名 */
	private String companyName;

	/**
	 * 受信者アドレスを取得する.
	 *
	 * @return 受信者アドレス
	 */
	public String getToAddr() {
		return toAddr;
	}

	/**
	 * 受信者アドレスを設定する.
	 *
	 * @param toAddr 受信者アドレス
	 */
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}

	/**
	 * 受信者氏名を取得する.
	 *
	 * @return 受信者氏名
	 */
	public String getToName() {
		return toName;
	}

	/**
	 * 受信者氏名を設定する.
	 *
	 * @param toName 受信者氏名
	 */
	public void setToName(String toName) {
		this.toName = toName;
	}

	/**
	 * 受信者(利用者)会社名を取得する.
	 *
	 * @return 受信者(利用者)会社名
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 受信者(利用者)会社名を設定する.
	 *
	 * @param companyName 受信者(利用者)会社名
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}