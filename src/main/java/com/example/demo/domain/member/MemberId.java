package com.example.demo.domain.member;

import com.example.demo.common.CommonUtils;
import com.example.demo.web.Regexp;

public class MemberId {

	private String value;

	public MemberId(String value) {
		if (!CommonUtils.checkRegexp(value, Regexp.MATCH_MAILADDRESS)) {
			System.out.println("error");
		}
		this.value = value;
	}

	public String getMemberId() {
		return this.value;
	}
}
