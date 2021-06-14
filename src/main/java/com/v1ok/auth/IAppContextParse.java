package com.v1ok.auth;

public interface IAppContextParse {

  IAppContext parse(String token, String securityKey);
}
