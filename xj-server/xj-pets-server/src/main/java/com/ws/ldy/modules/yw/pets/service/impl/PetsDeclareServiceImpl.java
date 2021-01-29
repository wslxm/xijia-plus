package com.ws.ldy.modules.yw.pets.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.client.yw.pets.model.vo.NewDeclareVO;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.common.utils.BigDecimalUtil;
import com.ws.ldy.common.utils.IdUtil;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.sys.pay.model.dto.EntPayDTO;
import com.ws.ldy.modules.sys.pay.model.vo.EntPayResultVO;
import com.ws.ldy.modules.sys.pay.service.PayService;
import com.ws.ldy.modules.sys.pay.service.PayWalletFlowService;
import com.ws.ldy.modules.yw.pets.mapper.PetsDeclareMapper;
import com.ws.ldy.modules.yw.pets.model.dto.PetsCapitalDTO;
import com.ws.ldy.modules.yw.pets.model.dto.PetsDeclareDTO;
import com.ws.ldy.modules.yw.pets.model.dto.PetsDeclareUpdStateDTO;
import com.ws.ldy.modules.yw.pets.model.entity.*;
import com.ws.ldy.modules.yw.pets.model.vo.*;
import com.ws.ldy.modules.yw.pets.service.*;
import com.ws.ldy.modules.yw.third.WxAppSubscribeSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 申报信息表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 13:48:14
 */
@Service
public class PetsDeclareServiceImpl extends BaseIServiceImpl<PetsDeclareMapper, PetsDeclare> implements PetsDeclareService {


    @Autowired
    private PetsPetInfoService petsPetInfoService;

    @Autowired
    private PayWalletFlowService payWalletFlowService;

    @Autowired
    private PetsUserService petsUserService;

    @Autowired
    private PetsHospitalService petsHospitalService;

    @Autowired
    private PetsCapitalService petsCapitalService;

    @Autowired
    private PayService payService;

    @Autowired
    private WxAppSubscribeSendUtil wxAppSubscribeSendUtil;

    @Override
    public NewDeclareVO newDeclareSuccess() {
        //查询时间最后的一条
        PetsDeclare petsDeclare = this.getOne(new LambdaQueryWrapper<PetsDeclare>()
                .orderByDesc(PetsDeclare::getCreateTime)
                .eq(PetsDeclare::getState, Enums.Pet.DeclareState.DECLARE_STATE_3.getValue())
                .last("limit 0,1")
        );
        NewDeclareVO vo = new NewDeclareVO();
        if (petsDeclare != null) {
            // 1、加入天数
            vo.setJoinDayNum(petsDeclare.getJoinDayNum());
            // 2、帮助次数
            vo.setHelpNum(petsDeclare.getHelpNum());
            // 3、获助金额
            vo.setPaidInAmount(petsDeclare.getPaidInAmount());
            //
            PetsPetInfo petsPetInfo = petsPetInfoService.findId(petsDeclare.getPetId());
            // 宠物名称/头像/类型/品种/性别
            vo.setNickname(petsPetInfo.getNickname());
            vo.setHeadPic(petsPetInfo.getHeadPic());
            vo.setSex(petsPetInfo.getSex());
            vo.setBreed(petsPetInfo.getBreed());
            vo.setType(petsPetInfo.getType());
            // 年龄/病因
            vo.setAge(petsDeclare.getPetAge());
            vo.setPathogeny(petsDeclare.getPathogeny());
        } else {
            vo.setJoinDayNum(0);
            vo.setHelpNum(0);
            vo.setPaidInAmount(new BigDecimal("0"));
        }
        return vo;
    }

    @Override
    public Integer findSuccessTotal() {
        int count = this.count(new LambdaQueryWrapper<PetsDeclare>()
                .eq(PetsDeclare::getState, Enums.Pet.DeclareState.DECLARE_STATE_3.getValue())
        );
        return count;
    }

    @Override
    public Integer findSuccessTotalByUserId(String userId) {
        int count = this.count(new LambdaQueryWrapper<PetsDeclare>()
                .eq(PetsDeclare::getState, Enums.Pet.DeclareState.DECLARE_STATE_3.getValue())
                .eq(PetsDeclare::getUserId, userId)
        );
        return count;
    }

    @Override
    public PetsDeclareFindTotalUserIdVO findTotalByUserId(String userId) {
        List<PetsDeclare> declareList = this.list(new LambdaQueryWrapper<PetsDeclare>()
                .select(PetsDeclare::getDeclareMoney)
                .eq(PetsDeclare::getUserId, userId));

        //
        PetsDeclareFindTotalUserIdVO vo = new PetsDeclareFindTotalUserIdVO();
        if (declareList.size() == 0) {
            vo.setDeclareMoneyTotal(new BigDecimal("0"));
            vo.setDeclareNum(0);
        } else {
            BigDecimal declareMoneyTotal = BigDecimalUtil.parse(new BigDecimal(declareList.stream().mapToDouble(p -> p.getDeclareMoney().doubleValue()).sum()));
            vo.setDeclareMoneyTotal(declareMoneyTotal);
            vo.setDeclareNum(declareList.size());
        }
        return vo;
    }


