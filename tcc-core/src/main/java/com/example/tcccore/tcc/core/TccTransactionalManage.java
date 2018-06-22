package com.example.tcccore.tcc.core;

import com.example.tcccore.config.SpringContextUtil;
import com.example.tcccore.entity.bean.TccPartnerEntity;
import com.example.tcccore.entity.bean.TccPartnerInvocationEntity;
import com.example.tcccore.entity.bean.TccTransactionalContext;
import com.example.tcccore.entity.bean.TccTransactionalEntity;
import com.example.tcccore.entity.enums.TransactionStatus;
import com.example.tcccore.entity.exceptions.TccCancelException;
import com.example.tcccore.entity.exceptions.TccConfirmException;
import com.example.tcccore.entity.exceptions.TccTryException;
import com.example.tcccore.utils.sequence.IdentifyGenerated;

import org.apache.commons.lang.reflect.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 事务管理器
 *
 * Created by MaXuewen on 2018/6/21.
 */
public class TccTransactionalManage {

  /**
   * 保存当前事务的上下文
   */
  private static ThreadLocal<TccTransactionalContext> tccTransactionContext = new ThreadLocal<>();
  /**
   * 保存当前正在执行的参与者的信息
   */
  private static ThreadLocal<TccPartnerEntity> currentPartner = new ThreadLocal<>();

  /**
   * 开始一个新的tcc事务，总是发生在发起端
   * @param proceedingJoinPoint tcc注解切点
   * @return
   * @throws Throwable
   */
  public static Object start(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    //创建事务
    TccTransactionalEntity transactionalEntity = new TccTransactionalEntity();
    transactionalEntity.setStartTime(new Date());
    transactionalEntity.setTransactionalId(IdentifyGenerated.INSTANCE.getNextId());
    transactionalEntity.setStatus(TransactionStatus.TRYING);
    //todo 日志，trying
    TccTransactionalContext tccTransactionalContext = new TccTransactionalContext();
    tccTransactionalContext.setTransactionalEntity(transactionalEntity);
    //事务保存在ThreadLocal中，参与者在rpc拦截器中添加
    tccTransactionContext.set(tccTransactionalContext);
    Object proceed;
    try {
      //开始执行try
      proceed = proceedingJoinPoint.proceed();
      transactionalEntity.setStatus(TransactionStatus.TRIED);
      //todo 日志，tried
    } catch (Throwable e) {
      //try 执行报错，开始执行回滚
      try {
        transactionalEntity.setStatus(TransactionStatus.CANCELED);
        //todo 日志，canceling、canceled
        cancel(tccTransactionalContext.getPartners());
        tccTransactionContext.remove();
        throw new TccTryException("事务cancel异常", e);
      } catch (Throwable cancelException) {
        transactionalEntity.setStatus(TransactionStatus.CANCELED_ERROR);
        //todo 日志，cancel_error
        tccTransactionContext.remove();
        throw new TccCancelException("事务try回滚异常", cancelException);
      }
    }

    //开始confirm
    try {
      //远程调用参与端的提交接口
      //todo 日志，confirming、confirmed
      transactionalEntity.setStatus(TransactionStatus.CONFIRMING);
      confirm(tccTransactionalContext.getPartners());
    } catch (Throwable e) {
      tccTransactionContext.remove();
      throw new TccConfirmException("事务confirm异常", e);
    }
    transactionalEntity.setStatus(TransactionStatus.CONFIRMED);
    tccTransactionContext.remove();
    return proceed;
  }

  /**
   * 回滚事务
   * @param partners
   */
  private static void cancel(List<TccPartnerEntity> partners) {
    partners.forEach(tccPartnerEntity -> CompletableFuture.runAsync(() -> {
      TccPartnerInvocationEntity cancelInfo = tccPartnerEntity.getCancelInfo();
      try {
        MethodUtils.invokeMethod(SpringContextUtil.getBean(cancelInfo.getTargetClass()), cancelInfo.getMethodName(),
            cancelInfo.getArgs(), cancelInfo.getParameterTypes());
      } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }));
  }

  /**
   * 确认提交事务
   * @param partners
   */
  private static void confirm(List<TccPartnerEntity> partners) {
    partners.forEach(tccPartnerEntity -> CompletableFuture.runAsync(() -> {
      TccPartnerInvocationEntity confirmInfo = tccPartnerEntity.getConfirmInfo();
      try {
        MethodUtils.invokeMethod(SpringContextUtil.getBean(confirmInfo.getTargetClass()), confirmInfo.getMethodName(),
            confirmInfo.getArgs(), confirmInfo.getParameterTypes());
      } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }));
  }

  public static TccTransactionalContext getCurrentContext() {
    return tccTransactionContext.get();
  }

  public static void setCurrentPartner(TccPartnerEntity id) {
    currentPartner.set(id);
  }

  public static TccPartnerEntity getCurrentPartner() {
    return currentPartner.get();
  }

  public static void removeCurrentPartner() {
    currentPartner.remove();
  }
}
