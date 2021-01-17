package com.ws.ldy.modules.sys.xj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.xj.mapper.XjAdminHelpMapper;
import com.ws.ldy.modules.sys.xj.model.entity.XjAdminHelp;
import com.ws.ldy.modules.sys.admin.model.vo.AdminDictionaryVO;
import com.ws.ldy.modules.sys.admin.model.vo.HelpTreeVO;
import com.ws.ldy.modules.sys.admin.service.AdminDictionaryService;
import com.ws.ldy.modules.sys.xj.service.XjAdminHelpService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 帮助中心表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-20 16:42:28
 */
@Service
public class XjAdminHelpServiceImpl extends BaseIServiceImpl<XjAdminHelpMapper, XjAdminHelp> implements XjAdminHelpService {

    @Autowired
    private AdminDictionaryService adminDictionaryService;

    /**
     * 帮助中心树菜单
     * @return
     */
    @Override
    public List<HelpTreeVO> findTree() {
        List<AdminDictionaryVO> byCodeFetchDictVOS = adminDictionaryService.findByCodeFetchDictVO(BaseConstant.ADMIN.HELP_CATEGORY, false, true, true);
        AdminDictionaryVO byCodeFetchDictVO = byCodeFetchDictVOS.get(0);
        if (byCodeFetchDictVO == null || byCodeFetchDictVO.getDictList() == null) {
            return null;
        }
        List<XjAdminHelp> ybHelp = this.list(new LambdaQueryWrapper<XjAdminHelp>()
                .orderByAsc(XjAdminHelp::getHelpVersion)  // 先版本排序
                .orderByAsc(XjAdminHelp::getCategory)     // 在类别排序
                .orderByAsc(XjAdminHelp::getSort)         // 在sort排序
                .orderByDesc(XjAdminHelp::getCreateTime)  // 在时间排序
        );
        if (ybHelp == null || ybHelp.size() <= 0) {
            return null;
        }
        //根据类别分组
        Map<Integer, List<XjAdminHelp>> ybHelpGroup = ybHelp.stream().collect(Collectors.groupingBy(o -> o.getCategory()));
        //
        List<HelpTreeVO> ybHelpTreeVOS = new ArrayList<>();
        byCodeFetchDictVO.getDictList().forEach(i -> {
            HelpTreeVO ybHelpTreeVO = new HelpTreeVO();
            ybHelpTreeVO.setId("0");
            ybHelpTreeVO.setName(i.getName());
            // 拼二级菜单
            List<HelpTreeVO> nextHelpTreeVOS = new ArrayList<>();
            List<XjAdminHelp> ybHelps = ybHelpGroup.get(Integer.parseInt(i.getCode()));
            if (ybHelps != null && ybHelps.size() > 0) {
                for (XjAdminHelp help : ybHelps) {
                    HelpTreeVO nextHelpTreeVO = new HelpTreeVO();
                    nextHelpTreeVO.setId(help.getId());
                    nextHelpTreeVO.setName(help.getTitle());
                    nextHelpTreeVOS.add(nextHelpTreeVO);
                }
            }
            ybHelpTreeVO.setYbHelpTreeVOS(nextHelpTreeVOS);
            ybHelpTreeVOS.add(ybHelpTreeVO);
        });
        return ybHelpTreeVOS;
    }

}
