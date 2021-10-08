package com.ws.ldy.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.core.enums.Base;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.manage.admin.mapper.AdminOgranMapper;
import com.ws.ldy.manage.admin.model.dto.AdminOgranDTO;
import com.ws.ldy.manage.admin.model.entity.AdminOgran;
import com.ws.ldy.manage.admin.model.query.AdminOgranQuery;
import com.ws.ldy.manage.admin.model.vo.AdminOgranVO;
import com.ws.ldy.manage.admin.service.AdminOgranService;
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
 * @author ws
 * @email 1720696548@qq.com
 * @date 2021-09-30 16:10:57
 */
@Service
public class AdminOgranServiceImpl extends BaseIServiceImpl<AdminOgranMapper, AdminOgran> implements AdminOgranService {

    @Override
    public List<AdminOgranVO> list(AdminOgranQuery query) {
        LambdaQueryWrapper<AdminOgran> queryWrapper = new LambdaQueryWrapper<AdminOgran>()
                .eq(query.getDisable() != null, AdminOgran::getDisable, query.getDisable())
                .eq(StringUtils.isNotBlank(query.getPid()), AdminOgran::getPid, query.getPid())
                .orderByDesc(AdminOgran::getCreateTime);
        List<AdminOgranVO> listVo = BeanDtoVoUtil.listVo(this.list(queryWrapper), AdminOgranVO.class);
        if (!query.getIsTree()) {
            return listVo;
        } else {
            List<AdminOgranVO> treeList = new ArrayList<>();
            for (AdminOgranVO adminOgranVO : listVo) {
                if ("0".equals(adminOgranVO.getPid())) {
                    treeList.add(adminOgranVO);
                    adminOgranVO.setOgrans(new ArrayList<>());
                    newxOgran(listVo, adminOgranVO);
                }
            }
            return treeList;
        }
    }


    /**
     * 递归
     * @author wangsong
     * @param listVo
     * @param adminOgranVO
     * @date 2021/9/30 0030 17:17
     * @return void
     * @version 1.0.0
     */
    private void newxOgran(List<AdminOgranVO> listVo, AdminOgranVO adminOgranVO) {
        if (adminOgranVO.getRoot().equals(3)) {
            return;
        }
        for (AdminOgranVO nextAdminOgranVO : listVo) {
            if (adminOgranVO.getId().equals(nextAdminOgranVO.getPid())) {
                adminOgranVO.getOgrans().add(nextAdminOgranVO);
                nextAdminOgranVO.setOgrans(new ArrayList<>());
                newxOgran(listVo, nextAdminOgranVO);
            }
        }
    }


    @Override
    public String insert(AdminOgranDTO dto) {
        AdminOgran entity = dto.convert(AdminOgran.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    public boolean upd(String id, AdminOgranDTO dto) {
        AdminOgran entity = dto.convert(AdminOgran.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    public boolean del(String id) {
        // 删除第三级数据 --> 如果传入id是第一级,获取第二级所有id，并删除第三级数据
        List<AdminOgran> list = this.list(new LambdaQueryWrapper<AdminOgran>().select(AdminOgran::getId).eq(AdminOgran::getPid, id));
        if (!list.isEmpty()) {
            List<String> ogranIds = list.stream().map(AdminOgran::getId).collect(Collectors.toList());
            this.removeByIds(ogranIds);
        }
        // 删除第二级数据+第一级数据
        this.remove(new LambdaQueryWrapper<AdminOgran>().and(p ->
                p.eq(AdminOgran::getPid, id)
                        .or().eq(AdminOgran::getId, id)));
        return this.removeById(id);
    }


    @Override
    public AdminOgranVO fingNextOgrans(String id) {
        // 获取当前数据
        AdminOgran adminOgran = this.getOne(new LambdaQueryWrapper<AdminOgran>()
                .select(AdminOgran::getId,AdminOgran::getPid, AdminOgran::getRoot, AdminOgran::getName, AdminOgran::getCode)
                .eq(AdminOgran::getId, id));
        if (adminOgran == null) {
            return null;
        }
        AdminOgranVO adminOgranVO = BeanDtoVoUtil.convert(adminOgran, AdminOgranVO.class);
        //
        if (adminOgran.getRoot().equals(Base.OgranRoot.V1.getValue())) {
            return adminOgranVO;
        } else if (adminOgran.getRoot().equals(Base.OgranRoot.V2.getValue())) {
            // 获取上级
            AdminOgran ogranOne = this.getOne(new LambdaQueryWrapper<AdminOgran>()
                    .select(AdminOgran::getId,AdminOgran::getPid,  AdminOgran::getRoot, AdminOgran::getName, AdminOgran::getCode)
                    .eq(AdminOgran::getPid, adminOgran.getPid()));
            AdminOgranVO vo = BeanDtoVoUtil.convert(ogranOne, AdminOgranVO.class);
            vo.setOgrans(new ArrayList<AdminOgranVO>());
            vo.getOgrans().add(adminOgranVO);
            return adminOgranVO;
        } else if (adminOgran.getRoot().equals(Base.OgranRoot.V3.getValue())) {
            // 获取上级
            AdminOgran ogranTwo = this.getOne(new LambdaQueryWrapper<AdminOgran>()
                    .select(AdminOgran::getId,AdminOgran::getPid,  AdminOgran::getRoot, AdminOgran::getName, AdminOgran::getCode)
                    .eq(AdminOgran::getId, adminOgranVO.getPid()));
            AdminOgranVO ogranTwoVO = BeanDtoVoUtil.convert(ogranTwo, AdminOgranVO.class);
            ogranTwoVO.setOgrans(new ArrayList<>());
            ogranTwoVO.getOgrans().add(adminOgranVO);
            if (adminOgran == null) {
                return adminOgranVO;
            }
            // 获取上级的上级
            AdminOgran ogranOne = this.getOne(new LambdaQueryWrapper<AdminOgran>()
                    .select(AdminOgran::getId, AdminOgran::getRoot, AdminOgran::getName, AdminOgran::getCode)
                    .eq(AdminOgran::getId, ogranTwoVO.getPid()));
            if (adminOgran == null) {
                return ogranTwoVO;
            }
            AdminOgranVO vo = BeanDtoVoUtil.convert(ogranOne, AdminOgranVO.class);
            vo.setOgrans(new ArrayList<>());
            vo.getOgrans().add(ogranTwoVO);
            return vo;
        }
        return null;
    }
}
