package io.github.wslxm.springbootplus2.core.config.error;


import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.utils.EnumUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 自定义异常类，通过此类可返回各种自定义异常信息，由GlobalExceptionHandler 处理返回
 * <p>
 * 使用：throw new ErrorException("1000000","自定义异常测试");
 * 返回：{"code": "1000000","msg": "自定义异常测试"}
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 12:44
 * @return
 */
@Component
@Data
@NoArgsConstructor  //无参构造
public class ErrorException extends RuntimeException {

    private Integer code;
    private String msg;


    //直接传递
    public ErrorException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //枚举传递（建议先定义枚举）
    public ErrorException(RType rType) {
        this.code = rType.getValue();
        this.msg = rType.getMsg();
    }

    //任意枚举传递（建议先定义枚举-必须存在值 value, msg）
    public <E> ErrorException(E e) {
        this.code = EnumUtil.getValue(e);
        this.msg = EnumUtil.getMsg(e);
    }
}
