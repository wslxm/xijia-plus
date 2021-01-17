package com.ws.ldy.modules.sys.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.sys.admin.model.entity.AdminDictionary;
import com.ws.ldy.modules.sys.admin.model.vo.AdminDictionaryVO;

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
     *
     * @param
     * @return
     */
    List<AdminDictionaryVO> findTree();


    /**
     *  根据code查询数据+ 下级所有层级数据（无限递归），先根据 Sort排序，在根据 Code排序
     *
     * @param code 父级code, 不传查询code，传递了只查询指定code下数据
     * @param isDisable 是否查询禁用数据 =true 查询   =false 不查询
     * @param isBottomLayer 是否需要最后一级数据  =true 需要   =false 不需要
     * @param isTree 是否返回树结构数据  =tree 是  = false 否(返回过滤后的 list列表)
     * @return java.util.List<com.ws.ldy.admin.model.vo.AdminDictionaryVO>
     * @date 2020/7/12 0012 19:22
     */
    List<AdminDictionaryVO> findByCodeFetchDictVO(String code, Boolean isDisable, Boolean isBottomLayer, Boolean isTree);

    /**
     * 查询下级所有Id, 包括禁用数据
     */
    List<String> findByIdFetchIds(String id);

    /**
     * 分组查询-key-value数据： 不包括禁用数据
     * @return
     */
    Map<String, AdminDictionaryVO.FindCodeGroup> findCodeGroup();


    /**
     * 查询字典类别
     * @param code 父级code, 不传默认为顶层
     * @return
     */
    List<AdminDictionary> findDictCategory(String code);


    /**
     * 拼接java 枚举类
     * @param dict
     */
    String generateEnumJava(AdminDictionaryVO dict);

    /**
     * 拼接js 字典数据
     * @param dict
     */
    String generateEnumJs(AdminDictionaryVO dict);
}
