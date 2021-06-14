package com.v1ok.auth.impl;

import com.v1ok.auth.AESUtil;
import com.v1ok.auth.IAccessTokenParse;

public class AccessTokenParse implements IAccessTokenParse {

  @Override
  public String parse(String accessToken, String securityKey) {
    return AESUtil.decryptStr(accessToken, securityKey);
  }
}
