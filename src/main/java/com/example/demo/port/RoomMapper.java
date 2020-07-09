package com.example.demo.port;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.model.RoomModel;

@Mapper
public interface RoomMapper {
	
	@Select("SELECT COUNT(ID) FROM ROOM")
	int select();
	
	@Select("SELECT * FROM ROOM WHERE id = #{id}")
	RoomModel find(String id);
}
