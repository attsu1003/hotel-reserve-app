package com.example.demo.domain.reserve;

public class Reserve {
	private ReserveId reserveId;
	private ReserveCondition reserveCondition;

	public Reserve(ReserveId reserveId, ReserveCondition reserveCondition) {
		super();
		this.reserveId = reserveId;
		this.reserveCondition = reserveCondition;
	}

}
