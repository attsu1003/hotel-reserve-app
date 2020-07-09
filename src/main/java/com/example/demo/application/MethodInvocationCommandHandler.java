package com.example.demo.application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.example.demo.application.command.Command;

public class MethodInvocationCommandHandler implements CommandHandler {

	private Class<? extends Command> commandType;
	private Method method;
	private Object object;

	public MethodInvocationCommandHandler(Class<? extends Command> commandType, Method method, Object object) {
		super();
		this.commandType = commandType;
		this.method = method;
		this.object = object;
	}

	@Override
	public Object handle(Command command) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return method.invoke(object, command);
	}

}
