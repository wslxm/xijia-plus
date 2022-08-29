package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import io.github.wslxm.springbootplus2.common.cache.CacheKey;
import io.github.wslxm.springbootplus2.common.cache.XjCacheUtil;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.result.ResultType;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.paramverification.StringUtil;
import io.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.DictionaryMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.DictionaryDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Dictionary;
import io.github.wslxm.springbootplus2.manage.sys.model.query.DictionaryQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryCodeGroup;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryVO;
import io.github.wslxm.springbootplus2.manage.sys.service.DictionaryService;
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
public class DictionaryServiceImpl extends BaseServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    /**
     * 父级pid
     */
    private static final String PID = "0";

    /**
     * 枚举最顶级
     */
    private static final String ENUMS = "ENUMS";

    @Override
    public List<DictionaryVO> list(DictionaryQuery query) {
        boolean isDisable = ObjectUtil.defaultIfNull( query.getIsDisable() , true);
        boolean isBottomLayer =ObjectUtil.defaultIfNull( query.getIsBottomLayer() , true);
        boolean isTree = ObjectUtil.defaultIfNull(query.getIsTree() , true);
        boolean isNextAll =ObjectUtil.defaultIfNull(query.getIsNextAll() , true);
        String code = query.getCode();

        // 1、判断 code , 不能传递字符串数字来查询
        if (StringUtils.isNotBlank(code)) {
            ValidUtil.isTrue(StringUtil.isInteger(code), "code 参数不能传递数字");
        }
        //2、获取所有字典数据
        List<DictionaryVO> dictListVO = XjCacheUtil.findListAll(isDisable);
        if (dictListVO == null || dictListVO.size() == 0) {
            return dictListVO;
        }

        // 3、是否根据code查询, 获取父级字典数据
        List<DictionaryVO> pDictListVO = new ArrayList<>();
        if (StringUtils.isNotBlank(code)) {
            //  3.1、找到父级code数据(只有一条)
            for (DictionaryVO p : dictListVO) {
                if (p.getCode().equals(code)) {
                    pDictListVO.add(p);
                    break;
                }
            }
        } else {
            // 3.2、如果没有, 设置为父级字典数据为顶级pid=0的数据 (可能多条)
            for (DictionaryVO p : dictListVO) {
                if (PID.equals(p.getPid())) {
                    pDictListVO.add(p);
                }
            }
        }
        // 没有找顶级code数据或没有字典数据,返回空
        if (pDictListVO.isEmpty()) {
            return pDictListVO;
        }

        // 4、数据过滤，是否需要最后一级数据（false不需要）
        if (!isBottomLayer) {
            dictListVO = dictListVO.stream().filter(i -> !StringUtil.isInteger(i.getCode())).collect(Collectors.toList());
        }


        // 5、递归获取 子级数据, pDictListVO 为tree数据(tree),  diceIds为指定code层级下所有字典id收集(返回 list 需要)
        List<String> diceIds = new ArrayList<>();
        // 开始递归
        for (DictionaryVO pDictVO : pDictListVO) {
            diceIds.add(pDictVO.getId());
            if (isNextAll) {
                this.nextLowerNode(dictListVO, pDictVO, diceIds, null);
            } else {
                this.nextLowerNode(dictListVO, pDictVO, diceIds, 1);
            }
        }

        // 6、判断返回 tree | list
        if (isTree) {
            return pDictListVO;
        } else {
            return dictListVO.stream().filter(i -> diceIds.contains(i.getId())).collect(Collectors.toList());
        }
    }


    @Override
    @CacheEvict(value = CacheKey.DICT_LIST_ALL, allEntries = true)
    public String insert(DictionaryDTO dto) {
        if (StringUtils.isBlank(dto.getCode().trim())) {
            throw new ErrorException(ResultType.PARAM_MISSING.getValue(), ResultType.PARAM_MISSING.getMsg() + ":code");
        }
        dto.setCode(dto.getCode().trim());
        if (!StringUtil.isInteger(dto.getCode()) && this.count(new LambdaQueryWrapper<Dictionary>().eq(Dictionary::getCode, dto.getCode())) > 0) {
            // 字符串code 为 string时不能重复, 为Integer时可以重复
            throw new ErrorException(ResultType.DICT_DUPLICATE);
        }
        Dictionary entity = dto.convert(Dictionary.class);
        boolean b = this.save(entity);
        return entity.getId();
    }


    @Override
    @CacheEvict(value = CacheKey.DICT_LIST_ALL, allEntries = true)
    public Boolean upd(String id, DictionaryDTO dto) {
        // 因为Code不能重复, 编辑了Code 需单独处理数据
        if (dto.getCode() != null) {
            dto.setCode(dto.getCode().trim());
            // 原数据
            Dictionary dict = this.getById(id);
            //  原数据code != new Code, 判断数据库是否存在修改后的code值 ， code为Integer时不处理
            if (!dict.getCode().equals(dto.getCode().trim())) {
                if (!StringUtil.isInteger(dto.getCode()) && this.count(new LambdaQueryWrapper<Dictionary>().eq(Dictionary::getCode, dto.getCode())) > 0) {
                    throw new ErrorException(ResultType.DICT_DUPLICATE);
                }
            }
        }
        Dictionary entity = dto.convert(Dictionary.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    @CacheEvict(value = CacheKey.DICT_LIST_ALL, allEntries = true)
    public Boolean del(String id) {
        List<String> ids = this.findByIdFetchIds(id);
        return this.removeByIds(ids);
    }


    @Override
    public Map<String, DictionaryCodeGroup> findCodeGroup() {
        List<DictionaryVO> dictList = XjCacheUtil.findListAll(false);
        List<DictionaryCodeGroup> dictionaryCodeGroupList = BeanDtoVoUtil.listVo(dictList, DictionaryCodeGroup.class);
        Map<String, DictionaryCodeGroup> dictGroupMap = new HashMap<>(dictList.size(), 1);
        // return -按添加顺序排序
        for (DictionaryCodeGroup fatherDictVo : dictionaryCodeGroupList) {
            // 不添加Integer参数类型，设置当前数据为父级，不论当前层次的，递归获取所有当前层次的下级数据
            if (StringUtil.isInteger(fatherDictVo.getCode())) {
                continue;
            }
            dictGroupMap.put(fatherDictVo.getCode(), fatherDictVo);
            // 添加子级
            for (DictionaryCodeGroup dictVo : dictionaryCodeGroupList) {
                if (dictVo.getPid().equals(fatherDictVo.getId())) {
                    if (fatherDictVo.getDictMap() == null) {
                        LinkedHashMap<String, DictionaryCodeGroup> map = new LinkedHashMap<>();
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
    public List<DictionaryVO> findDictCategory(String code) {
        String newPid = PID;
        List<DictionaryVO> dictList = XjCacheUtil.findListAll(true);
        if (StringUtils.isNotBlank(code)) {
            for (DictionaryVO adminDictionaryVO : dictList) {
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
    public DictionaryVO findDictCategoryNext(String code, String nextCode) {
        List<DictionaryVO> dictCategory = findDictCategory(code);
        if (dictCategory == null || dictCategory.isEmpty()) {
            return null;
        }
        Map<String, DictionaryVO> dictMap = dictCategory.stream().collect(Collectors.toMap(DictionaryVO::getCode, p -> p));
        return dictMap.get(nextCode);

    }

    @Override
    public Map<String, String> generateEnum(String enumName) {
        DictionaryQuery query = new DictionaryQuery();
        query.setCode(enumName);
        query.setIsDisable(true);
        query.setIsBottomLayer(true);
        query.setIsTree(true);
        List<DictionaryVO> dict = this.list(query);
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
     * @param recursiveHierarchy   递归层级 1-获取下一级 2-获取下2级 ....以此类推, null不限层级直到没有下级
     */
    private void nextLowerNode(List<DictionaryVO> dictVoList, DictionaryVO pDict, List<String> ids, Integer recursiveHierarchy) {
        // 递归层级控制
        if (recursiveHierarchy != null) {
            if (recursiveHierarchy == 0) {
                return;
            }
            recursiveHierarchy--;
        }
        for (DictionaryVO zDict : dictVoList) {
            // 当前层级类还没有子层级对象就创建/有就追加
            if (zDict.getPid().equals(pDict.getId())) {
                if (pDict.getDictList() == null) {
                    ArrayList<DictionaryVO> adminDictionaryVos = new ArrayList<>();
                    adminDictionaryVos.add(zDict);
                    pDict.setDictList(adminDictionaryVos);
                } else {
                    pDict.getDictList().add(zDict);
                }
                // 获取ids
                ids.add(zDict.getId());
                // 继续添加下级,无限级
                nextLowerNode(dictVoList, zDict, ids, recursiveHierarchy);
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
    private String generateEnumJava(DictionaryVO dict) {

        StringBuilder sb = new StringBuilder();
        sb.append("package io.github.wslxm.core.enums;;\n");
        sb.append("\nimport com.baomidou.mybatisplus.core.enums.IEnum;");
        sb.append("\nimport lombok.AllArgsConstructor;");
        sb.append("\nimport lombok.Getter;\n");
        sb.append("\npublic interface Enums {\n");
        //模块名
        for (DictionaryVO dictModule : dict.getDictList()) {
            sb.append("\n     /** ");
            sb.append("\n      * ").append(dictModule.getDesc());
            sb.append("\n      */ ");
            sb.append("\n    interface ").append(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictModule.getCode() + "{\n"));
            //枚举字典的-枚举名--驼峰模式
            if (dictModule.getDictList() == null) {
                continue;
            }
            for (DictionaryVO dictField : dictModule.getDictList()) {
                String moduleName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictField.getCode());
                sb.append("\n        // ").append(dictField.getDesc()).append("\n");
                sb.append("        @Getter\n");
                sb.append("        @AllArgsConstructor\n");
                sb.append("        enum ").append(moduleName).append(" implements IEnum<Integer> {\n");
                //枚举字典的-枚举属性
                if (dictField.getDictList() == null) {
                    continue;
                }
                for (DictionaryVO dictValue : dictField.getDictList()) {
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
    private String generateEnumJava2(DictionaryVO dict) {
        String code = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dict.getCode());
        StringBuilder sb = new StringBuilder();
        sb.append("package io.github.wslxm.core.enums;\n");
        sb.append("\nimport com.baomidou.mybatisplus.core.enums.IEnum;");
        sb.append("\nimport lombok.AllArgsConstructor;");
        sb.append("\nimport lombok.Getter;\n");
        sb.append("\n@SuppressWarnings(\"all\")");
        sb.append("\npublic interface ").append(code).append("{\n");
        // 字段名
        for (DictionaryVO dictField : dict.getDictList()) {
            String moduleName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictField.getCode());
            sb.append("\n        // ").append(dictField.getDesc()).append("\n");
            sb.append("        @Getter\n");
            sb.append("        @AllArgsConstructor\n");
            sb.append("        enum ").append(moduleName).append(" implements IEnum<Integer> {\n");
            //枚举字典的-枚举属性
            if (dictField.getDictList() == null) {
                continue;
            }
            for (DictionaryVO dictValue : dictField.getDictList()) {
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
    private String generateEnumJs(DictionaryVO dict) {
        StringBuilder sb = new StringBuilder();
        //
        sb.append("var Enums = {");
        //模块名
        for (DictionaryVO dictModule : dict.getDictList()) {
            sb.append("\n    // ").append(dictModule.getName());
            sb.append("\n    ").append(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dictModule.getCode() + ": {"));
            //枚举字典的-枚举名--驼峰模式
            for (DictionaryVO dictField : dictModule.getDictList()) {
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
        List<DictionaryVO> dictList = XjCacheUtil.findListAll(true);
        List<DictionaryVO> dictVoList = BeanDtoVoUtil.listVo(dictList, DictionaryVO.class);
        for (DictionaryVO adminDictionaryVO : dictVoList) {
            if (id.equals(adminDictionaryVO.getId())) {
                // 递归添加下级数据
                nextLowerNode(dictVoList, adminDictionaryVO, ids, null);
            }
        }
        return ids;
    }


    /**
     * 查询所有
     * @return list
     */
    @Override
    @Cacheable(value = CacheKey.DICT_LIST_ALL)
    public List<DictionaryVO> findListAll() {
        List<Dictionary> adminDictionaries = baseMapper.selectList(new LambdaQueryWrapper<Dictionary>()
                .orderByAsc(Dictionary::getSort)
                .orderByAsc(Dictionary::getCode)
        );
        return BeanDtoVoUtil.listVo(adminDictionaries, DictionaryVO.class);
    }
}

