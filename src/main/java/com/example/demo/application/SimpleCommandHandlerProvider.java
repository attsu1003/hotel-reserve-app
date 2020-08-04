package com.example.demo.application;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.example.demo.application.command.Command;

@Component
public class SimpleCommandHandlerProvider<D, S> implements CommandHandlerProvider<D, S> {

	private ReserveApplicationService reserveApplicationService;
	private MemberApplicationService memberApplicationService;
	private RequestMailApplicationService requestMailApplicationService;

	public SimpleCommandHandlerProvider(ReserveApplicationService reserveApplicationService,
			MemberApplicationService memberApplicationService,
			RequestMailApplicationService requestMailApplicationService) {
		super();
		this.reserveApplicationService = reserveApplicationService;
		this.memberApplicationService = memberApplicationService;
		this.requestMailApplicationService = requestMailApplicationService;
	}

	@SuppressWarnings("rawtypes")
	private final Map<Class, CommandHandler> map = new HashMap<>();

	@PostConstruct
	public void postConstruct() {
		List<Object> serviceBeans = new ArrayList<Object>() {
			{
				add(reserveApplicationService);
				add(memberApplicationService);
				add(requestMailApplicationService);
			}
		};
		for (Object serviceBean : serviceBeans) {
			this.register(serviceBean);
		}
	}

	private void register(Object serviceBean) {
		for (Method method : serviceBean.getClass().getDeclaredMethods()) {
			Class<?> commandType = method.getParameterTypes()[0];
			@SuppressWarnings("unchecked")
			CommandHandler commandHandler = new MethodInvocationCommandHandler((Class<? extends Command>) commandType,
					method, serviceBean);
			this.register(commandType, commandHandler);
		}
	}

	private void register(@SuppressWarnings("rawtypes") Class clazz, CommandHandler commandHandler) {
		map.put(clazz, commandHandler);
	}

	@Override
	public CommandHandler get(S source) {
		return this.gets(source);
	}

	private CommandHandler gets(S source) {
		return this.map.get(source.getClass());
	}

}
