package com.ws.ldy.core.config.error;

import com.ws.ldy.core.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  WebServerAutoConfiguration 转发异常转发过来的信息，返回 json参数
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2019/11/19 9:43
 * 方式1、直接跳转到具体错误页面
 * 方式2、返回json格式数据，由前端处理
 */
@Controller
@Slf4j
public class ErrorController {

    /**
     * 方式2：系统错误返回json
     *
     * @param code 对应错误码，ErrorPageConfig配置
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @date 2019/11/18 21:14
     */
    @RequestMapping(value = "/error/{code}")
    @ResponseBody
    public R<String> error(@PathVariable int code) {
        // 根据状态值查询对应的枚举
        // RType errorConstantEnum = EnumUtil.getByCode(Integer.valueOf(code), RType.class);
        if (code == 400) {
            return R.error(code, "400错误");
        } else if (code == 401) {
            return R.error(code, "禁止非法访问");
        } else if (code == 403) {
            return R.error(code, "无权限访问");
        } else if (code == 404) {
            return R.error(code, "找不到该请求");
        } else if (code == 415) {
            return R.error(code, "415错误");
        } else if (code == 500) {
            return R.error(code, "系统错误");
        } else {
            return R.error(code, "未知错误");
        }
    }
}
