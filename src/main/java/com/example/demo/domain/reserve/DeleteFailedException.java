package com.example.demo.domain.reserve;

public class DeleteFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	private String causeAttributeName;

	public DeleteFailedException(String message, String causeAttributeName) {
		super(message);
		this.causeAttributeName = causeAttributeName;
	}

	public DeleteFailedException(String message) {
		super(message);
	}

	public String getCauseAttributeName() {
		return causeAttributeName;
	}

	public void setCauseAttributeName(String causeAttributeName) {
		this.causeAttributeName = causeAttributeName;
	}
}
