package com.ws.ldy.modules.yw.pay.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.yw.pay.model.entity.PayRecord;
import com.ws.ldy.modules.yw.pay.model.vo.PayRecordVO;
import com.ws.ldy.modules.yw.pay.service.PayRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 第三方支付记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2021-01-05 10:14:05
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pay/payRecord")
@Api(value = "PayRecordController", tags = "pay  -->  第三方支付记录")
public class PayRecordController extends BaseController<PayRecordService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "orderNo", value = "交易订单号", required = false, paramType = "query", example = ""),

    })
    public R<IPage<PayRecordVO>> findPage(
            @RequestParam(required = false) String orderNo
    ) {
        Page<PayRecord> page = baseService.page(this.getPage(), new LambdaQueryWrapper<PayRecord>()
                .orderByDesc(PayRecord::getCreateTime)
                .eq(StringUtils.isNotBlank(orderNo), PayRecord::getOrderNo, orderNo)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, PayRecordVO.class));
    }

}
