package com.example.tcccore.entity.bean;

import com.example.tcccore.entity.enums.FeignRequestType;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 事务发起者调用参与者时，请求头中带的内容的实体
 *
 * Created by MaXuewen on 2018/6/21.
 */
@Data
@AllArgsConstructor
public class RequestHeaderContent {
  private Long transactionalId;
  private Long partnerId;
  private FeignRequestType requestType;
}
