package com.example.tcccore.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用在Feign调用的远程接口里面的方法上，表明当前调用的是一个tcc事务
 * Created by MaXuewen on 2018/6/21.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TccRpc {
}
