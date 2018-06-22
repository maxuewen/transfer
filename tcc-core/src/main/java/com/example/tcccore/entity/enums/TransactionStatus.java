package com.example.tcccore.entity.enums;

/**
 * 事务的状态
 * Created by MaXuewen on 2018/6/21.
 */
public enum TransactionStatus {

  /**
   * 正在执行try
   */
  TRYING,
  /**
   * try执行完成
   */
  TRIED,
  /**
   * 正在提交
   */
  CONFIRMING,
  /**
   * 提交完成
   */
  CONFIRMED,
  /**
   * 正在回滚
   */
  CANCELING,
  /**
   * 回滚完成
   */
  CANCELED,
  /**
   * 回滚异常
   */
  CANCELED_ERROR,
  /**
   * 事务结束
   */
  FINISH
}
