package com.v1ok.auth;

import com.v1ok.auth.impl.AppContextAccessTokenParse;
import com.v1ok.auth.impl.AppContextTokenParse;
import com.v1ok.auth.impl.UserContextAccessTokenParse;
import com.v1ok.auth.impl.UserContextTokenParse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.Validate;

/**
 * TOKEN 校验与解析工具类
 */
@Slf4j
public class AuthVerify {

  /**
   * 校验TOKEN
   *
   * @param token 字条串
   * @param securityKey 安全密钥
   * @return true 成功，false 失败
   */
  public static boolean verifyToken(String token, String securityKey) {

    Validate.notBlank(token, "The token string must not be blank.");
    Validate.notBlank(securityKey, "The securityKey must not be blank.");

    try {
      Claims claims = Jwts.parser().setSigningKey(DigestUtils.md5(securityKey))
          .parseClaimsJws(token).getBody();
      return claims != null;
    } catch (Exception exception) {
      log.warn("verify token is error", exception);
    }

    return false;
  }

  public static boolean verifyAccessToken(String accessToken, String securityKey) {
    Validate.notBlank(accessToken, "The accessToken string must not be blank.");
    Validate.notBlank(securityKey, "The securityKey must not be blank.");

    try {
      String decryptStr = AESUtil.decryptStr(accessToken, securityKey);

      Claims claims = Jwts.parser().setSigningKey(DigestUtils.md5(securityKey))
          .parseClaimsJws(decryptStr).getBody();
      return claims != null;
    } catch (Exception exception) {
      log.warn("verify accessToken is error", exception);
    }
    return false;
  }

  /**
   * 解析TOKEN
   *
   * @param token 字符串
   * @param securityKey 安全密钥
   * @return 用户信息
   */
  public static IUserContext parseUserContextToken(String token, String securityKey) {

    Validate.notBlank(token, "The token string must not be blank.");
    Validate.notBlank(securityKey, "The securityKey must not be blank.");

    UserContextTokenParse tokenParse = new UserContextTokenParse();
    return tokenParse.parse(token, securityKey);

  }

  public static IUserContext parseUserContextAccessToken(String accessToken, String securityKey) {
    Validate.notBlank(accessToken, "The token string must not be blank.");
    Validate.notBlank(securityKey, "The securityKey must not be blank.");

    UserContextAccessTokenParse tokenParse = new UserContextAccessTokenParse();
    return tokenParse.parse(accessToken, securityKey);
  }

  public static IAppContext parseAppContextToken(String token, String securityKey) {
    Validate.notBlank(token, "The token string must not be blank.");
    Validate.notBlank(securityKey, "The securityKey must not be blank.");

    AppContextTokenParse tokenParse = new AppContextTokenParse();
    return tokenParse.parse(token, securityKey);
  }

  public static IAppContext parseAppContextAccessToken(String accessToken, String securityKey) {

    Validate.notBlank(accessToken, "The token string must not be blank.");
    Validate.notBlank(securityKey, "The securityKey must not be blank.");

    AppContextAccessTokenParse tokenParse = new AppContextAccessTokenParse();
    return tokenParse.parse(accessToken, securityKey);
  }


}
