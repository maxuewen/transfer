package com.example.eurekademo.eurekademo.entity.bean;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * 用于转账
 *
 * Created by MaXuewen on 2018/6/19.
 */
@Data
public class TransferRequestEntity extends BaseRequestEntity {

  /**
   * 收款人姓名
   */
  @Length(min = 2, max = 20, message = "收款人姓名异常")
  String payeeName;

  /**
   * 收款人账号
   */
  @Length(min = 1, max = 20, message = "收款人账号异常")
  String payeeAccount;

  /**
   * 转账金额
   */
  @Pattern(regexp = "^(-)?(([1-9]\\d*)|([0]))(\\.(\\d){1,2})?$", message = "金额不正确")
  String amount;

  /**
   * 付款人姓名
   */
  @Length(min = 2, max = 20, message = "付款人姓名异常")
  String payerName;

  /**
   * 付款人账号
   */
  @Length(min = 1, max = 20, message = "付款人账号异常")
  String payerAccount;

  /**
   * 备注
   */
  @Length(max = 1000, message = "备注字段异常")
  String remark;
}
