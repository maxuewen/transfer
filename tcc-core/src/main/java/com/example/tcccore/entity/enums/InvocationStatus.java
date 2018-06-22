package com.example.tcccore.entity.enums;

/**
 * 一个远程调用的所有状态
 *
 * Created by MaXuewen on 2018/6/21.
 */
public enum InvocationStatus {
  /**
   * 本地开始远程调用
   */
  NEW,
  /**
   * 调用失败，正在重试
   */
  TRYING,
  /**
   * 正在执行远程服务
   */
  EXECUTING,
  /**
   * 远程服务执行完成
   */
  EXECUTED,
  /**
   * 远程服务执行完成
   */
  EXECUTED_ERROR,
  /**
   * 本地收到执行结果
   */
  COMPLETE
}
