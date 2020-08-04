package com.example.demo.port;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.demo.common.LogForms;
import com.example.demo.common.MailProperties;
import com.example.demo.common.MailTemplateCds;
import com.example.demo.domain.UnexpectedFailureException;
import com.example.demo.domain.model.MailDataModel;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public abstract class MailService {

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	private JavaMailSender emailSender;

	protected static final String PREFIX_SUBJECT = "mail_template_subject_";
	protected static final String PREFIX_BODY = "mail_template_body_";

	protected static enum TemplateTypes {
		SUBJECT, BODY
	};

	protected String getMailTemplateText(MailDataModel mailDataModel, TemplateTypes templateTypes,
			MailTemplateCds mailTemplateCds)
			throws IOException, TemplateNotFoundException, MalformedTemplateNameException, ParseException {
		String subject = null;
		Map<String, Object> map = new HashMap<String, Object>() {
			{
				put("model", mailDataModel);
			}
		};
		Template subjectTemplate = null;
		try {
			subjectTemplate = getMailTemplate(templateTypes, mailTemplateCds);
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

	protected void sendMail(MailDataModel mailDataModel) {
		SimpleMailMessage simpleMailMessage = createSimpleMailMessage(mailDataModel.getSubject(),
				mailDataModel.getBody(), mailDataModel.getFromAddr(), mailDataModel.getToAddr());
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

	

}
