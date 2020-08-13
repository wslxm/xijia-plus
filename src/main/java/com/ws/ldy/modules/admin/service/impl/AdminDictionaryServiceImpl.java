package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.common.utils.StringUtil;
import com.ws.ldy.modules.admin.mapper.AdminDictionaryMapper;
import com.ws.ldy.modules.admin.model.entity.AdminDictionary;
import com.ws.ldy.modules.admin.model.vo.AdminDictionaryVO;
import com.ws.ldy.modules.admin.service.AdminDictionaryService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdminDictionaryServiceImpl extends BaseIServiceImpl<AdminDictionaryMapper, AdminDictionary> implements AdminDictionaryService {

    /**
     * 分组的字典数据，不包括禁用数据
     * 版本号：version，当版本号一致时, 不返回前台 dictVOGroupMap 数据
     */
    private static Map<String, AdminDictionaryVO> dictVOGroupMap;
    public static Integer version = 0;


    /**
     * 根据code 查询下级所有 ，不包括禁用数据
     * @author wangsong
     * @param code
     * @date 2020/8/8 0008 1:15
     * @return com.ws.ldy.modules.admin.model.vo.AdminDictionaryVO
     * @version 1.0.0
     */
    @Override
    public AdminDictionaryVO findByCodeFetchDictVO(String code) {
        // 查询当前
        AdminDictionary dict = baseMapper.selectOne(new LambdaQueryWrapper<AdminDictionary>().eq(AdminDictionary::getCode, code));
        // 查询所有
        List<AdminDictionaryVO> dictVoList = BeanDtoVoUtil.listVo(baseMapper.selectList(new LambdaQueryWrapper<AdminDictionary>()
                .orderByAsc(AdminDictionary::getCode)
                .eq(AdminDictionary::getDisable, 0)
        ), AdminDictionaryVO.class);
        if (dict == null || dictVoList == null || dictVoList.size() == 0) {
            return null;
        }
        AdminDictionaryVO dictVO = dict.convert(AdminDictionaryVO.class);
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
        AdminDictionary dict = baseMapper.selectById(id);
        // 查询所有
        List<AdminDictionaryVO> dictVoList = BeanDtoVoUtil.listVo(baseMapper.selectList(null), AdminDictionaryVO.class);
        if (dict == null || dictVoList == null || dictVoList.size() == 0) {
            return null;
        }
        AdminDictionaryVO dictVO = dict.convert(AdminDictionaryVO.class);
        List<String> ids = new ArrayList<>();
        ids.add(dict.getId());
        // 递归添加下级数据
        nextLowerNode(dictVoList, dictVO, ids);
        return ids;
    }


    /**
     * 所有数据， 不包括禁用数据
     * <p>
     *     key - value 形式，因为所有添加下层数据是引用。每一个key下的value 数据依然有所有的层级关系数据
     * </p>
     * @author wangsong
     * @date 2020/8/8 0008 1:07
     * @return java.util.Map<java.lang.String, com.ws.ldy.modules.admin.model.vo.AdminDictionaryVO>
     * @version 1.0.0
     */
    @Override
    public Map<String, AdminDictionaryVO> findCodeGroup() {
        // return
        Map<String, AdminDictionaryVO> respDictVOMap = new HashMap<>();
        // 查询所有字典数据
        List<AdminDictionaryVO> dictVoList = BeanDtoVoUtil.listVo(baseMapper.selectList(new LambdaQueryWrapper<AdminDictionary>()
                .orderByAsc(AdminDictionary::getCode)
                .eq(AdminDictionary::getDisable, 0)
        ), AdminDictionaryVO.class);
        //
        for (AdminDictionaryVO fatherDictVo : dictVoList) {
            //  不添加Integer参数类型，设置当前数据为父级，不论当前层次的，递归获取所有当前层次的下级数据
            if (StringUtil.isInteger(fatherDictVo.getCode())) {
                continue;
            }
            respDictVOMap.put(fatherDictVo.getCode(), fatherDictVo);
            // 添加子级
            for (AdminDictionaryVO dictVo : dictVoList) {
                if (dictVo.getPid().equals(fatherDictVo.getId())) {
                    if (fatherDictVo.getDictMap() == null) {
                        fatherDictVo.setDictMap(new HashMap<String, AdminDictionaryVO>() {{
                            put(dictVo.getCode(), dictVo);
                        }});
                    } else {
                        fatherDictVo.getDictMap().put(dictVo.getCode(), dictVo);
                    }
                }
            }
        }
        dictVOGroupMap = respDictVOMap;
        // 添加版本号
        AdminDictionaryVO adminDictionaryVO = new AdminDictionaryVO();
        adminDictionaryVO.setVersion(version);
        adminDictionaryVO.setName("当前版本号");
        adminDictionaryVO.setDesc("调用版本号来判断是否和当前版本号一致，不一致重新调用本接口刷新本地缓存数据");
        dictVOGroupMap.put("VERSION", adminDictionaryVO);
        return dictVOGroupMap;
    }


    /**
     * 递归添加下级数据
     * @param dictVoList 所有节点
     * @param fatherDict 上级节点
     * @param ids 收集所有数据id
     */
    // @formatter:off
    public void nextLowerNode(List<AdminDictionaryVO> dictVoList, AdminDictionaryVO fatherDict,List<String> ids) {
        for (AdminDictionaryVO dict : dictVoList) {
            // 当前层级类还没有子层级对象就创建/有就追加
            if (dict.getPid().equals(fatherDict.getId())) {
                if (fatherDict.getDictList() == null) {
                    fatherDict.setDictList(new ArrayList<AdminDictionaryVO>() {{ add(dict);}});
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

