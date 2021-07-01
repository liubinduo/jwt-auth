package com.v1ok.auth;

import java.util.List;

/**
 * 用户信息
 */
public interface IUserContext extends IContext {

  String USER_ID = "userId";
  String USER_TENANT_ID = "tenantId";
  String USER_POSITIONS_ID_KEY = "positions";
  String USER_PERMISSIONS_ID_KEY = "permissions";

  /**
   * 岗位
   */
  String getPositions();

  /**
   * 用户ID
   */
  String getUserId();

  /**
   * 租户ID
   */
  String getTenantId();

  List<String> getPermissions();
}
