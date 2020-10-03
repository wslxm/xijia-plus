/**
  * Copyright 2020 bejson.com 
  */
package com.ws.ldy.others.kuaidi.sf.entity.placeAnOrder.response;

import com.ws.ldy.others.base.model.Convert;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class MsgData extends Convert {

    private String orderId;
    private String originCode;
    private String destCode;
    private int filterResult;
    private String remark;
    private String url;
    private String paymentLink;
    private String isUpstairs;
    private String isSpecialWarehouseService;
    private String mappingMark;
    private String agentMailno;
    private String returnExtraInfoList;
    private List<WaybillNoInfoList> waybillNoInfoList;
    private List<RouteLabelInfo> routeLabelInfo;
    private String contactInfoList;

}