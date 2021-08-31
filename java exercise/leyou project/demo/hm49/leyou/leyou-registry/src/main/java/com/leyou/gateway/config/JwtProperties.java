package com.leyou.gateway.config;

import com.leyou.common.utils.RsaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

@ConfigurationProperties(prefix = "leyou.jwt")
public class JwtProperties {

    private String pubKeyPath;// 公钥

    private String cookieName;

    private PublicKey publicKey; // 公钥

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    public JwtProperties() {
    }

    public JwtProperties(String pubKeyPath) {
        this.pubKeyPath = pubKeyPath;
    }

    public JwtProperties(String pubKeyPath, String cookieName) {
        this.pubKeyPath = pubKeyPath;
        this.cookieName = cookieName;
    }

    public JwtProperties(String pubKeyPath, String cookieName, PublicKey publicKey) {
        this.pubKeyPath = pubKeyPath;
        this.cookieName = cookieName;
        this.publicKey = publicKey;
    }

    /**
     * @PostContruct：在构造方法执行之后执行该方法
     */



    public String getPubKeyPath() {
        return pubKeyPath;
    }

    public void setPubKeyPath(String pubKeyPath) {
        this.pubKeyPath = pubKeyPath;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

}