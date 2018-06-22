package com.example.tcccore.web;

import com.example.tcccore.config.SpringContextUtil;
import com.example.tcccore.entity.bean.TccPartnerEntity;
import com.example.tcccore.entity.bean.TccPartnerInvocationEntity;

import org.apache.commons.lang.reflect.MethodUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by MaXuewen on 2018/6/21.
 */
@RestController
@RequestMapping("/api/tcc/v1")
public class TccController {

  @PostMapping("/cancel")
  public boolean cancel(@RequestBody TccPartnerEntity partnerEntity) {
    //todo: 日志，参与者 Executeing
    TccPartnerInvocationEntity cancelInfo = partnerEntity.getCancelInfo();
    return executor(cancelInfo);
  }

  @PostMapping("/confirm")
  public boolean confirm(@RequestBody TccPartnerEntity partnerEntity) {
    TccPartnerInvocationEntity cancelInfo = partnerEntity.getConfirmInfo();
    return executor(cancelInfo);
  }

  private boolean executor(TccPartnerInvocationEntity info) {
    Object bean = SpringContextUtil.getBean(info.getTargetClass());
    try {
      MethodUtils.invokeMethod(bean, info.getMethodName(), info.getArgs(), info.getParameterTypes());
      //todo: 日志，参与者 Executed
      return true;
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {

      e.printStackTrace();
      return false;
    }
  }

}
