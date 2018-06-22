package com.example.tcccore.tcc.aspect.feign;

import com.example.tcccore.entity.annotation.TccTransactional;
import com.example.tcccore.entity.bean.TccPartnerEntity;
import com.example.tcccore.entity.bean.TccPartnerInvocationEntity;
import com.example.tcccore.tcc.core.TccTransactionalManage;

import feign.InvocationHandlerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 拦截feign请求，再请求后，记录所有接口信息，用于将来的confirm和cancel时再次调用
 *
 * Created by MaXuewen on 2018/6/21.
 */
public class FeignInvocationHandler implements InvocationHandler {

  /**
   * spring 包装过的方法
   */
  private Map<Method, InvocationHandlerFactory.MethodHandler> map;

  public FeignInvocationHandler(Map<Method, InvocationHandlerFactory.MethodHandler> map) {
    this.map = map;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    TccTransactional tcc = method.getAnnotation(TccTransactional.class);
    if (tcc == null) {
      //非事务feign，直接执行
      return method.invoke(proxy, args);
    }
    //todo 保存请求前的日志

    //开始执行具体的任务
    InvocationHandlerFactory.MethodHandler methodHandler = map.get(method);
    Object result = methodHandler.invoke(args);

    //成成当前参与者
    TccPartnerInvocationEntity cancel = new TccPartnerInvocationEntity(method.getDeclaringClass(), tcc.cancelMethod()
    ,method.getParameterTypes(), args, true);

    TccPartnerInvocationEntity confirm = new TccPartnerInvocationEntity(method.getDeclaringClass(), tcc.confirmMethod()
        ,method.getParameterTypes(), args, true);

    TccPartnerEntity currentPartner = TccTransactionalManage.getCurrentPartner();
    currentPartner.setCancelInfo(cancel);
    currentPartner.setConfirmInfo(confirm);

    //添加当前参与者到上下文中
    TccTransactionalManage.getCurrentContext().getPartners().add(currentPartner);

    //移除ThreadLocal中的数据
    TccTransactionalManage.removeCurrentPartner();

    //todo: 日志记录
    return result;
  }
}
