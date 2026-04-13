package com.healthcare.telemedicine_service.util;

import io.agora.media.DynamicKey5;
import io.agora.media.RtcTokenBuilder2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AgoraTokenUtil {

    @Value("${agora.appId}")
    private String appId;

    @Value("${agora.appCertificate}")
    private String appCertificate;

    @Value("${agora.expiration}")
    private int expirationInSeconds;

    public String generateToken(String channelName, int uid, int role) {
        RtcTokenBuilder2 tokenBuilder = new RtcTokenBuilder2();
        // role: 1 = publisher, 2 = subscriber (audience)
        int tokenRole = (role == 1) ? RtcTokenBuilder2.RoleRolePublisher : RtcTokenBuilder2.RoleRoleSubscriber;
        String token = tokenBuilder.buildTokenWithUid(appId, appCertificate, channelName, uid, tokenRole, expirationInSeconds);
        return token;
    }
}