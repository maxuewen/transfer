package com.example.paydemo.paydemo.entity.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共响应实体
 * g
 * Created by MaXuewen on 2018/6/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseEntity {

  /**
   * 状态码
   */
  private String code;
  /**
   * 消息
   */
  private String message;

  /**
   * 相应时间戳
   */
  private Long timestamp;
}
