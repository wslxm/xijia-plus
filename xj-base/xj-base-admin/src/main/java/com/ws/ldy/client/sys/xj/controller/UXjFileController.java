package com.ws.ldy.client.sys.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.sys.xj.model.entity.XjFile;
import com.ws.ldy.modules.sys.xj.model.vo.XjFileVO;
import com.ws.ldy.modules.sys.xj.service.XjFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 常用工具文件管理
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-17 16:21:46
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/xj/file")
@Api(value = "UXjFileController", tags = "base-plus--常用工具/文件")
public class UXjFileController extends BaseController<XjFileService> {


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "列表查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "文件名(标题)", required = false, paramType = "query", example = ""),
    })
    public R<List<XjFileVO>> findList(@RequestParam(required = false) String name) {
        List<XjFile> list = baseService.list(new LambdaQueryWrapper<XjFile>()
                .orderByDesc(XjFile::getCreateTime)
                .like(StringUtils.isNotBlank(name), XjFile::getName, name)
        );
        return R.successFind(BeanDtoVoUtil.listVo(list, XjFileVO.class));
    }
}
