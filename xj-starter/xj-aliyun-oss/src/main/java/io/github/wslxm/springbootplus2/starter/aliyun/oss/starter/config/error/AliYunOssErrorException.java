package io.github.wslxm.springbootplus2.starter.aliyun.oss.starter.config.error;


import io.github.wslxm.springbootplus2.starter.aliyun.oss.starter.config.result.AliYunOssRType;
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
public class AliYunOssErrorException extends RuntimeException {

    private Integer code;
    private String msg;


    /**
     * 直接传递
     *
     * @param code code
     * @param msg  msg
     * @return
     * @version 1.0.0
     */
    public AliYunOssErrorException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public AliYunOssErrorException(String msg) {
        this.code = AliYunOssRType.FILE_UPLOAD_FAILED.getValue();
        this.msg = msg;
    }


    /**
     * 枚举传递（建议先定义枚举）
     *
     * @param rType rType
     * @version 1.0.0
     */
    public AliYunOssErrorException(AliYunOssRType rType) {
        this.code = rType.getValue();
        this.msg = rType.getMsg();
    }
}
