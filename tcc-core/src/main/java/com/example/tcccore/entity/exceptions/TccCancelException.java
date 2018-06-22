package com.example.tcccore.entity.exceptions;

/**
 * tcc事务处理异常
 *
 * Created by MaXuewen on 2017/9/11.
 */
public class TccCancelException extends Exception {
  public TccCancelException(String message) {
    super(message);
  }

  public TccCancelException(String message, Throwable cause) {
    super(message, cause);
  }
}
