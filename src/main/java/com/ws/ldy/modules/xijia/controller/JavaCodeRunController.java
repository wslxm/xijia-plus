package com.ws.ldy.modules.xijia.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.admin.model.dto.AdminAuthorityDTO;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.xijia.entity.dto.JavaCodeDTO;
import com.ws.ldy.modules.xijia.service.JavaCodeRunService;
import com.ws.ldy.modules.xijia.service.impl.JavaCodeRunServiceImpl;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

/**
 * java 代码运行器
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/29 0029 10:43
 * @version 1.0.0
 */
@RestController
@RequestMapping(BaseConstant.Sys.URI_PREFIX + "/xj/javaCodeRun")
@Api(value = "AdminBlacklistController", tags = "java 代码运行器", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class JavaCodeRunController extends BaseController {


    @Autowired
    private JavaCodeRunService javaCodeRunService;

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    @ApiOperation(value = "运行", notes = "")
    public R<Object> upd(@RequestBody JavaCodeDTO dto) {
        return R.success(javaCodeRunService.invoke(dto.getJavaCode()));
    }
}




