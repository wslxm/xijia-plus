package com.ws.ldy.adminconsole.template.impl;

import com.ws.ldy.adminconsole.template.CheckAopFilter;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * TODO  4、防盗链处理
 * <p>
 * “如果是直接在浏览器里输入有referer的页面,返回是null,也就是说referer只有从别的页面点击连接来到这页的才会有内容。
 * referer为NULL就是手工,非null就是连接过来的。刷新的时候,会检查服务端是否会有更新,
 * 没有的话,则使用本机的缓存,也就是说,你刷新时得到的响应依然是 前一次...”
 */
@Component
public class AntiTheftChainFilter extends CheckAopFilter {

    @Override
    public ResponseData start(ProceedingJoinPoint jp, HttpServletRequest request, Object[] args) {
        //获得url 来源,request.getServerName()，相当于当前服务器来源，ip 或 请求地址
        String referer = request.getHeader("referer");
        if (referer == null) {
            //允许url直接访问
            return ResponseData.success(0);
        }
        if (!referer.contains(request.getServerName())) {
            return ResponseData.error("403", "切勿非法盗用资源");
        }
        //System.out.println("refer is" + "" + referer);
        return ResponseData.success(0);
    }
}