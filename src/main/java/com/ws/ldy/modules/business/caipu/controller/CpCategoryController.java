package com.ws.ldy.modules.business.caipu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.function.LambdaUtils;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.common.utils.IdUtil;
import com.ws.ldy.common.utils.StringUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.business.caipu.model.entity.CpCategory;
import com.ws.ldy.modules.business.caipu.model.entity.CpInfo;
import com.ws.ldy.modules.business.caipu.model.vo.CpCategoryVO;
import com.ws.ldy.modules.business.caipu.service.CpCategoryService;
import com.ws.ldy.modules.business.caipu.service.CpInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 字典表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-10-04 21:44:55
 */
@RestController
@RequestMapping(BaseConstant.Sys.API +"/caipu/cpCategory")
@Api(value ="CpCategory" ,tags = "独立功能--菜谱类别字典表", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class CpCategoryController extends BaseController<CpCategoryService> {

    @Autowired
    private CpInfoService cpInfoService;


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "")
    @ApiImplicitParam(name = "isBottomLayer", value = "true 需要最后一级 false 不需要最后一级(默认true)", required = true, paramType = "query", example = "true")
    public R<List<CpCategoryVO>> findList(Boolean isBottomLayer) {
        List<CpCategory> list = baseService.list(new LambdaQueryWrapper<CpCategory>()
                .orderByAsc(CpCategory::getSort)
                .orderByAsc(CpCategory::getCode));
        if (isBottomLayer==null || isBottomLayer) {
            return R.successFind(BeanDtoVoUtil.listVo(list, CpCategoryVO.class));
        } else {
            List<CpCategory> newList = list.stream().filter(i -> !StringUtil.isInteger(i.getCode())).collect(Collectors.toList());
            return R.successFind(BeanDtoVoUtil.listVo(newList, CpCategoryVO.class));
        }

    }

    @RequestMapping(value = "/findTree", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有 Tree", notes = "")
    public R<List<CpCategoryVO>> findTree() {
        return R.successFind(baseService.findTree());
    }


    @RequestMapping(value = "/findUpdPidTree", method = RequestMethod.GET)
    @ApiOperation(value = "变更父级查询所有 Tree", notes = "不查询code为数字的")
    public R<List<CpCategoryVO>> findUpdPidTree() {
        return R.successFind(baseService.findTree());
    }




//    @RequestMapping(value = "/findVersion", method = RequestMethod.GET)
//    @ApiOperation(value = "获取字典版本", notes = "获取数据字典版本信息, 如果判断到本地版本和线上版本不一致,调用 findCodeGroup 接口刷新字典数据")
//    public R<Integer> findVersion() {
//        return R.success(BaseConstant.Cache.DICT_VERSION);
//    }


    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "Code查询(Tree)", notes = "无限层次, 树结构，只能传递字符串Code, 不能传递字符串数字Code，不包括禁用数据 ")
    public R<CpCategoryVO> findByCode(@RequestParam String code) {
        // 不能传递字符串数字来查询
        if (StringUtil.isInteger(code)) {
            throw new ErrorException(RType.PARAM_ERROR);
        }
        CpCategoryVO dict = baseService.findByCodeFetchDictVO(code, true);
        return R.success(dict);
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "字符串Code不能重复,  数字类型的Code可以重复")
    public R<Void> insert(@RequestBody com.ws.ldy.modules.sys.admin.model.dto.AdminDictionaryDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        if (StringUtils.isBlank(dto.getCode().trim())) {
            throw new ErrorException(RType.PARAM_MISSING.getValue(), RType.PARAM_MISSING.getMsg() + LambdaUtils.convert(com.ws.ldy.modules.sys.admin.model.dto.AdminDictionaryDTO::getCode));
        }
        dto.setCode(dto.getCode().trim());
        if (!StringUtil.isInteger(dto.getCode()) && baseService.count(new LambdaQueryWrapper<CpCategory>().eq(CpCategory::getCode, dto.getCode())) > 0) {
            // 字符串code 为string时不能重复, 为Integer时可以重复
            throw new ErrorException(RType.DICT_DUPLICATE);
        }
        baseService.save(dto.convert(CpCategory.class));
        //清除缓存
        BaseConstant.Cache.DICT_MAP_GROUP = null;
        return R.successInsert();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑", notes = "编辑后当前字典的字符串Code不能与其他字典的字符串Code重复， 不编辑Code时 + 编辑数字类型的Code时 不受影响")
    public R<Void> upd(@RequestBody com.ws.ldy.modules.sys.admin.model.dto.AdminDictionaryDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        // 因为Code不能重复, 编辑了Code 需单独处理数据
        if (dto.getCode() != null) {
            dto.setCode(dto.getCode().trim());
            // 原数据
            CpCategory dict = baseService.getById(dto.getId());
            //  原数据code != new Code, 判断数据库是否存在修改后的code值 ， code为Integer时不处理
            if (!dict.getCode().equals(dto.getCode().trim())) {
                if (!StringUtil.isInteger(dto.getCode()) && baseService.count(new LambdaQueryWrapper<CpCategory>().eq(CpCategory::getCode, dto.getCode())) > 0) {
                    throw new ErrorException(RType.DICT_DUPLICATE);
                }
            }
        }
        baseService.updateById(dto.convert(CpCategory.class));
        //清除缓存
        BaseConstant.Cache.DICT_MAP_GROUP = null;
        return R.successUpdate();
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "删除当前ID数据以及该ID下的所有子层级数据")
    public R<Void> del(@RequestParam String id) {
        List<String> ids = baseService.findByIdFetchIds(id);
        baseService.removeByIds(ids);
        return R.successDelete();
    }


    @RequestMapping(value = "/updBySort", method = RequestMethod.PUT)
    @ApiOperation(value = "修改排序", notes = "排序数字越小,越靠前")
    public R<Void> updBySort(@RequestParam String id, @RequestParam Integer sort) {
        CpCategory dict = new CpCategory();
        dict.setId(id);
        dict.setSort(sort);
        baseService.updateById(dict);
        return R.successUpdate();
    }



    @RequestMapping(value = "/insertInit", method = RequestMethod.PUT)
    @ApiOperation(value = "初始数据写人", notes = "排序数字越小,越靠前")
    public R<Void> insertInit() {
        List<CpInfo> list = cpInfoService.list(new LambdaQueryWrapper<CpInfo>()
                .select(CpInfo::getZid)
                .groupBy(CpInfo::getZid)
        );
        List<CpCategory> addCpInfo = new ArrayList<>();
        for (int i = 1; i < list.size() + 1 ; i++) {
            CpCategory dict = new CpCategory();
            dict.setId(IdUtil.snowflakeId());
            dict.setName(list.get(i-1).getZid());
            dict.setCode(i+"");
            dict.setDesc("-");
            dict.setDisable(0);
            dict.setSort(0);
            dict.setPid("1298865592406097921");
            addCpInfo.add(dict);
        }
        baseService.saveBatch(addCpInfo);
        return R.successUpdate();
    }
}
