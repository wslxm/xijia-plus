package io.github.wslxm.springbootplus2.starter.kuaidi.kuaidi100.entity;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 快递100 智能识别返回结果 List<KuaiDiCode 按相似度排序（高的在前）
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/16 0016 13:45
 * @version 1.0.1
 */
@Data
@ToString
public class KuaiDiCode {

    private String comCode;
    private String id;
    private Integer noCount;
    private String noPre;
    private LocalDateTime startTime;

}
