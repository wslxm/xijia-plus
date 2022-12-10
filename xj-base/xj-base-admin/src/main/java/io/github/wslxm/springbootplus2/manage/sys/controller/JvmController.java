package io.github.wslxm.springbootplus2.manage.sys.controller;

import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ToolJvmInfoVO;
import io.github.wslxm.springbootplus2.manage.sys.service.ToolServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * base--sys--jvm信息获取
 *
 * @author wangsong
 * @version 1.0.0
 * @email 1720696548@qq.com
 * @date 2022/12/10 0010 14:19
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/sys/jvm")
public class JvmController {


    @Autowired
    private ToolServer toolServer;

    /**
     * 获取系统的jvm信息
     *
     * @return io.github.wslxm.springbootplus2.core.result.Result<io.github.wslxm.springbootplus2.manage.sys.model.vo.ToolJvmInfoVO>
     * @author wangsong
     */
    @GetMapping(value = "/jvmInfo")
    public Result<ToolJvmInfoVO> jvmInfo() {
        return Result.success(toolServer.jvmInfo());
    }
}
