package com.boka.cloud.exception;

/**
 * 
 * Title : 登陆验证异常
 * 
 * Description:登陆验证异常
 * 
 * Author :韦嵩
 * 
 */
public class LoginException extends Exception {


	private static final long serialVersionUID = -7724462536568789306L;

	/**
	 * @param message
	 *            原异常信息
	 */
	public LoginException(String message) {
		super(message);
	}
	
}
