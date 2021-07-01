package com.v1ok.auth;

public interface IContext {

  default IUserContext currentUser() {
    return null;
  }
}
