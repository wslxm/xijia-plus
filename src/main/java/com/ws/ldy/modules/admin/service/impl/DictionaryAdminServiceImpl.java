package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.modules.admin.mapper.DictionaryAdminMapper;
import com.ws.ldy.modules.admin.model.entity.DictionaryAdmin;
import com.ws.ldy.modules.admin.model.vo.DictionaryAdminVo;
import com.ws.ldy.modules.admin.service.DictionaryAdminService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DictionaryAdminServiceImpl extends BaseIServiceImpl<DictionaryAdminMapper, DictionaryAdmin> implements DictionaryAdminService {


    @Override
    public DictionaryAdminVo findByCodeFetchDictVO(String code) {
        // 查询当前
        DictionaryAdmin dict = baseMapper.selectOne(new LambdaQueryWrapper<DictionaryAdmin>().eq(DictionaryAdmin::getCode, code));
        // 查询所有
        List<DictionaryAdminVo> dictVoList = BeanDtoVoUtil.listVo(baseMapper.selectList(null), DictionaryAdminVo.class);
        if (dict == null || dictVoList == null || dictVoList.size() == 0) {
            return null;
        }
        DictionaryAdminVo dictVO = dict.convert(DictionaryAdminVo.class);
        // 递归添加下级数据,  new ArrayList<>() 是没有用的, findByCodeIds收集Ids 所有
        nextLowerNode(dictVoList, dictVO, new ArrayList<>());
        return dictVO;
    }


    @Override
    public List<String> findByIdFetchIds(String id) {
        // 查询当前
        DictionaryAdmin dict = baseMapper.selectById(id);
        // 查询所有
        List<DictionaryAdminVo> dictVoList = BeanDtoVoUtil.listVo(baseMapper.selectList(null), DictionaryAdminVo.class);
        if (dict == null || dictVoList == null || dictVoList.size() == 0) {
            return null;
        }
        DictionaryAdminVo dictVO = dict.convert(DictionaryAdminVo.class);
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
    // @formatter:off
    private void nextLowerNode(List<DictionaryAdminVo> dictVoList, DictionaryAdminVo fatherDict,List<String> ids) {
        for (DictionaryAdminVo dict : dictVoList) {
            // 当前层级类还没有子层级对象就创建/有就追加
            if (dict.getPid().equals(fatherDict.getId())) {
                if (fatherDict.getDictList() == null) {
                    fatherDict.setDictList(new ArrayList<DictionaryAdminVo>() {{ add(dict);}});
                } else {
                    fatherDict.getDictList().add(dict);
                }
                //获取ids
                ids.add(dict.getId());
                //继续添加下级,无限级
                nextLowerNode(dictVoList, dict,ids);
            }
        }
    }
}

