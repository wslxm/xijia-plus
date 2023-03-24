package io.github.wslxm.springbootplus2.core.base.model;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.github.wslxm.springbootplus2.core.base.annotation.XjSecret;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 分页对象
 *
 * @author wangsong
 * @date 2023/03/24
 */
@Data
public class BasePage<T> implements Serializable {

    @XjSecret(isNext = true)
    private List<T> records;
    private long total;
    private long size;
    private long current;
    private List<OrderItem> orders;
    private boolean optimizeCountSql;
    private boolean searchCount;
    private boolean optimizeJoinOfCountSql;
    private Long maxLimit;
    private String countId;
}
