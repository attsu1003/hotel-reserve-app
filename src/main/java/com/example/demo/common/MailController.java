package com.example.demo.common;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.demo.domain.UnexpectedFailureException;

public class MailController {
	
	private static final String PREFIX_SUBJECT = "mail_template_subject_";
	private static final String PREFIX_BODY = "mail_template_body_";
	
	private static enum TemplateTypes {
		SUBJECT, BODY
	};
	
	/**
	 * メールテンプレートの種類に合わせてテンプレートを取得するパスを作成する.<br>
	 * 
	 * @param type
	 *            メールテンプレートの種類({@link TemplateTypes#SUBJECT},
	 *            {@link TemplateTypes#BODY})
	 * @param key
	 *            テンプレート識別キー
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


}