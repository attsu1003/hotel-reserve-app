package com.example.demo.application;

import com.example.demo.application.command.ChangePasswordCommand;
import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.application.command.DeleteMemberCommand;
import com.example.demo.application.command.SetPasswordCommand;
import com.example.demo.domain.member.CurrentPasswordException;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.domain.member.MemberIdFormInvalidException;
import com.example.demo.domain.member.MemberNotFoundException;
import com.example.demo.domain.member.PasswordNotMatchException;
import com.example.demo.domain.member.WrongPasswordException;

public interface MemberApplicationService {

	public void execute(CreateMemberCommand createMemberCommand) throws MemberAlreadyExistException, MemberIdFormInvalidException;

	public void execute(DeleteMemberCommand deleteMemberCommand) throws MemberNotFoundException, WrongPasswordException;

	public void execute(SetPasswordCommand setPasswordCommand)
			throws PasswordNotMatchException, CurrentPasswordException, MemberNotFoundException;

	public void execute(ChangePasswordCommand changePasswordCommand)
			throws PasswordNotMatchException, MemberNotFoundException;
}