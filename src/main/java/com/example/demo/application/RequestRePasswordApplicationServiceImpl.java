package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.example.demo.application.command.RequestRePasswordCommand;
import com.example.demo.domain.HotelReserveMessages;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.model.DestinationModel;
import com.example.demo.domain.model.MailDataModel;
import com.example.demo.domain.model.MemberModel;

@Service
public class RequestRePasswordApplicationServiceImpl implements RequestRePasswordApplicationService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	private ApplicationContext applicationContext;

	protected void addErrorMessage(String paramString, Object... paramArrayObject) {
		HotelReserveMessages hotelReserveMessages = getMessage();
		hotelReserveMessages.addErrorMessage(paramString,
				messageSource.getMessage(paramString, paramArrayObject, null));
	}

	@Override
	public void execute(RequestRePasswordCommand requestRePasswordCommand) {
		// DBからユーザ情報を取得。
		MemberModel memberModel = memberRepository.getMember(requestRePasswordCommand.getMailAddress());
		MailDataModel mailDataModel = new MailDataModel();
		mailDataModel.setFromAddr(messageSource.getMessage("fromaddress", null, null));
		mailDataModel.setPasswordSettingUrl(messageSource.getMessage("passwordSettingUrl", null, null));
		DestinationModel destinationModel = new DestinationModel();
		destinationModel.setToAddr(memberModel.getName());
		destinationModel.setToName("yamada taro");
		mailDataModel.addMultiDestInfo(destinationModel);
		// パスワード再設定URLの生成
		mailDataModel.setPasswordSettingUrl(mailDataModel.getPasswordSettingUrl() + "hashedRequestNo");
	}

	private HotelReserveMessages getMessage() {
		return applicationContext.getBean(HotelReserveMessages.class);
	}

}