
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

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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
	private MemberRepository memberRepository;

	@Autowired
	private JavaMailSender emailSender;

	protected void addErrorMessage(String paramString, Object... paramArrayObject) {
		HotelReserveMessages hotelReserveMessages = getMessage();
		hotelReserveMessages.addErrorMessage(paramString,
				messageSource.getMessage(paramString, paramArrayObject, null));
	}

//	public void createModel(MemberModel memberModel) throws IOException {
//		MailDataModel mailDataModel = new MailDataModel();
//		mailDataModel.setToAddr("attsuyagimpul@gmail.com");
//		mailDataModel.setFromAddr(MailProperties.getFromAddr());
//		mailDataModel.setPasswordSettingUrl(MailProperties.getPasswordSettingUrl());
//		// destinationModel.setToName("yamada taro");
//		// mailDataModel.addMultiDestInfo(destinationModel);
//		// パスワード再設定URLの生成
//		this.preparingSendMail(mailDataModel);
//	}

	public void preparingSendMail(MemberModel memberModel) throws IOException {
		MailDataModel mailDataModel = this.createMailDataModel(memberModel);
		Map<String, Object> map = new HashMap<String, Object>() {
			{
				put("model", mailDataModel);
			}
		};

		Configuration configuration = new Configuration(Configuration.getVersion());
		configuration.setDirectoryForTemplateLoading(new File(MailProperties.getMailTemplateResourcePath()));
		try {
			String subjectFile = this.createTemplatePath(TemplateTypes.SUBJECT, MailTemplateCds.MAIL_TEMPLATE_001);
			String bodyFile = this.createTemplatePath(TemplateTypes.BODY, MailTemplateCds.MAIL_TEMPLATE_001);

			Template subjectTemplate = configuration.getTemplate(subjectFile, "UTF-8");
			Template bodyTemplate = configuration.getTemplate(bodyFile, "UTF-8");

			try {
				String subject = FreeMarkerTemplateUtils.processTemplateIntoString(subjectTemplate, map);
				String body = FreeMarkerTemplateUtils.processTemplateIntoString(bodyTemplate, map);

				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setSubject(subject);
				simpleMailMessage.setText(body);
				simpleMailMessage.setFrom(mailDataModel.getFromAddr());
				simpleMailMessage.setTo(mailDataModel.getToAddr());
				emailSender.send(simpleMailMessage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnexpectedFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private MailDataModel createMailDataModel(MemberModel memberModel) {
		MailDataModel mailDataModel = new MailDataModel();
		mailDataModel.setToAddr(memberModel.getName());
		mailDataModel.setFromAddr(MailProperties.getFromAddr());
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