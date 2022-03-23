package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import io.github.wslxm.springbootplus2.cache.XjCacheUtil2;
import io.github.wslxm.springbootplus2.core.cache.XjCacheUtil;
import io.github.wslxm.springbootplus2.core.cache.cache.CacheKey;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.cache.cache.CacheKey2;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.paramverification.StringUtil;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminDictionaryMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminDictionaryDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminDictionary;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminDictionaryQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminDictionaryCodeGroup;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminDictionaryVO;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangsong
 */
@Service
@Slf4j
public class AdminDictionaryServiceImpl extends BaseIServiceImpl<AdminDictionaryMapper, AdminDictionary> implements AdminDictionaryService {

    /**
     * 父级pid
     */
    private static final String PID = "0";

    /**
     * 枚举最顶级
     */
    private static final String ENUMS = "ENUMS";

    @Override
    public List<AdminDictionaryVO> list(AdminDictionaryQuery query) {
        Boolean isDisable = query.getIsDisable();
        Boolean isBottomLayer = query.getIsBottomLayer();
        Boolean isTree = query.getIsTree();
        String code = query.getCode();
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
                throw new ErrorException(RType.PARAM_ERROR.getValue(), RType.PARAM_ERROR.getMsg() + ":code");
            }
        }

        //2、获取所有字典数据
        List<AdminDictionaryVO> dictListVO = XjCacheUtil2.findListALL(isDisable);

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


    @Override
    @CacheEvict(value = CacheKey2.DICT_LIST_ALL, allEntries = true)
    public String insert(AdminDictionaryDTO dto) {
        if (StringUtils.isBlank(dto.getCode().trim())) {
            throw new ErrorException(RType.PARAM_MISSING.getValue(), RType.PARAM_MISSING.getMsg() + ":code");
        }
        dto.setCode(dto.getCode().trim());
        if (!StringUtil.isInteger(dto.getCode()) && this.count(new LambdaQueryWrapper<AdminDictionary>().eq(AdminDictionary::getCode, dto.getCode())) > 0) {
            // 字符串code 为 string时不能重复, 为Integer时可以重复
            throw new ErrorException(RType.DICT_DUPLICATE);
        }
        AdminDictionary entity = dto.convert(AdminDictionary.class);
        boolean b = this.save(entity);
        return entity.getId();
    }


    @Override
    @CacheEvict(value = CacheKey2.DICT_LIST_ALL, allEntries = true)
    public Boolean upd(String id, AdminDictionaryDTO dto) {
        // 因为Code不能重复, 编辑了Code 需单独处理数据
        if (dto.getCode() != null) {
            dto.setCode(dto.getCode().trim());
            // 原数据
            AdminDictionary dict = this.getById(id);
            //  原数据code != new Code, 判断数据库是否存在修改后的code值 ， code为Integer时不处理
            if (!dict.getCode().equals(dto.getCode().trim())) {
                if (!StringUtil.isInteger(dto.getCode()) && this.count(new LambdaQueryWrapper<AdminDictionary>().eq(AdminDictionary::getCode, dto.getCode())) > 0) {
                    throw new ErrorException(RType.DICT_DUPLICATE);
                }
            }
        }
        AdminDictionary entity = dto.convert(AdminDictionary.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    @CacheEvict(value = CacheKey2.DICT_LIST_ALL, allEntries = true)
    public Boolean del(String id) {
        List<String> ids = this.findByIdFetchIds(id);
        return this.removeByIds(ids);
    }


    @Override
    public Map<String, AdminDictionaryCodeGroup> findCodeGroup() {
        List<AdminDictionaryVO> dictList = XjCacheUtil2.findListALL(false);
        List<AdminDictionaryCodeGroup> dictionaryCodeGroupList = BeanDtoVoUtil.listVo(dictList, AdminDictionaryCodeGroup.class);
        Map<String, AdminDictionaryCodeGroup> dictGroupMap = new HashMap<>(dictList.size(), 1);
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
        List<AdminDictionaryVO> dictList = XjCacheUtil2.findListALL(true);
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
        AdminDictionaryQuery query = new AdminDictionaryQuery();
        query.setCode(enumName);
        query.setIsDisable(true);
        query.setIsBottomLayer(true);
        query.setIsTree(true);
        List<AdminDictionaryVO> dict = this.list(query);
        String enumsJava = null;
        // 1、生成java枚举类
        if (ENUMS.equals(enumName)) {
            // 生成一个大的 Enums 类
            enumsJava = this.generateEnumJava(dict.get(0));
        } else {
            // 生成小模块的枚举类
            enumsJava = this.generateEnumJava2(dict.get(0));
        }

        // 2、生成js key
        // enumName 不为 ENUMS 时 js 重新查询字典数据
        if (!ENUMS.equals(enumName)) {
            query.setCode(ENUMS);
            dict = this.list(query);
        }
        String enumsJs = this.generateEnumJs(dict.get(0));

        // 3、返回
        Map<String, String> map = new HashMap<>(2, 1);
        // 完整的枚举字典
        map.put("java", enumsJava);
        // 枚举字典key，直接通过key获取
        map.put("js", enumsJs);
        log.info(enumsJava);
        log.info(enumsJs);
        return map;
    }


    /**
     * 递归添加下级数据
     *
     * @param dictVoList 所有节点
     * @param pDict      上级节点
     * @param ids        收集指定code下所有字典数据 id
     */
    private void nextLowerNode(List<AdminDictionaryVO> dictVoList, AdminDictionaryVO pDict, List<String> ids) {
        for (AdminDictionaryVO zDict : dictVoList) {
            // 当前层级类还没有子层级对象就创建/有就追加
            if (zDict.getPid().equals(pDict.getId())) {
                if (pDict.getDictList() == null) {
                    ArrayList<AdminDictionaryVO> adminDictionaryVos = new ArrayList<>();
                    adminDictionaryVos.add(zDict);
                    pDict.setDictList(adminDictionaryVos);
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
     *
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/8/16 0016 0:10
     * @version 1.0.1
     */
    private String generateEnumJava(AdminDictionaryVO dict) {

        StringBuilder sb = new StringBuilder();
        sb.append("package io.github.wslxm.core.enums;;\n");
        sb.append("\nimport com.baomidou.mybatisplus.core.enums.IEnum;");
        sb.append("\nimport lombok.AllArgsConstructor;");
        sb.append("\nimport lombok.Getter;\n");
        sb.append("\npublic interface Enums {\n");
        //模块名
        for (AdminDictionaryVO dictModule : dict.getDictList()) {
            sb.append("\n     /** ");
            sb.append("\n      * ").append(dictModule.getDesc());
            sb.append("\n      */ ");
            sb.append("\n    interface ").append(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictModule.getCode() + "{\n"));
            //枚举字典的-枚举名--驼峰模式
            if (dictModule.getDictList() == null) {
                continue;
            }
            for (AdminDictionaryVO dictField : dictModule.getDictList()) {
                String moduleName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictField.getCode());
                sb.append("\n        // ").append(dictField.getDesc()).append("\n");
                sb.append("        @Getter\n");
                sb.append("        @AllArgsConstructor\n");
                sb.append("        enum ").append(moduleName).append(" implements IEnum<Integer> {\n");
                //枚举字典的-枚举属性
                if (dictField.getDictList() == null) {
                    continue;
                }
                for (AdminDictionaryVO dictValue : dictField.getDictList()) {
                    sb.append("            ").append(dictField.getCode()).append("_")
                            .append(dictValue.getCode()).append("(").append(dictValue.getCode()).append(", \"")
                            .append(dictValue.getName()).append("\"),    // ").append(dictValue.getDesc()).append("\n");
                }
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
     *
     * @author wangsong
     * @email 1720696548@qq.com
     * @date 2020/8/16 0016 0:10
     * @version 1.0.1
     */
    private String generateEnumJava2(AdminDictionaryVO dict) {
        String code = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dict.getCode());
        StringBuilder sb = new StringBuilder();
        sb.append("package io.github.wslxm.core.enums;\n");
        sb.append("\nimport com.baomidou.mybatisplus.core.enums.IEnum;");
        sb.append("\nimport lombok.AllArgsConstructor;");
        sb.append("\nimport lombok.Getter;\n");
        sb.append("\n@SuppressWarnings(\"all\")");
        sb.append("\npublic interface ").append(code).append("{\n");
        // 字段名
        for (AdminDictionaryVO dictField : dict.getDictList()) {
            String moduleName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictField.getCode());
            sb.append("\n        // ").append(dictField.getDesc()).append("\n");
            sb.append("        @Getter\n");
            sb.append("        @AllArgsConstructor\n");
            sb.append("        enum ").append(moduleName).append(" implements IEnum<Integer> {\n");
            //枚举字典的-枚举属性
            if (dictField.getDictList() == null) {
                continue;
            }
            for (AdminDictionaryVO dictValue : dictField.getDictList()) {
                /// sb.append("            " + /* dictField.getCode() + */"V" + dictValue.getCode()
                sb.append("            V").append(dictValue.getCode())
                        .append("(").append(dictValue.getCode())
                        .append(", \"").append(dictValue.getName()).append("\"),    // ")
                        .append(dictValue.getDesc()).append("\n");
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
     *
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/8/16 0016 0:29
     * @version 1.0.1
     */
    private String generateEnumJs(AdminDictionaryVO dict) {
        StringBuilder sb = new StringBuilder();
        //
        sb.append("var Enums = {");
        //模块名
        for (AdminDictionaryVO dictModule : dict.getDictList()) {
            sb.append("\n    // ").append(dictModule.getName());
            sb.append("\n    ").append(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictModule.getCode() + ": {"));
            //枚举字典的-枚举名--驼峰模式
            for (AdminDictionaryVO dictField : dictModule.getDictList()) {
                String moduleName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictField.getCode());
                sb.append("\n        ").append(moduleName).append(" : \"").append(dictField.getCode()).append("\",  // ").append(dictField.getName());
            }
            sb.append("\n    },");
        }
        sb.append("\n};");
        return sb.toString();
    }


    /**
     * 查询下级所有Id,  包括禁用数据的Id,包括自己的Id
     *
     * @param id
     * @return java.util.List<java.lang.String>
     * @author wangsong
     * @date 2020/8/8 0008 1:16
     * @version 1.0.1
     */
    private List<String> findByIdFetchIds(String id) {
        List<String> ids = new ArrayList<>();
        ids.add(id);
        // 查询所有
        List<AdminDictionaryVO> dictList = XjCacheUtil2.findListALL(true);
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
     * 查询所有
     * @return list
     */
    @Override
    @Cacheable(value = CacheKey2.DICT_LIST_ALL)
    public List<AdminDictionaryVO> findListALL() {
        List<AdminDictionary> adminDictionaries = baseMapper.selectList(new LambdaQueryWrapper<AdminDictionary>()
                .orderByAsc(AdminDictionary::getSort)
                .orderByAsc(AdminDictionary::getCode)
        );
        return BeanDtoVoUtil.listVo(adminDictionaries, AdminDictionaryVO.class);
    }
}

