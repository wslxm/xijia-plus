package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminOrganMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminOrganDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminOrgan;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminOrganQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminOrganVO;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminOrganService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
public class AdminOrganServiceImpl extends BaseIServiceImpl<AdminOrganMapper, AdminOrgan> implements AdminOrganService {

    @Override
    public List<AdminOrganVO> list(AdminOrganQuery query) {
        if (query.getIsTree() == null) {
            query.setIsTree(false);
        }
        LambdaQueryWrapper<AdminOrgan> queryWrapper = new LambdaQueryWrapper<AdminOrgan>()
                .eq(query.getDisable() != null, AdminOrgan::getDisable, query.getDisable())
                .eq(StringUtils.isNotBlank(query.getPid()), AdminOrgan::getPid, query.getPid())
                .orderByDesc(AdminOrgan::getCreateTime);
        List<AdminOrganVO> listVo = BeanDtoVoUtil.listVo(this.list(queryWrapper), AdminOrganVO.class);
        if (!query.getIsTree()) {
            return listVo;
        } else {
            List<AdminOrganVO> treeList = new ArrayList<>();
            for (AdminOrganVO adminOrganVO : listVo) {
                if ("0".equals(adminOrganVO.getPid())) {
                    treeList.add(adminOrganVO);
                    newxOrgan(listVo, adminOrganVO);
                }
            }
            return treeList;
        }
    }


    /**
     * 递归
     *
     * @param listVo
     * @param adminOrganVO
     * @return void
     * @author wangsong
     * @date 2021/9/30 0030 17:17
     * @version 1.0.1
     */
    private void newxOrgan(List<AdminOrganVO> listVo, AdminOrganVO adminOrganVO) {
        if (adminOrganVO.getRoot().equals(Base.OrganRoot.V3.getValue())) {
            return;
        }
        for (AdminOrganVO nextAdminOrganVO : listVo) {
            if (adminOrganVO.getId().equals(nextAdminOrganVO.getPid())) {
                if (adminOrganVO.getOrgans() == null) {
                    List<AdminOrganVO> organs = new ArrayList<>();
                    organs.add(nextAdminOrganVO);
                    adminOrganVO.setOrgans(organs);
                } else {
                    adminOrganVO.getOrgans().add(nextAdminOrganVO);
                }
                newxOrgan(listVo, nextAdminOrganVO);
            }
        }
    }


    @Override
    public String insert(AdminOrganDTO dto) {
        AdminOrgan entity = dto.convert(AdminOrgan.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    public boolean upd(String id, AdminOrganDTO dto) {
        AdminOrgan entity = dto.convert(AdminOrgan.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    public boolean del(String id) {
        // 删除第三级数据 --> 如果传入id是第一级,获取第二级所有id，并删除第三级数据
        List<AdminOrgan> list = this.list(new LambdaQueryWrapper<AdminOrgan>().select(AdminOrgan::getId).eq(AdminOrgan::getPid, id));
        if (!list.isEmpty()) {
            List<String> organIds = list.stream().map(AdminOrgan::getId).collect(Collectors.toList());
            this.removeByIds(organIds);
        }
        // 删除第二级数据+第一级数据
        this.remove(new LambdaQueryWrapper<AdminOrgan>().and(p ->
                p.eq(AdminOrgan::getPid, id)
                        .or().eq(AdminOrgan::getId, id)));
        return this.removeById(id);
    }


    @Override
    public AdminOrganVO findNextOrgans(String id) {
        // 获取当前数据
        AdminOrgan adminOrgan = this.getOne(new LambdaQueryWrapper<AdminOrgan>()
                .select(AdminOrgan::getId, AdminOrgan::getPid, AdminOrgan::getRoot, AdminOrgan::getName, AdminOrgan::getCode)
                .eq(AdminOrgan::getId, id));
        if (adminOrgan == null) {
            return null;
        }
        AdminOrganVO adminOrganVO = BeanDtoVoUtil.convert(adminOrgan, AdminOrganVO.class);
        //
        if (adminOrgan.getRoot().equals(Base.OrganRoot.V1.getValue())) {
            return adminOrganVO;
        } else if (adminOrgan.getRoot().equals(Base.OrganRoot.V2.getValue())) {
            // 获取上级
            AdminOrgan organOne = this.getOne(new LambdaQueryWrapper<AdminOrgan>()
                    .select(AdminOrgan::getId, AdminOrgan::getPid, AdminOrgan::getRoot, AdminOrgan::getName, AdminOrgan::getCode)
                    .eq(AdminOrgan::getPid, adminOrgan.getPid()));
            if (organOne == null) {
                return adminOrganVO;
            }
            AdminOrganVO vo = BeanDtoVoUtil.convert(organOne, AdminOrganVO.class);
            vo.setOrgans(new ArrayList<AdminOrganVO>());
            vo.getOrgans().add(adminOrganVO);
            return adminOrganVO;
        } else if (adminOrgan.getRoot().equals(Base.OrganRoot.V3.getValue())) {
            // 获取上级
            AdminOrgan organTwo = this.getOne(new LambdaQueryWrapper<AdminOrgan>()
                    .select(AdminOrgan::getId, AdminOrgan::getPid, AdminOrgan::getRoot, AdminOrgan::getName, AdminOrgan::getCode)
                    .eq(AdminOrgan::getId, adminOrganVO.getPid()));
            if (organTwo == null) {
                return adminOrganVO;
            }
            AdminOrganVO organTwoVO = BeanDtoVoUtil.convert(organTwo, AdminOrganVO.class);
            organTwoVO.setOrgans(new ArrayList<>());
            organTwoVO.getOrgans().add(adminOrganVO);

            // 获取上级的上级
            AdminOrgan organOne = this.getOne(new LambdaQueryWrapper<AdminOrgan>()
                    .select(AdminOrgan::getId, AdminOrgan::getRoot, AdminOrgan::getName, AdminOrgan::getCode)
                    .eq(AdminOrgan::getId, organTwoVO.getPid()));
            if (organOne == null) {
                return organTwoVO;
            }
            AdminOrganVO vo = BeanDtoVoUtil.convert(organOne, AdminOrganVO.class);
            vo.setOrgans(new ArrayList<>());
            vo.getOrgans().add(organTwoVO);
            return vo;
        }
        return null;
    }
}
