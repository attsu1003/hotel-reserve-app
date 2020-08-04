package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.application.command.RequestDeleteMemberCommand;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.member.MemberService;
import com.example.demo.domain.model.MemberModel;
import com.example.demo.port.RequestDeleteMemberMailService;

@Component
public class RequestMailApplicationServiceImpl implements RequestMailApplicationService {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberService memberService;

	@Autowired
	RequestDeleteMemberMailService requestDeleteMemberMailService;

	@Override
	public void execute(RequestDeleteMemberCommand requestDeleteMemberCommand) throws MemberNotFoundException {
		if (isMemberNotExists(requestDeleteMemberCommand.getMailAddress())) {
			throw new MemberNotFoundException(
					"ユーザ名\"" + requestDeleteMemberCommand.getMailAddress() + "\"のユーザは登録されていません。", "userId");
		}
		MemberModel memberModel = memberRepository.getMember(requestDeleteMemberCommand.getMailAddress());
		requestDeleteMemberMailService.requestDeleteMemberMail(requestDeleteMemberCommand.getMailAddress());
	}

	private boolean isMemberNotExists(String password) {
		return !memberService.isMemberExists(password);
	}

}
