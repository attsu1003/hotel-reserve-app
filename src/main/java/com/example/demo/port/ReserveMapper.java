package com.example.demo.port;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.domain.model.ReserveModel;
import com.example.demo.domain.reserve.Reserve;

@Mapper
public interface ReserveMapper {

	@Select("SELECT COUNT(ID) FROM RESERVE WHERE CHECKINDATE <= #{checkInDay} AND #{checkOutDay} < CHECKOUTDATE")
	int isReservable(Date checkInDay, Date checkOutDay, int roomNum);

	@Select("SELECT COUNT(ID) FROM RESERVE WHERE CHECKINDATE <= #{date} AND CHECKOUTDATE > #{date}")
	int count(Date date);

	@Insert("INSERT INTO RESERVE (id, plan, checkindate, checkoutdate, numberofadultguest, numberofchildrenguest, totalhotelfee, memberid) VALUES(#{reserveId}, #{plan}, #{checkInDay}, #{checkOutDay}, #{numberOfAdultGuest}, #{numberOfChildrenGuest}, #{totalHotelFee}, #{memberId})")
	void insert(Reserve reserve);

	@Select("SELECT * FROM RESERVE WHERE ID = #{reserveId}")
	ReserveModel selectByReserveId(String reserveId);

	@Select("SELECT * FROM RESERVE WHERE MEMBERID = #{memberId}")
	List<ReserveModel> select(String memberId);

	@Update("UPDATE RESERVE SET plan = #{plan}, CHECKINDATE = #{checkInDay}, CHECKOUTDATE = #{checkOutDay}, NUMBEROFGUEST = #{numberOfGuest}, TOTALHOTELFEE = #{totalHotelFee} WHERE id = #{reserveId}")
	boolean update(Reserve reserve);

	@Delete("DELETE FROM RESERVE WHERE ID = #{id}")
	boolean delete(String id);

}