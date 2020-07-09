package com.example.demo.application;

import com.example.demo.application.command.DeleteCommand;
import com.example.demo.application.command.ReferCommand;
import com.example.demo.application.command.ReserveCommand;

public interface ReserveApplicationService {
	public void execute(ReserveCommand reserveCommand);

	public void execute(DeleteCommand deleteCommand);
}
