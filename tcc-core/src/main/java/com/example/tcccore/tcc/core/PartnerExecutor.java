package com.example.tcccore.tcc.core;

import com.example.tcccore.entity.bean.RequestHeaderContent;
import com.example.tcccore.entity.bean.TccTransactionalEntity;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.InvocationTargetException;

/**
 * 事务处理器
 *
 * Created by MaXuewen on 2018/6/21.
 */
public interface PartnerExecutor {
  /**
   *
   * @return
   */
  Object normal(ProceedingJoinPoint proceedingJoinPoint, RequestHeaderContent headerContent) throws Throwable;

  /**
   * 回滚事务
   * @return 是否成功
   */
  void cancel(ProceedingJoinPoint proceedingJoinPoint, RequestHeaderContent headerContent) throws NoSuchMethodException,
      IllegalAccessException, InvocationTargetException;

  /**
   * 提交事务
   * @return 是否成功
   */
  void confirm(ProceedingJoinPoint proceedingJoinPoint, RequestHeaderContent headerContent) throws NoSuchMethodException,
      IllegalAccessException, InvocationTargetException;
}
