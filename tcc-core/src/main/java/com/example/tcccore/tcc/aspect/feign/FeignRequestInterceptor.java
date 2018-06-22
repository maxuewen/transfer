package com.example.tcccore.tcc.aspect.feign;

import static com.example.tcccore.entity.constant.CommonsString.REQUEST_HEADER_KEY;

import com.example.tcccore.entity.bean.RequestHeaderContent;
import com.example.tcccore.entity.bean.TccPartnerEntity;
import com.example.tcccore.entity.enums.FeignRequestType;
import com.example.tcccore.tcc.core.TccTransactionalManage;
import com.example.tcccore.utils.JsonUtil;
import com.example.tcccore.utils.sequence.IdentifyGenerated;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by MaXuewen on 2018/6/22.
 */
public class FeignRequestInterceptor implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate requestTemplate) {
    //生成参与者
    Long partnerId = IdentifyGenerated.INSTANCE.getNextId();
    TccPartnerEntity partnerEntity = new TccPartnerEntity();
    partnerEntity.setTransactionalId(TccTransactionalManage.getCurrentContext().getTransactionalEntity().getTransactionalId());
    partnerEntity.setPartnerId(partnerId);
    partnerEntity.setHost(requestTemplate.url());
    partnerEntity.setStartTime(new Date());
    //保存当前参与者到ThreadLocal中
    TccTransactionalManage.setCurrentPartner(partnerEntity);
    //根据ThreadLocal中的状态来设置请求头中的内容
    FeignRequestType type = null;
    switch (TccTransactionalManage.getCurrentContext().getTransactionalEntity().getStatus()) {
      case TRYING: type = FeignRequestType.TRY; break;
      case CANCELED: type = FeignRequestType.CANCEL; break;
      case CONFIRMING: type = FeignRequestType.CONFIRM; break;
    }
    //生成请求头信息
    RequestHeaderContent headerContent = new RequestHeaderContent(TccTransactionalManage.getCurrentContext()
        .getTransactionalEntity().getTransactionalId(),partnerId, type);
    requestTemplate.header(REQUEST_HEADER_KEY, JsonUtil.dumps(headerContent));

  }
}
