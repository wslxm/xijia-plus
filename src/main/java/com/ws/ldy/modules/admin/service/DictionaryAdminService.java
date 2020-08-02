package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.DictionaryAdmin;
import com.ws.ldy.modules.admin.model.vo.DictionaryAdminVo;

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
     *   根据code查询当前字典下所有字典（可多层级）
     *
     * @param code
     * @return java.util.List<com.ws.ldy.admin.model.vo.DictionaryAdminVo>
     * @date 2020/7/12 0012 19:22
     */
    DictionaryAdminVo findCode(String code);

}
