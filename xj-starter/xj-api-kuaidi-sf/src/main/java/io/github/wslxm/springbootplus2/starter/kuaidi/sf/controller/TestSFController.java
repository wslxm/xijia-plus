package io.github.wslxm.springbootplus2.starter.kuaidi.sf.controller;

import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.kuaidi.sf.entity.placeAnOrder.request.CargoDetails;
import io.github.wslxm.springbootplus2.starter.kuaidi.sf.entity.placeAnOrder.request.ContactInfoList;
import io.github.wslxm.springbootplus2.starter.kuaidi.sf.entity.placeAnOrder.request.SFOrder;
import io.github.wslxm.springbootplus2.starter.kuaidi.sf.service.SFService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/open/sf/")
@Api(value = "TestSFController", tags = "KuaiDi  -->  顺丰快递test")
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
        contactInfo1.setCountry("CN"); //国家或地区
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
        ArrayList<CargoDetails> cargoDetails1 = new ArrayList<>();
        cargoDetails1.add(cargoDetails);
        //
        SFOrder en = new SFOrder();
        en.setContactInfoList(contactInfoList);
        en.setCargoDetails(cargoDetails1);
        en.setOrderId(orderId);
        en.setRemark("下单备注");
        return sfService.placeAnOrder(en);
    }


    @ApiOperation("取消订单测试")
    @RequestMapping(value = "/cancelOrderTest", method = RequestMethod.GET)
    public R<String> cancelOrderTest(String orderId) {
        return sfService.cancelOrder(orderId);
    }
}
