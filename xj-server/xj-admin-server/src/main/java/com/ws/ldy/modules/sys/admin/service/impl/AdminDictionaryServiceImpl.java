package com.ws.ldy.modules.sys.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import com.ws.ldy.common.cache.BaseCache;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.sys.admin.mapper.AdminDictionaryMapper;
import com.ws.ldy.modules.sys.admin.model.entity.AdminDictionary;
import com.ws.ldy.modules.sys.admin.model.vo.AdminDictionaryVO;
import com.ws.ldy.modules.sys.admin.service.AdminDictionaryService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class AdminDictionaryServiceImpl extends BaseIServiceImpl<AdminDictionaryMapper, AdminDictionary> implements AdminDictionaryService {


    /**
     * 父级pid
     */
    private static final String pid = "0";

    @Override
    public List<AdminDictionaryVO> findTree() {
        List<AdminDictionaryVO> respDictList = new ArrayList<>();
        List<AdminDictionary> dictList = this.list(new LambdaQueryWrapper<AdminDictionary>()
                .orderByAsc(AdminDictionary::getSort)
                .orderByAsc(AdminDictionary::getCode)
        );
        List<AdminDictionaryVO> dictVoList = BeanDtoVoUtil.listVo(dictList, AdminDictionaryVO.class);
        // 递归添加下级数据,  new ArrayList<>() 是没有用的, findByCodeIds收集Ids 所有
        dictVoList.forEach(item -> {
            if (pid.equals(item.getPid())) {
                nextLowerNode(dictVoList, item, new ArrayList<>());
                respDictList.add(item);
            }
        });
        return respDictList;
    }


    /**
     * 根据code 查询下级所有
     * @author wangsong
     * @param code 父级code, 不传查询code，传递了只查询指定code下数据
     * @param isDisable     是否查询禁用数据      =true 查询*默认   =false 不查询
     * @param isBottomLayer 是否需要最后一级数据  =true 需要*默认   =false 不需要
     * @param isTree        是否返回树结构数据    =tree 是*默认  = false 否(返回过滤后的 list列表)
     * @date 2020/8/8 0008 1:15
     * @return com.ws.ldy.modules.admin.model.vo.AdminDictionaryVO
     * @version 1.0.0
     */
    @Override
    public List<AdminDictionaryVO> findByCodeFetchDictVO(String code, Boolean isDisable, Boolean isBottomLayer, Boolean isTree) {
        // 默认参数
        if (isDisable == null) {
            isDisable = true;
        }
        if (isBottomLayer == null) {
            isBottomLayer = true;
        }
        if (isTree == null) {
            isTree = true;
        }
        // 1、判断 code , 不能传递字符串数字来查询
        if (org.apache.commons.lang3.StringUtils.isNotBlank(code)) {
            if (this.isInteger(code)) {
                throw new ErrorException(RType.PARAM_ERROR);
            }
        }

        // 2、查询所有字典数据
        List<AdminDictionary> dictList = this.list(new LambdaQueryWrapper<AdminDictionary>()
                .orderByAsc(AdminDictionary::getSort)
                .orderByAsc(AdminDictionary::getCode)
                .eq(!isDisable, AdminDictionary::getDisable, Enums.Base.Disable.DISABLE_0.getValue())
        );
        if (dictList.size() == 0) {
            return null;
        }
        List<AdminDictionaryVO> dictListVO = BeanDtoVoUtil.listVo(dictList, AdminDictionaryVO.class);

        // 3、是否根据code查询, 找到父级code数据
        List<AdminDictionaryVO> pDictListVO = new ArrayList<>();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(code)) {
            // 查询指定code, 只有一条
            for (AdminDictionaryVO p : dictListVO) {
                if (p.getCode().equals(code)) {
                    pDictListVO.add(p);
                    break;
                }
            }
        } else {
            // 设置顶级code数据, 查询所有可能为多条
            for (AdminDictionaryVO p : dictListVO) {
                if (pid.equals(p.getPid())) {
                    pDictListVO.add(p);
                }
            }
        }
        if (pDictListVO.size() == 0) {
            // 没有顶级code数据
            return null;
        }

        // 4、数据过滤，是否需要最后一级数据（false不需要）
        if (!isBottomLayer) {
            dictListVO = dictListVO.stream().filter(i -> ! this.isInteger(i.getCode())).collect(Collectors.toList());
        }

        // 5、递归添加下级数据, pDictListVO 为tree数据, diceIds 为指定code层级下所有字典id收集
        List<String> diceIds = new ArrayList<>();
        // 使用新拷贝数据
        List<AdminDictionaryVO> dictListTwoVO = BeanDtoVoUtil.listVo(dictList, AdminDictionaryVO.class);
        // 开始递归
        for (AdminDictionaryVO pDictVO : pDictListVO) {
            diceIds.add(pDictVO.getId());
            this.nextLowerNode(dictListTwoVO, pDictVO, diceIds);
        }

        // 6、判断返回 tree 还是  list(前端自行解析list展示)
        List<AdminDictionaryVO> vos = null;
        if (isTree) {
            vos = pDictListVO;
        } else {
            vos = dictListVO.stream().filter(i -> diceIds.contains(i.getId())).collect(Collectors.toList());
        }
        return vos;
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
     * 所有数据，不包括禁用数据
     * <p>
     *     key - value 形式，因为所有添加下层数据是引用。每一个key下的value 数据依然有所有的层级关系数据
     * </p>
     * @author wangsong
     * @date 2020/8/8 0008 1:07
     * @return java.util.Map<java.lang.String, com.ws.ldy.modules.admin.model.vo.AdminDictionaryVO>
     * @version 1.0.0
     */
    @Override
    public Map<String, AdminDictionaryVO.FindCodeGroup> findCodeGroup() {
        if (BaseCache.DICT_MAP_GROUP != null) {
            // 缓存获取数据
            return BaseCache.DICT_MAP_GROUP;
        } else {
            // return -按添加顺序排序
            Map<String, AdminDictionaryVO.FindCodeGroup> respDictVOMap = new HashMap<>();
            // 查询所有字典数据
            List<AdminDictionaryVO.FindCodeGroup> dictVoList = BeanDtoVoUtil.listVo(baseMapper.selectList(new LambdaQueryWrapper<AdminDictionary>()
                    .orderByAsc(AdminDictionary::getSort)
                    .orderByAsc(AdminDictionary::getCode)
                    .eq(AdminDictionary::getDisable, 0)
            ), AdminDictionaryVO.FindCodeGroup.class);
            //
            for (AdminDictionaryVO.FindCodeGroup fatherDictVo : dictVoList) {
                // 不添加Integer参数类型，设置当前数据为父级，不论当前层次的，递归获取所有当前层次的下级数据
                if (this.isInteger(fatherDictVo.getCode())) {
                    continue;
                }
                respDictVOMap.put(fatherDictVo.getCode(), fatherDictVo);
                // 添加子级
                for (AdminDictionaryVO.FindCodeGroup dictVo : dictVoList) {
                    if (dictVo.getPid().equals(fatherDictVo.getId())) {
                        if (fatherDictVo.getDictMap() == null) {
                            fatherDictVo.setDictMap(new LinkedHashMap<String, AdminDictionaryVO.FindCodeGroup>() {{
                                put(dictVo.getCode(), dictVo);
                            }});
                        } else {
                            fatherDictVo.getDictMap().put(dictVo.getCode(), dictVo);
                        }
                    }
                }
            }
            // 缓存到jvm
            BaseCache.DICT_MAP_GROUP = respDictVOMap;
            return BaseCache.DICT_MAP_GROUP;
        }
    }


    @Override
    public List<AdminDictionary> findDictCategory(String code) {
        String newPid = pid;
        if (StringUtils.isNotBlank(code)) {
            AdminDictionary dict = this.getOne(new LambdaQueryWrapper<AdminDictionary>().eq(AdminDictionary::getCode, code));
            if (dict != null) {
                newPid = dict.getId();
            }
        }
        List<AdminDictionary> list = this.list(new LambdaQueryWrapper<AdminDictionary>().eq(AdminDictionary::getPid, newPid));
        return list;
    }


    /**
     * 递归添加下级数据
     * @param dictVoList 所有节点
     * @param pDict 上级节点
     * @param ids 收集指定code下所有字典数据 id
     */
    private void nextLowerNode(List<AdminDictionaryVO> dictVoList, AdminDictionaryVO pDict, List<String> ids) {
        for (AdminDictionaryVO zDict : dictVoList) {
            // 当前层级类还没有子层级对象就创建/有就追加
            if (zDict.getPid().equals(pDict.getId())) {
                if (pDict.getDictList() == null) {
                    pDict.setDictList(new ArrayList<AdminDictionaryVO>() {{
                        add(zDict);
                    }});
                } else {
                    pDict.getDictList().add(zDict);
                }
                // 获取ids
                ids.add(zDict.getId());
                // 继续添加下级,无限级
                nextLowerNode(dictVoList, zDict, ids);
            }
        }
    }


    /**
     * 生成java 代码枚举对象， 请将生成好的代码直接替换到 enums/Enums 类
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/8/16 0016 0:10
     * @version 1.0.0
     */
    @Override
    public String generateEnumJava(AdminDictionaryVO dict) {

        StringBuffer sb = new StringBuffer();
        sb.append("package com.ws.ldy.enums;\n");
        sb.append("\nimport lombok.AllArgsConstructor;");
        sb.append("\nimport lombok.Getter;\n");
        sb.append("\npublic interface Enums {\n");
        //模块名
        for (AdminDictionaryVO dictModule : dict.getDictList()) {
            sb.append("\n     /** ");
            sb.append("\n      * " + dictModule.getDesc() + "");
            sb.append("\n      */ ");
            sb.append("\n    interface " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictModule.getCode() + "{\n"));
            //枚举字典的-枚举名--驼峰模式
            if (dictModule.getDictList() == null) {
                continue;
            }
            for (AdminDictionaryVO dictField : dictModule.getDictList()) {
                String moduleName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictField.getCode());
                sb.append("\n        // " + dictField.getDesc() + "\n");
                sb.append("        @Getter\n");
                sb.append("        @AllArgsConstructor\n");
                sb.append("        enum " + moduleName + " implements IEnum<Integer> {\n");
                //枚举字典的-枚举属性
                if (dictField.getDictList() == null) {
                    continue;
                }
                for (AdminDictionaryVO dictValue : dictField.getDictList()) {
                    sb.append("            " + dictField.getCode() + "_" + dictValue.getCode() + "(" + dictValue.getCode() + ", \"" + dictValue.getName() + "\"),    // " + dictValue.getDesc() + "\n");
                }
                //
                sb.append("            ;\n");
                sb.append("            private Integer value;\n");
                sb.append("            private String desc;\n");
                sb.append("        }\n");
            }
            sb.append("    }\n");
        }
        sb.append("}\n");
        return sb.toString();
    }


    /**
     * 拼接js 字典数据-KEY
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/8/16 0016 0:29
     * @version 1.0.0
     */
    @Override
    public String generateEnumJs(AdminDictionaryVO dict) {
        StringBuffer sb = new StringBuffer();
        //
        sb.append("var Enums = {");
        //模块名
        for (AdminDictionaryVO dictModule : dict.getDictList()) {
            sb.append("\n    // " + dictModule.getName() + "");
            sb.append("\n    " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictModule.getCode() + ": {"));
            //枚举字典的-枚举名--驼峰模式
            for (AdminDictionaryVO dictField : dictModule.getDictList()) {
                String moduleName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictField.getCode());
                sb.append("\n        " + moduleName + " : \"" + dictField.getCode() + "\",  // " + dictField.getName());
            }
            sb.append("\n    },");
        }
        sb.append("\n};");
        return sb.toString();
    }


    /**
     * 是否为数字验证
     */
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

    /**
     * 判断字符串是否是一个isInteger 数字类型
     * @param str
     * @return
     */
    private boolean isInteger(String str) {
        return pattern.matcher(str).matches();
    }


