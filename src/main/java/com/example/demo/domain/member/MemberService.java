package com.example.demo.domain.member;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.MailController;
import com.example.demo.domain.model.MemberModel;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MailController mailController;

	public boolean isMemberExists(String username) {
		MemberModel memberModel = memberRepository.getMember(username);
		return memberModel != null;
	}

	public boolean isPasswordNotMatch(String password, String confirmPassword) {
		return !password.equals(confirmPassword);
	}

	public void updatePassword(MemberModel memberModel) {
		try {
			mailController.sendMail(memberModel.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}