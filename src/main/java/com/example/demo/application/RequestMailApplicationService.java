package com.example.demo.application;

import com.example.demo.application.command.RequestDeleteMemberCommand;
import com.example.demo.domain.member.MemberNotFoundException;

public interface RequestMailApplicationService {

	public void execute(RequestDeleteMemberCommand requestDeleteMemberCommand) throws MemberNotFoundException;

}
