package com.example.tcccore.config;

import static com.example.tcccore.entity.constant.CommonsString.REQUEST_HEADER_KEY;

import com.example.tcccore.entity.bean.RequestHeaderContent;
import com.example.tcccore.entity.bean.TccPartnerEntity;
import com.example.tcccore.tcc.aspect.feign.FeignInvocationHandler;
import com.example.tcccore.tcc.aspect.feign.FeignRequestInterceptor;
import com.example.tcccore.tcc.core.TccTransactionalManage;
import com.example.tcccore.utils.JsonUtil;
import com.example.tcccore.utils.sequence.IdentifyGenerated;

import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Retryer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Date;

/**
 * feign 配置类
 *
 * Created by MaXuewen on 2018/6/21.
 */
@Configuration
public class FeignConfig {

  @Bean
  public Feign.Builder templateBuilder() {
    return Feign.builder()
        .options(new Request.Options(7 * 1000, 7 * 1000))
        .logger(new Logger.JavaLogger())
        .logLevel(Logger.Level.HEADERS)
        .retryer(Retryer.NEVER_RETRY)
        //添加请求拦截器
        .requestInterceptor(new FeignRequestInterceptor())
        //执行对feign请求的拦截
        .invocationHandlerFactory((target, map) -> new FeignInvocationHandler(map));
  }
}
