package com.ymlinks.boot.exception;

/**
 * Title : 用户验证异常
 * <p>
 * Description:用户验证异常
 * <p>
 * Author :韦嵩
 */
public class AuthException extends RuntimeException {


    private static final long serialVersionUID = 5694699188175334854L;

    /**
     * @param message 原异常信息
     */
    public AuthException(String message) {
        super(message);
    }

}
