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
import com.ws.ldy.manage.xj.model.dto.XjAdminConfigDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminConfig;
import com.ws.ldy.manage.xj.model.query.XjAdminConfigQuery;
import com.ws.ldy.manage.xj.model.vo.XjAdminConfigVO;
import com.ws.ldy.manage.xj.service.XjAdminConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/config")
@Api(value = "XjAdminConfigController", tags = "base-plus--全局配置")
public class XjAdminConfigController extends BaseController<XjAdminConfigService> {

    @GetMapping(value = "/list")
    @ApiOperation(value = "分页查询")
    public R<IPage<XjAdminConfigVO>> list(@ModelAttribute @Validated XjAdminConfigQuery query) {
        LambdaQueryWrapper<XjAdminConfig> queryWrapper = new LambdaQueryWrapper<XjAdminConfig>()
                .orderByDesc(XjAdminConfig::getCreateTime)
                .eq(StringUtils.isNotBlank(query.getCode()), XjAdminConfig::getCode, query.getCode())
                .like(StringUtils.isNotBlank(query.getName()), XjAdminConfig::getName, query.getName());
        if (query.getIsExport()) {
            // excel
            ExcelUtil.exportExcelDownload(BeanDtoVoUtil.listVo(baseService.list(queryWrapper), XjAdminConfigVO.class), response);
            return null;
        } else if (query.getCurrent() <= 0) {
            // list
            IPage<XjAdminConfigVO> page = new Page<>();
            page = page.setRecords(BeanDtoVoUtil.listVo(baseService.list(queryWrapper), XjAdminConfigVO.class));
            return R.successFind(page);
        } else {
            // page
            IPage<XjAdminConfigVO> page = BeanDtoVoUtil.pageVo(baseService.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminConfigVO.class);
            return R.successFind(page);
        }
    }

    @RequestMapping(value = "findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "CODE查询")
    public R<XjAdminConfigVO> findByCode(@RequestParam String code) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.findByCode(code), XjAdminConfigVO.class));
    }

    @PostMapping
    @ApiOperation(value = "添加")
    public R<Boolean> insert(@RequestBody @Validated XjAdminConfigDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated XjAdminConfigDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.removeById(id));
    }
}
