package com.example.tcccore.entity.exceptions;

/**
 * tcc事务处理异常
 *
 * Created by MaXuewen on 2017/9/11.
 */
public class TccTryException extends Exception {
  public TccTryException(String message) {
    super(message);
  }

  public TccTryException(String message, Throwable cause) {
    super(message, cause);
  }
}
