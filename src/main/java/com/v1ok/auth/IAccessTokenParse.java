package com.v1ok.auth;

public interface IAccessTokenParse {

  String parse(String accessToken, String securityKey);

}
