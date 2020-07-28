package com.example.demo.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.domain.model.MemberModel;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	public boolean isMemberExists(CreateMemberCommand createMemberCommand) {
		MemberModel memberModel = memberRepository.getMember(createMemberCommand.getUsername());
		return memberModel != null;
	}
}
