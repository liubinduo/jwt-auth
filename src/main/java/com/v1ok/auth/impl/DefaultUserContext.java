package com.v1ok.auth.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.v1ok.auth.IUserContext;

/**
 * Created by liubinduo on 2017/5/15.
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DefaultUserContext implements IUserContext {


  protected String userId;
  protected String positions;
  protected List<String> permissions;
  protected String tenantId;

}
