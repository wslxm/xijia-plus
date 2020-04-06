package com.ws.ldy.base.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * TODO  通用dao  方法详情参考JPA
 * 参考文章：https://blog.csdn.net/qq_36682365/article/details/78035096
 *
 * @param <T>
 * @param <ID>
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 * spring 注入容器注解 @Component,@Service,@Controller,@Repository
 */
/*
 * 我们在这里直接继承 JpaRepository，CrudRepository，PagingAndSortingRepository，JpaSpecificationExecutor
 * 这里面已经有很多现成的方法
 * 这也是JPA的一大优点
 *
 *  （1） JpaRepository： 继承（2）PagingAndSortingRepository，实现一组 JPA 规范相关的方法
 *  （2） PagingAndSortingRepository： 继承 （3）CrudRepository，实现了一组分页排序相关的方法
 *  （3） CrudRepository： 继承 Repository，实现了一组 CRUD 相关的方法
 *  （4） JpaSpecificationExecutor： 实现条件查询
 *
 *   注解说明
 *   @Query: 自定义sql , 接口方法上使用，如: @Query(value = "select * from t_admin_role_menu where role_id = ?1", nativeQuery = true)
 *   @Modifying : 接口方法上如涉及到删除/修改/添加需要加上@Modifying
 *   @Transactional : 也可以根据需要添加 @Transactional 对事物的支持，查询超时的设置等
 */
@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * 分页+排序+条件查询
     *
     * @param spec
     * @param pageable,分页+排序信息
     * @return
     */
    Page<T> findAll(Specification<T> spec, Pageable pageable);

    /**
     * 排序+条件查询
     *
     * @param spec
     * @param sort
     * @return
     */
    List<T> findAll(Specification<T> spec, Sort sort);

    /**
     * 条件查询
     *
     * @param spec
     * @return
     */
    List<T> findAll(Specification<T> spec);

}