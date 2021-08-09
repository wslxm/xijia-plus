package com.ws.ldy.manage.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.manage.admin.model.dto.AdminDictionaryDTO;
import com.ws.ldy.manage.admin.model.entity.AdminDictionary;
import com.ws.ldy.manage.admin.model.vo.AdminDictionaryCodeGroup;
import com.ws.ldy.manage.admin.model.vo.AdminDictionaryVO;

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
     * 查询所有（缓存到jvm）
     * @param isDisable  是否查询禁用数据 =true 查询*默认   =false 不查询
     * @return
     */
    List<AdminDictionaryVO> findList(Boolean isDisable);

    /**
     * 添加
     * @param
     * @return
     */
    Boolean insert(AdminDictionaryDTO dto);

    /**
     * 编辑
     * @param
     * @return
     */
    Boolean upd(AdminDictionaryDTO dto);


    /**
     * 修改排序
     * @param id
     * @param sort
     * @return
     */
    Boolean updBySort(String id, Integer sort);

    /**
     * id删除，并删除下级数据
     * @param id
     * @return
     */
    Boolean del(String id);


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
    Map<String, AdminDictionaryCodeGroup> findCodeGroup();


    /**
     * 查询字典类别(级联数据)
     * @param code 父级code, 不传默认为顶层
     * @return
     */
    List<AdminDictionaryVO> findDictCategory(String code);


    /**
     * 生成枚举
     * @param enumName 父级枚举key，只生成指定key下的数据
     */
    Map<String, String> generateEnum(String enumName);

}
