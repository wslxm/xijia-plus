package com.ws.ldy.others.kuaidi.kuaidi100.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.others.kuaidi.kuaidi100.entity.KuaiDiCode;
import com.ws.ldy.others.kuaidi.kuaidi100.service.KuaiDi100Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kuaidi100/")
@Api(value = "KuaiDi100Controller", tags = "v-1.3 -- 快递100",consumes = BaseConstant.InterfaceType.PC_BASE)
public class KuaiDi100Controller {

    @Autowired
    private KuaiDi100Service kuaiDi100Service;

    @ApiOperation(value = "输入快递号智能识别快递公司", notes = "按相似度排序,相似度越高越靠前")
    @RequestMapping(value = "/findKuaiDiCode", method = RequestMethod.GET)
    public R<List<KuaiDiCode>> findKuaiDiCode(String orderId) {
        return R.success(kuaiDi100Service.findKuaiDiCode(orderId));
    }


    @ApiOperation(value = "根据快递单号查询物流信息", notes = "" +
            "\r\n 1、自动识别单号" +
            "\r\n 2、返回参数查看：https://api.kuaidi100.com/help/doc/?code=5f0ffb5ebc8da837cbd8aefc&openKey=%E5%AE%9E%E6%97%B6%E5%BF%AB%E9%80%92%E6%9F%A5%E8%AF%A2#part2" +
            "")
    @RequestMapping(value = "/findOrder", method = RequestMethod.GET)
    public R<String> findOrder(String orderId) {
        return R.success(kuaiDi100Service.findOrder(orderId));
    }
}
