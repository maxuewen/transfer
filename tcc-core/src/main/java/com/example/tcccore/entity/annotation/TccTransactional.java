package com.example.tcccore.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于开启一个tcc事务
 *
 * Created by MaXuewen on 2018/6/21.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TccTransactional {

  /**
   * 提交事务的方法名
   * 需要保证和@TccTransactional 所在的类是同一个类
   * @return
   */
  String confirmMethod() default "";

  /**
   * 回滚事务的方法名
   * 需要保证和@TccTransactional 所在的类是同一个类
   * @return
   */
  String cancelMethod() default "";
}
