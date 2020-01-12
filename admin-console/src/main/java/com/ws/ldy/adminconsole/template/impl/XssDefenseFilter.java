package com.ws.ldy.adminconsole.template.impl;

import com.ws.ldy.adminconsole.template.CheckAopFilter;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import org.apache.commons.lang.StringEscapeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * TODO  2、xss 攻击防御, <script> 参数转换 html 格式  +
 * URL特殊字符转码，参考：https://blog.csdn.net/qq_41463655/article/details/103390542
 * <p>
 * <p>
 * 1、api 接口携带 <script> 参数，返回页面输出展示参数
 * 2、如评论回复，评论 <script>alert('sss')</script> ，进入页面就会弹alert框
 * 3、如评论回复，评论 <script>window.location.href='http://www.itmayiedu.com';</script> ，进入页面会直接跳转页面
 * <p>
 * 参数转换前： <script>alert('sss')</script>   参数转换后：  &lt;script&gt;alert('sss')&lt;/script&gt;
 */
@Component
public class XssDefenseFilter extends CheckAopFilter {

    @Override
    public ResponseData start(ProceedingJoinPoint jp, HttpServletRequest request, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                //  特殊字符转码 如url的 + 号
                //  URLEncoder.encode 将特殊字符转译
                //  URLDecoder.decode 将转译的特殊字符还原
                args[i] = StringEscapeUtils.escapeHtml(URLDecoder.decode(args[i].toString()));
            }
        }
        return ResponseData.success(0);
    }
}
