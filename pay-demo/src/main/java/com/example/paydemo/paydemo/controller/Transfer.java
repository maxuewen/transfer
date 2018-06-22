package com.example.paydemo.paydemo.controller;

import com.example.paydemo.paydemo.entity.bean.TransferRequestEntity;
import com.example.paydemo.paydemo.entity.bean.TransferResponseEntity;
import com.example.paydemo.paydemo.entity.exceptions.TransferException;
import com.example.paydemo.paydemo.service.TransferService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MaXuewen on 2018/6/22.
 */
@Slf4j
@RestController
@RequestMapping("/api/transfer/v1")
public class Transfer {

  @Autowired
  TransferService transferService;

  @PostMapping("/transfer")
  public TransferResponseEntity transfer(@Validated @RequestBody TransferRequestEntity requestEntity) throws TransferException {
    log.info("收到支付请求，参数为{}", requestEntity);
    transferService.validated(requestEntity);
    return transferService.transfer(requestEntity);
  }
}
