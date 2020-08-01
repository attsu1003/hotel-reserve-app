package com.example.demo.domain.member;

import com.example.demo.domain.BaseException;

public class PasswordNotMatchException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String causeAttributeName;

	public PasswordNotMatchException(String message, String causeAttributeName) {
		super(message);
		this.causeAttributeName = causeAttributeName;
	}

	public String getCauseAttributeName() {
		return causeAttributeName;
	}

	public void setCauseAttributeName(String causeAttributeName) {
		this.causeAttributeName = causeAttributeName;
	}
}