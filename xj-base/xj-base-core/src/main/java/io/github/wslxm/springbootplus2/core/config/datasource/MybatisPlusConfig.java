package io.github.wslxm.springbootplus2.core.config.datasource;import com.baomidou.mybatisplus.annotation.DbType;import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;import org.apache.ibatis.session.SqlSessionFactory;import org.mybatis.spring.annotation.MapperScan;import org.springframework.beans.factory.annotation.Qualifier;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import org.springframework.context.annotation.Primary;import org.springframework.core.io.support.PathMatchingResourcePatternResolver;import org.springframework.transaction.annotation.EnableTransactionManagement;import javax.sql.DataSource;/** * @author wangsong */@EnableTransactionManagement@Configuration@MapperScan("com.baomidou.cloud.service.*.mapper*")public class MybatisPlusConfig {    /**     * 分页插件配置     *     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor     * @version 1.0.0     */    @Bean    public PaginationInnerInterceptor paginationInnerInterceptor() {        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();        // 设置最大单页限制数量，默认 500 条，-1 不受限制        paginationInterceptor.setMaxLimit(-1L);        paginationInterceptor.setDbType(DbType.MYSQL);        // 开启 count 的 join 优化,只针对部分 left join        paginationInterceptor.setOptimizeJoin(true);        return paginationInterceptor;    }    /**     * 配置注入     * @return     */    @Bean    public MybatisPlusInterceptor mybatisPlusInterceptor() {        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();        // 分页插件        interceptor.addInnerInterceptor(paginationInnerInterceptor());        // 乐观锁        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());        return interceptor;    }//    @Bean//    @Primary//    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {//        try {//            MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();//            // 如果不写这里，会导致 mybatis 出现 invalid bound statement (not found) 的问题//            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*Mapper.xml"));//            bean.setDataSource(dataSource);//            return bean.getObject();//        } catch (Exception e) {//            e.printStackTrace();//            throw new RuntimeException(e);//        }//    }}