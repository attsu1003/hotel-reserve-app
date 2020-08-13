package com.example.demo.application;

import java.lang.reflect.InvocationTargetException;

import org.springframework.stereotype.Component;

import com.example.demo.application.command.Command;

@Component
public class RunEnvironment {

	private CommandHandlerProvider commandHandlerProvider;

	public RunEnvironment(CommandHandlerProvider commandHandlerProvider) {
		super();
		this.commandHandlerProvider = commandHandlerProvider;
	}

	public Object runHandler(Command command) throws Exception {
		CommandHandler commandHandler = this.commandHandlerProvider.get(command);
		return commandHandler.handle(command);
	}

}
