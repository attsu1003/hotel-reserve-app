package com.example.demo.application;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.application.command.DeleteCommand;
import com.example.demo.application.command.ReserveCommand;
import com.example.demo.application.command.UpdateReserveCommand;
import com.example.demo.domain.reserve.DeleteFailedException;
import com.example.demo.domain.reserve.NoVacancyRoomException;
import com.example.demo.domain.reserve.UpdateFailedException;

public interface ReserveApplicationService {
	
	@Transactional
	public void execute(ReserveCommand reserveCommand) throws NoVacancyRoomException;
	
	@Transactional
	public void execute(UpdateReserveCommand updateReserveCommand) throws NoVacancyRoomException, UpdateFailedException;
	
	@Transactional
	public void execute(DeleteCommand deleteCommand) throws DeleteFailedException;
}
