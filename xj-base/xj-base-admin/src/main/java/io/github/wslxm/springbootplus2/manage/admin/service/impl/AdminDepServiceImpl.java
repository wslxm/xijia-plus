package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminDepMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminDepDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminDep;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminDepQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminDepVO;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserDepVO;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminDepService;
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
public class AdminDepServiceImpl extends BaseIServiceImpl<AdminDepMapper, AdminDep> implements AdminDepService {

    /**
     * 顶级父Id 数据Id
     */
    private final static String PID = "0";

    @Override
    public List<AdminDepVO> list(AdminDepQuery query) {
        Boolean isTree = ObjectUtil.defaultIfNull(query.getIsTree(), false);
        LambdaQueryWrapper<AdminDep> queryWrapper = new LambdaQueryWrapper<AdminDep>()
                .eq(query.getDisable() != null, AdminDep::getDisable, query.getDisable())
                .eq(StringUtils.isNotBlank(query.getPid()), AdminDep::getPid, query.getPid())
                .in(query.getIds() != null && query.getIds().size() > 0, AdminDep::getId, query.getIds())
                .orderByDesc(AdminDep::getCreateTime);
        List<AdminDepVO> listVo = BeanDtoVoUtil.listVo(this.list(queryWrapper), AdminDepVO.class);
        if (!isTree) {
            return listVo;
        } else {
            List<AdminDepVO> treeList = new ArrayList<>();
            for (AdminDepVO adminDepVO : listVo) {
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
    private void nextDep(List<AdminDepVO> listVo, AdminDepVO adminDepVO) {
        if (adminDepVO.getRoot().equals(Base.DepRoot.V3.getValue())) {
            return;
        }
        for (AdminDepVO nextAdminDepVO : listVo) {
            if (adminDepVO.getId().equals(nextAdminDepVO.getPid())) {
                if (adminDepVO.getDeps() == null) {
                    List<AdminDepVO> deps = new ArrayList<>();
                    deps.add(nextAdminDepVO);
                    adminDepVO.setDeps(deps);
                } else {
                    adminDepVO.getDeps().add(nextAdminDepVO);
                }
                nextDep(listVo, nextAdminDepVO);
            }
        }
    }


    @Override
    public String insert(AdminDepDTO dto) {
        AdminDep entity = dto.convert(AdminDep.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    public boolean upd(String id, AdminDepDTO dto) {
        AdminDep entity = dto.convert(AdminDep.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    public boolean del(String id) {
        // 删除第三级数据 --> 如果传入id是第一级,获取第二级所有id，并删除第三级数据
        List<AdminDep> list = this.list(new LambdaQueryWrapper<AdminDep>().select(AdminDep::getId).eq(AdminDep::getPid, id));
        if (!list.isEmpty()) {
            List<String> depIds = list.stream().map(AdminDep::getId).collect(Collectors.toList());
            this.removeByIds(depIds);
        }
        // 删除第二级数据+第一级数据
        this.remove(new LambdaQueryWrapper<AdminDep>().and(p ->
                p.eq(AdminDep::getPid, id)
                        .or().eq(AdminDep::getId, id)));
        return this.removeById(id);
    }


    @Override
    public AdminUserDepVO findNextDeps(List<AdminDepVO> depVOs, String depIds) {
        if (StringUtils.isBlank(depIds)) {
            return null;
        }
        if (depVOs == null || depVOs.size() == 0) {
            return null;
        }
        List<AdminUserDepVO> adminUserDepVOS = BeanDtoVoUtil.listVo(depVOs, AdminUserDepVO.class);
        // 获取当前数据
        Map<String, AdminUserDepVO> adminUserDepVOMaps = adminUserDepVOS.stream()
                .collect(Collectors.toMap(AdminUserDepVO::getId, p -> p));
        String[] depIdsArray = depIds.split(",");
        AdminUserDepVO vo = null;
        StringBuilder depNames = new StringBuilder("");
        if (depIdsArray.length > 0) {
            vo = BeanDtoVoUtil.convert(adminUserDepVOMaps.get(depIdsArray[0]), AdminUserDepVO.class);
            if (vo != null) {
                depNames.append(vo.getName());
            }
        }
        if (vo != null && depIdsArray.length > 1) {
            AdminUserDepVO voTwo = BeanDtoVoUtil.convert(adminUserDepVOMaps.get(depIdsArray[1]), AdminUserDepVO.class);
            vo.setDep(voTwo);
            if (voTwo != null) {
                depNames.append("/").append(voTwo.getName());
            }
        }
        if (vo != null && vo.getDep() != null && depIdsArray.length > 2) {
            AdminUserDepVO voThree = BeanDtoVoUtil.convert(adminUserDepVOMaps.get(depIdsArray[2]), AdminUserDepVO.class);
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
}
