package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.common.utils.StringUtil;
import com.ws.ldy.modules.admin.mapper.DictionaryAdminMapper;
import com.ws.ldy.modules.admin.model.entity.DictionaryAdmin;
import com.ws.ldy.modules.admin.model.vo.DictionaryAdminVo;
import com.ws.ldy.modules.admin.service.DictionaryAdminService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DictionaryAdminServiceImpl extends BaseIServiceImpl<DictionaryAdminMapper, DictionaryAdmin> implements DictionaryAdminService {

    /**
     * 分组的字典数据
     * 版本号：version，当版本号一致时, 不返回前台 dictVOGroupMap 数据
     */
    private static Map<String, DictionaryAdminVo> dictVOGroupMap;
    public static Integer version = 0;


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


    @Override
    public Map<String, DictionaryAdminVo> findCodeGroup() {
        // 数据为空或者版本号变动刷新前台数据，未变动不刷新此数据
        if (dictVOGroupMap == null || !version.equals(dictVOGroupMap.get("version").getVersion())) {
            // return
            Map<String, DictionaryAdminVo> respDictVOMap = new HashMap<>();
            // 查询菜单
            List<DictionaryAdminVo> dictVoList = BeanDtoVoUtil.listVo(baseMapper.selectList(null), DictionaryAdminVo.class);
            for (DictionaryAdminVo fatherDictVo : dictVoList) {
                //  不添加Integer参数时间，设置当前数据为父级，不论当前层次的，递归获取所有当前层次的下级数据
                if (StringUtil.isInteger(fatherDictVo.getCode())) {
                    continue;
                }
                respDictVOMap.put(fatherDictVo.getCode(), fatherDictVo);
                nextLowerNode(dictVoList, fatherDictVo, new ArrayList<>());
            }
            dictVOGroupMap = respDictVOMap;
            // 添加版本号
            DictionaryAdminVo dictionaryAdminVo = new DictionaryAdminVo();
            dictionaryAdminVo.setVersion(version);
            dictionaryAdminVo.setName("版本号");
            dictionaryAdminVo.setDesc("根据版本号来判断是否刷新本地缓存数据");
            dictVOGroupMap.put("version", dictionaryAdminVo);
            return dictVOGroupMap;
        } else {
            // 没有变更, 添加当前版本号信息
            Map<String, DictionaryAdminVo> respDictVOMap = new HashMap<>();
            DictionaryAdminVo dictionaryAdminVo = new DictionaryAdminVo();
            dictionaryAdminVo.setVersion(version);
            dictionaryAdminVo.setName("版本号");
            dictionaryAdminVo.setDesc("当前版本没有更新,不返回任何数据,请获取本地的缓存数据,如需获取最新服务器数据,可尝试增/删/改操作后重新访问改接口");
            respDictVOMap.put("version", dictionaryAdminVo);
            return respDictVOMap;
        }
    }


    /**
     * 递归添加下级数据
     * @param dictVoList 所有节点
     * @param fatherDict 上级节点
     * @param ids 收集所有数据id
     */
    // @formatter:off
    public void nextLowerNode(List<DictionaryAdminVo> dictVoList, DictionaryAdminVo fatherDict,List<String> ids) {
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

