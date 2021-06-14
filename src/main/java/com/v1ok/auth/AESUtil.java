package com.v1ok.auth;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class AESUtil {

  private static final String AES = "AES";
  private static final String PKCS5_PADDING = "AES/CBC/PKCS5Padding";

  public static String encryptStr(String content, String password) {
    byte[] encrypt = encrypt(content, password);
    return Base64.encodeBase64URLSafeString(encrypt);
  }

  public static String encryptStr(String content, String password, Charset charset) {
    byte[] encrypt = encrypt(content, password, charset);
    return Base64.encodeBase64URLSafeString(encrypt);
  }

  public static byte[] encrypt(String content, String password) {
    return encrypt(content, password, StandardCharsets.UTF_8);
  }

  @SneakyThrows
  public static byte[] encrypt(String content, String password, Charset charset) {

    KeyHandler keyHandler = KeyHandler.create(password);

    byte[] byteContent = content.getBytes(charset);

    Cipher cipher = Cipher.getInstance(PKCS5_PADDING);// 创建密码器
    cipher.init(Cipher.ENCRYPT_MODE, keyHandler.key, keyHandler.iv);// 初始化为加密模式的密码器
    return cipher.doFinal(byteContent);
  }

  public static String decryptStr(String content, String password) {
    return decryptStr(content, password, StandardCharsets.UTF_8);
  }

  @SneakyThrows
  public static String decryptStr(String content, String password, Charset charset) {
    byte[] bytes = Base64.decodeBase64(content);
    byte[] decrypt = decrypt(bytes, password);
    return new String(decrypt, charset);
  }

  @SneakyThrows
  public static byte[] decrypt(byte[] content, String password) {

    KeyHandler keyHandler = KeyHandler.create(password);

    Cipher cipher = Cipher.getInstance(PKCS5_PADDING);// 创建密码器
    cipher.init(Cipher.DECRYPT_MODE, keyHandler.key, keyHandler.iv);// 初始化为解密模式的密码器
    return cipher.doFinal(content);
  }


  @Data
  private static class KeyHandler {

    SecretKeySpec key;
    IvParameterSpec iv;

    public static KeyHandler create(String password) {

      String md5Hex = DigestUtils.md5Hex(password);
      String keyStr = StringUtils.substring(md5Hex, 0, 16);
      String ivValue = StringUtils.substring(md5Hex, 16, 32);

      KeyHandler keyHandler = new KeyHandler();

      keyHandler.key = new SecretKeySpec(keyStr.getBytes(StandardCharsets.US_ASCII), AES);
      keyHandler.iv = new IvParameterSpec(ivValue.getBytes(StandardCharsets.US_ASCII));

      return keyHandler;

    }

  }

}
