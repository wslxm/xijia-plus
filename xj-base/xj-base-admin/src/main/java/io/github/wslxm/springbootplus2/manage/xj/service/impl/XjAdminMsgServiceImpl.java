package io.github.wslxm.springbootplus2.manage.xj.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminDictionaryVO;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminDictionaryService;
import io.github.wslxm.springbootplus2.manage.xj.mapper.XjAdminMsgMapper;
import io.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminMsgDTO;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminMsg;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminMsgQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminMsgFindAllNumVO;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminMsgVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminMsgService;
import io.github.wslxm.springbootplus2.starter.websocket.service.WebsocketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单-->及时消息通知表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-09-23 10:40:23
 */
@Service
public class XjAdminMsgServiceImpl extends BaseIServiceImpl<XjAdminMsgMapper, XjAdminMsg> implements XjAdminMsgService {

    @Autowired
    private WebsocketService webSocketService;

    @Autowired
    private AdminDictionaryService adminDictionaryService;

    @Override
    public IPage<XjAdminMsgVO> findPage(XjAdminMsgQuery query) {
        if (query.getIsLoginUser() == null) {
            query.setIsLoginUser(true);
        }
        LambdaQueryWrapper<XjAdminMsg> queryWrapper = new LambdaQueryWrapper<XjAdminMsg>()
                .orderByDesc(XjAdminMsg::getCreateTime)
                .eq(query.getIsRead() != null, XjAdminMsg::getIsRead, query.getIsRead())
                .eq(query.getIsLoginUser(), XjAdminMsg::getUserId, JwtUtil.getJwtUser(request).getUserId())
                .in(StringUtils.isNotBlank(query.getMsgTypes()), XjAdminMsg::getMsgType, StringUtils.isNotBlank(query.getMsgTypes()) ? Arrays.asList(query.getMsgTypes().split(",")) : null)
                .notIn(StringUtils.isNotBlank(query.getNoMsgTypes()), XjAdminMsg::getMsgType, StringUtils.isNotBlank(query.getNoMsgTypes()) ? Arrays.asList(query.getNoMsgTypes().split(",")) : null);
        return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminMsgVO.class);
    }

    @Override
    public String insert(XjAdminMsgDTO dto) {
        // 根据业务类型获取配置
        AdminDictionaryVO msgTypeDict = adminDictionaryService.findDictCategoryNext("MSG_TYPE", dto.getMsgType() + "");
        ValidUtil.isTrue(msgTypeDict == null, "没有找到对应的字典配置");

        // 获取动态参数
        String paramsStr = null;
        if (dto.getRouteParams() != null && dto.getRouteParams().size() > 0) {
            StringBuilder params = new StringBuilder("");
            Map<String, String> routeParams = dto.getRouteParams();
            for (String key : routeParams.keySet()) {
                params.append("&").append(key).append("=").append(routeParams.get(key));
            }
            paramsStr = params.toString().substring(1);
        }

        // 拼接路由一
        StringBuilder routePath = new StringBuilder("");
        if (StringUtils.isNotEmpty(msgTypeDict.getExt2())) {
            routePath.append(msgTypeDict.getExt2());
            routePath.append(routePath.toString().contains("?") ? "&" + paramsStr : "?" + paramsStr);
        }
        // 拼接路由二
        StringBuilder routePathTwo = new StringBuilder("");
        if (StringUtils.isNotEmpty(msgTypeDict.getExt3())) {
            routePathTwo.append(msgTypeDict.getExt3());
            routePathTwo.append(routePath.toString().contains("?") ? "&" + paramsStr : "?" + paramsStr);
        }

        // 拼接发送json内容
        Map<String, String> contentMap = new HashMap<>();
        // 标题
        contentMap.put("title", msgTypeDict.getExt1());
        // 消息
        contentMap.put("message", dto.getContent());
        // 跳转地址
        contentMap.put("routePath", routePath.toString());
        // 第二跳转地址(可让其 用户pc端使用第一路由 + 用户app端使用第二路由)
        contentMap.put("routePathTwo", routePathTwo.toString());


        // 保存消息
        XjAdminMsg entity = new XjAdminMsg();
        entity.setUserId(dto.getUserId());
        entity.setContent(JSON.toJSONString(contentMap));
        entity.setUserType(dto.getUserType());
        entity.setMsgType(dto.getMsgType());
        entity.setIsRead(Base.IsRead.V0.getValue());
        boolean b = this.save(entity);

        // 发送webSocket消息
        boolean isWebsocket = dto.getIsWebsocket() == null ? false : dto.getIsWebsocket();
        if (isWebsocket) {
            webSocketService.send("sys-sms", "系统", dto.getUserId(), JSON.toJSONString(entity), null);
        }
        return entity.getId();
    }

    @Override
    public boolean updRead(String id) {
        XjAdminMsg entity = new XjAdminMsg();
        entity.setId(id);
        entity.setIsRead(Base.IsRead.V1.getValue());
        return this.updateById(entity);
    }

    @Override
    public Long findUnreadNum() {
        return this.count(new LambdaQueryWrapper<XjAdminMsg>()
                .eq(XjAdminMsg::getIsRead, Base.IsRead.V0.getValue())
                .eq(XjAdminMsg::getUserId, JwtUtil.getJwtUser(request).getUserId())
        );
    }

    @Override
    public XjAdminMsgFindAllNumVO findAllNum() {
        String userId = JwtUtil.getJwtUser(request).getUserId();
        XjAdminMsgFindAllNumVO vo = new XjAdminMsgFindAllNumVO();
        vo.setAllNum(this.count(new LambdaQueryWrapper<XjAdminMsg>().eq(XjAdminMsg::getUserId, userId)));
        vo.setHaveReadNum(this.count(new LambdaQueryWrapper<XjAdminMsg>().eq(XjAdminMsg::getIsRead, Base.IsRead.V1.getValue()).eq(XjAdminMsg::getUserId, userId)));
        vo.setUnreadNum(this.count(new LambdaQueryWrapper<XjAdminMsg>().eq(XjAdminMsg::getIsRead, Base.IsRead.V0.getValue()).eq(XjAdminMsg::getUserId, userId)));
        return vo;
    }
}
