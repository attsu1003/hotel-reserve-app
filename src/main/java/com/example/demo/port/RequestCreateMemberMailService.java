package com.example.demo.port;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.example.demo.common.MailProperties;
import com.example.demo.common.MailTemplateCds;
import com.example.demo.domain.model.MailDataModel;

@Service
public class RequestCreateMemberMailService extends MailService {

	MailTemplateCds mailTemplateCds = MailTemplateCds.MAIL_TEMPLATE_003;

	public void requestCreateMemberMail(String mailAddress) {
		MailDataModel mailDataModel = this.prepareSendDeleteMemberMail(mailAddress);
		this.sendMail(mailDataModel);
	}

	private MailDataModel prepareSendDeleteMemberMail(String mailAddress) {
		MailDataModel mailDataModel = this.createMailDataModel(mailAddress);
		try {
			mailDataModel.setSubject(getMailTemplateText(mailDataModel, TemplateTypes.SUBJECT, mailTemplateCds));
			mailDataModel.setBody(getMailTemplateText(mailDataModel, TemplateTypes.BODY, mailTemplateCds));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mailDataModel;
	}

	protected MailDataModel createMailDataModel(String mailAddress) {
		MailDataModel mailDataModel = new MailDataModel();
		mailDataModel.setFromAddr(MailProperties.getFromAddr());
		mailDataModel.setToAddr(mailAddress);
		mailDataModel.setPasswordSettingUrl(MailProperties.getCreateMemberUrl());
		return mailDataModel;
	}

}
