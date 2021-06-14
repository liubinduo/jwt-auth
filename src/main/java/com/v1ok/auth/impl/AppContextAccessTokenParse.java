package com.v1ok.auth.impl;

import com.v1ok.auth.IAppContext;
import com.v1ok.auth.IAppContextParse;
import org.apache.commons.lang3.Validate;

public class AppContextAccessTokenParse extends AbstractContextParse implements IAppContextParse {

  private final static AccessTokenParse ACCESS_TOKEN_PARSE = new AccessTokenParse();

  @Override
  public IAppContext parse(String accessToken, String securityKey) {

    Validate.notBlank(accessToken, "The token string must not be blank.");
    Validate.notBlank(securityKey, "The securityKey must not be blank.");

    String token = ACCESS_TOKEN_PARSE.parse(accessToken, securityKey);

    return parseAppContext(token, securityKey);

  }

}
