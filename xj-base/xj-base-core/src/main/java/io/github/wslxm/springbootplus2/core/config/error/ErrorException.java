package io.github.wslxm.springbootplus2.core.config.error;


import com.baomidou.mybatisplus.core.enums.IEnum;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.utils.EnumUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * 自定义异常类，通过此类可返回各种自定义异常信息，由GlobalExceptionHandler 处理返回
 * <p>
 * 使用：throw new ErrorException("1000000","自定义异常测试");
 * 返回：{"code": "1000000","msg": "自定义异常测试"}
 * </p>
 *
 * @author wangsong
 * @version 1.0.0
 * @email 1720696548@qq.com
 * @date 2020/2/9 0009 12:44
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Data
@NoArgsConstructor
public class ErrorException extends RuntimeException {

    private Integer code;
    private String msg;


    /**
     * 直接传递错误码和错误信息
     *
     * @param code code
     * @param msg  msg
     * @return
     * @version 1.0.0
     */
    public ErrorException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 只传入错误内容
     *
     * @param errorMsg
     * @return <E>
     * @version 1.0.0
     */
    public <E> ErrorException(String errorMsg) {
        this.code = RType.SYR_ERROR.getValue();
        this.msg = errorMsg;
    }


    /**
     * 枚举传递（建议先定义枚举）
     *
     * @param rType rType
     * @version 1.0.0
     */
    public ErrorException(RType rType) {
        this.code = rType.getValue();
        this.msg = rType.getMsg();
    }

    /**
     * 任意枚举传递（建议先定义枚举- 枚举必须存在值 value, msg）且继承 IEnum 内
     *
     * @param e e
     * @return <E>
     * @version 1.0.0
     */
    public <E extends IEnum> ErrorException(E e) {
        this.code = EnumUtil.getValue(e);
        this.msg = EnumUtil.getMsg(e);
    }
}
