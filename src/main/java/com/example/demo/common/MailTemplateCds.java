package com.example.demo.common;

import java.util.Optional;

import com.example.demo.domain.InvalidArgumentException;



/**
 * メール送信時のテンプレート識別キーの列挙クラス.
 * 
 */
public enum MailTemplateCds {

	/**
	 * メールテンプレートenum：031
	 */
	MAIL_TEMPLATE_031("031", null, null);

	private final String key;
	private final Optional<String> groupKey;
	private final Optional<String> patternKey;

	private MailTemplateCds(String key, String groupKey, String patternKey) {
		this.key = key;
		this.groupKey = Optional.ofNullable(groupKey);
		this.patternKey = Optional.ofNullable(patternKey);
	}

	/**
	 * キーを返却する。
	 * 
	 * @return キー
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * グループキーを返却する。
	 * 
	 * @return グループキー
	 */
	public String getGroupKey() {
		return this.groupKey.orElse(null);
	}

	/**
	 * パターンコードを返却する。
	 * 
	 * @return パターンコード
	 */
	public String getPatternCode() {
		return this.patternKey.isPresent() ? patternKey.get() : key;
	}

	/**
	 * 指定したキーに基づいてコードを返却する。
	 * 
	 * @param key キー
	 * @return コード
	 * @throws InvalidArgumentException 
	 */
	public static Optional<MailTemplateCds> getKeyFromCode(String key) throws InvalidArgumentException {
		if (null == key)
			throw new InvalidArgumentException(LogForms.INVALID_PARAM.getMessage());

		Optional<MailTemplateCds> result = Optional.empty();
		for (MailTemplateCds val : MailTemplateCds.values()) {
			if (val.key.equalsIgnoreCase(key)) {
				result = Optional.of(val);
				break;
			}
		}
		return result;
	}
}