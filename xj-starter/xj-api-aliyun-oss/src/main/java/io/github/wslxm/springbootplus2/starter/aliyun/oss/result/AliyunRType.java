package io.github.wslxm.springbootplus2.starter.aliyun.oss.result;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常常量类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 11:16
 * @return
 */

@SuppressWarnings("all")
@Getter
@NoArgsConstructor
public enum AliyunRType {

    FILE_UPLOAD_FAILED(10020, "文件上传失败"),
    ;

    private Integer value;
    private String msg;

    AliyunRType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }
}
