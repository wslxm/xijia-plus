/**
  * Copyright 2020 bejson.com 
  */
package io.github.wslxm.springbootplus2.starter.kuaidi.sf.entity.placeAnOrder.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Data
@ToString
public class MsgData implements Serializable {

    private static final long serialVersionUID = -5701640941889720501L;
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