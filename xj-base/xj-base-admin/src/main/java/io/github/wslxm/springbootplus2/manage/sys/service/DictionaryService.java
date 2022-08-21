package io.github.wslxm.springbootplus2.manage.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.DictionaryDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Dictionary;
import io.github.wslxm.springbootplus2.manage.sys.model.query.DictionaryQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryCodeGroup;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryVO;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
public interface DictionaryService extends IService<Dictionary> {

    /**
     * 列表查询, 根据code查询数据+ 下级所有层级数据（无限递归），先根据 Sort排序，在根据 Code排序
     *
     * @param query query
     * @return java.util.List<io.github.wslxm.admin.model.vo.DictionaryVO>
     * @date 2020/7/12 0012 19:22
     */
    List<DictionaryVO> list(DictionaryQuery query);


    /**
     * 添加
     *
     * @param dto dto
     * @return java.lang.String
     * @author wangsong
     * @date 2021/12/27 16:43
     * @version 1.0.0
     */
    String insert(DictionaryDTO dto);

    /**
     * 编辑
     *
     * @param id  id
     * @param dto dto
     * @return Boolean
     */
    Boolean upd(String id, DictionaryDTO dto);


    /**
     * id删除，并删除下级数据
     *
     * @param id id
     * @return java.lang.Boolean
     * @author wangsong
     * @date 2021/12/27 16:43
     * @version 1.0.0
     */
    Boolean del(String id);

    /**
     * 分组查询-key-value数据 (前端尽量缓存该数据) ----  缓存获取
     * <p>
     * key-value 形式，因为所有添加下层数据是引用。每一个key下的value 数据依然有所有的层级关系数据
     * </p>
     *
     * @return java.util.Map<java.lang.String, io.github.wslxm.modules.admin.model.vo.DictionaryVO>
     * @author wangsong
     * @date 2020/8/8 0008 1:07
     * @version 1.0.1
     */
    Map<String, DictionaryCodeGroup> findCodeGroup();


    /**
     * 通过字典类别 查询该类别下所有数据 (级联数据) ----  缓存获取
     *
     * @param code 父级code, 不传默认为顶层
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.DictionaryVO>
     * @author wangsong
     * @date 2021/12/27 16:45
     * @version 1.0.0
     */
    List<DictionaryVO> findDictCategory(String code);


    /**
     * 通过字典类别 + 值 获取具体的数据 ----  缓存获取
     *
     * @param code     父 code, 不传默认为顶层
     * @param nextCode 子 code
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.DictionaryVO>
     * @author wangsong
     * @date 2021/12/27 16:45
     * @version 1.0.0
     */
    DictionaryVO findDictCategoryNext(String code, String nextCode);


    /**
     * 生成枚举 (生成 Enum 的java 类和 生成js findCodeGroup查询数据的key )
     *
     * @param enumName 父级枚举key，只生成指定key下的数据
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @author wangsong
     * @date 2021/12/27 16:45
     * @version 1.0.0
     */
    Map<String, String> generateEnum(String enumName);


    /**
     * 获取所有字典数据 （查询缓存）
     *
     * @return
     */
    List<DictionaryVO> findListALL();

}
