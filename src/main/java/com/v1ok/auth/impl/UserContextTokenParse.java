package com.v1ok.auth.impl;

import com.v1ok.auth.IUserContext;
import com.v1ok.auth.IUserContextParse;
import com.v1ok.auth.impl.AbstractContextParse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

@Slf4j
public class UserContextTokenParse extends AbstractContextParse implements IUserContextParse {


  @Override
  public IUserContext parse(String token, String securityKey) {

    Validate.notBlank(token, "The token string must not be blank.");
    Validate.notBlank(securityKey, "The securityKey must not be blank.");

    return this.parseUserContext(token, securityKey);

  }


}
