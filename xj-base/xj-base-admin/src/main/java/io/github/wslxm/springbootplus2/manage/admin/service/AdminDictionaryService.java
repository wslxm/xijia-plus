package io.github.wslxm.springbootplus2.manage.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminDictionaryDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminDictionary;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminDictionaryQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminDictionaryCodeGroup;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminDictionaryVO;

import java.util.List;
import java.util.Map;

/**
 *   数据字典
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
public interface AdminDictionaryService extends IService<AdminDictionary> {

    /**
     * 列表查询, 根据code查询数据+ 下级所有层级数据（无限递归），先根据 Sort排序，在根据 Code排序
     * @return java.util.List<io.github.wslxm.admin.model.vo.AdminDictionaryVO>
     * @date 2020/7/12 0012 19:22
     */
    List<AdminDictionaryVO> list(AdminDictionaryQuery query);

    String insert(AdminDictionaryDTO dto);

    Boolean upd(String id, AdminDictionaryDTO dto);

    /**
     * id删除，并删除下级数据
     * @param id
     */
    Boolean del(String id);

    /**
     * 分组查询-key-value数据 (前端尽量缓存该数据)
     * <p>
     *     key-value 形式，因为所有添加下层数据是引用。每一个key下的value 数据依然有所有的层级关系数据
     * </p>
     * @author wangsong
     * @date 2020/8/8 0008 1:07
     * @return java.util.Map<java.lang.String, io.github.wslxm.modules.admin.model.vo.AdminDictionaryVO>
     * @version 1.0.1
     */
    Map<String, AdminDictionaryCodeGroup> findCodeGroup();

    /**
     * 查询字典类别(级联数据)
     * @param code 父级code, 不传默认为顶层
     */
    List<AdminDictionaryVO> findDictCategory(String code);

    /**
     * 生成枚举 (生成 Enum 的java 类和 生成js findCodeGroup查询数据的key )
     * @param enumName 父级枚举key，只生成指定key下的数据
     */
    Map<String, String> generateEnum(String enumName);
}
