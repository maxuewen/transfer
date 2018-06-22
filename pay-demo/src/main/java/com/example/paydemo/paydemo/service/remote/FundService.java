package com.example.paydemo.paydemo.service.remote;

import com.example.paydemo.paydemo.entity.bean.TransferRequestEntity;
import com.example.tcccore.entity.annotation.TccRpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用
 * 执行金额的增加和减少
 *
 * Created by MaXuewen on 2018/6/22.
 */
@FeignClient("fund-demo")
public interface FundService {

  /**
   * 调用远程减少金额接口
   *
   * @param requestEntity 支付请求体
   * @return
   */
  @TccRpc
  @RequestMapping(value = "/decrease",method = RequestMethod.POST)
  void decrease(@RequestParam(value = "requestEntity") TransferRequestEntity requestEntity);

  /**
   * 调用远程增加金额接口
   *
   * @param requestEntity 支付请求体
   * @return
   */
  @TccRpc
  @RequestMapping(value = "/increase",method = RequestMethod.POST)
  void increase(@RequestParam(value = "requestEntity") TransferRequestEntity requestEntity);
}
