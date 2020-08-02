package com.ws.ldy.config.error;

import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.EnumUtil;
import com.ws.ldy.others.base.controller.BaseController;
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
@SuppressWarnings("all")
@Controller
@Slf4j
public class ErrorController extends BaseController {

    /**
     * 方式2：系统错误返回json
     *
     * @param code 对应错误码，ErrorPageConfig配置
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @date 2019/11/18 21:14
     */
    @RequestMapping(value = "/error/{code}")
    @ResponseBody
    public void error(@PathVariable int code) {
        // 根据状态值查询对应的枚举
        RType errorConstantEnum = EnumUtil.getByCode(Integer.valueOf(code), RType.class);
        // 返回对应提示
        if (errorConstantEnum != null) {
            throw new ErrorException(errorConstantEnum);
        }
        //返回500错误
        throw new ErrorException(RType.SYS_ERROR_CODE_500);
    }
}
