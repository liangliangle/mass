package com.mass.common.exception;

/**
 * @author 李亮亮
 */
public class ValidationException extends BaseException {
  private static final long serialVersionUID = 1L;

  public ValidationException() {
    // TODO Auto-generated constructor stub
  }

  public ValidationException(String message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  public ValidationException(Throwable cause) {
    super(cause);
    // TODO Auto-generated constructor stub
  }

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  public ValidationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    // TODO Auto-generated constructor stub
  }

}
