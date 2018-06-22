package com.example.tcccore.entity.bean;

import com.example.tcccore.entity.enums.InvocationStatus;
import com.example.tcccore.entity.enums.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * tcc事务的参与者
 *
 * Created by MaXuewen on 2018/6/21.
 */
@Data
public class TccPartnerEntity {

  /**
   * 参与者id
   */
  private Long partnerId;
  /**
   * 所属事务id
   */
  private Long TransactionalId;
  /**
   * 事务确认方法
   */
  private TccPartnerInvocationEntity confirmInfo;
  /**
   * 事务回滚方法
   */
  private TccPartnerInvocationEntity cancelInfo;
  /**
   * 当前参与者的事务状态
   */
  private TransactionStatus transactionStatus;
  /**
   * 接口调用的状态
   */
  private InvocationStatus invocationStatus;

  /**
   * 开始调用时间
   */
  private Date startTime;

  /**
   * 远程事务所在项目的地址，如：1、http://x.x.x.x:8080  2、http://xxx-xxx
   */
  private String host;

}
