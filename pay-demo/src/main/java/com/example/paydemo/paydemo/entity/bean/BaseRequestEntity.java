package com.example.paydemo.paydemo.entity.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

/**
 * 所有客户端请求类的基类
 *
 * Created by MaXuewen on 2018/6/19.
 */
@Data
public class BaseRequestEntity {
  /**
   * 请求唯一Id
   */
  @NotBlank(message = "请求id异常")
  private String requestId;

  /**
   * 请求时间戳
   */
  @Past(message = "时间戳异常")
  private Long timestamp;

  /**
   * 请求关键内容摘要
   */
  @NotBlank(message = "签名异常")
  private String sign;
}
