package com.ymlinks.cloud.exception;

/**
 * 
 * Title : 异常代码定义
 * 
 * Description: 
 * 
 * Author : 韦嵩
 *
 */
public class ExceptionCode {

	public static final String PARAM_NULL = "参数不全!";

	public static final String AUTH_FAILD = "认证失败,请重新登录";

    public static final String GENERATE_TOKEN_FAILD = "令牌生成失败!";

	public static final String MOBILE_AUTH_FAILD = "验证码错误!";

	public static final String USER_NOT_EXISTS = "用户不存在!";
	
	public static final String PASSWORD_ERROR = "密码错误!";

	public static final String MOBILE_EXISTS = "手机号已经被注册";

	public static final String DATA_NOT_EXISTS = "数据不存在";

	public static final String GENERATE_ORDER_FAILED = "预约失败";

    public static final String EMPID_EXISTS = "员工编码已存在";

    public static final String EMP_BINDED = "该手机用户已绑定门店";

	public static final String MOBILE_IS_NULL = "手机号为空！";

	public static final String USER_NOT_ACTIVATE = "用户状态未激活！";
}