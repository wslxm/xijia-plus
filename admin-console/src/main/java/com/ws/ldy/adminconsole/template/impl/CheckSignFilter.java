package com.ws.ldy.adminconsole.template.impl;

import com.ws.ldy.adminconsole.template.CheckAopFilter;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import com.ws.ldy.admincore.utils.SignUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
  * TODO   1、验签
  * @author 王松
  * @mail  1720696548@qq.com
  * @date  2020/1/13 0013 20:42
  */
@Component
public class CheckSignFilter extends CheckAopFilter {
    /**
     * 只对url包含path参数的接口进行验签
     */
    private  final static String  path = "/public";

    @Override
    public ResponseData start(ProceedingJoinPoint jp, HttpServletRequest request, Object[] args) {
        // 只对存在 /public 的开放接口进行验签，不存在直接返回true 放行
        String servletPath = request.getServletPath();
        if(!servletPath.contains(path)) {    // = if(servletPath.indexOf(path) == -1 )
            return ResponseData.success(0);
        }
        // 验签
        Map<String, String> verifyMap = SignUtil.toVerifyMap(request.getParameterMap(), false);
        if (!SignUtil.verify(verifyMap)) {
            return ResponseData.error("403", "验签失败");
        }
        return ResponseData.success(0);
    }
}
