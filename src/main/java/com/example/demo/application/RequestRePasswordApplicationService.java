package com.example.demo.application;

import com.example.demo.application.command.RequestRePasswordCommand;

public interface RequestRePasswordApplicationService {

	public void execute(RequestRePasswordCommand requestRePasswordCommand);

}