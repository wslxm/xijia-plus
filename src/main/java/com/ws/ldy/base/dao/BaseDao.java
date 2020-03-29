package com.ws.ldy.base.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;


/**
 * TODO  通用dao  方法详情参考JPA
 * 参考文章：https://blog.csdn.net/qq_36682365/article/details/78035096
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/10/31 21:12
 * spring 注入容器注解 @Component,@Service,@Controller,@Repository
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseDao <T,ID extends Serializable> extends JpaRepository<T,ID> {

    /**
     * 分页+排序+条件查询
     * @param spec
     * @param pageable
     * @return
     */
    Page<T> findAll(Specification<T> spec, Pageable pageable);

}