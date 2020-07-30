package com.example.demo.application;

import java.io.IOException;

import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.application.command.RequestRePasswordCommand;
import com.example.demo.domain.member.MemberAlreadyExistException;
import com.example.demo.domain.member.MemberNotFoundException;

public interface MemberApplicationService {
	
	public void execute(CreateMemberCommand createMemberCommand) throws MemberAlreadyExistException;
	
	public void execute(RequestRePasswordCommand requestRePasswordCommand) throws MemberNotFoundException, IOException;

}
