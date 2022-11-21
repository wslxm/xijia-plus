package io.github.wslxm.springbootplus2.core.utils.tree;

import lombok.Data;

import java.util.List;

/**
 * tree 工具类 父对象
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/11/21 0021 9:25 
 * @version 1.0.0
 */
@Data
public class BaseTree<T, S> {

    /**
     * id
     */
    private S id;
    /**
     * 关联父节点值
     */
    private S pid;

    /**
     * 下级tree数据 （处理数据后存放）
     */
    private List<T> children;

    /**
     * 父级tree数据 （处理数据后存放）
     */
    private T parentNode;

}
