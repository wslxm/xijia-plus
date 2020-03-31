package com.ws.ldy.common.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IPage {
    private Integer page;
    private Integer limit;
}
