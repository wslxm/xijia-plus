package com.ws.ldy.manage.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.core.utils.excel.ExcelUtil;
import com.ws.ldy.manage.xj.model.dto.XjAdminBannerDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminBanner;
import com.ws.ldy.manage.xj.model.query.XjAdminBannerQuery;
import com.ws.ldy.manage.xj.model.vo.XjAdminBannerVO;
import com.ws.ldy.manage.xj.service.XjAdminBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * banner表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-23 23:14:01
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/banner")
@Api(value = "XjAdminBannerController", tags = "base-plus--banner")
public class XjAdminBannerController extends BaseController<XjAdminBannerService> {

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表/分页查询")
    public R<IPage<XjAdminBannerVO>> list(@ModelAttribute @Validated XjAdminBannerQuery query) {
        LambdaQueryWrapper<XjAdminBanner> queryWrapper = new LambdaQueryWrapper<XjAdminBanner>()
                .orderByAsc(XjAdminBanner::getPosition)
                .orderByAsc(XjAdminBanner::getSort)
                .orderByDesc(XjAdminBanner::getCreateTime)
                .eq(StringUtils.isNotBlank(query.getName()), XjAdminBanner::getName, query.getName());
        if (query.getIsExport()) {
            // excel
            ExcelUtil.exportExcelDownload(BeanDtoVoUtil.listVo(baseService.list(queryWrapper), XjAdminBannerVO.class), response);
        } else if (query.getCurrent() > 0) {
            // list
            IPage<XjAdminBannerVO> page = new Page<>();
            page = page.setRecords(BeanDtoVoUtil.listVo(baseService.list(queryWrapper), XjAdminBannerVO.class));
            return R.successFind(page);
        } else {
            // page
            IPage<XjAdminBannerVO> page = BeanDtoVoUtil.pageVo(baseService.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminBannerVO.class);
            return R.successFind(page);
        }
        return null;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "ID查询")
    public R<XjAdminBannerVO> findId(@PathVariable String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), XjAdminBannerVO.class));
    }


    @PostMapping
    @ApiOperation(value = "添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated XjAdminBannerDTO dto) {
        XjAdminBanner adminBanner = dto.convert(XjAdminBanner.class);
        return R.successInsert(baseService.save(adminBanner));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated XjAdminBannerDTO dto) {
        XjAdminBanner entity = dto.convert(XjAdminBanner.class);
        entity.setId(id);
        return R.successUpdate(baseService.updateById(entity));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.removeById(id));
    }


//    @DeleteMapping(value = "/delByIds")
//    @ApiOperation(value = "批量ID删除")
//    public R<Boolean> delByIds(@RequestBody List<String> ids) {
//        return R.successDelete(baseService.removeByIds(ids));
//    }
}
