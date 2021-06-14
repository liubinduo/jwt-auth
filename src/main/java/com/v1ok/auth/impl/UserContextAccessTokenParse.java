package com.v1ok.auth.impl;

import com.v1ok.auth.IUserContext;
import com.v1ok.auth.IUserContextParse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

@Slf4j
public class UserContextAccessTokenParse extends AbstractContextParse implements IUserContextParse {

  private final static AccessTokenParse ACCESS_TOKEN_PARSE = new AccessTokenParse();

  @Override
  public IUserContext parse(String accessToken, String securityKey) {

    Validate.notBlank(accessToken, "The accessToken string must not be blank.");
    Validate.notBlank(securityKey, "The securityKey must not be blank.");

    String token = ACCESS_TOKEN_PARSE.parse(accessToken, securityKey);

    return parseUserContext(token, securityKey);

  }

}
