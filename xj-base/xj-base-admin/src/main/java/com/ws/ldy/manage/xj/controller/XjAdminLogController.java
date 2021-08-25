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
import com.ws.ldy.manage.xj.model.dto.XjAdminLogDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminLog;
import com.ws.ldy.manage.xj.model.query.XjAdminLogQuery;
import com.ws.ldy.manage.xj.model.vo.XjAdminLogVO;
import com.ws.ldy.manage.xj.service.XjAdminLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 操作记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-28 20:44:32
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin +"/xj/log")
@Api(value = "XjAdminLogController", tags = "base-plus--操作记录")
public class XjAdminLogController extends BaseController<XjAdminLogService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "分页查询")
    public R<IPage<XjAdminLogVO>> list(@ModelAttribute @Validated XjAdminLogQuery query) {
        LambdaQueryWrapper<XjAdminLog> queryWrapper = new LambdaQueryWrapper<XjAdminLog>()
                .orderByDesc(XjAdminLog::getCreateTime)
                .like(StringUtils.isNotBlank(query.getFullName()), XjAdminLog::getFullName, query.getFullName())
                .like(StringUtils.isNotBlank(query.getUri()), XjAdminLog::getUri, query.getUri())
                .like(StringUtils.isNotBlank(query.getClassDesc()), XjAdminLog::getClassDesc, query.getClassDesc())
                .like(StringUtils.isNotBlank(query.getMethodDesc()), XjAdminLog::getMethodDesc, query.getMethodDesc())
                .eq(query.getState() != null, XjAdminLog::getState, query.getState());
        if (query.getIsExport()) {
            // excel
            ExcelUtil.exportExcelDownload(BeanDtoVoUtil.listVo(baseService.list(queryWrapper), XjAdminLogVO.class), response);
            return null;
        } else if (query.getCurrent() <= 0) {
            // list
            IPage<XjAdminLogVO> page = new Page<>();
            page = page.setRecords(BeanDtoVoUtil.listVo(baseService.list(queryWrapper), XjAdminLogVO.class));
            return R.successFind(page);
        } else {
            // page
            IPage<XjAdminLogVO> page = BeanDtoVoUtil.pageVo(baseService.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminLogVO.class);
            return R.successFind(page);
        }
    }

    @PostMapping
    @ApiOperation(value = "添加")
    public R<Boolean> insert(@RequestBody @Validated XjAdminLogDTO dto) {
        XjAdminLog adminLog = dto.convert(XjAdminLog.class);
        return R.successInsert(baseService.save(adminLog));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated XjAdminLogDTO dto) {
        XjAdminLog entity = dto.convert(XjAdminLog.class);
        entity.setId(id);
        return R.successUpdate(baseService.updateById(entity));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.removeById(id));
    }

}