    @Override
    public Boolean insert(PetsDeclareDTO dto) {
        String userId = JwtUtil.getJwtUser(request).getUserId();
        //
        PetsDeclare petsDeclare = dto.convert(PetsDeclare.class);
        petsDeclare.setUserId(userId);
        petsDeclare.setPaidInAmount(dto.getDeclareMoney());
        petsDeclare.setState(Enums.Pet.DeclareState.DECLARE_STATE_0.getValue());
        // 申报订单号
        petsDeclare.setOrderNo(IdUtil.timestampRandom());
        // 宠物年龄
        PetsPetInfo petsPetInfo = petsPetInfoService.findId(dto.getPetId());
        petsDeclare.setPetAge(petsPetInfo.getAge());
        // 报销成功次数
        int declareNum = this.count(new LambdaQueryWrapper<PetsDeclare>()
                .eq(PetsDeclare::getState, Enums.Pet.DeclareState.DECLARE_STATE_3.getValue())
                .eq(PetsDeclare::getUserId, userId)
        );
        petsDeclare.setDeclareNum(declareNum);
        // 累积缴费金额
        BigDecimal userTotalAmount = payWalletFlowService.findUserTotalAmount(userId);
        petsDeclare.setPayMoney(userTotalAmount);
        // 保单加入时间
        petsDeclare.setApplyTime(petsPetInfo.getApplyTime());
        // 剩余天数
        long expirationDayNum = LocalDateTimeUtil.betweenTwoTime(LocalDateTime.now(), petsPetInfo.getExpirationTime(), ChronoUnit.DAYS);
        petsDeclare.setExpirationDayNum((int) expirationDayNum);
        // 帮助次数
        petsDeclare.setHelpNum(petsPetInfo.getHelpNum());
        // 加入天数
        long joinDayNum = LocalDateTimeUtil.betweenTwoTime(petsPetInfo.getJoinTime(), LocalDateTime.now(), ChronoUnit.DAYS);
        petsDeclare.setJoinDayNum((int) joinDayNum);
        boolean result = this.save(petsDeclare);
        return result;
    }

    @Override
    public Boolean upd(PetsDeclareDTO dto) {
        PetsDeclare petsDeclare = dto.convert(PetsDeclare.class);
        petsDeclare.setState(Enums.Pet.DeclareState.DECLARE_STATE_0.getValue());
        boolean b = this.updateById(petsDeclare);
        return b;
    }

    @Override
    public Page<PetsDeclarePageVO> findPage(Page<PetsDeclare> page, String id, String userId, String fullName, Integer state, LocalDateTime startPassTime, LocalDateTime endPassTime) {
        return baseMapper.findPage(page, id, userId, fullName, state, startPassTime, endPassTime);
    }

    @Override
    public List<PetsDeclareFindNewDayListVO> findNewDayList(LocalDateTime startTime, LocalDateTime endTime) {
        return baseMapper.findNewDayList(startTime, endTime);
    }

    @Override
    public Page<PetsDeclareFindHomePageVO> findHomePage(Page<PetsDeclare> page) {
        Page<PetsDeclareFindHomePageVO> homePage = baseMapper.findHomePage(page);
        return homePage;
    }


    @Override
    public List<PetsDeclaretWindControlVO> findListWindControl(String userId) {
        return baseMapper.findListWindControl(userId);
    }

    @Override
    public PetsDeclareVO findId(String id) {
        PetsDeclare petsDeclare = this.getById(id);
        PetsPetInfo petsPetInfo = petsPetInfoService.getById(petsDeclare.getPetId());
        PetsHospital petsHospital = petsHospitalService.getById(petsDeclare.getHospitalId());
        PetsUser petsUser = petsUserService.getById(petsDeclare.getUserId());
        //
        PetsDeclareVO vo = BeanDtoVoUtil.convert(petsDeclare, PetsDeclareVO.class);
        vo.setHospitalVO(BeanDtoVoUtil.convert(petsHospital, PetsHospitalVO.class));
        vo.setPetInfoVO(BeanDtoVoUtil.convert(petsPetInfo, PetsPetInfoVO.class));
        vo.setPetsUserVO(BeanDtoVoUtil.convert(petsUser, PetsUserVO.class));
        return vo;
    }

