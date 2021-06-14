package com.v1ok.auth.impl;

import static com.v1ok.auth.IAppContext.APP_ID;
import static com.v1ok.auth.IAppContext.SECURITY_KEY;
import static com.v1ok.auth.IUserContext.USER_ID;
import static com.v1ok.auth.IUserContext.USER_PERMISSIONS_ID_KEY;
import static com.v1ok.auth.IUserContext.USER_POSITIONS_ID_KEY;
import static com.v1ok.auth.IUserContext.USER_TENANT_ID;

import com.v1ok.auth.IAppContext;
import com.v1ok.auth.IContext;
import com.v1ok.auth.IGenerator;
import com.v1ok.auth.IUserContext;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;

public class TokenGenerator implements IGenerator {

  @Override
  public String token(IContext context, String securityKey) {
    return token(context, securityKey, DateUtils.addMinutes(new Date(), 60));
  }

  @Override
  public String token(IContext context, String securityKey, Date expiration) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    byte[] apiKeySecretBytes = DigestUtils.md5(securityKey);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    if (context instanceof IAppContext) {

      IAppContext appContext = (IAppContext) context;

      JwtBuilder builder = Jwts.builder().
          setHeaderParam("typ", "JWT").
          claim(APP_ID, appContext.getAppId()).
          claim(SECURITY_KEY, securityKey).
          signWith(signatureAlgorithm, signingKey)
          .setExpiration(expiration);

      return builder.compact();

    }

    if (context instanceof IUserContext) {

      IUserContext userContext = (IUserContext) context;

      JwtBuilder builder = Jwts.builder().
          setHeaderParam("typ", "JWT").
          claim(USER_ID, userContext.getUserId()).
          claim(USER_TENANT_ID, userContext.getTenantId()).
          claim(USER_POSITIONS_ID_KEY, userContext.getPositions()).
          claim(USER_PERMISSIONS_ID_KEY, userContext.getPermissions()).
          signWith(signatureAlgorithm, signingKey)
          .setExpiration(expiration);

      return builder.compact();
    }

    return null;
  }
}
