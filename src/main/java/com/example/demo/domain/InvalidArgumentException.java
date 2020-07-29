package com.example.demo.domain;

/**
 * メソッドの引数が不正な場合、生成される例外（Runtime例外、通常運用時には起こりえない例外）
 * 
 */
public class InvalidArgumentException extends BaseException {

	/** シリアルID */
	private static final long serialVersionUID = 327215019676781739L;

	/**
	 * コンストラクタ
	 */
	public InvalidArgumentException() {
	}

	/**
	 * コンストラクタ
	 * 
	 * @param cause エラー原因
	 */
	public InvalidArgumentException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 */
	public InvalidArgumentException(String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 * @param cause エラー原因
	 */
	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
}