package com.mass.common.exception;

/**
 * @author 李亮亮
 * 登陆验证异常
 */
public class AuthorizationException extends BaseException {

  private static final long serialVersionUID = 1L;

  public AuthorizationException() {
    // TODO Auto-generated constructor stub
  }

  public AuthorizationException(String message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  public AuthorizationException(Throwable cause) {
    super(cause);
    // TODO Auto-generated constructor stub
  }

  public AuthorizationException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  public AuthorizationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    // TODO Auto-generated constructor stub
  }

}
