package com.example.tcccore.tcc.core;

import com.example.tcccore.config.SpringContextUtil;
import com.example.tcccore.entity.annotation.TccTransactional;
import com.example.tcccore.entity.bean.RequestHeaderContent;

import org.apache.commons.lang.reflect.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 参与者事务执行器
 *
 * Created by MaXuewen on 2018/6/22.
 */
public class PartnerExecutorImpl implements PartnerExecutor {

  /**
   * 执行try
   * @param proceedingJoinPoint
   * @return
   */
  @Override
  public Object normal(ProceedingJoinPoint proceedingJoinPoint, RequestHeaderContent headerContent) throws Throwable {
    //todo: 日志记录，根据日志的状态，做幂等
    return proceedingJoinPoint.proceed();
  }

  /**
   * 执行cancel
   */
  @Override
  public void cancel(ProceedingJoinPoint proceedingJoinPoint, RequestHeaderContent headerContent) throws
      NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    //todo: 日志记录，根据日志的状态，做幂等
    Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
    Class<?>[] parameterTypes = method.getParameterTypes();
    TccTransactional annotation = method.getAnnotation(TccTransactional.class);
    String cancelMethod = annotation.cancelMethod();

    if (!StringUtils.isEmpty(cancelMethod)) {
      Object[] args = proceedingJoinPoint.getArgs();
      Class<?> aClass = proceedingJoinPoint.getTarget().getClass();
      Object bean = SpringContextUtil.getBean(aClass);
      MethodUtils.invokeMethod(bean, cancelMethod, args, parameterTypes);
    }
  }

  /**
   * 执行confirm
   */
  @Override
  public void confirm(ProceedingJoinPoint proceedingJoinPoint, RequestHeaderContent headerContent) throws
      NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    //todo: 日志记录，根据日志的状态，做幂等
    Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
    TccTransactional annotation = method.getAnnotation(TccTransactional.class);
    String confirmMethod = annotation.confirmMethod();

    if (!StringUtils.isEmpty(confirmMethod)) {
      Class<?> aClass = proceedingJoinPoint.getTarget().getClass();
      MethodUtils.invokeMethod(SpringContextUtil.getBean(aClass), confirmMethod, proceedingJoinPoint.getArgs(),
          method.getParameterTypes());
    }
  }
}
