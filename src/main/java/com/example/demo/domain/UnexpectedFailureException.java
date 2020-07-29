package com.example.demo.domain;

import com.example.demo.domain.BaseException;

/**
 * システム的に（業務的にではなく）通常、起こりえない事象が発生した場合に生成する例外。
 * 
 *
 */
public class UnexpectedFailureException extends BaseException {

	/** シリアルID */
	private static final long serialVersionUID = 6818368589384820470L;

	/**
	 * コンストラクタ
	 */
	public UnexpectedFailureException() {
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 */
	public UnexpectedFailureException(String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param cause エラー原因
	 */
	public UnexpectedFailureException(Throwable cause) {
		super(cause);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message メッセージ
	 * @param cause   エラー原因
	 */
	public UnexpectedFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message            メッセージ
	 * @param cause              エラー原因
	 * @param enableSuppression  抑制の有効化または無効化
	 * @param writableStackTrace 書き込み可能スタックトレースの有効化または無効化
	 */
	public UnexpectedFailureException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause);
	}

}