package com.example.demo.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.RoomModel;
import com.example.demo.port.RoomMapper;

@Repository
public class RoomRepository {

	@Autowired
	RoomMapper roomMapper;

	public int countRoom() {
		return roomMapper.select();
	}

	public RoomModel findRoom(String id) {
		return roomMapper.find(id);
	}
}
