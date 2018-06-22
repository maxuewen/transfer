package com.example.tcccore.entity.exceptions;

/**
 * tcc事务处理异常
 *
 * Created by MaXuewen on 2017/9/11.
 */
public class TccConfirmException extends Exception {
  public TccConfirmException(String message) {
    super(message);
  }

  public TccConfirmException(String message, Throwable cause) {
    super(message, cause);
  }
}
