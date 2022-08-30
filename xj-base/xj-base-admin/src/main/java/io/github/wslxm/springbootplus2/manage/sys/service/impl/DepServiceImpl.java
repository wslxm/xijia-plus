package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.constant.NumberConstant;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.DepMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.DepDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Dep;
import io.github.wslxm.springbootplus2.manage.sys.model.query.DepQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DepVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.SysUserDepVO;
import io.github.wslxm.springbootplus2.manage.sys.service.DepService;
import io.github.wslxm.springbootplus2.starter.redis.lock.XjDistributedLock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 基础表--组织机构
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2021-09-30 16:10:57
 */
@Service
public class DepServiceImpl extends BaseServiceImpl<DepMapper, Dep> implements DepService {

    /**
     * 顶级父Id 数据Id
     */
    private final static String PID = "0";

    @Override
    public List<DepVO> list(DepQuery query) {
        Boolean isTree = ObjectUtil.defaultIfNull(query.getIsTree(), false);
        LambdaQueryWrapper<Dep> queryWrapper = new LambdaQueryWrapper<Dep>()
                .eq(query.getDisable() != null, Dep::getDisable, query.getDisable())
                .eq(StringUtils.isNotBlank(query.getPid()), Dep::getPid, query.getPid())
                .in(query.getIds() != null && query.getIds().size() > 0, Dep::getId, query.getIds())
                .orderByDesc(Dep::getCreateTime);
        List<DepVO> listVo = BeanDtoVoUtil.listVo(this.list(queryWrapper), DepVO.class);
        if (!isTree) {
            return listVo;
        } else {
            List<DepVO> treeList = new ArrayList<>();
            for (DepVO adminDepVO : listVo) {
                if (PID.equals(adminDepVO.getPid())) {
                    treeList.add(adminDepVO);
                    nextDep(listVo, adminDepVO);
                }
            }
            return treeList;
        }
    }


    /**
     * 递归
     *
     * @param listVo
     * @param adminDepVO
     * @return void
     * @author wangsong
     * @date 2021/9/30 0030 17:17
     * @version 1.0.1
     */
    private void nextDep(List<DepVO> listVo, DepVO adminDepVO) {
        if (adminDepVO.getRoot().equals(Base.DepRoot.V3.getValue())) {
            return;
        }
        for (DepVO nextDepVO : listVo) {
            if (adminDepVO.getId().equals(nextDepVO.getPid())) {
                if (adminDepVO.getDeps() == null) {
                    List<DepVO> deps = new ArrayList<>();
                    deps.add(nextDepVO);
                    adminDepVO.setDeps(deps);
                } else {
                    adminDepVO.getDeps().add(nextDepVO);
                }
                nextDep(listVo, nextDepVO);
            }
        }
    }


    @Override
    @XjDistributedLock(lockName = "'xj-sys-dep_'+#dto.code", waitTime = 5L)
    public String insert(DepDTO dto) {
        this.isCodeRepeat(dto.getCode(), null);
        Dep entity = dto.convert(Dep.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    @XjDistributedLock(lockName = "'xj-sys-dep_'+#dto.code", waitTime = 5L)
    public boolean upd(String id, DepDTO dto) {
        this.isCodeRepeat(dto.getCode(), id);
        Dep entity = dto.convert(Dep.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    public boolean del(String id) {
        // 删除第三级数据 --> 如果传入id是第一级,获取第二级所有id，并删除第三级数据
        List<Dep> list = this.list(new LambdaQueryWrapper<Dep>().select(Dep::getId).eq(Dep::getPid, id));
        if (!list.isEmpty()) {
            List<String> depIds = list.stream().map(Dep::getId).collect(Collectors.toList());
            this.removeByIds(depIds);
        }
        // 删除第二级数据+第一级数据
        this.remove(new LambdaQueryWrapper<Dep>().and(p ->
                p.eq(Dep::getPid, id)
                        .or().eq(Dep::getId, id)));
        return this.removeById(id);
    }


    @Override
    public SysUserDepVO findNextDeps(List<DepVO> depVos, String depIds) {
        if (StringUtils.isBlank(depIds)) {
            return null;
        }
        if (depVos == null || depVos.size() == 0) {
            return null;
        }
        List<SysUserDepVO> adminSysUserDepVos = BeanDtoVoUtil.listVo(depVos, SysUserDepVO.class);
        // 获取当前数据
        Map<String, SysUserDepVO> adminSysUserDepVoMaps = adminSysUserDepVos.stream()
                .collect(Collectors.toMap(SysUserDepVO::getId, p -> p));
        String[] depIdsArray = depIds.split(",");
        SysUserDepVO vo = null;
        StringBuilder depNames = new StringBuilder("");
        if (depIdsArray.length > 0) {
            vo = BeanDtoVoUtil.convert(adminSysUserDepVoMaps.get(depIdsArray[0]), SysUserDepVO.class);
            if (vo != null) {
                depNames.append(vo.getName());
            }
        }
        if (vo != null && depIdsArray.length > 1) {
            SysUserDepVO voTwo = BeanDtoVoUtil.convert(adminSysUserDepVoMaps.get(depIdsArray[1]), SysUserDepVO.class);
            vo.setDep(voTwo);
            if (voTwo != null) {
                depNames.append("/").append(voTwo.getName());
            }
        }
        if (vo != null && vo.getDep() != null && depIdsArray.length > NumberConstant.TWO) {
            SysUserDepVO voThree = BeanDtoVoUtil.convert(adminSysUserDepVoMaps.get(depIdsArray[2]), SysUserDepVO.class);
            vo.getDep().setDep(voThree);
            if (voThree != null) {
                depNames.append("/").append(voThree.getName());
            }
        }
        //
        if (vo != null) {
            vo.setDepNames(depNames.toString());
        }
        return vo;
    }


    /**
     * 角色code重复验证
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2022/8/20 0020 14:33
     * @version 1.0.0
     */
    private void isCodeRepeat(String code, String excludeId) {
        long count = this.count(new LambdaQueryWrapper<Dep>()
                .eq(Dep::getCode, code)
                .ne(Dep::getId, excludeId)
        );
        if (count > 0) {
            throw new ErrorException("角色code已存在");
        }
    }
}
