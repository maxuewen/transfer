package com.example.tcccore.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by MaXuewen on 2017/8/23.
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
  private static ApplicationContext applicationContext = null;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (SpringContextUtil.applicationContext == null) {
      SpringContextUtil.applicationContext  = applicationContext;
    }
  }

  public static Object getBean(String name){
    return getApplicationContext().getBean(name);
  }

  public static <T> T getBean(Class<T> clazz){
    return getApplicationContext().getBean(clazz);
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }
}
