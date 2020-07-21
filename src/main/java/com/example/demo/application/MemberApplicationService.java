package com.example.demo.application;

import com.example.demo.application.command.CreateMemberCommand;

public interface MemberApplicationService {
	
	public void execute(CreateMemberCommand createMemberCommand);

}
