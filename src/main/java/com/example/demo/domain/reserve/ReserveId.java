package com.example.demo.domain.reserve;

import java.util.UUID;

public class ReserveId {

	private String reserveId;

	public String getReserveId() {
		return reserveId;
	}

	public ReserveId() {
		String randomeUUID = UUID.randomUUID().toString().replaceAll("-", "");
		this.reserveId = randomeUUID.replaceAll("-", "");
	}

	public ReserveId(String reserveId) {
		this.reserveId = reserveId;
	}
}
