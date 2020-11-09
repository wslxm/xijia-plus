package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.admin.model.vo.HelpTreeVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ws.ldy.enums.BaseConstant;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import com.ws.ldy.config.error.ErrorException;
import org.springframework.format.annotation.DateTimeFormat;

import com.ws.ldy.modules.admin.model.entity.AdminHelp;
import com.ws.ldy.modules.admin.model.vo.AdminHelpVO;
import com.ws.ldy.modules.admin.model.dto.AdminHelpDTO;
import com.ws.ldy.modules.admin.service.AdminHelpService;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.others.base.controller.BaseController;

import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 帮助中心表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-20 16:42:28
 */
@RestController
@RequestMapping(BaseConstant.Sys.URI_PREFIX + "/admin/adminHelp")
@Api(value = "AdminHelp", tags = "帮助中心表", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminHelpController extends BaseController<AdminHelpService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "title", value = "标题", required = false, paramType = "query", example = ""),

    })
    public R<IPage<AdminHelpVO>> findPage(@RequestParam(required = false) String title) {
        Page<AdminHelp> page = baseService.page(this.getPage(), new LambdaQueryWrapper<AdminHelp>()
                .orderByAsc(AdminHelp::getHelpVersion)  // 先版本排序
                .orderByAsc(AdminHelp::getCategory)     // 在类别排序
                .orderByAsc(AdminHelp::getSort)         // 在sort排序
                .orderByDesc(AdminHelp::getCreateTime)  // 在时间排序
                .eq(StringUtils.isNotBlank(title), AdminHelp::getTitle, title)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, AdminHelpVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    public R<AdminHelpVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), AdminHelpVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated AdminHelpDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        AdminHelp adminHelp = dto.convert(AdminHelp.class);
        return R.successInsert(baseService.save(adminHelp));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated AdminHelpDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(AdminHelp.class)));
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }



    //====================================================================================================
    //======================================== 帮助中心查看相关 =============================================
    //====================================================================================================

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ApiOperation(value = "查看 -->  ID查询 -- 查看详情调用此方法获取数据,浏览量自动+1", notes = "")
    public R<AdminHelpVO> findById(@RequestParam String id) {
        AdminHelp ybHelp = baseService.getById(id);
        //浏览量+1
        if (ybHelp != null) {
            baseService.update(new LambdaUpdateWrapper<AdminHelp>()
                    .set(AdminHelp::getBrowseNum, ybHelp.getBrowseNum() + 1)
                    .eq(AdminHelp::getId, ybHelp.getId())
            );
        }
        return R.successFind(BeanDtoVoUtil.convert(ybHelp, AdminHelpVO.class));
    }


    @RequestMapping(value = "/findTree", method = RequestMethod.GET)
    @ApiOperation(value = "查看 -->  左侧菜单-- 帮助中心tree菜单", notes = "")
    public R<List<HelpTreeVO>> findTree() {
        return R.successFind(baseService.findTree());
    }

}
