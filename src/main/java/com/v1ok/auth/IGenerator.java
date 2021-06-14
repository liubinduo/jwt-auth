package com.v1ok.auth;

import java.util.Date;

public interface IGenerator {

  String token(IContext context, String securityKey);

  String token(IContext context, String securityKey, Date expiration);
}
