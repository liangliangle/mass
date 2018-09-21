package com.mass.common.exception;

/**
 * 数据访问层exception.
 * 
 * @author 李亮亮
 */
public class DaoException extends BaseException {
  private static final long serialVersionUID = 1L;

  public DaoException() {
    // TODO Auto-generated constructor stub
  }

  public DaoException(String message) {
    super(message);
  }

  public DaoException(Throwable cause) {
    super(cause);
  }

  public DaoException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  public DaoException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    // TODO Auto-generated constructor stub
  }

}
