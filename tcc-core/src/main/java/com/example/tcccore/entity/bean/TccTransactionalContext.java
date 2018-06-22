package com.example.tcccore.entity.bean;

import lombok.Data;

import java.util.List;

/**
 * 事务执行上下文
 *
 * Created by MaXuewen on 2018/6/21.
 */
@Data
public class TccTransactionalContext {
  /**
   * 当前事务
   */
  private TccTransactionalEntity transactionalEntity;
  /**
   * 事务参与者
   */
  private List<TccPartnerEntity> partners;
}
