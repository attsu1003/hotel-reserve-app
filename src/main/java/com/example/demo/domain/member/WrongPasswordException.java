package com.example.demo.domain.member;

import com.example.demo.domain.BaseException;

public class WrongPasswordException extends BaseException {

	private static final long serialVersionUID = 1L;

	private String causeAttributeName;

	public WrongPasswordException(String message, String causeAttributeName) {
		super(message);
		this.causeAttributeName = causeAttributeName;
	}

	public WrongPasswordException(String message) {
		super(message);
	}

	public String getCauseAttributeName() {
		return causeAttributeName;
	}

	public void setCauseAttributeName(String causeAttributeName) {
		this.causeAttributeName = causeAttributeName;
	}

}
