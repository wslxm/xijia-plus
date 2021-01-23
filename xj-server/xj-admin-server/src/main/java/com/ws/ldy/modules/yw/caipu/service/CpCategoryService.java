package com.ws.ldy.modules.yw.caipu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.yw.caipu.model.entity.CpCategory;
import com.ws.ldy.modules.yw.caipu.model.vo.CpCategoryVO;

import java.util.List;

/**
 * 菜谱类别字典表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-10-04 21:44:55
 */
public interface CpCategoryService extends IService<CpCategory> {

    /**
     *
     * @param
     * @return
     */
    List<CpCategoryVO> findTree();


    /**
     *  根据code查询数据+ 下级所有层级数据（无限递归），不包括禁用数据,数据Sort排序，在根据 Code排序
     *
     * @param code
     * @param isDisable 是否不查询禁用数据(true=不查询)
     * @return java.util.List<com.ws.ldy.admin.model.vo.AdminDictionaryVO>
     * @date 2020/7/12 0012 19:22
     */
    CpCategoryVO findByCodeFetchDictVO(String code, boolean isDisable);

    /**
     * 查询下级所有Id, 包括禁用数据
     */
    List<String> findByIdFetchIds(String id);
}

