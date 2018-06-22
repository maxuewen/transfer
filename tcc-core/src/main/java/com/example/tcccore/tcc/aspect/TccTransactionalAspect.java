package com.example.tcccore.tcc.aspect;

import static com.example.tcccore.entity.constant.CommonsString.REQUEST_HEADER_KEY;

import com.example.tcccore.entity.bean.RequestHeaderContent;
import com.example.tcccore.tcc.core.PartnerExecutorImpl;
import com.example.tcccore.tcc.core.TccTransactionalManage;
import com.example.tcccore.utils.JsonUtil;
import com.example.tcccore.utils.RequestHolderUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StringUtils;

/**
 * @TccTransactional 注解的从处理器
 *
 * Created by MaXuewen on 2018/6/21.
 */
@Aspect
public class TccTransactionalAspect {

  @Pointcut("@annotation(com.example.tcccore.entity.annotation.TccTransactional)")
  public void pointcut() { }

  /**
   * 交给事务管理器处理，封装所有的事务操作
   * @param proceedingJoinPoint
   * @return 方法的执行结果
   */
  @Around("pointcut()")
  public Object tccTransactionalExecutor(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

    //查看请求头中的信息
    String header = RequestHolderUtils.getRequest().getHeader(REQUEST_HEADER_KEY);
    RequestHeaderContent headerContent = null;
    if (!StringUtils.isEmpty(header)) {
      headerContent = JsonUtil.loads(header, RequestHeaderContent.class);
    }

    if (headerContent == null) {
      //请求头没有内容，代表这是一个事务的发起者
      return TccTransactionalManage.start(proceedingJoinPoint);
    } else {
      //代表这是一个事务参与者
      PartnerExecutorImpl partnerExecutor = new PartnerExecutorImpl();
      switch (headerContent.getRequestType()) {
        case TRY: return partnerExecutor.normal(proceedingJoinPoint, headerContent);
        case CANCEL: partnerExecutor.cancel(proceedingJoinPoint, headerContent); break;
        case CONFIRM: partnerExecutor.confirm(proceedingJoinPoint, headerContent); break;
      }
      return null;
    }
  }

}
