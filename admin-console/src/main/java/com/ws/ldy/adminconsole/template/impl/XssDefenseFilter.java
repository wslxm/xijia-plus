package com.ws.ldy.adminconsole.template.impl;

import com.ws.ldy.adminconsole.template.CheckAopFilter;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringEscapeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * TODO  2、xss 攻击防御, <script> 参数转换 html 格式  +
 * <p>
 * xss 只针对传递的字符串参数进行过滤，对象参数、json 字符串参数不过滤
 * <p>
 * URL特殊字符转码，参考：https://blog.csdn.net/qq_41463655/article/details/103390542
 * <p>
 * 1、api 接口携带 <script> 参数，返回页面输出展示参数
 * 2、如评论回复，评论 <script>alert('sss')</script> ，进入页面就会弹alert框
 * 3、如评论回复，评论 <script>window.location.href='http://www.itmayiedu.com';</script> ，进入页面会直接跳转页面
 * 参数转换前： <script>alert('sss')</script>   参数转换后：  &lt;script&gt;alert('sss')&lt;/script&gt;
 * <p>
 * 特殊字符转码 如url的 + 号
 * URLEncoder.encode 将特殊字符转译
 * URLDecoder.decode 将转译的特殊字符还原
 */
@Component
public class XssDefenseFilter extends CheckAopFilter {

    @Override
    public ResponseData start(ProceedingJoinPoint jp, HttpServletRequest request, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            //是字符串且不是json数据进行转换
            if ((args[i] instanceof String) && !isJson(args[i].toString())) {  //  System.out.println(args[i].toString());
                args[i] = StringEscapeUtils.escapeHtml(URLDecoder.decode(args[i].toString()));
                // args[i] = StringEscapeUtils.escapeHtml(args[i].toString());
            }
        }
        return ResponseData.success(0);
    }


    /**
     * TODO  判断string 是否为json
     *
     * @param content
     * @return boolean
     * @author 王松
     * @mail 1720696548@qq.com
     * @date 2020/1/13 0013 22:51
     */
    public static boolean isJson(String content) {
        try {
            JSONArray.fromObject(content);
            //JSONObject.fromObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
