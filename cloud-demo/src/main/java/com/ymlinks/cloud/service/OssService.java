package com.ymlinks.cloud.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OssService {

    @Value("oss.access.key.id")
    private String accessKeyId;
    @Value("oss.access.key.secret")
    private String accessKeySecret;
    @Value("oss.access.role.arn")
    private String roleArn;
    private static final String REGION_CN_HANGZHOU = "cn-shanghai";
    private static final String STS_API_VERSION = "2015-04-01";


    public void getToken() {

        long durationSeconds = 900; // 设置Token有效期 默认是3600秒
        String policy = "{\"Statement\": [{\"Action\": [\"oss:GetObject\", \"oss:PutObject\"], \"Effect\": \"Allow\", \"Resource\": [\"acs:oss:*:*:$BUCKET_NAME/*\", \"acs:oss:*:*:$BUCKET_NAME\"]}],\"Version\": \"1\"}\n";
        String roleSessionName = "alice-001";

        // 此处必须为 HTTPS
        ProtocolType protocolType = ProtocolType.HTTPS;
        IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);

        // 创建一个 AssumeRoleRequest 并设置请求参数
        AssumeRoleRequest request = new AssumeRoleRequest();
        request.setVersion(STS_API_VERSION);
        request.setMethod(MethodType.POST);
        request.setProtocol(protocolType);
        request.setRoleArn(roleArn);
        request.setRoleSessionName(roleSessionName);
        request.setPolicy(policy);
        request.setDurationSeconds(durationSeconds);

        try {
            AssumeRoleResponse response = client.getAcsResponse(request);
            Map<String, String> respMap = new LinkedHashMap<>();
            respMap.put("AccessKeyId", response.getCredentials().getAccessKeyId());
            respMap.put("AccessKeySecret", response.getCredentials().getAccessKeySecret());
            respMap.put("SecurityToken", response.getCredentials().getSecurityToken());
            respMap.put("Expiration", response.getCredentials().getExpiration());
        } catch (ClientException e) {
        }

    }
}
