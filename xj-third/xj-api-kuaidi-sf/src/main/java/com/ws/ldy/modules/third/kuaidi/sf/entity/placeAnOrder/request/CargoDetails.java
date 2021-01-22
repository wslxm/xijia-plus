package com.ws.ldy.modules.third.kuaidi.sf.entity.placeAnOrder.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CargoDetails implements Serializable {
    private static final long serialVersionUID = -4597761414885235115L;
    /**
     * 货物名称，如果需要生成电子
     * 运单，则为必填
     */
    private String name;

}
