package com.ws.ldy.modules.third.kuaidi.kuaidi100.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ws.ldy.common.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws.ldy.modules.third.kuaidi.kuaidi100.entity.KuaiDiCode;
import com.ws.ldy.modules.third.kuaidi.kuaidi100.service.KuaiDi100Service;
import com.ws.ldy.modules.third.kuaidi.kuaidi100.util.KuaiDi100Util;
import java.util.List;

/**
 * 快递100
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/16 0016 10:27
 * @version 1.0.0
 */
@Service
public class KuaiDi100ServiceImpl implements KuaiDi100Service {

    @Autowired
    private KuaiDi100Util kuaiDi100Util;


    @Override
    public R<String> findOrder(String orderId) {
        //获取快递编码
        List<KuaiDiCode> kuaiDiCodeList = findKuaiDiCode(orderId);
        if (kuaiDiCodeList.size() == 0) {
           return R.error(10099,"无法识别该快递单号");
        }
        String orderJson = null;
        // 遍历快递公司： 找找时间立即跳出
        for (KuaiDiCode kuaiDiCode : kuaiDiCodeList) {
            // 没有找到： {"result":false,"returnCode":"500","message":"查询无结果，请隔段时间再查"}
            orderJson = kuaiDi100Util.findOrder(orderId, kuaiDiCode.getComCode());
            JSONObject jsonObject = JSON.parseObject(orderJson);
            // 当没有找到快递, 会出现result=false,找到了物流,没有result字段
            Boolean result = (Boolean) jsonObject.get("result");
            if (result == null) {
                break;
            }
        }
        //判断是否找到物流信息
        JSONObject jsonObject = JSON.parseObject(orderJson);
        Boolean result = (Boolean) jsonObject.get("result");
        if (result != null && !result) {
            return R.error(10099,"查询无结果，请隔段时间再查");
        }
        return R.success(orderJson);
    }


    /**
     * 智能识别快递单号
     * @param orderId
     * @return
     */
    private List<KuaiDiCode> findKuaiDiCode(String orderId) {
        List<KuaiDiCode> kuaiDiCode = kuaiDi100Util.findKuaiDiCode(orderId);
        return kuaiDiCode;
    }
}
