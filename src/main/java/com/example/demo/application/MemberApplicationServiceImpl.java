package com.example.demo.application;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.application.command.RequestRePasswordCommand;
import com.example.demo.application.command.SetPasswordCommand;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.member.MemberService;
import com.example.demo.domain.member.PasswordNotMatchException;
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
		if (isMemberExists(createMemberCommand.getUsername())) {
			throw new MemberAlreadyExistException("ユーザ名\"" + createMemberCommand.getUsername() + "\"のユーザは既に登録されています。",
					"userId");
		}
		memberRepository.createMember(new MemberModel(createMemberCommand.getUsername(),
				this.hashingPassword(createMemberCommand.getPassword())));
	}

	@Override
	public void execute(RequestRePasswordCommand requestRePasswordCommand) throws MemberNotFoundException, IOException {
		if (isMemberNotExists(requestRePasswordCommand.getMailAddress())) {
			throw new MemberNotFoundException(
					"ユーザ名\"" + requestRePasswordCommand.getMailAddress() + "\"のユーザは登録されていません。", "userId");
		}
		MemberModel memberModel = memberRepository.getMember(requestRePasswordCommand.getMailAddress());
		memberService.updatePassword(memberModel);
	}

	@Override
	public void execute(SetPasswordCommand setPasswordCommand)
			throws PasswordNotMatchException, MemberNotFoundException {
		// 入力したパスワードと確認用に入力したパスワードが一致しない場合
		if (isPasswordNotMatch(setPasswordCommand.getPassword(), setPasswordCommand.getConfirmPassword())) {
			throw new PasswordNotMatchException("入力したパスワードとパスワード(確認用)が一致しません。", "password");
		}
		if (isMemberNotExists(setPasswordCommand.getMailAddress())) {
			throw new MemberNotFoundException("ユーザ情報が見つかりません。恐れ入りますがパスワード再設定依頼を実施してください。");
		}
		memberRepository.updatePassword(this.hashingPassword(setPasswordCommand.getPassword()),
				setPasswordCommand.getMailAddress());
	}

	private boolean isMemberExists(String username) {
		return memberService.isMemberExists(username);
	}

	private boolean isMemberNotExists(String password) {
		return !memberService.isMemberExists(password);
	}

	private boolean isPasswordNotMatch(String password, String confirmPassword) {
		return memberService.isPasswordNotMatch(password, confirmPassword);
	}

	// パスワードをハッシュ化
	private String hashingPassword(String password) {
		return passwordEncoder.encode(password);
	}
}