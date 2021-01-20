/**
  * Copyright 2020 bejson.com 
  */
package com.ws.ldy.modules.third.kuaidi.sf.entity.placeAnOrder.response;


import com.ws.ldy.modules.sys.base.model.Convert;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RouteLabelData extends Convert {

    private String waybillNo;
    private String sourceTransferCode;
    private String sourceCityCode;
    private String sourceDeptCode;
    private String sourceTeamCode;
    private String destCityCode;
    private String destDeptCode;
    private String destDeptCodeMapping;
    private String destTeamCode;
    private String destTeamCodeMapping;
    private String destTransferCode;
    private String destRouteLabel;
    private String proName;
    private String cargoTypeCode;
    private String limitTypeCode;
    private String expressTypeCode;
    private String codingMapping;
    private String codingMappingOut;
    private String xbFlag;
    private String printFlag;
    private String twoDimensionCode;
    private String proCode;
    private String printIcon;
    private String abFlag;
    private String destPortCode;
    private String destCountry;
    private String destPostCode;
    private String goodsValueTotal;
    private String currencySymbol;
    private String cusBatch;
    private String goodsNumber;
    private String errMsg;
    private String checkCode;
    private String proIcon;
    private String fileIcon;
    private String fbaIcon;
    private String icsmIcon;
    private String destGisDeptCode;
    private String newIcon;
}