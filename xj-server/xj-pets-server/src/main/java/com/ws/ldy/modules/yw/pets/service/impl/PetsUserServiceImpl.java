package com.ws.ldy.modules.yw.pets.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.client.yw.pets.model.dto.LoginDTO;
import com.ws.ldy.client.yw.pets.model.dto.UpdHeadOrNameOrPhoneDTO;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.config.auth.entity.JwtUser;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.third.aliyun.sms.util.AliSmsUtil;
import com.ws.ldy.modules.yw.pets.mapper.PetsUserMapper;
import com.ws.ldy.modules.yw.pets.model.dto.PetsUserDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsUser;
import com.ws.ldy.modules.yw.pets.model.vo.PetsUserFindUserCountVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsUserVO;
import com.ws.ldy.modules.yw.pets.service.PetsUserService;
import com.ws.ldy.modules.yw.third.WxAppSubscribeSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 11:03:46
 */
@Service
public class PetsUserServiceImpl extends BaseIServiceImpl<PetsUserMapper, PetsUser> implements PetsUserService {


    @Autowired
    private WxAppSubscribeSendUtil wxAppSubscribeSendUtil;

    @Autowired
    private AliSmsUtil aliSmsUtil;

    @Override
    public Boolean login(LoginDTO dto) {
        // 防止重复注册
        PetsUser petsUser = null;
        synchronized (PetsUserServiceImpl.class) {
            petsUser = this.getOne(new LambdaQueryWrapper<PetsUser>().eq(PetsUser::getWxOpenId, dto.getWxOpenId()));
        }
        if (petsUser == null) {
            // 注册
            petsUser = dto.convert(PetsUser.class);
            this.save(petsUser);
        } else {
            //直接登录 并 更新性别信息
            this.update(new LambdaUpdateWrapper<PetsUser>()
                    .set(PetsUser::getWxGender, dto.getWxGender())
                    .eq(PetsUser::getId, petsUser.getId())
            );
        }
        // 生成token
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserId(petsUser.getId());
        jwtUser.setUsername(petsUser.getWxName());
        jwtUser.setHead(petsUser.getWxHeadPic());
        jwtUser.setType(Enums.Admin.AuthorityType.AUTHORITY_TYPE_1.getValue());
        jwtUser.setFullName(null);
        jwtUser.setPhone(null);
        // 生成Token并放入响应头
        String jwtToken = JwtUtil.createToken(jwtUser,response);
        return true;
    }

    @Override
    public Boolean updHeadOrNameOrPhone(UpdHeadOrNameOrPhoneDTO dto) {
        PetsUser petsUser = new PetsUser();
        petsUser.setId(JwtUtil.getJwtUser(request).getUserId());
        if (dto.getType().equals(1)) {
            // 修改昵称
            if (StringUtils.isBlank(dto.getWxName())) {
                throw new ErrorException(RType.PARAM_ERROR);
            }
            petsUser.setWxName(dto.getWxName());
        } else if (dto.getType().equals(2)) {
            // 修改电话
            if (StringUtils.isBlank(dto.getPhone())) {
                throw new ErrorException(RType.PARAM_ERROR);
            }
            // 短信验证
            aliSmsUtil.verifySMS(dto.getPhone(), dto.getCode());
            petsUser.setPhone(dto.getPhone());
        } else if (dto.getType().equals(3)) {
            // 修改头像
            if (StringUtils.isBlank(dto.getWxHeadPic())) {
                throw new ErrorException(RType.PARAM_ERROR);
            }
            petsUser.setWxHeadPic(dto.getWxHeadPic());
        }
        boolean b = this.updateById(petsUser);
        return b;
    }

    @Override
    public Boolean updPetOwner(PetsUserDTO dto) {
        //短信验证
        aliSmsUtil.verifySMS(dto.getPhone(), dto.getCode());
        PetsUser petsUser = dto.convert(PetsUser.class);
        petsUser.setId(JwtUtil.getJwtUser(request).getUserId());
        boolean b = this.updateById(petsUser);

        // 发送订阅消息-->   // 审核内容  // 审核时间     // 审核结果    // 备注
        String wxOrderId = this.findWxOrderId(petsUser.getId());
        String checkContent = "宠物主人认证";
        String checkTime = LocalDateTimeUtil.parse(LocalDateTimeUtil.now());
        String checkResult = "通过";
        String remark = "宠物主人认证通过";
        wxAppSubscribeSendUtil.checkMsg(wxOrderId, checkContent, checkTime, checkResult, remark);
        return b;
    }


    @Override
    public PetsUser findUser() {
        PetsUser petsUser = this.getOne(new LambdaQueryWrapper<PetsUser>().eq(PetsUser::getId, JwtUtil.getJwtUser(request).getUserId()));
        return petsUser;
    }

    @Override
    public List<PetsUser> findNew5() {
        Page page = new Page(1, 5);
        Page<PetsUser> resPage = this.page(page, new LambdaQueryWrapper<PetsUser>().orderByDesc(PetsUser::getCreateTime));
        return resPage.getRecords();
    }


    @Override
    public Page<PetsUserVO> findPage(Page<PetsUser> page, String fullName, String phone, String disable) {
        Page<PetsUserVO> resPage = baseMapper.findPage(page, fullName, phone, disable);
        return resPage;
    }

    @Override
    public PetsUser findId(String userId) {
        PetsUser user = this.getOne(new LambdaQueryWrapper<PetsUser>().eq(PetsUser::getId, userId));
        return user;
    }

    @Override
    public String findWxOrderId(String userId) {
        PetsUser user = this.getOne(new LambdaQueryWrapper<PetsUser>().select(PetsUser::getWxOpenId).eq(PetsUser::getId, userId));
        return user.getWxOpenId();
    }

    @Override
    public boolean isItCertified(String userId) {
        PetsUser petsUser = this.getById(JwtUtil.getJwtUser(request).getUserId());
        if (StringUtils.isBlank(petsUser.getIdCard()) || StringUtils.isBlank(petsUser.getFullName()) || StringUtils.isBlank(petsUser.getPhone())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean updAddress(String province, String city, String area, String userId) {
        PetsUser petsUser = new PetsUser();
        petsUser.setProvince(province);
        petsUser.setUpdateTime(LocalDateTime.now());
        petsUser.setCity(city);
        petsUser.setArea(area);
        petsUser.setId(userId);
        this.updateById(petsUser);
        return true;
    }


    @Override
    public PetsUserFindUserCountVO findUserCount() {
        List<PetsUser> list = this.list(new LambdaQueryWrapper<PetsUser>()
                .select(PetsUser::getWxGender)
        );
        Map<Integer, List<PetsUser>> userMap = list.stream().filter(p -> p.getWxGender() != null)
                .collect(Collectors.groupingBy(PetsUser::getWxGender));
        // 返回统计 ( 1-男 2-女 0-未知)
        PetsUserFindUserCountVO vo = new PetsUserFindUserCountVO();
        vo.setUserTotal(Long.parseLong(list.size() + ""));
        vo.setGirlUserTotal(userMap.containsKey(2) ? userMap.get(0).size() : 0L);
        vo.setBoyUserTotal(userMap.containsKey(1) ? userMap.get(0).size() : 0L);
        vo.setUnknownUserTotal(userMap.containsKey(0) ? userMap.get(0).size() : 0L);
        return vo;
    }
}
