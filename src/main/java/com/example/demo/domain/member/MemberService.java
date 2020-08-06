package com.example.demo.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.MemberModel;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	public boolean isMemberExists(String username) {
		MemberModel memberModel = memberRepository.getMember(username);
		return memberModel != null;
	}

	public boolean isPasswordNotMatch(String password, String confirmPassword) {
		return !password.equals(confirmPassword);
	}
}