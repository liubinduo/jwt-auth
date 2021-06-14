package com.v1ok.auth.impl;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.v1ok.auth.IAppContext;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DefaultAppContext implements IAppContext {

  private String appId;

  private String securityKey;

}
