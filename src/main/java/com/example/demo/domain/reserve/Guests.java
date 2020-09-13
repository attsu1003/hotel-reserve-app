package com.example.demo.domain.reserve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Guests {

	List<Guest> guests=new ArrayList<Guest>();

	public static final int MAXIMUM_APPROVE_GUEST_NUMBER = 4;

	public static List<Integer> getMaxNumberOfGuestList() {
		List<Integer> numberOfGuestList = new ArrayList<>();
		for (int i = 1; i <= MAXIMUM_APPROVE_GUEST_NUMBER; i++) {
			numberOfGuestList.add(i);
		}
		return numberOfGuestList;
	}

	public void add(Guest guest) {
		if (guests.size() < MAXIMUM_APPROVE_GUEST_NUMBER) {
			guests.add(guest);
		}
	}

	public List<Guest> getGuestList() {
		return Collections.unmodifiableList(guests);
	}
}
