package io.github.wslxm.springbootplus2.core.base.model;

import lombok.Data;


/**
 *  通用请求对象
 *
 *  <P>
 *      适应场景
 *      1、根据 id 进行数据操作，如：发送消息,修改数据指定内容 （此时传递id即可）
 *  </P>
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 * spring
 */
@Data
public class BaseId extends Convert {

    /**
     * id
     */
    private String id;

}