    @Override
    public List<PetsDeclareFindDeclareSuccessCityVO> findDeclareSuccessCity() {
        List<PetsDeclareFindDeclareSuccessCityVO> declareSuccessCity = baseMapper.findDeclareSuccessCity(Enums.Pet.DeclareState.DECLARE_STATE_3.getValue());
        return declareSuccessCity;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updState(PetsDeclareUpdStateDTO dto) {
        PetsDeclare petsDeclare = this.getById(dto.getId());
        if (petsDeclare == null) {
            throw new ErrorException(RType.PARAM_ERROR);
        }
        if (dto.getIsPass()) {
            // 通过
            // 1、防重 (该接口有幂等操作,可防止重复点击)
            if (petsDeclare.getState().equals(Enums.Pet.DeclareState.DECLARE_STATE_3.getValue())) {
                throw new ErrorException(RType.DECLARE_STATE_ERROR);
            }
            // 2、判断录入实发金额并开始退款， 不能大于申报金额
            if (petsDeclare.getDeclareMoney().doubleValue() < dto.getPaidInAmount().doubleValue()) {
                throw new ErrorException(RType.DECLARE_AMOUNT_ERROR);
            }
            // 3、修改申报状态
            PetsDeclare updPetsDeclare = new PetsDeclare();
            updPetsDeclare.setId(petsDeclare.getId());
            updPetsDeclare.setRemarks(dto.getRemarks());
            updPetsDeclare.setPaidInAmount(dto.getPaidInAmount());
            updPetsDeclare.setPassTime(LocalDateTime.now());
            updPetsDeclare.setPayTime(LocalDateTime.now());
            updPetsDeclare.setState(Enums.Pet.DeclareState.DECLARE_STATE_3.getValue());
            boolean b = this.updateById(updPetsDeclare);
            this.updateById(updPetsDeclare);

            // 4、修改资金+流水(修改完成后续错误会自动回滚)
            String userId = petsDeclare.getUserId();
            String orderNo = petsDeclare.getOrderNo();
            BigDecimal paidInAmount = dto.getPaidInAmount();
            Boolean res = this.entPayCapitalAndWalletFlow(userId, orderNo, paidInAmount);

            // 5、修改自己宠物被帮助次数+1 ，其他所有人帮助次数+1
            petsPetInfoService.updDeclareSuccess(petsDeclare.getPetId());

            // 6、申报成功发放金额（如果失败抛出自定义异常并回滚）
            this.declarePay(userId, orderNo, paidInAmount);
        } else {
            // 1、不通过，驳回并备注
            PetsDeclare updPetsDeclare = new PetsDeclare();
            updPetsDeclare.setId(dto.getId());
            updPetsDeclare.setRemarks(dto.getRemarks());
            updPetsDeclare.setErrorType(dto.getErrorType());
            updPetsDeclare.setState(Enums.Pet.DeclareState.DECLARE_STATE_1.getValue());
            boolean b = this.updateById(updPetsDeclare);
        }
        // 发送订阅消息-->   // 审核内容  // 审核时间     // 审核结果    // 备注
        PetsUser user = petsUserService.findId(petsDeclare.getUserId());
        String feedbackContent = dto.getIsPass() ? "通过" : "不通过";
        String replyContent = dto.getRemarks();
        wxAppSubscribeSendUtil.feedbackCheckMsg(user.getWxOpenId(), feedbackContent, replyContent);
        return true;
    }


    /**
     * 申报成功进行微信打款
     * @return
     */
    private boolean declarePay(String userId, String orderNo, BigDecimal paidInAmount) {
        // 发起支付（商家打款）
        String wxOrderId = petsUserService.findWxOrderId(userId);
        EntPayDTO dto = new EntPayDTO();
        dto.setOpenid(wxOrderId);
        dto.setAmount(paidInAmount);
        dto.setDescription("理赔");
        dto.setOrderNo(orderNo);
        // dto.setCheckName();
        // dto.setReUserName();
        // 能返回表示交易成功
        R<EntPayResultVO> vo = payService.entPay(dto);
        // 成功
        return true;
    }


    /**
     * 企业打款成功--资金/流水
     * @param userId   申报用户id
     * @param orderNo  申报订单号
     * @param paidInAmount 实际报销金额
     * @return
     */
    private Boolean entPayCapitalAndWalletFlow(String userId, String orderNo, BigDecimal paidInAmount) {
        // 1、资金计算
        PetsCapital capital = petsCapitalService.findCapital();
        BigDecimal moneySurplus = BigDecimalUtil.subtract(capital.getMoneySurplus(), paidInAmount);
        BigDecimal moneyPayment = BigDecimalUtil.add(capital.getMoneyPayment(), paidInAmount);

        // 2、修改互助资金池剩余额度(减少 ) + 平台互助金已发放总金额（增加）
        PetsCapitalDTO capitalDto = new PetsCapitalDTO();
        capitalDto.setMoneySurplus(moneySurplus);
        capitalDto.setMoneyPayment(moneyPayment);
        capitalDto.setId(capital.getId());
        capitalDto.setVersion(capital.getVersion());
        BigDecimal moneyAfter = petsCapitalService.updCapital(capitalDto);

        // 3、添加用户流水
        payWalletFlowService.addUserWalletFlow(userId, orderNo, paidInAmount, new BigDecimal("0"),
                Enums.Pet.WalletType.WALLET_TYPE_1, Enums.Pay.PayBusiness.PAY_BUSINESS_2
        );
        // 4、添加平台总资产记录
        payWalletFlowService.addPlatformWalletFlow(orderNo, paidInAmount, moneyAfter,
                Enums.Pet.WalletType.WALLET_TYPE_2, Enums.Pay.PayBusiness.PAY_BUSINESS_2
        );
        return true;
    }
}
