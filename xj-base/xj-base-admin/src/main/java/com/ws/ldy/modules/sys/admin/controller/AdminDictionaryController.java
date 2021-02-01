package com.ws.ldy.modules.sys.admin.controller;

import com.ws.ldy.common.cache.BaseCache;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.admin.model.dto.AdminDictionaryDTO;
import com.ws.ldy.modules.sys.admin.model.entity.AdminDictionary;
import com.ws.ldy.modules.sys.admin.model.vo.AdminDictionaryVO;
import com.ws.ldy.modules.sys.admin.service.AdminDictionaryService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 字典表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/adminDictionary")
@Api(value = "AdminDictionaryController", tags = "base--字典管理")
public class AdminDictionaryController extends BaseController<AdminDictionaryService> {


    @RequestMapping(value = "/findCodeGroup", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有-code分组", notes = "1、根据Code字段分组排列数据,分组下的数据仍然有层级关系" +
            "\r\n 2、版本号(key=version)未发送变化后端不返回任何数据,前端请定义全局变量来缓存此字段" +
            "\r\n 3、所有select选择框,状态字段都使用此接口的数据获取中文值" +
            "\r\n 4、添加/更新/删除/修改排序后端都会更新版本号,重新拉取直接获取最新数据" +
            "\r\n 5、不包括禁用数据" +
            "\r\n 建议: 打开首页时调用此方法,刷新缓存数据, 刷新首页时在此刷新缓存"
    )
    public R<Map<String, AdminDictionaryVO.FindCodeGroup>> findCodeGroup() {
        return R.successFind(baseService.findCodeGroup());
    }


    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "Code查询(默认返回Tree数据, 可指定Tree或List)", notes = "不能传递字符串数字Code查询 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "父级code, 不传查询code，传递了只查询指定code下数据", required = false, paramType = "query"),
            @ApiImplicitParam(name = "isDisable", value = "是否查询禁用数据 true 查询*默认  false 不查询", required = false, paramType = "query"),
            @ApiImplicitParam(name = "isBottomLayer", value = "是否需要最后一级数据 true 需要*默认   false 不需要", required = false, paramType = "query"),
            @ApiImplicitParam(name = "isTree", value = "isTree 是否返回树结构数据  tree 是*默认  false 否(返回过滤后的 list列表)", required = false, paramType = "query"),
    })
    public R<List<AdminDictionaryVO>> findByCode(@RequestParam(required = false) String code,
                                                 @RequestParam(required = false) Boolean isDisable,
                                                 @RequestParam(required = false) Boolean isBottomLayer,
                                                 @RequestParam(required = false) Boolean isTree
    ) {
        List<AdminDictionaryVO> dictVO = baseService.findByCodeFetchDictVO(code, isDisable, isBottomLayer, isTree);
        return R.success(dictVO);
    }


    @RequestMapping(value = "/findDictCategory", method = RequestMethod.GET)
    @ApiOperation(value = "查询字典类别", notes = "pid不传查顶级数据, 传了pid查指定 pid下的一级数据")
    public R<List<AdminDictionaryVO>> findDictCategory(@RequestParam(required = false) String code) {
        List<AdminDictionary> dictCategorys = baseService.findDictCategory(code);
        return R.success(BeanDtoVoUtil.listVo(dictCategorys, AdminDictionaryVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "字符串Code不能重复,  数字类型的Code可以重复")
    public R<Boolean> insert(@RequestBody AdminDictionaryDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑", notes = "编辑后当前字典的字符串Code不能与其他字典的字符串Code重复， 不编辑Code时 + 编辑数字类型的Code时 不受影响")
    public R<Boolean> upd(@RequestBody AdminDictionaryDTO dto) {
        return R.successUpdate(baseService.upd(dto));
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "删除当前ID数据以及该ID下的所有子层级数据")
    public R<Boolean> del(@RequestParam String id) {
        List<String> ids = baseService.findByIdFetchIds(id);
        boolean res = baseService.removeByIds(ids);
        //清除缓存
        BaseCache.DICT_MAP_GROUP = null;
        return R.successDelete(res);
    }


    @RequestMapping(value = "/updBySort", method = RequestMethod.PUT)
    @ApiOperation(value = "修改排序", notes = "排序数字越小,越靠前")
    public R<Void> updBySort(@RequestParam String id, @RequestParam Integer sort) {
        AdminDictionary dict = new AdminDictionary();
        dict.setId(id);
        dict.setSort(sort);
        baseService.updateById(dict);
        //清除缓存
        BaseCache.DICT_MAP_GROUP = null;
        return R.successUpdate();
    }


    @RequestMapping(value = "/generateEnum", method = RequestMethod.GET)
    @ApiOperation(value = "生成枚举", notes = "排序数字越小,越靠前, \n 返回参数Map<String, String> ==> \n map.java = 完整的java枚举字段 \n map.js = 代码枚举字典key，前端直接通过key获取对应值")
    public R<Map<String, String>> generateEnum() {
        return R.success( baseService.generateEnum("ENUMS"));
    }
}
