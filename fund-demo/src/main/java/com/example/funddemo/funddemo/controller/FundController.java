package com.example.funddemo.funddemo.controller;

import com.example.eurekademo.eurekademo.entity.bean.TransferRequestEntity;
import com.example.eurekademo.eurekademo.entity.exceptions.TransferException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MaXuewen on 2018/6/22.
 */
@Slf4j
@RestController
@RequestMapping("/api/fund/v1")
public class FundController {

  @PostMapping("/decrease")
  public void decrease(@RequestBody TransferRequestEntity requestEntity) throws TransferException {
    log.info("收到支付请求，参数为{}", requestEntity);

    //todo: 做相应的数据库操作
  }

  @PostMapping("/increase")
  public void increase(@RequestBody TransferRequestEntity requestEntity) throws TransferException {
    log.info("收到支付请求，参数为{}", requestEntity);

    //todo: 做相应的数据库操作
  }
}
