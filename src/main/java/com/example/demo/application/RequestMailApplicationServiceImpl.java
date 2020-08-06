package com.example.demo.application;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.application.command.RequestCreateMemberCommand;
import com.example.demo.application.command.RequestDeleteMemberCommand;
import com.example.demo.application.command.RequestRePasswordCommand;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.member.MemberService;
import com.example.demo.port.RequestCreateMemberMailService;
import com.example.demo.port.RequestDeleteMemberMailService;
import com.example.demo.port.RequestRePasswordMailService;

@Component
public class RequestMailApplicationServiceImpl implements RequestMailApplicationService {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberService memberService;

	@Autowired
	RequestCreateMemberMailService requestCreateMemberMailService;

	@Autowired
	RequestRePasswordMailService requestRePasswordMailService;

	@Autowired
	RequestDeleteMemberMailService requestDeleteMemberMailService;

	@Override
	public void execute(RequestCreateMemberCommand requestCreateMemberCommand) throws MemberAlreadyExistException {
		if (isMemberExists(requestCreateMemberCommand.getMailAddress())) {
			throw new MemberAlreadyExistException(
					"メールアドレス\"" + requestCreateMemberCommand.getMailAddress() + "既に登録されています。");
		}
		requestCreateMemberMailService.requestCreateMemberMail(requestCreateMemberCommand.getMailAddress());
	}

	@Override
	public void execute(RequestRePasswordCommand requestRePasswordCommand) throws MemberNotFoundException, IOException {
		if (isMemberNotExists(requestRePasswordCommand.getMailAddress())) {
			throw new MemberNotFoundException(
					"ユーザ名\"" + requestRePasswordCommand.getMailAddress() + "\"のユーザは登録されていません。", "userId");
		}
		requestRePasswordMailService.requestRePasswordMail(requestRePasswordCommand.getMailAddress());
	}

	@Override
	public void execute(RequestDeleteMemberCommand requestDeleteMemberCommand) throws MemberNotFoundException {
		if (isMemberNotExists(requestDeleteMemberCommand.getMailAddress())) {
			throw new MemberNotFoundException(
					"ユーザ名\"" + requestDeleteMemberCommand.getMailAddress() + "\"のユーザは登録されていません。", "userId");
		}
		requestDeleteMemberMailService.requestDeleteMemberMail(requestDeleteMemberCommand.getMailAddress());
	}

	private boolean isMemberExists(String password) {
		return memberService.isMemberExists(password);
	}

	private boolean isMemberNotExists(String password) {
		return !memberService.isMemberExists(password);
	}

}
