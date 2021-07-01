package com.v1ok.auth.impl;

import static com.v1ok.auth.IAppContext.APP_ID;
import static com.v1ok.auth.IAppContext.SECURITY_KEY;
import static com.v1ok.auth.IUserContext.USER_ID;
import static com.v1ok.auth.IUserContext.USER_PERMISSIONS_ID_KEY;
import static com.v1ok.auth.IUserContext.USER_POSITIONS_ID_KEY;
import static com.v1ok.auth.IUserContext.USER_TENANT_ID;

import com.v1ok.auth.IAppContext;
import com.v1ok.auth.IUserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

@Slf4j
public abstract class AbstractContextParse {

  protected IUserContext parseUserContext(String token, String securityKey) {
    try {
      Claims claims = Jwts.parser().setSigningKey(DigestUtils.md5(securityKey))
          .parseClaimsJws(token).getBody();

      String userId = claims.get(USER_ID, String.class);
      String tenantId = claims.get(USER_TENANT_ID, String.class);
      String positions = claims.get(USER_POSITIONS_ID_KEY, String.class);
      List permissions = claims.get(USER_PERMISSIONS_ID_KEY, List.class);

      return DefaultUserContext.builder()
          .userId(userId)
          .tenantId(tenantId)
          .positions(positions)
          .permissions(permissions).build();
    } catch (Exception e) {
      log.error("parse token is error.", e);
    }

    return null;
  }

  protected IAppContext parseAppContext(String token, String securityKey) {
    try {
      Claims claims = Jwts.parser().setSigningKey(DigestUtils.md5(securityKey))
          .parseClaimsJws(token).getBody();

      String appId = claims.get(APP_ID, String.class);
      securityKey = claims.get(SECURITY_KEY, String.class);

      return DefaultAppContext.builder()
          .appId(appId)
          .securityKey(securityKey)
          .build();
    } catch (Exception e) {
      log.error("parse accessToken is error.", e);
    }

    return null;
  }

}
