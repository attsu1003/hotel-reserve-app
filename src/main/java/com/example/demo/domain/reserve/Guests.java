package com.example.demo.domain.reserve;

import java.util.ArrayList;
import java.util.List;

public class Guests {

	List<Integer> guests;

	public static final int MAXIMUM_APPROVE_GUEST_NUMBER = 4;

	public static List<Integer> getMaxNumberOfGuestList() {
		List<Integer> numberOfGuestList = new ArrayList<>();
		for (int i = 1; i <= MAXIMUM_APPROVE_GUEST_NUMBER; i++) {
			numberOfGuestList.add(i);
		}
		return numberOfGuestList;
	}
}
