package io.github.wslxm.springbootplus2.manage.sys.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 操作记录表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-28 20:44:32
 */
@Data
@ToString(callSuper = true)
public class LogQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;

    /**
     * 请求人
     */
    private String fullName;

    /**
     * 请求uri
     */
    private String uri;

    /**
     * 类备注
     */
    private String classDesc;
    /**
     * 方法备注
     */
    private String methodDesc;
    /**
     * 1-请求成功 0-请求失败
     */
    private Integer state;
    /**
     * 请求方式(get-post)
     */
    private String method;
    /**
     * 请求时间开始
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeStart;
    /**
     * 请求时间结束
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeEnd;
}

