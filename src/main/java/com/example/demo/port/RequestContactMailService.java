package com.example.demo.port;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.example.demo.common.MailProperties;
import com.example.demo.common.MailTemplateCds;
import com.example.demo.domain.model.MailDataModel;

@Service
public class RequestContactMailService extends MailService {

	MailTemplateCds mailTemplateCds = MailTemplateCds.MAIL_TEMPLATE_004;

	public void requestContactMail(String name, String mailAddress, String category, String contents) {
		MailDataModel mailDataModel = this.prepareSendDeleteMemberMail(name, mailAddress, category, contents);
		this.sendMail(mailDataModel);
	}

	private MailDataModel prepareSendDeleteMemberMail(String name, String mailAddress, String category,
			String contents) {
		MailDataModel mailDataModel = this.createMailDataModel(name, mailAddress, category, contents);
		try {
			mailDataModel.setSubject(getMailTemplateText(mailDataModel, TemplateTypes.SUBJECT, mailTemplateCds));
			mailDataModel.setBody(getMailTemplateText(mailDataModel, TemplateTypes.BODY, mailTemplateCds));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mailDataModel;
	}

	protected MailDataModel createMailDataModel(String name, String mailAddress, String category, String contents) {
		MailDataModel mailDataModel = new MailDataModel();
		mailDataModel.setName(name);
		mailDataModel.setFromAddr(mailAddress);
		mailDataModel.setToAddr(MailProperties.getFromAddr());
		mailDataModel.setCategory(category);
		mailDataModel.setContents(contents);

		return mailDataModel;
	}

}
