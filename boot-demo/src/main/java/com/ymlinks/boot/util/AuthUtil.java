package com.ymlinks.boot.util;

import com.ymlinks.boot.constant.Constant;
import com.ymlinks.boot.exception.AuthException;
import com.ymlinks.boot.exception.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class AuthUtil {

    @Autowired
    private StringRedisTemplate template;

    public static final String DEVICE_ID = "deviceId";
    public static final String USER_ID = "userId";

    /**
     * 验证用户返回用户ID
     *
     * @param request
     * @return
     */
    public Map<String, String> auth(HttpServletRequest request) {
        String access_token = request.getHeader("access_token");
        String deviceId = request.getHeader("device_id");
        if (Assert.isNotNull(access_token) && Assert.isNotNull(deviceId)) {
            HashOperations<String, String, String> ops = this.template.opsForHash();
            String userId = ops.get(access_token, USER_ID);
            if (Assert.isNotNull(userId)) {
                String authKey = RedisNsUtil.authName(userId);
                Map<String, String> authMap = new HashMap<String, String>();
                authMap.put(DEVICE_ID, deviceId);
                authMap.put(USER_ID, userId);
                ops.getOperations().expire(access_token, Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.DAYS); // 延长失效时间
                ops.getOperations().expire(authKey, Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.DAYS); // 延长失效时间
                return authMap;
            }
        }
        throw new AuthException(ExceptionCode.AUTH_FAILD);
    }

    /**
     * 不需要登录,只需要获取信息
     *
     * @param request
     * @return
     * @throws AuthException
     */
    public Map<String, String> preAuth(HttpServletRequest request) throws AuthException {
        String access_token = request.getHeader("access_token");
        String deviceId = request.getHeader("device_id");
        if (Assert.isNotNull(deviceId)) {
            Map<String, String> result = new HashMap<>();
            result.put(DEVICE_ID, deviceId);
            if (Assert.isNotNull(access_token)) {
                HashOperations<String, String, String> ops = this.template.opsForHash();
                String userId = ops.get(access_token, USER_ID);
                if (Assert.isNotNull(userId)) {
                    result.put(USER_ID, ops.get(access_token, USER_ID));
                    String authKey = RedisNsUtil.authName(userId);
                    ops.getOperations().expire(access_token, Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.DAYS); // 延长失效时间
                    ops.getOperations().expire(authKey, Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.DAYS); // 延长失效时间
                }
            }

            return result;
        }
        throw new AuthException(ExceptionCode.AUTH_FAILD);
    }

    /**
     * 手机验证码验证
     *
     * @param mobile
     * @param code
     * @param product
     * @return
     */
    public boolean authMobile(String mobile, String code, String product) {
        if (Assert.isNotNull(mobile) && Assert.isNotNull(code)) {
            HashOperations<String, String, String> ops = this.template.opsForHash();
            String authcode = ops.get(RedisNsUtil.authName(mobile), product);
            if (code.equals(authcode))
                return true;
        }
        return false;
    }

    /**
     * 生成access_token
     *
     * @param userId
     * @param deviceId
     * @return
     */
    public String getToken(String userId, String deviceId) {
        String access_token = null;
        if (Assert.isNotNull(userId)) {
            HashOperations<String, String, String> ops = this.template.opsForHash();
            String authKey = RedisNsUtil.authName(userId);
            // 保持一个用户只能登录一个设备 删除原来的access_token
            access_token = ops.get(authKey, "access_token");
            if (Assert.isNotNull(access_token)) {
                ops.getOperations().delete(access_token);
                ops.getOperations().delete(authKey);
            }
            access_token = TokenUtil.getAccessToken();
            ops.put(access_token, USER_ID, userId);
            ops.put(access_token, DEVICE_ID, deviceId);
            ops.put(authKey, "access_token", access_token);
            ops.getOperations().expire(access_token, Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.DAYS);
            ops.getOperations().expire(authKey, Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.DAYS);
        }
        return access_token;
    }

}
