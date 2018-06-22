package com.example.tcccore.entity.exceptions;

/**
 * tcc事务处理异常
 *
 * Created by MaXuewen on 2017/9/11.
 */
public class TccTransactionalException extends Exception {
  public TccTransactionalException(String message) {
    super(message);
  }

  public TccTransactionalException(String message, Throwable cause) {
    super(message, cause);
  }
}
