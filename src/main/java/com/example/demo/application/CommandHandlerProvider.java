package com.example.demo.application;

import com.example.demo.application.command.Command;

@SuppressWarnings("rawtypes")
public interface CommandHandlerProvider<D,S> extends Provider<Command, CommandHandlerProvider> {
	CommandHandler get(S source);
}
