package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.DictionaryAdmin;
import com.ws.ldy.modules.admin.model.vo.DictionaryAdminVo;

import java.util.List;

/**
 *   数据字典
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
public interface DictionaryAdminService extends IService<DictionaryAdmin> {

//    List<DictionaryAdmin> findByType(String name);


    /**
     *   根据code查询数据+ 下级所有层级数据（无限递归）
     *
     * @param code
     * @return java.util.List<com.ws.ldy.admin.model.vo.DictionaryAdminVo>
     * @date 2020/7/12 0012 19:22
     */
    DictionaryAdminVo findByCodeFetchDictVO(String code);

    /**
     * 根据code查询数据ID + 加下级所有ID
     */
    List<String> findByIdFetchIds(String id);

}