//    /**
//     * 拼接js 对应的枚举字典数据
//     * @author wangsong
//     * @mail 1720696548@qq.com
//     * @date 2020/8/16 0016 0:29
//     * @version 1.0.0
//     */
//    @Override
//    public String generateEnumJs(AdminDictionaryVO dict) {
//        StringBuffer sb = new StringBuffer();
//        //
//        sb.append("var enums = {\n");
//        //模块名
//        for (AdminDictionaryVO dictModule : dict.getDictList()) {
//            sb.append("\n     /** ");
//            sb.append("\n      * " + dictModule.getDesc() + "");
//            sb.append("\n      */ ");
//            sb.append("\n      " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictModule.getCode() + ": {\n"));
//            //枚举字典的-枚举名--驼峰模式
//            for (AdminDictionaryVO dictField : dictModule.getDictList()) {
//                String moduleName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictField.getCode());
//                sb.append("         // " + dictField.getDesc() + "\n");
//                sb.append("         " + moduleName + " : {\n");
//                sb.append("           key: \"" + dictField.getCode()  + "\",\n");
//                sb.append("           value: [\n");
//                //枚举字典的-枚举属性
//                for (AdminDictionaryVO dictValue : dictField.getDictList()) {
//                    sb.append("          {\""+ dictValue.getCode()+"\":\""+dictValue.getName()+"\"},   // " + dictValue.getDesc() + "\n");
//                }
//                //
//                sb.append("            ],\n");
//                sb.append("        },\n");
//            }
//            sb.append("    },\n");
//        }
//        sb.append("};\n");
//        return sb.toString();
//    }
}

