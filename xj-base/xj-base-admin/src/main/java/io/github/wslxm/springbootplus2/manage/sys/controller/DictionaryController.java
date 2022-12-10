package io.github.wslxm.springbootplus2.manage.sys.controller;

import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.DictionaryDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.DictionaryQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryCodeGroup;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryVO;
import io.github.wslxm.springbootplus2.manage.sys.service.DictionaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * base--sys--字典管理
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/sys/dictionary")
public class DictionaryController extends BaseController<DictionaryService> {


    /**
     * 列表查询 (默认返回Tree数据,可指定Tree或List)
     * <p>
     * 不能传递字符串数字Code查询
     * </P>
     *
     * @param query
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List < io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryVO>>
     * @author wangsong
     */
    @GetMapping(value = "/list")
    public Result<List<DictionaryVO>> list(@ModelAttribute DictionaryQuery query) {
        return Result.success(baseService.list(query));
    }

    /**
     * 添加
     * <p>
     * 字符串类型的 Code不能重复,  数字类型的Code可以重复
     * </P>
     *
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.String>
     * @author wangsong
     */
    @PostMapping
    public Result<String> insert(@RequestBody DictionaryDTO dto) {
        return Result.successInsert(baseService.insert(dto));
    }


    /**
     * 编辑
     * <p>
     * 编辑后当前字典的字符串Code不能与其他字典的字符串Code重复， 不编辑Code时 + 编辑数字类型的Code时 不受影响
     * </P>
     *
     * @param id
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     * @author wangsong
     */
    @PutMapping(value = "/{id}")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody DictionaryDTO dto) {
        return Result.successUpdate(baseService.upd(id, dto));
    }


    /**
     * ID删除
     * <p>
     * 删除当前ID数据以及该ID下的所有子层级数据
     * </P>
     *
     * @param id
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     * @author wangsong
     */
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> del(@PathVariable String id) {
        return Result.successDelete(baseService.del(id));
    }


    /**
     * 查询所有-code分组
     * <p>
     * 1、根据Code字段分组排列数据,分组下的数据仍然有层级关系" +
     * "\r\n 2、版本号(key=version)未发送变化后端不返回任何数据,前端请定义全局变量来缓存此字段" +
     * "\r\n 3、所有select选择框,状态字段都使用此接口的数据获取中文值" +
     * "\r\n 4、不包括禁用数据" +
     * "\r\n 建议: 打开首页时调用此方法,刷新缓存数据, 刷新首页时在此刷新缓存
     * </P>
     *
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.Map < java.lang.String, io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryCodeGroup>>
     * @author wangsong
     */
    @GetMapping(value = "/findCodeGroup")
    public Result<Map<String, DictionaryCodeGroup>> findCodeGroup() {
        return Result.successFind(baseService.findCodeGroup());
    }


    /**
     * 获取类别(级联数据)
     * <p>
     * 不传查顶级数据, 传了code查指定code的下一级数据
     * </P>
     *
     * @param code
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List < io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryVO>>
     * @author wangsong
     */
    @GetMapping(value = "/list/category")
    public Result<List<DictionaryVO>> listCategory(@RequestParam(required = false) String code) {
        return Result.success(baseService.findDictCategory(code));
    }


    /**
     * 生成枚举
     * <p>
     * 1、生成 Enum 的java 类和 生成js findCodeGroup查询数据的key\r\n" +
     * "2、排序数字越小,越靠前\r\n" +
     * "3、返回参数Map<String, String> ==>  map.java = 完整的java枚举字段  map.js = 代码枚举字典key,  前端直接通过key获取对应值
     * </P>
     *
     * @param enumsKey
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.Map < java.lang.String, java.lang.String>>
     * @author wangsong
     */
    @GetMapping(value = "/generateEnum")
    public Result<Map<String, String>> generateEnum(String enumsKey) {
        return Result.success(baseService.generateEnum(enumsKey));
    }
}
