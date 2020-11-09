package com.ws.ldy.others.generatecode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.others.generatecode.jdbc.JDBCPool;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ws.ldy.enums.BaseConstant;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import com.ws.ldy.config.error.ErrorException;

import com.ws.ldy.others.generatecode.model.entity.XjDatasource;
import com.ws.ldy.others.generatecode.model.vo.XjDatasourceVO;
import com.ws.ldy.others.generatecode.model.dto.XjDatasourceDTO;
import com.ws.ldy.others.generatecode.service.XjDatasourceService;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.others.base.controller.BaseController;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;


/**
 * 代码生成数据源维护表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-04 20:11:08
 */
@RestController
@RequestMapping(BaseConstant.Sys.URI_PREFIX + "/xj/xjDatasource")
@Api(value = "XjDatasourceController", tags = "代码生成数据源维护表", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class XjDatasourceController extends BaseController<XjDatasourceService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "dbTitle", value = "数据库标题", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "dbName", value = "数据库名", required = false, paramType = "query", example = ""),

    })
    public R<IPage<XjDatasourceVO>> findPage(
            @RequestParam(required = false) String dbTitle,
            @RequestParam(required = false) String dbName
    ) {
        Page<XjDatasource> page = baseService.page(this.getPage(), new LambdaQueryWrapper<XjDatasource>()
                .orderByDesc(XjDatasource::getCreateTime)
                .like(StringUtils.isNotBlank(dbTitle), XjDatasource::getDbTitle, dbTitle)
                .like(StringUtils.isNotBlank(dbName), XjDatasource::getDbName, dbName)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, XjDatasourceVO.class));
    }

    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "列表查询", notes = "")
    public R<List<XjDatasourceVO>> findList(
            @RequestParam(required = false) String dbName
    ) {
        List<XjDatasource> list = baseService.list(new LambdaQueryWrapper<XjDatasource>()
                .orderByDesc(XjDatasource::getCreateTime)
                .eq(StringUtils.isNotBlank(dbName), XjDatasource::getDbName, dbName)
        );
        return R.successFind(BeanDtoVoUtil.listVo(list, XjDatasourceVO.class));
    }


    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    public R<XjDatasourceVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), XjDatasourceVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated XjDatasourceDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        XjDatasource xjDatasource = dto.convert(XjDatasource.class);
        return R.successInsert(baseService.save(xjDatasource));
    }


    @RequestMapping(value = "/dataSourceTest", method = RequestMethod.POST)
    @ApiOperation("数据源连接测试")
    public R<Boolean> dataSourceTest(@RequestBody @Validated XjDatasourceDTO dto) {
        // 主要没报错, 表示连接成功
        JDBCPool.getConn(dto.getDbUrl(), dto.getDbUsername(), dto.getDbPassword());
        return R.success(true);
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated XjDatasourceDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(XjDatasource.class)));
    }


    @RequestMapping(value = "/updPwd", method = RequestMethod.PUT)
    @ApiOperation(value = "修改/重置密码", notes = "")
    public R<Boolean> updDbPwd(@RequestParam String id, @RequestParam String password) {
        return R.successUpdate(baseService.update(new LambdaUpdateWrapper<XjDatasource>()
                .set(XjDatasource::getDbPassword, password)
                .eq(XjDatasource::getId, id))
        );
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量ID删除", notes = "")
    public R<Boolean> delByIds(@RequestParam String[] ids) {
        return R.successDelete(baseService.removeByIds(Arrays.asList(ids)));
    }
}
