package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.model.MemberModel;

@Component
public class MemberApplicationServiceImpl implements MemberApplicationService {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void execute(CreateMemberCommand createMemberCommand) {
		// TODO Auto-generated method stub
		System.out.println("through");
		memberRepository.createMember(new MemberModel(createMemberCommand.getMemberId(), "test",
				this.hashingPassword(createMemberCommand.getPassword())));

	}

	// パスワードをハッシュ化
	private String hashingPassword(String password) {
		return passwordEncoder.encode(password);
	}
}
