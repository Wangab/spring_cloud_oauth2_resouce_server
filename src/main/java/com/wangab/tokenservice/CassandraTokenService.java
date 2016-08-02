package com.wangab.tokenservice;

import org.apache.usergrid.security.tokens.TokenInfo;
import org.apache.usergrid.security.tokens.cassandra.TokenServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * Created by wanganbang on 8/1/16.
 */
public class CassandraTokenService implements ResourceServerTokenServices {
    private static final Logger LOG = LoggerFactory.getLogger(CassandraTokenService.class);

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        System.out.println(accessToken);
        TokenServiceImpl tokenService = new TokenServiceImpl();
        try {
            TokenInfo tokenInfo = tokenService.getTokenInfo(accessToken);
            System.out.println(tokenInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        System.out.println(accessToken);
        return null;
    }

}
