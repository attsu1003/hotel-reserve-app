package com.example.demo.domain.member;

public class Member {
	MemberId memberId;
	Password password;

	public Member(MemberId memberId, Password password) {
		this.memberId = memberId;
		this.password = password;
	}

	public String getMemberId() {
		return memberId.getMemberId();
	}

	public String getPassword() {
		return password.getPassword();
	}
}
