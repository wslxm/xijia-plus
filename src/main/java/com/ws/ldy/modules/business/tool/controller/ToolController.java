package com.ws.ldy.modules.business.tool.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.business.tool.dto.JavaCodeDTO;
import com.ws.ldy.modules.business.tool.util.JavaCodeRunV2Util;
import com.ws.ldy.modules.business.tool.util.TransformUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BaseConstant.Sys.API + "/tool/")
@Api(value = "AdminBlacklistController", tags = "独立功能--相关工具", consumes = BaseConstant.InterfaceType.PC_BASE)
public class ToolController {

    /**
     * 1、符号转文字工具
     * @author wangsong
     * @param content
     * @param narrow
     * @date 2020/12/11 0011 13:52
     * @return com.ws.ldy.common.result.R<java.lang.String>
     * @version 1.0.0
     */
    @ApiOperation("")
    @RequestMapping(value = "/fhConvert", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容", required = false, paramType = "query"),
            @ApiImplicitParam(name = "narrow", value = "缩小倍数（建议3或4）", required = false, paramType = "query")
    })
    public R<String> convert(@RequestParam String content, @RequestParam Integer narrow, @RequestParam String fill) {
        String transform = null;
        if (StringUtils.isNotBlank(content)) {
            transform = TransformUtil.transform(content, narrow, fill);
        }
        return R.success(transform);
    }


    /**
     * 2、java代码运行器
     * @author wangsong
     * @param dto
     * @date 2020/12/11 0011 13:52
     * @return com.ws.ldy.common.result.R<java.lang.Object>
     * @version 1.0.0
     */
    @RequestMapping(value = "/javaCodeRun", method = RequestMethod.POST)
    @ApiOperation(value = "java代码运行器", notes = "")
    // @XjApiIdempotent
    // @XjIsApiIdempotent
    public R<String> upd(@RequestBody JavaCodeDTO dto) {
        return R.success(JavaCodeRunV2Util.run(dto.getJavaCode()));
    }
}
