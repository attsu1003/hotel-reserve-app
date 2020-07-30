package com.example.demo.application;

import java.io.IOException;

import com.example.demo.application.command.RequestRePasswordCommand;

public interface RequestRePasswordApplicationService {

	public void execute(RequestRePasswordCommand requestRePasswordCommand) throws IOException;

}