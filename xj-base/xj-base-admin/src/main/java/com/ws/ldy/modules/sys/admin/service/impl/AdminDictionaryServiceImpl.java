package com.ws.ldy.modules.sys.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import com.ws.ldy.cache.JvmCache;
import com.ws.ldy.common.cache.CacheKey;
import com.ws.ldy.common.function.LambdaUtils;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.common.utils.paramVerification.StringUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.Base;
import com.ws.ldy.modules.sys.admin.mapper.AdminDictionaryMapper;
import com.ws.ldy.modules.sys.admin.model.dto.AdminDictionaryDTO;
import com.ws.ldy.modules.sys.admin.model.entity.AdminDictionary;
import com.ws.ldy.modules.sys.admin.model.vo.AdminDictionaryCodeGroup;
import com.ws.ldy.modules.sys.admin.model.vo.AdminDictionaryVO;
import com.ws.ldy.modules.sys.admin.service.AdminDictionaryService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AdminDictionaryServiceImpl extends BaseIServiceImpl<AdminDictionaryMapper, AdminDictionary> implements AdminDictionaryService {

    /**
     * 父级pid
     */
    private static final String PID = "0";


    /**
     * 查询所有（缓存到jvm）
     * @param isDisable  是否查询禁用数据 =true 查询*默认   =false 不查询
     * @return
     */
    @Override
    public List<AdminDictionaryVO> findList(Boolean isDisable) {
        if (!JvmCache.containsKey(CacheKey.DICT_LIST_KEY.getKey())) {
            // 查询所有字典数据
            List<AdminDictionary> adminDictionaries = baseMapper.selectList(new LambdaQueryWrapper<AdminDictionary>()
                    .orderByAsc(AdminDictionary::getSort)
                    .orderByAsc(AdminDictionary::getCode)
            );
            List<AdminDictionaryVO> dictList = BeanDtoVoUtil.listVo(adminDictionaries, AdminDictionaryVO.class);
            JvmCache.set(CacheKey.DICT_LIST_KEY.getKey(), dictList);
        }
        List<AdminDictionaryVO> listVO = JvmCache.getList(CacheKey.DICT_LIST_KEY.getKey(), AdminDictionaryVO.class);
        /**
         * 是否获取禁用数据
         */
        // 为了让数据不改变缓存数据,不使用引用,使用深拷贝
        List<AdminDictionaryVO> adminDictionaryVOS = BeanDtoVoUtil.listVo(listVO, AdminDictionaryVO.class);
        if (isDisable) {
            return adminDictionaryVOS;
        } else {
            //排除禁用数据(.stream() 后的数据依旧是引用数据)
            return adminDictionaryVOS.stream().filter(i -> i.getDisable().equals(Base.Disable.V0.getValue())).collect(Collectors.toList());
        }
    }


    @Override
    public Boolean insert(AdminDictionaryDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        if (StringUtils.isBlank(dto.getCode().trim())) {
            throw new ErrorException(RType.PARAM_MISSING.getValue(), RType.PARAM_MISSING.getMsg() + LambdaUtils.convert(AdminDictionaryDTO::getCode));
        }
        dto.setCode(dto.getCode().trim());
        if (!StringUtil.isInteger(dto.getCode()) && this.count(new LambdaQueryWrapper<AdminDictionary>().eq(AdminDictionary::getCode, dto.getCode())) > 0) {
            // 字符串code 为 string时不能重复, 为Integer时可以重复
            throw new ErrorException(RType.DICT_DUPLICATE);
        }
        boolean res = this.save(dto.convert(AdminDictionary.class));
        //清除缓存
        JvmCache.del(CacheKey.DICT_LIST_KEY.getKey());
        return res;
    }

    @Override
    public Boolean upd(AdminDictionaryDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        // 因为Code不能重复, 编辑了Code 需单独处理数据
        if (dto.getCode() != null) {
            dto.setCode(dto.getCode().trim());
            // 原数据
            AdminDictionary dict = this.getById(dto.getId());
            //  原数据code != new Code, 判断数据库是否存在修改后的code值 ， code为Integer时不处理
            if (!dict.getCode().equals(dto.getCode().trim())) {
                if (!StringUtil.isInteger(dto.getCode()) && this.count(new LambdaQueryWrapper<AdminDictionary>().eq(AdminDictionary::getCode, dto.getCode())) > 0) {
                    throw new ErrorException(RType.DICT_DUPLICATE);
                }
            }
        }
        boolean res = this.updateById(dto.convert(AdminDictionary.class));
        //清除缓存
        JvmCache.del(CacheKey.DICT_LIST_KEY.getKey());
        return res;
    }


    @Override
    public Boolean updBySort(String id, Integer sort) {
        AdminDictionary dict = new AdminDictionary();
        dict.setId(id);
        dict.setSort(sort);
        boolean b = this.updateById(dict);
        //清除缓存
        JvmCache.del(CacheKey.DICT_LIST_KEY.getKey());
        return b;
    }

    @Override
    public Boolean del(String id) {
        List<String> ids = this.findByIdFetchIds(id);
        boolean res = this.removeByIds(ids);
        //清除缓存
        JvmCache.del(CacheKey.DICT_LIST_KEY.getKey());
        return res;
    }


    /**
     * 根据code 查询下级所有
     * @author wangsong
     * @param code 父级code, 不传查询code，传递了只查询指定code下数据
     * @param isDisable     是否查询禁用数据       = true 查询*默认   = false 不查询
     * @param isBottomLayer 是否需要最后一级数据   = true 需要*默认   = false 不需要
     * @param isTree        是否返回树结构数据    = tree 是*默认     = false 否(返回过滤后的 list列表)
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
        if (StringUtils.isNotBlank(code)) {
            if (StringUtil.isInteger(code)) {
                throw new ErrorException(RType.PARAM_ERROR);
            }
        }

        //2、获取所有字典数据
        List<AdminDictionaryVO> dictListVO = findList(isDisable);

        if (dictListVO.isEmpty()) {
            return dictListVO;
        }

        // 3、是否根据code查询, 找到父级code数据（只有一条）, 如果没有, 设置为父级字典数据为顶级code=0的数据(可能多条)
        List<AdminDictionaryVO> pDictListVO = new ArrayList<>();
        if (StringUtils.isNotBlank(code)) {
            for (AdminDictionaryVO p : dictListVO) {
                if (p.getCode().equals(code)) {
                    pDictListVO.add(p);
                    break;
                }
            }
        } else {
            for (AdminDictionaryVO p : dictListVO) {
                if (PID.equals(p.getPid())) {
                    pDictListVO.add(p);
                }
            }
        }
        if (pDictListVO.isEmpty()) {
            // 没有顶级code数据
            return pDictListVO;
        }

        // 4、数据过滤，是否需要最后一级数据（false不需要）
        if (!isBottomLayer) {
            dictListVO = dictListVO.stream().filter(i -> !StringUtil.isInteger(i.getCode())).collect(Collectors.toList());
        }

        // 5、递归添加下级数据, pDictListVO 为tree数据, diceIds 为指定code层级下所有字典id收集
        List<String> diceIds = new ArrayList<>();
        // 开始递归
        for (AdminDictionaryVO pDictVO : pDictListVO) {
            diceIds.add(pDictVO.getId());
            this.nextLowerNode(dictListVO, pDictVO, diceIds);
        }

        // 6、判断返回 tree 还是 / list(list前端自行解析list展示)
        if (isTree) {
            return pDictListVO;
        } else {
            return dictListVO.stream().filter(i -> diceIds.contains(i.getId())).collect(Collectors.toList());
        }
    }


    /**
     * 查询下级所有Id,  包括禁用数据的Id,包括自己的Id
     * @author wangsong
     * @param id
     * @date 2020/8/8 0008 1:16
     * @return java.util.List<java.lang.String>
     * @version 1.0.0
     */
    @Override
    public List<String> findByIdFetchIds(String id) {
        List<String> ids = new ArrayList<>();
        ids.add(id);
        // 查询所有
        List<AdminDictionaryVO> dictList = this.findList(true);
        List<AdminDictionaryVO> dictVoList = BeanDtoVoUtil.listVo(dictList, AdminDictionaryVO.class);
        for (AdminDictionaryVO adminDictionaryVO : dictVoList) {
            if (id.equals(adminDictionaryVO.getId())) {
                // 递归添加下级数据
                nextLowerNode(dictVoList, adminDictionaryVO, ids);
            }
        }
        return ids;
    }


    /**
     * 查询所有数据并根据code分组，不包括禁用数据 (前端缓存该数据)
     * <p>
     *     key - value 形式，因为所有添加下层数据是引用。每一个key下的value 数据依然有所有的层级关系数据
     * </p>
     * @author wangsong
     * @date 2020/8/8 0008 1:07
     * @return java.util.Map<java.lang.String, com.ws.ldy.modules.admin.model.vo.AdminDictionaryVO>
     * @version 1.0.0
     */
    @Override
    public Map<String, AdminDictionaryCodeGroup> findCodeGroup() {
        List<AdminDictionaryVO> dictList = findList(false);
        List<AdminDictionaryCodeGroup> dictionaryCodeGroupList = BeanDtoVoUtil.listVo(dictList, AdminDictionaryCodeGroup.class);
        Map<String, AdminDictionaryCodeGroup> dictGroupMap = new HashMap<>();
        // return -按添加顺序排序
        for (AdminDictionaryCodeGroup fatherDictVo : dictionaryCodeGroupList) {
            // 不添加Integer参数类型，设置当前数据为父级，不论当前层次的，递归获取所有当前层次的下级数据
            if (StringUtil.isInteger(fatherDictVo.getCode())) {
                continue;
            }
            dictGroupMap.put(fatherDictVo.getCode(), fatherDictVo);
            // 添加子级
            for (AdminDictionaryCodeGroup dictVo : dictionaryCodeGroupList) {
                if (dictVo.getPid().equals(fatherDictVo.getId())) {
                    if (fatherDictVo.getDictMap() == null) {
                        LinkedHashMap<String, AdminDictionaryCodeGroup> map = new LinkedHashMap<>();
                        map.put(dictVo.getCode(), dictVo);
                        fatherDictVo.setDictMap(map);
                    } else {
                        fatherDictVo.getDictMap().put(dictVo.getCode(), dictVo);
                    }
                }
            }
        }
        return dictGroupMap;
    }


    @Override
    public List<AdminDictionaryVO> findDictCategory(String code) {
        String newPid = PID;
        List<AdminDictionaryVO> dictList = this.findList(true);
        if (StringUtils.isNotBlank(code)) {
            for (AdminDictionaryVO adminDictionaryVO : dictList) {
                if (code.equals(adminDictionaryVO.getCode())) {
                    newPid = adminDictionaryVO.getId();
                    break;
                }
            }
        }
        final String finalNewPid = newPid;
        return dictList.stream().filter(p -> p.getPid().equals(finalNewPid)).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> generateEnum(String enumName) {
        List<AdminDictionaryVO> dict = this.findByCodeFetchDictVO(enumName, true, true, true);
        String enumsJava = null;
        if (enumName.equals("ENUMS")) {
            enumsJava = this.generateEnumJava(dict.get(0));
        } else {
            enumsJava = this.generateEnumJava2(dict.get(0));
        }
        String enumsJs = this.generateEnumJs(dict.get(0));
        Map<String, String> map = new HashMap<>();
        // 完整的枚举字典
        map.put("java", enumsJava);
        // 枚举字典key，直接通过key获取
        map.put("js", enumsJs);
        log.debug(enumsJava);
        log.debug(enumsJs);
        return map;
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
                    ArrayList<AdminDictionaryVO> adminDictionaryVOS = new ArrayList<>();
                    adminDictionaryVOS.add(zDict);
                    pDict.setDictList(adminDictionaryVOS);
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
     * 生成java 代码枚举对象， 请将生成好的代码直接替换到 enums/Enums 类( 指定为3级，所有子模块枚举共用一个枚举类)
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/8/16 0016 0:10
     * @version 1.0.0
     */
    private String generateEnumJava(AdminDictionaryVO dict) {

        StringBuilder sb = new StringBuilder();
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
     * 生成java 代码枚举对象， 请将生成好的代码直接替换到 enums/Enums 小的二级类( 指定为2级，每个子模块枚举一个类)
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/8/16 0016 0:10
     * @version 1.0.0
     */
    private String generateEnumJava2(AdminDictionaryVO dict) {
        String code = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dict.getCode());
        StringBuilder sb = new StringBuilder();
        sb.append("package com.ws.ldy.enums;\n");
        sb.append("\nimport lombok.AllArgsConstructor;");
        sb.append("\nimport lombok.Getter;\n");
        sb.append("\n@SuppressWarnings(\"all\")" +
                "\npublic interface " + code + "{\n");
        // 字段名
        for (AdminDictionaryVO dictField : dict.getDictList()) {
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
                sb.append("            " + /* dictField.getCode() + */"V" + dictValue.getCode()
                        + "(" + dictValue.getCode() + ", \"" + dictValue.getName() + "\"),    // " + dictValue.getDesc() + "\n");
            }
            sb.append("            ;\n");
            sb.append("            private Integer value;\n");
            sb.append("            private String desc;\n");
            sb.append("        }\n");
        }
        sb.append("    }\n");
        return sb.toString();
    }


    /**
     * 拼接js 字典数据-KEY
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/8/16 0016 0:29
     * @version 1.0.0
     */
    private String generateEnumJs(AdminDictionaryVO dict) {
        StringBuilder sb = new StringBuilder();
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


}

