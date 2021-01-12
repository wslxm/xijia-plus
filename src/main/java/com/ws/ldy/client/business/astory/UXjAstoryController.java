package com.ws.ldy.client.business.astory;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.business.astory.model.entity.XjAstory;
import com.ws.ldy.modules.business.astory.model.vo.XjAstoryVO;
import com.ws.ldy.modules.business.astory.service.XjAstoryService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 段子表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-20 22:04:11
 */
@RestController
@RequestMapping(BaseConstant.Sys.API + "/consumer/astory/xjAstory")
@Api(value ="UXjAstoryController" ,tags = "独立功能--段子(用户端)", consumes = BaseConstant.InterfaceType.PC_USER)
public class UXjAstoryController extends BaseController<XjAstoryService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes= "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query",example = "20"),
            @ApiImplicitParam(name = "title", value = "标题", required = false, paramType = "query",example = ""),
           @ApiImplicitParam(name = "typeid", value = "类别id", required = false, paramType = "query",example = ""),

    })
    public R<IPage<XjAstoryVO>> findPage( 
                                            @RequestParam(required = false) String title,
                                            @RequestParam(required = false) Integer typeid
                                              ) {
        Page<XjAstory> page = baseService.page(this.getPage(), new LambdaQueryWrapper<XjAstory>()
                .orderByDesc(XjAstory::getCreateTime)
                 .eq(StringUtils.isNotBlank(title),XjAstory::getTitle,title)
                .eq(typeid != null,XjAstory::getTypeid,typeid)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, XjAstoryVO.class));
    }


}
