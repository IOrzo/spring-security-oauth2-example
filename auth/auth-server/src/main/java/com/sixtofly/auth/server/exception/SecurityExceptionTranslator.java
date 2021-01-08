package com.sixtofly.auth.server.exception;

import com.sixtofly.common.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * @author xie yuan bing
 * @date 2019-09-24 22:14
 * @description
 */
public class SecurityExceptionTranslator extends DefaultWebResponseExceptionTranslator {

    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        // Try to extract a SpringSecurityException from the stacktrace
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);

        Exception ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class,
                causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new UnauthorizedException());
        }

        ase = (AccessDeniedException) throwableAnalyzer
                .getFirstThrowableOfType(AccessDeniedException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new ForbiddenException());
        }

        ase = (InvalidGrantException) throwableAnalyzer
                .getFirstThrowableOfType(InvalidGrantException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new InvalidException());
        }

        ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer
                .getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new MethodNotAllowed());
        }

        ase = (BusinessException) throwableAnalyzer.getFirstThrowableOfType(BusinessException.class, causeChain);
        if (ase != null) {
            BusinessException businessException = (BusinessException)ase;
            return handleOAuth2Exception(new CustomOauth2Exception(businessException.getCode(),businessException.getMsg()));
        }

        ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(
                OAuth2Exception.class, causeChain);

        if (ase != null) {
            return handleOAuth2Exception((OAuth2Exception) ase);
        }

        return handleOAuth2Exception(new ServerErrorException());

    }


    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) {

        int status = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CACHE_CONTROL, "no-store");
        headers.set(HttpHeaders.PRAGMA, "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
            headers.set(HttpHeaders.WWW_AUTHENTICATE, String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
        }

        // 客户端异常直接返回客户端,不然无法解析
        if (e instanceof ClientAuthenticationException) {
            return new ResponseEntity<>(e, headers,
                    HttpStatus.valueOf(status));
        }
        return new ResponseEntity<>(new CustomOauth2Exception(e.getOAuth2ErrorCode(),e.getMessage()), headers,
                HttpStatus.valueOf(status));

    }
}
