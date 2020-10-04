package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.AdminDictionary;
import com.ws.ldy.modules.admin.model.vo.AdminDictionaryVO;

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


   List<AdminDictionaryVO>  findTree();

    /**
     *  根据code查询数据+ 下级所有层级数据（无限递归），不包括禁用数据,数据Sort排序，在根据 Code排序
     *
     * @param code
     * @param isDisable 是否查询禁用数据
     * @return java.util.List<com.ws.ldy.admin.model.vo.AdminDictionaryVO>
     * @date 2020/7/12 0012 19:22
     */
    AdminDictionaryVO findByCodeFetchDictVO(String code, boolean isDisable);

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
