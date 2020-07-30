package com.example.demo.domain.member;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.MailController;
import com.example.demo.common.MailProperties;
import com.example.demo.domain.model.MailDataModel;
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
	
	public boolean isMemberNotExists(String username) {
		MemberModel memberModel = memberRepository.getMember(username);
		return memberModel == null;
	}
	
	public void updatePassword(MemberModel memberModel) {
		try {
			mailController.preparingSendMail(memberModel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
