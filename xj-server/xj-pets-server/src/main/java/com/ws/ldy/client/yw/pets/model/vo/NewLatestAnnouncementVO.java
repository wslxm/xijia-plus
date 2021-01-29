package com.ws.ldy.client.yw.pets.model.vo;


import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@ApiModel(value = "NewLatestAnnouncementVO", description = "最新公示")
public class NewLatestAnnouncementVO extends Convert {

    private static final long serialVersionUID = -2755952123153289208L;
    @ApiModelProperty(notes = "帮助总次数 = 申请成功的数量", position = 1)
    private Integer declareSuccessTotal;

    @ApiModelProperty(notes = "2、分摊人数 = 当前正在总续费的人数", position = 2)
    private Integer renewTotal;

    @ApiModelProperty(notes = "3、互助总金额 = 已发放总额度", position = 3)
    private BigDecimal moneyPayment;

}
