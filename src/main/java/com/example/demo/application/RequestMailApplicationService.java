package com.example.demo.application;

import java.io.IOException;

import com.example.demo.application.command.RequestContactCommand;
import com.example.demo.application.command.RequestCreateMemberCommand;
import com.example.demo.application.command.RequestDeleteMemberCommand;
import com.example.demo.application.command.RequestRePasswordCommand;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.domain.member.MemberNotFoundException;

public interface RequestMailApplicationService {

	public void execute(RequestCreateMemberCommand requestCreateMemberCommand) throws MemberAlreadyExistException;

	public void execute(RequestRePasswordCommand requestRePasswordCommand) throws MemberNotFoundException, IOException;

	public void execute(RequestDeleteMemberCommand requestDeleteMemberCommand) throws MemberNotFoundException;

	void execute(RequestContactCommand requestContactCommand);
}
