package com.example.demo.domain.member;

import com.example.demo.common.CommonUtils;
import com.example.demo.web.Regexp;

public class MemberId {

	private String value;

	public MemberId(String value) throws MemberIdFormInvalidException {
		if (!CommonUtils.checkRegexp(value, Regexp.MATCH_MAILADDRESS)) {
			throw new MemberIdFormInvalidException("メンバーIDがメールアドレス形式となっていません。");
		}
		this.value = value;
		
	}

	public String getMemberId() {
		return this.value;
	}
}
