package com.example.demo.application;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.application.command.Command;

@Component
public class SimpleCommandHandlerProvider<D, S> implements CommandHandlerProvider<D, S> {
	
	private ReserveApplicationService reserveApplicationService;
	
	public SimpleCommandHandlerProvider(ReserveApplicationService reserveApplicationService) {
		super();
		this.reserveApplicationService = reserveApplicationService;
	}

	@SuppressWarnings("rawtypes")
	private final Map<Class, CommandHandler> map = new HashMap<>();
//	private List<Object> serviceBeans = new ArrayList<Object>() {
//		{
//			add(reserveApplicationService);
//		}
//	};

	@PostConstruct
	public void postConstruct() {
		List<Object> serviceBeans = new ArrayList<Object>() {
			{
			add(reserveApplicationService);
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
