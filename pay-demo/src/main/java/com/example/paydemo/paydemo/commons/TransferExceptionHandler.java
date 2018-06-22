package com.example.paydemo.paydemo.commons;

import com.example.paydemo.paydemo.entity.bean.TransferResponseEntity;
import com.example.paydemo.paydemo.entity.exceptions.BadRequestException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by MaXuewen on 2018/6/22.
 */
@Slf4j
@ControllerAdvice
public class TransferExceptionHandler {

  /**
   * BadRequestException
   */
  @ExceptionHandler(BadRequestException.class)
  public TransferResponseEntity badRequest(BadRequestException ex) {
    log.error("BadRequestException:{}", ex);
    return new TransferResponseEntity("400", ex.getMessage());
  }

  /**
   * 内部错误
   */
  @ExceptionHandler(Exception.class)
  public TransferResponseEntity internalError(Exception ex){
    log.error("内部错误:{}", ex);
    return new TransferResponseEntity("500", ex.getMessage());
  }
}
