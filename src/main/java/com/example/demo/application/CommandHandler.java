package com.example.demo.application;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.application.command.Command;

public interface CommandHandler {
	public Object handle(Command command)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
