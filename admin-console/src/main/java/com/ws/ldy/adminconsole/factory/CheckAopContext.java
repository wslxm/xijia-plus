package com.ws.ldy.adminconsole.factory;

import com.ws.ldy.adminconsole.template.CheckAopFilter;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import com.ws.ldy.admincore.utils.SpringContextUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
  * TODO  AOP 连接验证方法集中管理处
  * @author 王松
  * @mail  1720696548@qq.com
  * @date  2020/1/13 0013 20:11 
  */
@Component
public class CheckAopContext {


    private static List<CheckAopFilter> checkAopFilters = new ArrayList<>();

    /**
     * TODO  初始化验证规则，按顺序执行
     * @author 王松
     * @mail  1720696548@qq.com
     * @date  2020/1/13 0013 20:14
     * @return void
     */
    public void init(){
        if(checkAopFilters.size() <= 0){
            CheckAopFilter checkAopFilter1 = (CheckAopFilter) SpringContextUtil.getBean("checkSignFilter");        // 验签
            CheckAopFilter checkAopFilter2 = (CheckAopFilter) SpringContextUtil.getBean("xssDefenseFilter");       // xss 攻击处理，url特殊字符
            CheckAopFilter checkAopFilter3 = (CheckAopFilter) SpringContextUtil.getBean("roleAuthorityFilter");    // url 权限
            CheckAopFilter checkAopFilter4 = (CheckAopFilter) SpringContextUtil.getBean("antiTheftChainFilter");   // 防盗链处理
            CheckAopFilter checkAopFilter5 = (CheckAopFilter) SpringContextUtil.getBean("accessRecordLogFilter");  // 执行业务逻辑,记录日志
            //执行顺序此次指定
            checkAopFilters.add(checkAopFilter1);
            checkAopFilters.add(checkAopFilter2);
            checkAopFilters.add(checkAopFilter3);
            checkAopFilters.add(checkAopFilter4);
            checkAopFilters.add(checkAopFilter5);
        }
    }


    /**
     * TODO    开始验证
     * @author 王松
     * @mail  1720696548@qq.com
     * @param jp
     * @param request
     * @param args
     * @date  2020/1/13 0013 20:14
     * @return com.ws.ldy.admincore.controller.vo.ResponseData
     */
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
        // 所有验证成功
        return responseData;
    }
}
