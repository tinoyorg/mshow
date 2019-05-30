package com.server.mshow.common;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wxapp")
public class WxAuth {
    private String appId;

    private String secret;

    private String grantType;

    private String sessionHost;

    private  String tokenHost;

    private  String acodeUnlimitHost;

    private String appSecret;

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getTokenHost() {
        return tokenHost;
    }

    public void setTokenHost(String tokenHost) {
        this.tokenHost = tokenHost;
    }
    public String getAcodeUnlimitHost() {
        return acodeUnlimitHost;
    }

    public void setAcodeUnlimitHost(String acodeUnlimitHost) {
        this.acodeUnlimitHost = acodeUnlimitHost;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getSessionHost() {
        return sessionHost;
    }

    public void setSessionHost(String sessionHost) {
        this.sessionHost = sessionHost;
    }



}