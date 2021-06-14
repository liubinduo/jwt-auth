package com.v1ok.auth;

public interface IUserContextParse {

  IUserContext parse(String token, String securityKey);
}
