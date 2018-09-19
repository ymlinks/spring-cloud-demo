package com.ymlinks.common.exception;

/**
 * 
 * Title : 通用自定义异常
 * 
 * Description:通用的自定义异常
 * 
 * Author :韦嵩
 * 
 */
public class CommonException extends RuntimeException {


	private static final long serialVersionUID = 7608330395582067150L;
	private int code = 400;

	/**
	 * @param message
	 *            原异常信息
	 */
	public CommonException(String message) {
		super(message);
	}

	/**
	 * @param message
	 *            异常信息
	 */
	public CommonException(int code, String message) {
		super(message);
		this.code= code;
	}
	
	/**
	 * @param message
	 *            异常信息
	 */
	public CommonException(String message, Exception e) {
		super(message, e);
	}

	/**
	 * @param message
	 *            异常信息
	 */
	public CommonException(int code, String message, Exception e) {
		super(message, e);
		this.code= code;
	}

	public int getCode() {
		return code;
	}
}
