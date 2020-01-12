package com.ws.ldy.adminconsole.factory;

import com.ws.ldy.adminconsole.template.CheckAopFilter;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import com.ws.ldy.admincore.utils.SpringContextUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class CheckAopContext {

    private static List<CheckAopFilter> checkAopFilters = new ArrayList<>();

    /**
     * 初始化验证规则，按顺序执行
     */
    public void init(){
        if(checkAopFilters.size() <= 0){
            CheckAopFilter checkAopFilter1 = (CheckAopFilter) SpringContextUtil.getBean("checkSignFilter");        // 验签
            CheckAopFilter checkAopFilter2 = (CheckAopFilter) SpringContextUtil.getBean("xssDefenseFilter");       // xss 攻击处理
            CheckAopFilter checkAopFilter3 = (CheckAopFilter) SpringContextUtil.getBean("roleAuthorityFilter");    // url 权限
            CheckAopFilter checkAopFilter4 = (CheckAopFilter) SpringContextUtil.getBean("antiTheftChainFilter");   // 防盗链处理
            CheckAopFilter checkAopFilter5 = (CheckAopFilter) SpringContextUtil.getBean("accessRecordLogFilter");  // 调用接口并打印日志
            checkAopFilters.add(checkAopFilter1);
            checkAopFilters.add(checkAopFilter2);
            checkAopFilters.add(checkAopFilter3);
            checkAopFilters.add(checkAopFilter4);
            checkAopFilters.add(checkAopFilter5);
        }
    }

    // 开始验证
    public ResponseData Check(ProceedingJoinPoint jp, HttpServletRequest request, Object[] args) {
        init();
        ResponseData responseData = null;
        for (CheckAopFilter checkAopFilter : checkAopFilters) {
            responseData = checkAopFilter.start(jp, request, args);
            if (!responseData.getCode().equals("0")) {
                //验证失败直接返回
                return responseData;
            }
        }
        return responseData;
    }
}
