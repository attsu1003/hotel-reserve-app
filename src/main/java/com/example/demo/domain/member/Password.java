package com.example.demo.domain.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.common.CommonUtils;
import com.example.demo.web.Regexp;

public class Password {

	PasswordEncoder passwordEncoder;
	String value;

	public Password(String value, PasswordEncoder passwordEncoder) {
		if (!CommonUtils.checkRegexp(value, Regexp.MATCH_PASSWORD)) {
			System.out.println("error");
		}
		this.value = passwordEncoder.encode(value);
	}

	public String getPassword() {
		return this.value;
	}
}
