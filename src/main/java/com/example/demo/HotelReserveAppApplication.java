package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.MemberModel;
import com.example.demo.domain.model.RoomModel;
import com.example.demo.domain.room.RoomRepository;
import com.example.demo.port.MemberMapper;
import com.example.demo.port.ReserveMapper;

@SpringBootApplication
public class HotelReserveAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HotelReserveAppApplication.class, args);
	}

	private final MemberMapper memberMapper;
	private final RoomRepository roomRepository;

	public HotelReserveAppApplication(MemberMapper memberMapper, RoomRepository roomRepository) {
		this.memberMapper = memberMapper;
		this.roomRepository = roomRepository;
//		Member member = this.memberMapper.select("1234567");
//		System.out.println(member.getMemberId());
//		System.out.println(member.getMemberName());
//		MemberModel memberModel = new MemberModel("1111111", "test", "passwd");
//		memberMapper.insert(memberModel);
		MemberModel memberModel = memberMapper.select("1234567");
		int roomNum = this.roomRepository.countRoom();
		RoomModel roomModel = this.roomRepository.findRoom("101");
		System.out.println(roomModel.getId());
		System.out.println(roomNum);
		System.out.println(memberModel.getName());
		System.out.println(memberModel.getPasswd());
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
//
//		MemberModel memberModel = new MemberModel("1111111", "test", "passwd");
//		memberMapper.insert(memberModel);
//		Member member = memberMapper.select("1234567");
//		System.out.println(member.getMemberId());
//		System.out.println(member.getMemberName());
	}

}
