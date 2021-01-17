package com.ws.ldy.client.yw.caipu;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.modules.yw.caipu.model.entity.CpInfo;
import com.ws.ldy.modules.yw.caipu.model.vo.CpInfoVO;
import com.ws.ldy.modules.yw.caipu.service.CpInfoService;
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
 * 菜谱表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-04 18:50:10
 */
@RestController
@RequestMapping("/consumer/caipu/cpInfo")
@Api(value = "CpInfo", tags = "独立功能--菜谱(用户端)")
public class UCpInfoController extends BaseController<CpInfoService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "search", value = "分类/主分类/菜谱标题--模糊查询", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "zid", value = "主分类", required = false, paramType = "query", example = ""),
    })
    public R<IPage<CpInfoVO>> findPage(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String zid
    ) {
        Page<CpInfo> page = baseService.page(this.getPage(), new LambdaQueryWrapper<CpInfo>()
                .orderByDesc(CpInfo::getCreateTime)
                .and(StringUtils.isNotBlank(search), i ->
                        i.like(CpInfo::getCid, search)
                                .or().like(CpInfo::getZid, search)
                                .or().like(CpInfo::getTitle, search)
                ).eq(StringUtils.isNotBlank(zid), CpInfo::getZid, zid)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, CpInfoVO.class));
    }


    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    public R<CpInfoVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), CpInfoVO.class));
    }
}
