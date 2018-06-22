package com.example.tcccore.entity.bean;

import com.example.tcccore.entity.enums.TransactionStatus;

import lombok.Data;

import java.util.Date;

/**
 * tcc事务实体
 *
 * Created by MaXuewen on 2018/6/21.
 */
@Data
public class TccTransactionalEntity {
  /**
   * 事务id
   */
  private Long TransactionalId;
  /**
   * 事务的状态
   */
  private TransactionStatus status;

  /**
   * 事务开始时间
   */
  private Date startTime;
  /**
   * 事务更新时间
   */
  private Date updateTime;
}
