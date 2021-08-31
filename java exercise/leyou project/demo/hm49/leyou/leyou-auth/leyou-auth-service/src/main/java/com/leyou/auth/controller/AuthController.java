package com.leyou.controller;

import com.leyou.auth.config.JwtProperties;
import com.leyou.auth.service.AuthService;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JwtUtils;

import com.leyou.common.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties properties;

    /**
     * 登录授权
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("accredit")
    public ResponseEntity<Void> authentication(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response) {
        // 登录校验
        String token = this.authService.authentication(username, password);
        if (StringUtils.isBlank(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // 将token写入cookie,并指定httpOnly为true，防止通过JS获取和修改
        CookieUtils.setCookie(request, response, properties.getCookieName(),
                token, properties.getExpire(), null, true);
        System.out.println(properties.getCookieName());
        return ResponseEntity.ok().build();
    }
    /**
     * 验证用户信息
     * @param ly_token
     * @return
     */
    @RequestMapping("verify")
    public ResponseEntity<UserInfo> verifyUser(@CookieValue("LY_TOKEN")String ly_token, HttpServletRequest request, HttpServletResponse response){
        try {
            // 从token中解析token信息
            UserInfo userInfo = JwtUtils.getInfoFromToken(ly_token, this.properties.getPublicKey());
            // 解析成功要重新刷新token
            ly_token = JwtUtils.generateToken(userInfo, this.properties.getPrivateKey(), this.properties.getExpire());
            // 更新cookie中的token
            CookieUtils.setCookie(request, response, this.properties.getCookieName(), ly_token, this.properties.getExpire());

            // 解析成功返回用户信息
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            System.out.println("leyou_token=");
            e.printStackTrace();
        }
        // 出现异常则，响应500

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}