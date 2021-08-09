package com.ws.ldy.core.base.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 实体类转换工具, mapstruct ,子类自动生成 class代码
 * <P>
 *     D dto    -- 请求参数
 *     E entity -- 实体类,对应DB
 *     V vo     -- 响应参数
 * </P>
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/20 0020 12:02 
 * @version 1.0.0
 */
public abstract class BaseConverter<D, E, V> {

    public abstract D toDTO(E e);

    public abstract List<D> toDTO(List<E> e);

    public abstract E toEntity(D d);

    public abstract List<E> toEntity(List<D> d);

    public abstract V toVO(E e);

    public abstract List<V> toVO(List<E> e);

    /**
     * mybatis-plus Page 装换 , 需要连接数据库和依赖包，不做演示
     * @param page
     * @return
     */
    public IPage<V> toPage(IPage<E> page) {
        if (page == null) {
            return null;
        }
        return page.convert(this::toVO);
    }
}
