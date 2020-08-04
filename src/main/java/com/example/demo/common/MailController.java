
package com.example.demo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.demo.domain.HotelReserveMessages;
import com.example.demo.domain.UnexpectedFailureException;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.model.MailDataModel;
import com.example.demo.domain.model.MemberModel;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Component
public class MailController {

	private static final String PREFIX_SUBJECT = "mail_template_subject_";
	private static final String PREFIX_BODY = "mail_template_body_";

	private static enum TemplateTypes {
		SUBJECT, BODY
	};

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private JavaMailSender emailSender;

	public void sendMail(String mailAddress) throws IOException {
		MailDataModel mailDataModel = this.createMailDataModel(mailAddress);
		String subject = null;
		String body = null;
		try {	
			subject = getMailTemplateText(mailDataModel);
			body = setBody(mailDataModel);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.sendMail(mailDataModel, subject, body);
	}

	private String getMailTemplateText(MailDataModel mailDataModel)
			throws IOException, TemplateNotFoundException, MalformedTemplateNameException, ParseException {
		String subject = null;
		Map<String, Object> map = new HashMap<String, Object>() {
			{
				put("model", mailDataModel);
			}
		};
		Template subjectTemplate = null;
		try {
			subjectTemplate = getMailTemplate(TemplateTypes.SUBJECT, MailTemplateCds.MAIL_TEMPLATE_001);
		} catch (TemplateNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnexpectedFailureException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedTemplateNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			subject = FreeMarkerTemplateUtils.processTemplateIntoString(subjectTemplate, map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subject;
	}

	private Template getMailTemplate(TemplateTypes templateTypes, MailTemplateCds mailTemplateCds)
			throws UnexpectedFailureException, TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException {
		Configuration configuration = new Configuration(Configuration.getVersion());
		configuration.setDirectoryForTemplateLoading(new File(MailProperties.getMailTemplateResourcePath()));
		String subjectFile = this.createTemplatePath(templateTypes, mailTemplateCds);

		Template subjectTemplate = configuration.getTemplate(subjectFile, "UTF-8");
		return subjectTemplate;
	}

	private String setBody(MailDataModel mailDataModel)
			throws IOException, TemplateNotFoundException, MalformedTemplateNameException, ParseException {
		String body = null;
		Map<String, Object> map = new HashMap<String, Object>() {
			{
				put("model", mailDataModel);
			}
		};
		Template bodyTemplate = null;
		try {
			bodyTemplate = getMailTemplate(TemplateTypes.BODY, MailTemplateCds.MAIL_TEMPLATE_001);
		} catch (TemplateNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnexpectedFailureException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedTemplateNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			body = FreeMarkerTemplateUtils.processTemplateIntoString(bodyTemplate, map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return body;
	}

	private void sendMail(MailDataModel mailDataModel, String subject, String body) {
		SimpleMailMessage simpleMailMessage = createSimpleMailMessage(subject, body, mailDataModel.getFromAddr(),
				mailDataModel.getToAddr());
		emailSender.send(simpleMailMessage);
	}

	private SimpleMailMessage createSimpleMailMessage(String subject, String body, String fromAddress,
			String toAddress) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		simpleMailMessage.setFrom(fromAddress);
		simpleMailMessage.setTo(toAddress);
		return simpleMailMessage;
	}

	private MailDataModel createMailDataModel(String mailAddress) {
		MailDataModel mailDataModel = new MailDataModel();
		mailDataModel.setFromAddr(MailProperties.getFromAddr());
		mailDataModel.setToAddr(mailAddress);
		mailDataModel.setPasswordSettingUrl(MailProperties.getPasswordSettingUrl());
		return mailDataModel;
	}

	/**
	 * メールテンプレートの種類に合わせてテンプレートを取得するパスを作成する.<br>
	 * 
	 * @param type メールテンプレートの種類({@link TemplateTypes#SUBJECT},
	 *             {@link TemplateTypes#BODY})
	 * @param key  テンプレート識別キー
	 * @return テンプレートのリソースパス
	 * @throws UnexpectedFailureException
	 */
	private String createTemplatePath(TemplateTypes type, MailTemplateCds key) throws UnexpectedFailureException {
		Path path = null;
		StringBuilder file = null;
		try {
			// テンプレートファイル名生成.
			file = new StringBuilder().append((TemplateTypes.SUBJECT == type) ? PREFIX_SUBJECT : PREFIX_BODY)
					.append(key.getPatternCode()).append(".ftl");

			path = Paths.get(file.toString()); // テンプレートファイル名を連結.
		} catch (InvalidPathException e) {
			String msg = null;
			if (null != file && null != path) {
				int stx = file.toString().lastIndexOf(File.separator);
				msg = LogForms.RESOURCE_NOT_FOUND.getMessage(file.substring(stx), path.toString());
			} else {
				msg = "リソースファイルがの存在有無を確認して下さい.";
			}
			throw new UnexpectedFailureException(msg, e);
		}
		return path.normalize().toString();
	}

	private HotelReserveMessages getMessage() {
		return applicationContext.getBean(HotelReserveMessages.class);
	}

}