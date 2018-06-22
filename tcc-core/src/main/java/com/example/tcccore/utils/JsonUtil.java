package com.example.tcccore.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Created by MaXuewen on 2017/8/22.
 */
@Slf4j
public class JsonUtil {
  private static ObjectMapper mapper = new ObjectMapper();

  public static ObjectMapper getMapper() {
    return mapper;
  }

  /**
   * json serialization.
   *
   * @param obj 要进行序列化的对象
   * @return 返回序列化之后的字符串
   */
  public static String dumps(final Object obj) {
    try {
      if (null == obj) {
        return null;
      }
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException ex) {
      log.error("failed serialize object to json string: {}", ex);
      return null;
    }
  }

  /**
   * json deserialization.
   *
   * @param jsonStr 序列化字符串
   * @param cls     指定反序列化之后的类信息
   * @return 返回反序列化之后的实例对象
   */
  public static <T> T loads(final String jsonStr, final Class<T> cls) {
    T object = null;
    try {
      if (StringUtils.isEmpty(jsonStr)) {
        return null;
      }
      object = mapper.readValue(jsonStr, cls);
    } catch (IOException ex) {
      log.error("failed deserialize string: {} to Class {}'s instance!", jsonStr, cls.getName());
    }
    return object;
  }

  public static JsonNode jsonNode(final String jsonStr) {
    try {
      if (StringUtils.isEmpty(jsonStr)) {
        return null;
      }
      return mapper.readTree(jsonStr);
    } catch (IOException ex) {
      log.error("failed deserialize string: {} to JsonNode", jsonStr);
    }
    return null;
  }

}
