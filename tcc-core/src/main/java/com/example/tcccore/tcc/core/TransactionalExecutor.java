package com.example.tcccore.tcc.core;

import com.example.tcccore.entity.bean.TccPartnerEntity;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.List;

/**
 * Created by MaXuewen on 2018/6/21.
 */
public interface TransactionalExecutor {

  /**
   * 如果是事务的发起者的话，就开启一个事务
   * 如果是事务参与者，只会更新日志
   * @return 创建的事务实体或者null
   */
  Object start(ProceedingJoinPoint proceedingJoinPoint) throws Throwable;

  /**
   * 回滚事务
   * @return 是否成功
   */
  void cancel(List<TccPartnerEntity> partners);

  /**
   * 提交事务
   * @return 是否成功
   */
  void confirm(List<TccPartnerEntity> partners);
}
