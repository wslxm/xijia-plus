package com.ws.ldy.manage.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.cache.CacheKey;
import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.core.cache.CacheUtil;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.core.utils.excel.ExcelUtil;
import com.ws.ldy.manage.xj.model.dto.XjAdminBlacklistDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminBlacklist;
import com.ws.ldy.manage.xj.model.query.XjAdminBlacklistQuery;
import com.ws.ldy.manage.xj.model.vo.XjAdminBlacklistVO;
import com.ws.ldy.manage.xj.service.XjAdminBlacklistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 黑名单
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-27 22:44:49
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/blacklist")
@Api(value = "XjAdminBlacklistController", tags = "base-plus--黑名单")
public class XjAdminBlacklistController extends BaseController<XjAdminBlacklistService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public R<IPage<XjAdminBlacklistVO>> list(@ModelAttribute @Validated XjAdminBlacklistQuery query) {
        LambdaQueryWrapper<XjAdminBlacklist> queryWrapper = new LambdaQueryWrapper<XjAdminBlacklist>()
                .orderByDesc(XjAdminBlacklist::getCreateTime)
                .eq(query.getType() != null, XjAdminBlacklist::getType, query.getType()
                );
        if (query.getIsExport()) {
            // excel
            ExcelUtil.exportExcelDownload(BeanDtoVoUtil.listVo(baseService.list(queryWrapper), XjAdminBlacklistVO.class), response);
            return null;
        } else if (query.getCurrent() <= 0) {
            // list
            IPage<XjAdminBlacklistVO> page = new Page<>();
            page = page.setRecords(BeanDtoVoUtil.listVo(baseService.list(queryWrapper), XjAdminBlacklistVO.class));
            return R.successFind(page);
        } else {
            // page
            IPage<XjAdminBlacklistVO> page = BeanDtoVoUtil.pageVo(baseService.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminBlacklistVO.class);
            return R.successFind(page);
        }
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public R<Boolean> insert(@RequestBody @Validated XjAdminBlacklistDTO dto) {
        XjAdminBlacklist adminBlacklist = dto.convert(XjAdminBlacklist.class);
        boolean result = baseService.save(adminBlacklist);
        // 置空缓存
        CacheUtil.del(CacheKey.BLACK_LIST.getKey());
        return R.successInsert(result);
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated XjAdminBlacklistDTO dto) {
        XjAdminBlacklist entity = dto.convert(XjAdminBlacklist.class);
        entity.setId(id);
        boolean result = baseService.updateById(entity);
        // 置空缓存
        CacheUtil.del(CacheKey.BLACK_LIST.getKey());
        return R.successUpdate(result);
    }


//    @RequestMapping(value = "/updDisable", method = RequestMethod.PUT)
//    @ApiOperation(value = "禁用/启用", notes = "必须传递ID")
//    public R<Boolean> updDisable(@RequestParam String id, @RequestParam String disable) {
//        boolean result = baseService.update(new LambdaUpdateWrapper<XjAdminBlacklist>()
//                .set(XjAdminBlacklist::getDisable, disable)
//                .eq(XjAdminBlacklist::getId, id));
//        // 置空缓存
//        CacheUtil.del(CacheKey.BLACK_LIST.getKey());
//        return R.successUpdate(result);
//    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@PathVariable String id) {
        boolean result = baseService.removeById(id);
        // 置空缓存
        CacheUtil.del(CacheKey.BLACK_LIST.getKey());
        return R.successDelete(result);
    }
}
