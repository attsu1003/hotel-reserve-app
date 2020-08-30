package com.example.demo.port;

import java.util.Date;
import java.util.List;

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

	@Insert("INSERT INTO RESERVE VALUES(#{id}, #{plan}, #{checkindate}, #{checkoutdate}, #{numberofguest}, #{totalhotelfee}, #{memberid})")
	void insert(String id, String plan, Date checkindate, Date checkoutdate, int numberofguest, int totalhotelfee,
			String memberid);

	@Select("SELECT * FROM RESERVE WHERE MEMBERID = #{memberId}")
	List<ReserveModel> select(String memberId);

	@Delete("DELETE FROM RESERVE WHERE ID = #{id}")
	void delete(String id);
}