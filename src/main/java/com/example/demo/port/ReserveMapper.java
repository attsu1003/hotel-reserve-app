package com.example.demo.port;

import java.util.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.model.ReserveModel;

@Mapper
public interface ReserveMapper {

	@Select("SELECT COUNT(ID) FROM RESERVE WHERE CHECKINDATE <= #{checkInDay} AND #{checkOutDay} < CHECKOUTDATE")
	int isReservable(Date checkInDay, Date checkOutDay, int roomNum);
	
	@Select("SELECT COUNT(ID) FROM RESERVE WHERE CHECKINDATE <= #{date} AND CHECKOUTDATE > #{date}")
	int count(Date date);

	@Insert("INSERT INTO RESERVE VALUES(#{id}, #{checkindate}, #{checkoutdate}, #{memberid})")
	void insert(String id, Date checkindate, Date checkoutdate, String memberid);
	
	@Select("SELECT * FROM RESERVE WHERE ID = #{id}")
	ReserveModel select(String id);
	
	@Delete("DELETE FROM RESERVE WHERE ID = #{id}")
	void delete(String id);
}