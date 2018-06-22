package com.example.tcccore.entity.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * tcc事务参与者执行所需数据的封装
 *
 * Created by MaXuewen on 2018/6/21.
 */
@Data
@AllArgsConstructor
public class TccPartnerInvocationEntity {

  /**
   * 接口调用类，即feign类
   */
  private Class targetClass;
  /**
   * 需要被调用的方法名字
   */
  private String methodName;
  /**
   * 被调用方法参数类型
   */
  private Class[] parameterTypes;
  /**
   * 被调用方法参数
   */
  private Object[] args;
  /**
   * true：远程调用的事务
   * false：本地事务
   */
  boolean isRemote = false;
}
