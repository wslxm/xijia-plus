package com.ws.ldy.config.filter.swagger;


import com.alibaba.fastjson.JSON;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.result.RType;
import com.ws.ldy.manage.xj.model.entity.XjAdminConfig;
import com.ws.ldy.manage.xj.service.XjAdminConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * swagger 文档控制 过滤器 (数据库动态控制)
 * <P>
 *    判断是否可访问swagger文档, 不可访问时 直接返回无权限json格式提示
 * </P>
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/3/29 0029 19:49
 * @version 1.0.0
 */
@Component
@Slf4j
public class SwaggerFilter implements Filter {


    @Autowired
    private XjAdminConfigService xjAdminConfigService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if (uri.indexOf("swagger-ui.html") != -1) {
            // 默认展示,配置为false不展示
            XjAdminConfig xjAdminConfig = xjAdminConfigService.findByCode("is_swagger");
            if (xjAdminConfig != null && "false".equals(xjAdminConfig.getContent())) {
                R<Void> r = R.error(RType.SYS_ERROR_CODE_401);
                servletResponse.setContentType("application/json;charset=utf-8");
                servletResponse.getWriter().write(JSON.toJSONString(r));
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
