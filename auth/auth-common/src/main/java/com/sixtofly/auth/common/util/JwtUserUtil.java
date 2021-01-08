package com.sixtofly.auth.common.util;

import com.sixtofly.auth.common.constants.CommonConstants;
import com.sixtofly.auth.common.entity.SecurityUser;
import com.sixtofly.common.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Base64;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 从JWT令牌中获取用户信息
 * @author xie yuan bing
 * @date 2020-01-19 18:01
 * @description
 */
@UtilityClass
@Slf4j
public class JwtUserUtil {

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     * 需解析令牌数据
     */
    public static SecurityUser getUser(Authentication authentication) {
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
            String tokenValue = details.getTokenValue();
            if (StringUtils.isBlank(tokenValue)) {
                throw new BusinessException("读取用户信息失败，请认证");
            }
            String signKey = Base64.getEncoder().encodeToString(CommonConstants.JWT_SIGN_KEY.getBytes());
            Claims claims = Jwts.parser().setSigningKey(signKey).parseClaimsJws(tokenValue).getBody();
            Long userId = claims.get("userId", Long.class);
            String username = claims.get("user_name", String.class);
            Collection authorities = claims.get("authorities", Collection.class);
            String name = claims.get("name", String.class);
            String nickName = claims.get("nickName", String.class);
            SecurityUser user = new SecurityUser(username, "", AuthorityUtils.commaSeparatedStringToAuthorityList(authorities.stream().collect(Collectors.joining(",")).toString()));
            return user;
        }
        return null;
    }


    public SecurityUser tryGetUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    public SecurityUser getUser() {
        SecurityUser user = tryGetUser();
        if (user == null) {
            throw new RuntimeException();
        }
        return user;
    }


}
