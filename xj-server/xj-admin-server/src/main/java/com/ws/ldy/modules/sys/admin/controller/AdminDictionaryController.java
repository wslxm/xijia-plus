package com.ws.ldy.modules.sys.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.cache.BaseCache;
import com.ws.ldy.common.function.LambdaUtils;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * 字典表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin +"/adminDictionary")
@Api(value = "AdminDictionaryController", tags = "base--字典管理")
public class AdminDictionaryController extends BaseController<AdminDictionaryService> {


//    @RequestMapping(value = "/findList", method = RequestMethod.GET)
//    @ApiOperation(value = "查询所有", notes = "")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "code", value = "字典code, 如不传递查询所有", required = false, paramType = "query"),
//            @ApiImplicitParam(name = "isBottomLayer", value = "true 需要最后一级 false 不需要最后一级(默认true)", required = false, paramType = "query"),
//    })
//    public R<List<AdminDictionaryVO>> findList(Boolean isBottomLayer, String code) {
//        List<AdminDictionary> list = baseService.list(new LambdaQueryWrapper<AdminDictionary>()
//                .orderByAsc(AdminDictionary::getSort)
//                .orderByAsc(AdminDictionary::getCode)
//        );
//        if (isBottomLayer == null || isBottomLayer) {
//            return R.successFind(BeanDtoVoUtil.listVo(list, AdminDictionaryVO.class));
//        } else {
//            List<AdminDictionary> newList = list.stream().filter(i -> !StringUtil.isInteger(i.getCode())).collect(Collectors.toList());
//            return R.successFind(BeanDtoVoUtil.listVo(newList, AdminDictionaryVO.class));
//        }
//    }

//    @RequestMapping(value = "/findTree", method = RequestMethod.GET)
//    @ApiOperation(value = "查询所有 Tree", notes = "")
//    public R<List<AdminDictionaryVO>> findTree() {
//        return R.successFind(baseService.findTree());
//    }


//    @RequestMapping(value = "/findUpdPidTree", method = RequestMethod.GET)
//    @ApiOperation(value = "变更父级查询所有 Tree", notes = "不查询code为数字的")
//    public R<List<AdminDictionaryVO>> findUpdPidTree() {
//        return R.successFind(baseService.findTree());
//    }


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


//    @RequestMapping(value = "/findVersion", method = RequestMethod.GET)
//    @ApiOperation(value = "获取字典版本", notes = "获取数据字典版本信息, 如果判断到本地版本和线上版本不一致,调用 findCodeGroup 接口刷新字典数据")
//    public R<Integer> findVersion() {
//        return R.success(BaseConstant.Cache.DICT_VERSION);
//    }


    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "Code查询(默认返回Tree数据, 可指定)", notes = "不能传递字符串数字Code查询 ")
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
    @ApiOperation(value = "查询字典类别", notes = "pid不传查顶级类别,传了pid查对应pid下的类别")
    public R<List<AdminDictionaryVO>> findDictCategory(@RequestParam(required = false) String code) {
        List<AdminDictionary> dictCategorys = baseService.findDictCategory(code);
        return R.success(BeanDtoVoUtil.listVo(dictCategorys, AdminDictionaryVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "字符串Code不能重复,  数字类型的Code可以重复")
    public R<Void> insert(@RequestBody AdminDictionaryDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        if (StringUtils.isBlank(dto.getCode().trim())) {
            throw new ErrorException(RType.PARAM_MISSING.getValue(), RType.PARAM_MISSING.getMsg() + LambdaUtils.convert(AdminDictionaryDTO::getCode));
        }
        dto.setCode(dto.getCode().trim());
        if (!isInteger(dto.getCode()) && baseService.count(new LambdaQueryWrapper<AdminDictionary>().eq(AdminDictionary::getCode, dto.getCode())) > 0) {
            // 字符串code 为string时不能重复, 为Integer时可以重复
            throw new ErrorException(RType.DICT_DUPLICATE);
        }
        baseService.save(dto.convert(AdminDictionary.class));
        //清除缓存
        BaseCache.DICT_MAP_GROUP = null;
        return R.successInsert();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑", notes = "编辑后当前字典的字符串Code不能与其他字典的字符串Code重复， 不编辑Code时 + 编辑数字类型的Code时 不受影响")
    public R<Void> upd(@RequestBody AdminDictionaryDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        // 因为Code不能重复, 编辑了Code 需单独处理数据
        if (dto.getCode() != null) {
            dto.setCode(dto.getCode().trim());
            // 原数据
            AdminDictionary dict = baseService.getById(dto.getId());
            //  原数据code != new Code, 判断数据库是否存在修改后的code值 ， code为Integer时不处理
            if (!dict.getCode().equals(dto.getCode().trim())) {
                if (!isInteger(dto.getCode()) && baseService.count(new LambdaQueryWrapper<AdminDictionary>().eq(AdminDictionary::getCode, dto.getCode())) > 0) {
                    throw new ErrorException(RType.DICT_DUPLICATE);
                }
            }
        }
        baseService.updateById(dto.convert(AdminDictionary.class));
        //清除缓存
        BaseCache.DICT_MAP_GROUP = null;
        return R.successUpdate();
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "删除当前ID数据以及该ID下的所有子层级数据")
    public R<Void> del(@RequestParam String id) {
        List<String> ids = baseService.findByIdFetchIds(id);
        baseService.removeByIds(ids);
        //清除缓存
        BaseCache.DICT_MAP_GROUP = null;
        return R.successDelete();
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
        List<AdminDictionaryVO> dict = baseService.findByCodeFetchDictVO("ENUMS", true, false, true);
        String enumsJava = baseService.generateEnumJava(dict.get(0));
        String enumsJs = baseService.generateEnumJs(dict.get(0));
        Map<String, String> map = new HashMap<>();
        map.put("java", enumsJava); // 完整的枚举字典
        map.put("js", enumsJs);     // 枚举字典key，直接通过key获取
        System.out.println(enumsJava);
        System.out.println(enumsJs);
        return R.success(map);
    }


    /**
     * 是否为数字验证
     */
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

    /**
     * 判断字符串是否是一个isInteger 数字类型
     * @param str
     * @return
     */
    private boolean isInteger(String str) {
        return pattern.matcher(str).matches();
    }
}
