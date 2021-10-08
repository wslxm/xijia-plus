/**
  * Copyright 2020 bejson.com 
  */
package com.github.wslxm.springbootplus2.starter.kuaidi.sf.entity.placeAnOrder.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class ApiResultData implements Serializable {

    private static final long serialVersionUID = -6044448511655140277L;
    private boolean success;
    private String errorCode;
    private String errorMsg;
    private String msgData;

}