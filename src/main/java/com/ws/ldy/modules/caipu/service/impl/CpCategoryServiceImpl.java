package com.ws.ldy.modules.caipu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.caipu.mapper.CpCategoryMapper;
import com.ws.ldy.modules.caipu.model.entity.CpCategory;
import com.ws.ldy.modules.caipu.model.vo.CpCategoryVO;
import com.ws.ldy.modules.caipu.service.CpCategoryService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜谱类别字典表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-04 21:44:55
 */
@Service
public class CpCategoryServiceImpl extends BaseIServiceImpl<CpCategoryMapper, CpCategory> implements CpCategoryService {

    @Override
    public List<CpCategoryVO> findTree() {
        List<CpCategoryVO> respDictList = new ArrayList<>();
        List<CpCategory> dictList = this.list(new LambdaQueryWrapper<CpCategory>()
                .orderByAsc(CpCategory::getSort)
                .orderByAsc(CpCategory::getCode)
        );
        List<CpCategoryVO> dictVoList = BeanDtoVoUtil.listVo(dictList, CpCategoryVO.class);
        // 递归添加下级数据,  new ArrayList<>() 是没有用的, findByCodeIds收集Ids 所有
        dictVoList.forEach(item -> {
            if ("0".equals(item.getPid())) {
                nextLowerNode(dictVoList, item, new ArrayList<>());
                respDictList.add(item);
            }
        });
        return respDictList;
    }


    /**
     * 根据code 查询下级所有 ，不包括禁用数据
     * @author wangsong
     * @param code
     * @date 2020/8/8 0008 1:15
     * @return com.ws.ldy.modules.admin.model.vo.AdminDictionaryVO
     * @version 1.0.0
     */
    @Override
    public CpCategoryVO findByCodeFetchDictVO(String code, boolean isDisable) {
        // 查询当前
        CpCategory dict = baseMapper.selectOne(new LambdaQueryWrapper<CpCategory>().eq(CpCategory::getCode, code));
        // 查询所有
        List<CpCategoryVO> dictVoList = BeanDtoVoUtil.listVo(baseMapper.selectList(new LambdaQueryWrapper<CpCategory>()
                .orderByAsc(CpCategory::getSort)
                .orderByAsc(CpCategory::getCode)
                .eq(isDisable, CpCategory::getDisable, Enums.Base.Disable.DISABLE_0.getValue())
        ), CpCategoryVO.class);

        if (dict == null || dictVoList == null || dictVoList.size() == 0) {
            return null;
        }
        CpCategoryVO dictVO = dict.convert(CpCategoryVO.class);
        // 递归添加下级数据,  new ArrayList<>() 是没有用的, findByCodeIds收集Ids 所有
        nextLowerNode(dictVoList, dictVO, new ArrayList<>());
        return dictVO;
    }


    /**
     * 查询下级所有Id, 包括禁用数据
     * @author wangsong
     * @param id
     * @date 2020/8/8 0008 1:16
     * @return java.util.List<java.lang.String>
     * @version 1.0.0
     */
    @Override
    public List<String> findByIdFetchIds(String id) {
        // 查询当前
        CpCategory dict = baseMapper.selectById(id);
        // 查询所有
        List<CpCategoryVO> dictVoList = BeanDtoVoUtil.listVo(baseMapper.selectList(null), CpCategoryVO.class);
        if (dict == null || dictVoList == null || dictVoList.size() == 0) {
            return null;
        }
        CpCategoryVO dictVO = dict.convert(CpCategoryVO.class);
        List<String> ids = new ArrayList<>();
        ids.add(dict.getId());
        // 递归添加下级数据
        nextLowerNode(dictVoList, dictVO, ids);
        return ids;
    }



    /**
     * 递归添加下级数据
     * @param dictVoList 所有节点
     * @param fatherDict 上级节点
     * @param ids 收集所有数据id
     */
    public void nextLowerNode(List<CpCategoryVO> dictVoList, CpCategoryVO fatherDict, List<String> ids) {
        for (CpCategoryVO dict : dictVoList) {
            // 当前层级类还没有子层级对象就创建/有就追加
            if (dict.getPid().equals(fatherDict.getId())) {
                if (fatherDict.getCpCategoryVOList() == null) {
                    fatherDict.setCpCategoryVOList(new ArrayList<CpCategoryVO>() {{
                        add(dict);
                    }});
                } else {
                    fatherDict.getCpCategoryVOList().add(dict);
                }
                // 获取ids
                ids.add(dict.getId());
                // 继续添加下级,无限级
                nextLowerNode(dictVoList, dict, ids);
            }
        }
    }
}
