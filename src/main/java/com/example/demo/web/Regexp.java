package com.example.demo.web;

public class Regexp {

	// 全角文字以外
	public static final String MATCH_EXCEPT_ZENKAKU = "^[ -~｡-ﾟ]*";
	public static final String MATCH_MAILADDRESS = "^[\\w!#%&'/=~`\\*\\+\\?\\{\\}\\^\\$\\-\\|]+(\\.[\\w!#%&'/=~`\\*\\+\\?\\{\\}\\^\\$\\-\\|]+)*@[\\w!#%&'/=~`\\*\\+\\?\\{\\}\\^\\$\\-\\|]+(\\.[\\w!#%&'/=~`\\*\\+\\?\\{\\}\\^\\$\\-\\|]+)*$";
	public static final String MATCH_PASSWORD = "^[a-zA-Z0-9#%+-/=^_~]*";
}
