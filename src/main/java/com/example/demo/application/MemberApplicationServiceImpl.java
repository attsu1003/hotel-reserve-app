package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.member.MemberService;
import com.example.demo.domain.model.MemberModel;

@Component
public class MemberApplicationServiceImpl implements MemberApplicationService {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MemberService memberService;

	@Override
	public void execute(CreateMemberCommand createMemberCommand) throws MemberAlreadyExistException {
		// TODO Auto-generated method stub
		if (memberService.isMemberExists(createMemberCommand)) {
			throw new MemberAlreadyExistException("ユーザ名\"" + createMemberCommand.getUsername() + "\"のユーザは既に登録されています。",
					"userId");
		}
		memberRepository.createMember(new MemberModel(createMemberCommand.getUsername(),
				this.hashingPassword(createMemberCommand.getPassword())));
	}

	// パスワードをハッシュ化
	private String hashingPassword(String password) {
		return passwordEncoder.encode(password);
	}
}
