package com.example.demo.application;

import com.example.demo.application.command.DeleteCommand;
import com.example.demo.application.command.ReserveCommand;
import com.example.demo.application.command.UpdateReserveCommand;
import com.example.demo.domain.reserve.DeleteFailedException;
import com.example.demo.domain.reserve.NoVacancyRoomException;
import com.example.demo.domain.reserve.UpdateFailedException;

public interface ReserveApplicationService {

	public void execute(ReserveCommand reserveCommand) throws NoVacancyRoomException;

	public void execute(UpdateReserveCommand updateReserveCommand) throws NoVacancyRoomException, UpdateFailedException;

	public void execute(DeleteCommand deleteCommand) throws DeleteFailedException;
}
