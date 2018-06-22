package com.example.eurekademo.eurekademo.entity.bean;

import lombok.Data;

/**
 * Created by MaXuewen on 2018/6/22.
 */
@Data
public class TransferResponseEntity extends BaseResponseEntity {

  /**
   * 生成的订单号
   */
  private String orderId;

  public TransferResponseEntity(String code, String mesagger) {
    super(code, mesagger, System.currentTimeMillis());
  }
}
