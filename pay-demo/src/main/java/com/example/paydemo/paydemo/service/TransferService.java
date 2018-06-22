package com.example.paydemo.paydemo.service;

import com.example.paydemo.paydemo.commons.TransferExceptionHandler;
import com.example.paydemo.paydemo.entity.bean.TransferRequestEntity;
import com.example.paydemo.paydemo.entity.bean.TransferResponseEntity;
import com.example.paydemo.paydemo.entity.exceptions.TransferException;
import com.example.paydemo.paydemo.service.remote.FundService;
import com.example.tcccore.entity.annotation.TccTransactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MaXuewen on 2018/6/22.
 */
@Service
public class TransferService {

  @Autowired
  private FundService fundService;

  /**
   * 对请求内容的验证
   *
   * @param requestEntity 转账请求内容
   */
  public void validated(TransferRequestEntity requestEntity) throws TransferException {

    /**
     * todo:
     * 1、用redis判断当前请求id是否已经从在
     * 2、验证签名、时间戳
     * 3、判断转账金额是否合理
     * 4、验证收付款账号是否从在并且状态正常、与姓名是否匹配
     * 5、判断付款账号余额是否充足
     *
     * 否则throw new BadException(xx,xx);
     *
     *  @see TransferExceptionHandler
     */
  }

  /**
   * 开始转账，这是一个tcc事务
   * @param requestEntity
   * @return
   */
  @TccTransactional
  public TransferResponseEntity transfer(TransferRequestEntity requestEntity) {
    fundService.decrease(requestEntity);
    fundService.increase(requestEntity);

    //todo:
    return new TransferResponseEntity("200", "");
  }
}
