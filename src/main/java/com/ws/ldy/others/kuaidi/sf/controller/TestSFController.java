package com.ws.ldy.others.kuaidi.sf.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.others.kuaidi.sf.entity.placeAnOrder.request.CargoDetails;
import com.ws.ldy.others.kuaidi.sf.entity.placeAnOrder.request.ContactInfoList;
import com.ws.ldy.others.kuaidi.sf.entity.placeAnOrder.request.SFOrder;
import com.ws.ldy.others.kuaidi.sf.service.SFService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(BaseConstant.Sys.URI_PREFIX + "/sf/")
@Api(value = "TestSFController", tags = "v-1.3 -- 顺丰快递 --> test-API",consumes = BaseConstant.InterfaceType.PC_BASE)
public class TestSFController {

    @Autowired
    private SFService sfService;

    @ApiOperation("发货测试")
    @RequestMapping(value = "/placeAnOrderTest", method = RequestMethod.GET)
    public R<String> placeAnOrder(String orderId) {
        // 地址
        List<ContactInfoList> contactInfoList = new ArrayList<>();
        // 寄件人信息
        ContactInfoList contactInfo1 = new ContactInfoList();
        contactInfo1.setContactType(1);  //1，寄件方信息 2，到件方信息
        contactInfo1.setContact("王松");
        contactInfo1.setCountry("CN");
        contactInfo1.setTel("17628689969");
        contactInfo1.setAddress("四川省成都市双流区xx小区xx单元");
        contactInfoList.add(contactInfo1);
        // 到件方信息
        ContactInfoList contactInfo2 = new ContactInfoList();
        contactInfo2.setContactType(2);
        contactInfo2.setContact("王松");
        contactInfo2.setCountry("CN");
        contactInfo2.setTel("17628689969");
        contactInfo2.setAddress("四川省成都市双流区xx小区xx单元");
        contactInfoList.add(contactInfo2);
        // 货物明细
        CargoDetails cargoDetails = new CargoDetails();
        cargoDetails.setName("测试商品");
        ArrayList<CargoDetails> cargoDetails1 = new ArrayList<CargoDetails>() {{
            add(cargoDetails);
        }};
        //
        SFOrder en = new SFOrder();
        en.setContactInfoList(contactInfoList);
        en.setCargoDetails(cargoDetails1);
        en.setOrderId(orderId);
        en.setRemark("下单备注");
        return R.success(sfService.placeAnOrder(en));
    }


    @ApiOperation("取消订单测试")
    @RequestMapping(value = "/cancelOrderTest", method = RequestMethod.GET)
    public R<String> cancelOrderTest(String orderId) {
        return R.success(sfService.cancelOrder(orderId));
    }
}
