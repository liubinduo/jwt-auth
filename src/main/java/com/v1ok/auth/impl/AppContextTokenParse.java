package com.v1ok.auth.impl;

import com.v1ok.auth.IAppContext;
import com.v1ok.auth.IAppContextParse;
import org.apache.commons.lang3.Validate;

public class AppContextTokenParse extends AbstractContextParse implements IAppContextParse {

  @Override
  public IAppContext parse(String token, String securityKey) {

    Validate.notBlank(token, "The token string must not be blank.");
    Validate.notBlank(securityKey, "The securityKey must not be blank.");

    return parseAppContext(token, securityKey);

  }

}
