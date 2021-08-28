package com.ws.ldy.manage.admin.controller;

import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.result.R;
import com.ws.ldy.manage.admin.model.dto.AdminDictionaryDTO;
import com.ws.ldy.manage.admin.model.query.AdminDictionaryQuery;
import com.ws.ldy.manage.admin.model.vo.AdminDictionaryCodeGroup;
import com.ws.ldy.manage.admin.model.vo.AdminDictionaryVO;
import com.ws.ldy.manage.admin.service.AdminDictionaryService;
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
@RequestMapping(BaseConstant.Uri.apiAdmin + "/dictionary")
@Api(value = "AdminDictionaryController", tags = "base--字典管理")
public class AdminDictionaryController extends BaseController<AdminDictionaryService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询 (默认返回Tree数据,可指定Tree或List)", notes = "不能传递字符串数字Code查询")
    public R<List<AdminDictionaryVO>> list(@ModelAttribute AdminDictionaryQuery query) {
        return R.success(baseService.list(query));
    }

    @PostMapping
    @ApiOperation(value = "添加", notes = "字符串类型的 Code不能重复,  数字类型的Code可以重复")
    public R<Boolean> insert(@RequestBody AdminDictionaryDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "编辑", notes = "编辑后当前字典的字符串Code不能与其他字典的字符串Code重复， 不编辑Code时 + 编辑数字类型的Code时 不受影响")
    public R<Boolean> upd(@PathVariable String id, @RequestBody AdminDictionaryDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除", notes = "删除当前ID数据以及该ID下的所有子层级数据")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.del(id));
    }


    @GetMapping(value = "/findCodeGroup")
    @ApiOperation(value = "查询所有-code分组", notes = "1、根据Code字段分组排列数据,分组下的数据仍然有层级关系" +
            "\r\n 2、版本号(key=version)未发送变化后端不返回任何数据,前端请定义全局变量来缓存此字段" +
            "\r\n 3、所有select选择框,状态字段都使用此接口的数据获取中文值" +
            "\r\n 4、不包括禁用数据" +
            "\r\n 建议: 打开首页时调用此方法,刷新缓存数据, 刷新首页时在此刷新缓存"
    )
    public R<Map<String, AdminDictionaryCodeGroup>> findCodeGroup() {
        return R.successFind(baseService.findCodeGroup());
    }


    @GetMapping(value = "/list/category")
    @ApiOperation(value = "获取类别(级联数据)", notes = "不传查顶级数据, 传了code查指定code的下一级数据")
    public R<List<AdminDictionaryVO>> listCategory(@RequestParam(required = false) String code) {
        return R.success(baseService.findDictCategory(code));
    }


    @GetMapping(value = "/generateEnum")
    @ApiOperation(value = "生成枚举", notes = "" +
            "1、生成 Enum 的java 类和 生成js findCodeGroup查询数据的key\r\n" +
            "2、排序数字越小,越靠前\r\n" +
            "3、返回参数Map<String, String> ==>  map.java = 完整的java枚举字段  map.js = 代码枚举字典key,  前端直接通过key获取对应值\r\n" +
            "")
    public R<Map<String, String>> generateEnum(String enumsKey) {
        return R.success(baseService.generateEnum(enumsKey));
    }
}
