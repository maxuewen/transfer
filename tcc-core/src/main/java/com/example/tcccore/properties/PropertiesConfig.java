package com.example.tcccore.properties;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by MaXuewen on 2017/11/13.
 */
@Data
@Component
@ConfigurationProperties
public class PropertiesConfig {

  @Value("${common.sequence.workerId}")
  private long workId;

  @Value("${common.sequence.datacenterId}")
  private long dataCenterId;

}
