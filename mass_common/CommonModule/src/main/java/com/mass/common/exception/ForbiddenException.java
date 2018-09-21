package com.mass.common.exception;

/**
 * 禁止访问的异常.
 * @author 李亮亮
 */
public class ForbiddenException extends BaseException {

  private static final long serialVersionUID = 1L;

  public ForbiddenException() {
    // TODO Auto-generated constructor stub
  }

  public ForbiddenException(String message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  public ForbiddenException(Throwable cause) {
    super(cause);
    // TODO Auto-generated constructor stub
  }

  public ForbiddenException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  public ForbiddenException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    // TODO Auto-generated constructor stub
  }

}
