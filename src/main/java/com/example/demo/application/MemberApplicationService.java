package com.example.demo.application;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.application.command.ChangePasswordCommand;
import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.application.command.DeleteMemberCommand;
import com.example.demo.application.command.SetPasswordCommand;
import com.example.demo.domain.member.CurrentPasswordException;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.PasswordNotMatchException;
import com.example.demo.domain.member.WrongPasswordException;

public interface MemberApplicationService {

	@Transactional
	public void execute(CreateMemberCommand createMemberCommand) throws MemberAlreadyExistException;

	@Transactional
	public void execute(DeleteMemberCommand deleteMemberCommand) throws MemberNotFoundException, WrongPasswordException;

	@Transactional
	public void execute(SetPasswordCommand setPasswordCommand)
			throws PasswordNotMatchException, CurrentPasswordException, MemberNotFoundException;

	@Transactional
	public void execute(ChangePasswordCommand changePasswordCommand)
			throws PasswordNotMatchException, MemberNotFoundException;
}