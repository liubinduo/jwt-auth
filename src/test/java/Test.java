import com.v1ok.auth.AuthVerify;
import com.v1ok.auth.Generator;
import com.v1ok.auth.IAppContext;
import com.v1ok.auth.IUserContext;
import com.v1ok.auth.impl.DefaultAppContext;
import com.v1ok.auth.impl.DefaultUserContext;

public class Test {

  public static void main(String[] args) {

    String securityKey = "PMS_2020";
    String userId = "2AF467EAF821000";
    String tenantId = "91210200726041960P";

    {

      IUserContext userContext = DefaultUserContext.builder().userId(userId).tenantId(tenantId).build();

      String token = Generator.token(userContext, securityKey);
      String accessToken = Generator.accessToken(userContext, securityKey);

      boolean b = AuthVerify.verifyAccessToken("O37l7_SFkNufcTojiE7wYH2O_MqjTovaWeaVpAQCE1SerrApprl7Yxblo2iKzKrDWlYFoHETZuobBplBGC1Qn38hNdRscRjp57ooiA-t3Ya1qOH0DEDnGogzu_ymqG0F38e90EEQwQarpXWiMarkzjBjGYKOxrExgGyHkMlRjgqrQv6lvtkqm0g_EdoTewYMbNGk-gHPDiGwjTxziD2M5JalOW67CNuP0rkDMchrdlC-3UYhNntPl4tsVqnzciIu", securityKey);

      System.out.println("userContext:");
      System.out.println("verify: " + b);
      System.out.println("token: " + token);
      System.out.println("accessToken: " + accessToken);

      IUserContext tokenContext = AuthVerify.parseUserContextToken(token, securityKey);
      IUserContext accessTokenContext = AuthVerify
          .parseUserContextAccessToken(accessToken, securityKey);

      System.out.println("token: " + tokenContext);
      System.out.println("accessToken: " + accessTokenContext);
    }
    {
      IAppContext appContext = DefaultAppContext.builder().appId("2B0F9A47F821000").securityKey(securityKey)
          .build();

      String token = Generator.token(appContext, securityKey);
      String accessToken = Generator.accessToken(appContext, securityKey);
      boolean b = AuthVerify.verifyAccessToken(accessToken, securityKey);
      System.out.println("appContext:");
      System.out.println("verify: " + b);
      System.out.println("token: " + token);
      System.out.println("accessToken: " + accessToken);

      IAppContext tokenContext = AuthVerify.parseAppContextToken(token, securityKey);
      IAppContext accessTokenContext = AuthVerify
          .parseAppContextAccessToken(accessToken, securityKey);

      System.out.println("token: " + tokenContext);
      System.out.println("accessToken: " + accessTokenContext);

    }


  }
}
