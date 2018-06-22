package com.example.eurekademo.eurekademo.entity.exceptions;

/**
 * Created by MaXuewen on 2018/6/22.
 */
public class TransferException extends Exception {

  private String code;
  private String message;

  public TransferException(String code, String message) {
    super(message);
    this.code = code;
    this.message = message;
  }

  public TransferException(String code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
    this.message = message;
  }
}
