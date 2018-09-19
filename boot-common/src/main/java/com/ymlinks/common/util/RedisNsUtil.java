package com.ymlinks.common.util;

/**
 * 
 * Title : 缓存中命名空间
 * 
 * Description:缓存中命名空间
 * 
 * Author : 韦嵩
 * 
 */
public class RedisNsUtil {

	/**
	 * 验证码空间
	 */
	private static final String AUTH = "auth";
	
	/**
	 * 令牌空间
	 */
	private static final String TOKEN = "token";
	
	/**
	 * 分隔符
	 */
	private static final String SEPARATOR = ":";
	
	public static String authName (String obj) {
		return AUTH+SEPARATOR+obj;
	}
	
	public static String tokenName () {
		return TOKEN;
	}
}
