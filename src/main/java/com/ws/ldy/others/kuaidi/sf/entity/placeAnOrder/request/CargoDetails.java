package com.ws.ldy.others.kuaidi.sf.entity.placeAnOrder.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CargoDetails {
    /**
     * 货物名称，如果需要生成电子
     * 运单，则为必填
     */
    private String name;

}
