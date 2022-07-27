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
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserOrganVO;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminOrganService;
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
public class AdminOrganServiceImpl extends BaseIServiceImpl<AdminOrganMapper, AdminOrgan> implements AdminOrganService {

    /**
     * 顶级父Id 数据Id
     */
    private final static String PID = "0";

    @Override
    public List<AdminOrganVO> list(AdminOrganQuery query) {
        if (query.getIsTree() == null) {
            query.setIsTree(false);
        }
        LambdaQueryWrapper<AdminOrgan> queryWrapper = new LambdaQueryWrapper<AdminOrgan>()
                .eq(query.getDisable() != null, AdminOrgan::getDisable, query.getDisable())
                .eq(StringUtils.isNotBlank(query.getPid()), AdminOrgan::getPid, query.getPid())
                .in(query.getIds() != null && query.getIds().size() > 0, AdminOrgan::getId, query.getIds())
                .orderByDesc(AdminOrgan::getCreateTime);
        List<AdminOrganVO> listVo = BeanDtoVoUtil.listVo(this.list(queryWrapper), AdminOrganVO.class);
        if (!query.getIsTree()) {
            return listVo;
        } else {
            List<AdminOrganVO> treeList = new ArrayList<>();
            for (AdminOrganVO adminOrganVO : listVo) {
                if (PID.equals(adminOrganVO.getPid())) {
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
    public AdminUserOrganVO findNextOrgans(List<AdminOrganVO> organVOs, String organIds) {
        if (StringUtils.isBlank(organIds)) {
            return null;
        }
        if (organVOs == null || organVOs.size() == 0) {
            return null;
        }
        List<AdminUserOrganVO> adminUserOrganVOS = BeanDtoVoUtil.listVo(organVOs, AdminUserOrganVO.class);
        // 获取当前数据
        Map<String, AdminUserOrganVO> adminUserOrganVOMaps = adminUserOrganVOS.stream()
                .collect(Collectors.toMap(AdminUserOrganVO::getId, p -> p));
        String[] organIdsArray = organIds.split(",");
        AdminUserOrganVO vo = null;
        StringBuilder organNames = new StringBuilder("");
        if (organIdsArray.length > 0) {
            vo = BeanDtoVoUtil.convert(adminUserOrganVOMaps.get(organIdsArray[0]), AdminUserOrganVO.class);
            if (vo != null) {
                organNames.append(vo.getName());
            }
        }
        if (vo != null && organIdsArray.length > 1) {
            AdminUserOrganVO voTwo = BeanDtoVoUtil.convert(adminUserOrganVOMaps.get(organIdsArray[1]), AdminUserOrganVO.class);
            vo.setOrgan(voTwo);
            if (voTwo != null) {
                organNames.append("/").append(voTwo.getName());
            }
        }
        if (vo != null && vo.getOrgan() != null && organIdsArray.length > 2) {
            AdminUserOrganVO voThree = BeanDtoVoUtil.convert(adminUserOrganVOMaps.get(organIdsArray[2]), AdminUserOrganVO.class);
            vo.getOrgan().setOrgan(voThree);
            if (voThree != null) {
                organNames.append("/").append(voThree.getName());
            }
        }
        //
        if (vo != null) {
            vo.setOrganNames(organNames.toString());
        }
        return vo;
    }
}
