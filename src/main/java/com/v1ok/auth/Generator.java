package com.v1ok.auth;

import com.v1ok.auth.impl.AccessTokenGenerator;
import com.v1ok.auth.impl.TokenGenerator;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import com.v1ok.auth.IAppContext;
import com.v1ok.auth.IUserContext;

public class Generator {

  private final static TokenGenerator TOKEN_GENERATOR = new TokenGenerator();
  private final static AccessTokenGenerator ACCESS_TOKEN_GENERATOR = new AccessTokenGenerator();

  public static String token(IUserContext userContext, String securityKey) {
    return token(userContext, securityKey, DateUtils.addMinutes(new Date(), 60));
  }

  public static String token(IUserContext userContext, String securityKey, Date expiration) {
    return TOKEN_GENERATOR.token(userContext, securityKey, expiration);
  }

  public static String token(IAppContext appContext, String securityKey) {
    return token(appContext, securityKey, DateUtils.addMinutes(new Date(), 60));
  }

  public static String token(IAppContext appContext, String securityKey, Date expiration) {
    return TOKEN_GENERATOR.token(appContext, securityKey, expiration);
  }


  public static String accessToken(IUserContext userContext, String securityKey) {
    return accessToken(userContext, securityKey, DateUtils.addMinutes(new Date(), 60));
  }

  public static String accessToken(IUserContext userContext, String securityKey, Date expiration) {
    return ACCESS_TOKEN_GENERATOR.token(userContext, securityKey, expiration);
  }

  public static String accessToken(IAppContext appContext, String securityKey) {
    return accessToken(appContext, securityKey, DateUtils.addMinutes(new Date(), 60));
  }

  public static String accessToken(IAppContext appContext, String securityKey, Date expiration) {
    return ACCESS_TOKEN_GENERATOR.token(appContext, securityKey, expiration);
  }

}
