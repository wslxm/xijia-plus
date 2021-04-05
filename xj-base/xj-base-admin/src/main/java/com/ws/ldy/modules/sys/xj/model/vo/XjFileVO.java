package com.ws.ldy.modules.sys.xj.model.vo;


import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 常用工具文件管理
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-17 16:21:46
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjFileVO 对象", description = "常用工具文件管理")
public class XjFileVO extends BaseVo {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "文件名(标题)" ,position = 0)
    private String name;

    @ApiModelProperty(notes = "文件描叙" ,position = 1)
    private String desc;

    @ApiModelProperty(notes = "文件大小" ,position = 2)
    private Double size;

    @ApiModelProperty(notes = "文件格式(后缀)" ,position = 3)
    private String suffix;

    @ApiModelProperty(notes = "文件url" ,position = 4)
    private String url;

    @ApiModelProperty(notes = "文件类型(1-开发工具 2-源码  3-文档   4-图片 5-音频 6-视频 7-sql)" ,position = 5)
    private Integer type;

}

