package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import com.example.demo.domain.HotelReserveMessages;

public abstract class AbstractController {

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	private ApplicationContext applicationContext;

	protected void addMessage(String paramString, Object... paramArrayObject) {
		HotelReserveMessages hotelReserveMessages = getMessage();
		hotelReserveMessages.addMessage(paramString, messageSource.getMessage(paramString, paramArrayObject, null));

	}

	protected void addErrorMessage(String paramString, Object... paramArrayObject) {
		HotelReserveMessages hotelReserveMessages = getMessage();
		hotelReserveMessages.addErrorMessage(paramString,
				messageSource.getMessage(paramString, paramArrayObject, null));
	}

	protected HotelReserveMessages getMessage() {
		return applicationContext.getBean(HotelReserveMessages.class);
	}
}
