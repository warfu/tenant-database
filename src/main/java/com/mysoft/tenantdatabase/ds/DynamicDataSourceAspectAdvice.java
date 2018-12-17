package com.mysoft.tenantdatabase.ds;

import lombok.extern.slf4j.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.context.request.*;
import org.apache.shiro.web.util.WebUtils;
import javax.servlet.http.*;


@Slf4j
@Aspect
@Component
@Order(1)
// 请注意：这里order一定要小于tx:annotation-driven的order，即先执行DynamicDataSourceAspectAdvice切面，再执行事务切面，才能获取到最终的数据源
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DynamicDataSourceAspectAdvice {

    @Around("execution(* com.mysoft.tenantdatabase.controller.*.*(..))")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable
    {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();
        String tenantKey = request.getHeader("tenant");
        // 前端必须传入tenant header, 否则返回400
        if( !StringUtils.hasText(tenantKey) ) {
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        log.info("当前租户key:{}", tenantKey);
        DataSourceContextHolder.setDataSourceKey(tenantKey);
        Object result = jp.proceed();
        DataSourceContextHolder.clearDataSourceKey();
        return result;
    }
}
