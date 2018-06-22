package com.example.tcccore.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by MaXuewen on 2018/6/21.
 */
@Configuration
public class TccRestTemplate {

  @Bean(name = "tccRestTemplate")
  @LoadBalanced
  public RestTemplate customRestTemplate() {

    RestTemplateBuilder builder = new RestTemplateBuilder();

    builder.setReadTimeout(7*1000);
    builder.setConnectTimeout(7*1000);

    return builder.build();
  }

}
