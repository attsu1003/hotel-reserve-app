package com.example.demo.application;

import com.example.demo.application.command.DeleteCommand;
import com.example.demo.application.command.ReserveCommand;
import com.example.demo.domain.reserve.NoVacancyRoomException;

public interface ReserveApplicationService {
	public void execute(ReserveCommand reserveCommand) throws NoVacancyRoomException;

	public void execute(DeleteCommand deleteCommand);
}
