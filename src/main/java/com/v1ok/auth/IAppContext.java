package com.v1ok.auth;

public interface IAppContext extends IContext {

  String APP_ID = "appId";
  String SECURITY_KEY = "securityKey";

  String getAppId();

  String getSecurityKey();
}
