package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.application.command.Command;
import com.example.demo.domain.reserve.ReserveRepository;

@Component
public class ApplicationCommandBus {

	@Autowired
	private RunEnvironment runEnvironment;
	@Autowired
	ReserveRepository reserveRepository;

	public void dispatch(Command command)
			throws Exception {
		runEnvironment.runHandler(command);
	}

}
