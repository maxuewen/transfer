package com.example.tcccore.entity.constant;

/**
 * Created by MaXuewen on 2018/6/21.
 */
public interface CommonsString {

  /**
   * 请求远程参与者时请求头中的key
   */
  String REQUEST_HEADER_KEY = "REQUEST_HEADER_KEY";

  /**
   * 参与端回滚接口
   */
  String CANCEL_REQUEST_URL = "/api/tcc/v1/cancel";

  /**
   * 参与端提交接口
   */
  String COMFIRM_REQUEST_URL = "/api/tcc/v1/confirm";
}
