package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.application.command.ChangePasswordCommand;
import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.application.command.DeleteMemberCommand;
import com.example.demo.application.command.SetPasswordCommand;
import com.example.demo.domain.member.CurrentPasswordException;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.member.MemberService;
import com.example.demo.domain.member.PasswordNotMatchException;
import com.example.demo.domain.member.WrongPasswordException;
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
		if (isMemberExists(createMemberCommand.getId())) {
			throw new MemberAlreadyExistException("ユーザ名\"" + createMemberCommand.getId() + "\"のユーザは既に登録されています。",
					"userId");
		}
		memberRepository.createMember(
				new MemberModel(createMemberCommand.getId(), this.hashingPassword(createMemberCommand.getPassword())));
	}

	@Override
	public void execute(DeleteMemberCommand deleteMemberCommand)
			throws MemberNotFoundException, WrongPasswordException {
		// ユーザ情報が見つからない場合
		if (isMemberNotExists(deleteMemberCommand.getId())) {
			throw new MemberNotFoundException("ユーザ情報が見つかりません。恐れ入りますがパスワード再設定依頼を実施してください。");
		}
		// 入力したパスワードが間違っている場合
		if (isMemberNotExists(new MemberModel(deleteMemberCommand.getId(), deleteMemberCommand.getPassword()))) {
			throw new WrongPasswordException("パスワードの入力が誤っています。");
		}
		memberRepository.deleteMember(
				new MemberModel(deleteMemberCommand.getId(), this.hashingPassword(deleteMemberCommand.getPassword())));
	}

	@Override
	public void execute(SetPasswordCommand setPasswordCommand)
			throws PasswordNotMatchException, CurrentPasswordException, MemberNotFoundException {
		// 入力したパスワードと確認用に入力したパスワードが一致しない場合
		if (isPasswordNotMatch(setPasswordCommand.getPassword(), setPasswordCommand.getConfirmPassword())) {
			throw new PasswordNotMatchException("入力したパスワードとパスワード(確認用)が一致しません。", "password");
		}
		// 入力したパスワードが既にそのユーザのパスワードに一致する場合
		if (isMemberExists(new MemberModel(setPasswordCommand.getMailAddress(), setPasswordCommand.getPassword()))) {
			throw new CurrentPasswordException("入力したパスワードは既に使用されています。異なるパスワードを入力してください。");
		}
		// ユーザ情報が読み込めない場合
		if (isMemberNotExists(setPasswordCommand.getMailAddress())) {
			throw new MemberNotFoundException("ユーザ情報が見つかりません。恐れ入りますがパスワード再設定依頼を実施してください。");
		}
		memberRepository.changePassword(this.hashingPassword(setPasswordCommand.getPassword()),
				setPasswordCommand.getMailAddress());
	}

	@Override
	public void execute(ChangePasswordCommand changePasswordCommand)
			throws PasswordNotMatchException, MemberNotFoundException {
		// 入力した新パスワードと確認用に入力した新パスワードが一致しない場合
		if (isPasswordNotMatch(changePasswordCommand.getNewPassword(), changePasswordCommand.getNewConfirmPassword())) {
			throw new PasswordNotMatchException("入力した新パスワードと新パスワード(確認用)が一致しません。", "password");
		}
		// 入力した現在のパスワードが間違っている場合
		if (isMemberNotExists(
				new MemberModel(changePasswordCommand.getMailAddress(), changePasswordCommand.getPassword()))) {
			throw new MemberNotFoundException("現在のパスワードの入力が誤っています。");
		}
		memberRepository.changePassword(this.hashingPassword(changePasswordCommand.getNewPassword()),
				changePasswordCommand.getMailAddress());
	}

	private boolean isMemberExists(String id) {
		return memberService.isMemberExists(id);
	}

	private boolean isMemberExists(MemberModel memberModel) {
		return passwordEncoder.matches(memberModel.getPassword(),
				memberRepository.getMember(memberModel.getUsername()).getPassword());
	}

	private boolean isMemberNotExists(MemberModel memberModel) {
		return !passwordEncoder.matches(memberModel.getPassword(),
				memberRepository.getMember(memberModel.getUsername()).getPassword());
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