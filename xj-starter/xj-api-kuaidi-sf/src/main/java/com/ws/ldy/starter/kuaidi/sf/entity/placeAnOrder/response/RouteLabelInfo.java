/**
  * Copyright 2020 bejson.com 
  */
package com.ws.ldy.starter.kuaidi.sf.entity.placeAnOrder.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class RouteLabelInfo implements Serializable {

    private static final long serialVersionUID = -1555241724824864477L;
    private String code;
    private RouteLabelData routeLabelData;
    private String message;


}