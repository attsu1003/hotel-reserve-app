package com.example.demo.application;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.example.demo.application.command.RequestRePasswordCommand;
import com.example.demo.common.MailController;
import com.example.demo.domain.HotelReserveMessages;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.member.MemberService;
import com.example.demo.domain.model.DestinationModel;
import com.example.demo.domain.model.MailDataModel;
import com.example.demo.domain.model.MemberModel;
////
////@Component
////public class RequestRePasswordApplicationServiceImpl implements RequestRePasswordApplicationService {
////
////	@Autowired
////	private MemberRepository memberRepository;
////	
////	@Autowired
////	MailController mailController;
////	
////	@Autowired
////	MemberService memberService;
////
////	@Override
////	public void execute(RequestRePasswordCommand requestRePasswordCommand) throws IOException {
////		// DBからユーザ情報を取得。
////		MemberModel memberModel = memberRepository.getMember(requestRePasswordCommand.getMailAddress());
////		mailController.createModel(memberModel);
////		
////	}
//
//	
//
//}