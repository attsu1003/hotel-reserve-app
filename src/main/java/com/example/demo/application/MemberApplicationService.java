package com.example.demo.application;

import com.example.demo.application.command.CreateMemberCommand;
import com.example.demo.domain.member.MemberAlreadyExistException;

public interface MemberApplicationService {
	
	public void execute(CreateMemberCommand createMemberCommand) throws MemberAlreadyExistException;

}
