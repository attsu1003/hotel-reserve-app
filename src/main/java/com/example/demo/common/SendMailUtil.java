//package com.example.demo.common;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.OpenOption;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.time.DateTimeException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.function.Consumer;
//
//import org.springframework.boot.autoconfigure.mail.MailProperties;
//import org.springframework.mail.SimpleMailMessage;
//
//import com.example.demo.domain.model.DestinationModel;
//import com.example.demo.domain.model.MailDataModel;
//
//import jp.co.nikkeibp.biztarget.constant.LogForms;
//import jp.co.nikkeibp.biztarget.constant.MailTemplateCds;
//import jp.co.nikkeibp.biztarget.exception.OperatorCorrespondingException;
//import jp.co.nikkeibp.biztarget.exception.SysBaseException;
//import jp.co.nikkeibp.biztarget.util.LogUtils;
//
///**
// * メール送信機能.
// * 
// * @author JeongGuk.Lee
// */
//public class SendMailUtil {
//
//	private final static String ERR_MESSAGE_KEYS = "SendMailErr";
//	private static MailController control;
//
//	/**
//	 * 指定テンプレートキーのメール本文を作成して送信を行う.<br>
//	 * <br>
//	 * 同一テンプレートで複数の宛先に送信することが可能で、{@code MailDataModel#setDestInfo(List&lt;DestinationModel&gt;)}を設定するか、<br>
//	 * {@code MailDataModel#addDestInfo(DestinationModel)}で{@linkplain MailDataModel メール送信データ}モデルに宛先情報を追加することで複数宛先へ同じテンプレートによる<br>
//	 * 送信設定が可能である.<br>
//	 * 
//	 * @param key テンプレートキー
//	 * @param model メールデータモデル
//	 * @return 送信エラーの件数
//	 * @throws OperatorCorrespondingException メール送信エラーのため、オーペレータへのエラー通知を求める時の例外.
//	 */
//	public static synchronized int sendMail(String key, MailDataModel model) throws OperatorCorrespondingException {
//
//		int errCnt = 0; // 送信に失敗した件数.
//		Optional<String> display = null; // 画面表示用メッセージ
//		Map<String, String> result = null;
//
//		try {
//
//			// 受信者がいない場合メール送信を中止する。
//			if (model.getMultiDestInfo().isEmpty()) {
//				return errCnt;
//			}
//
//			control = new MailController();
//			// メール送信準備と送信要求.
//			List<SimpleMailMessage> requestSendData = control.preparingSendMail(key, model);
//			result = control.requestSendMail(requestSendData);
//			// 送信エラーの出力
//			if (!result.isEmpty()) {
//				errCnt = Integer.parseInt(result.get(RESULT_ERR_CNT));
//				String errInfo = result.get(RESULT_ERR_INFO);
//				if (0 < errCnt) {
//					display = writeToFile(key, model.getContractNo(), errInfo);
//					throw new OperatorCorrespondingException(display.get());
//				}
//			}
//
//		} catch (Exception e) {
//			throw new OperatorCorrespondingException(e.getMessage(), e.getCause());
//		}
//
//		return errCnt;
//	}
//
//	/**
//	 * 送信失敗データやエラー情報のファイル出力
//	 * 
//	 * @param key テンプレートキー
//	 * @param contractNo 案件No
//	 * @param errInfo エラー情報
//	 * @throws OperatorCorrespondingException メール送信処理中の障害で運用ルールでの対応を要する例外.
//	 */
//	private static Optional<String> writeToFile(final String key, final String contractNo, String errInfo) throws OperatorCorrespondingException {
//
//		Optional<String> result = Optional.empty();
//		int logOutputCnt = 0, totalCnt = 0;
//		MailProperties prop = null;
//		Path outputPath = null;
//
//		try {
//			prop = MailProperties.getInstance();
//			Optional<MailTemplateCds> templateCd = MailTemplateCds.getKeyFromCode(key);
//
//			// 対象の出力パス＋ファイル名を結合.
//			outputPath = Paths.get(prop.getOutputFilePath());
//			if (templateCd.isPresent()) {
//				outputPath = outputPath.resolve(String.format("template_%s", templateCd.get().getPatternCode()));
//			}
//
//			// 出力日付の取得.
//			LocalDateTime ldt = LocalDateTime.now();
//			String createDate = ldt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//
//			// 出力
//			String[] errors = errInfo.split(MailFunctionBase.MAIL_SEPERATOR);
//			totalCnt = errors.length;
//			int cnt = 0;
//			for (String err : errors) {
//				Path outputDir = outputPath.getParent();
//				if (null == outputDir) {
//					throw new IllegalArgumentException("送信失敗したメールの出力先フォルダの取得に失敗しました.");
//				}
//				Files.createDirectories(outputDir); // 出力先パスが存在しない時はパス上存在しないフォルダを全て作成する.
//				String outputFilename = String.format("%1$s_%2$s_%3$02d", outputPath.toString(), createDate, cnt++);
//				OpenOption[] options = { StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE };
//				try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilename), StandardCharsets.UTF_8, options)) {
//					writer.write(err);
//					writer.flush();
//				} catch (IOException e) {
//					incrementCnt.accept(logOutputCnt);
//					writeLog.printInfo(err, outputFilename);
//				}
//			}
//		} catch (SysBaseException | DateTimeException | IOException e) {
//			String msg = LogForms.MAIL_OUTPUT_FAILTURE.getMessage();
//			LogUtils.outputErrMsg("SendMailErr", msg);
//			throw new OperatorCorrespondingException(msg, e);
//		}
//
//		if (0 < totalCnt) {
//			StringBuilder sb = new StringBuilder(150);
//			if (logOutputCnt > 0)
//				sb.append(LogForms.MAIL_ADD_LOG.getMessage());
//			result = Optional.of(sb.toString());
//		}
//
//		return result;
//	}
//
//	private static Consumer<Integer> incrementCnt = (count) -> count += 1;
//
//	private interface OutputErrLog<T, U> {
//		public void printInfo(T t, U u);
//	}
//
//	private static OutputErrLog<String, String> writeLog = (errInfo, outputFilename) -> {
//		int beginIndex = outputFilename.lastIndexOf(File.separator);
//		LogUtils.outputErrMsg(ERR_MESSAGE_KEYS, LogForms.MAIL_OUTPUT_LOG.getMessage(outputFilename.substring(beginIndex)));
//		LogUtils.outputErrMsg(ERR_MESSAGE_KEYS, errInfo);
//	};
//
//	/**
//	 * パスワード再設定用メール送信情報を取得する(BP管理者用)
//	 * 
//	 * @return fromAdress、パスワード設定URLの設定されたMailDataModel
//	 */
//	public static MailDataModel getReqRePwdModel() {
//		MailDataModel model = new MailDataModel();
//		MailProperties prop = MailProperties.getInstance();
//		model.setFromAddr(prop.getFromAddress());
//		model.setPasswordSettingUrl(prop.getPasswordSettingUrl());
//		model.setRepwdUrl(prop.getRePasswordRequestUrl());
//		return model;
//	}
//
//	/**
//	 * パスワード再設定用メール送信情報を取得する(利用者用)
//	 * 
//	 * @return fromAdress、パスワード設定URLの設定されたMailDataModel
//	 */
//	public static MailDataModel getReqRePwdModelCustomer() {
//		MailDataModel model = new MailDataModel();
//		MailProperties prop = MailProperties.getInstance();
//		model.setFromAddr(prop.getFromAddress());
//		model.setPasswordSettingUrl(prop.getCustomerPasswordSettingUrl());
//		model.setRepwdUrl(prop.getCustomerRePasswordRequestUrl());
//		return model;
//	}
//
//	/**
//	 * ファイル再送リクエストメール送信情報を取得する
//	 * 
//	 * @return destInfo、再送依頼メールを送る先が設定されたMailDataModel
//	 */
//	public static MailDataModel getRequestResendModel() {
//		MailDataModel model = new MailDataModel();
//		MailProperties prop = MailProperties.getInstance();
//		// 受信者情報の設定
//		DestinationModel destInfo = new DestinationModel();
//		destInfo.setToAddr(prop.getMailToAddressForRequestResend());
//		model.setSingleDestInfo(destInfo);
//		return model;
//	}
//
//	/**
//	 * リード・サポートセンター利用規約同意メール送信情報を取得する
//	 * 
//	 * @return destInfo、同意確認メールの送信先が設定されたMailDataModel
//	 */
//	public static MailDataModel getPersonalInfoConfModel() {
//		MailDataModel model = new MailDataModel();
//		// 受信者情報の設定
//		DestinationModel destInfo = new DestinationModel();
//		MailProperties prop = MailProperties.getInstance();
//		destInfo.setToAddr(prop.getPersonalInfoConfDestAddress());
//		model.setSingleDestInfo(destInfo);
//		model.setHtml(false);
//		return model;
//	}
//
//	/**
//	 * パスワード設定用URLを取得する
//	 * 
//	 * @return パスワード設定URL
//	 */
//	public static String getRequestPwdURL() {
//		return MailProperties.getInstance().getPasswordSettingUrl();
//	}
//
//	/**
//	 * 送信者メールアドレスを取得する.
//	 * 
//	 * @return 送信者メールアドレス
//	 */
//	public static String getFromAddress() {
//		return MailProperties.getInstance().getFromAddress();
//	}
//
//	/**
//	 * セミナー受講登録完了メールの送信者メールアドレスを取得する.
//	 * 
//	 * @return セミナー受講登録完了メールの送信者メールアドレス
//	 */
//	public static String getFromSeminarAddress() {
//		return MailProperties.getInstance().getFromSeminarAddress();
//	}
//
//	/**
//	 * 受信者(BP管理者用)メールアドレスを取得する.
//	 * 
//	 * @return 受信者メールアドレス
//	 */
//	public static String getToAddress() {
//		return MailProperties.getInstance().getToAddress();
//	}
//
//	/**
//	 * 利用者用メール設定呼び出しURLを取得する.
//	 * 
//	 * @return 呼び出しURL
//	 */
//	public static String getCustomerPasswordSettingUrl() {
//		return MailProperties.getInstance().getCustomerPasswordSettingUrl();
//	}
//
//	/**
//	 * 利用者登録画面呼び出しURLを取得する.
//	 * 
//	 * @return 呼び出しURL
//	 */
//	public static String getExternalCustomerInfoUrl() {
//		return MailProperties.getInstance().getExternalCustomerInfoUrl();
//	}
//	
//	/**
//	 * BP関係者メールアドレスを取得する.
//	 * 
//	 * @return BP関係者メールアドレス
//	 */
//	public static String getBpPersonConcernedAddress() {
//		return MailProperties.getInstance().getBpPersonConcernedAddress();
//	}
//}