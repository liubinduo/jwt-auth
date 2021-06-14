package com.v1ok.auth.impl;

import com.v1ok.auth.AESUtil;
import com.v1ok.auth.IContext;
import com.v1ok.auth.IGenerator;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

public class AccessTokenGenerator extends TokenGenerator implements IGenerator {

  @Override
  public String token(IContext context, String securityKey) {
    return token(context, securityKey, DateUtils.addMinutes(new Date(), 60));
  }

  @Override
  public String token(IContext context, String securityKey, Date expiration) {
    String token = super.token(context, securityKey, expiration);
    return AESUtil.encryptStr(token, securityKey);
  }
}
