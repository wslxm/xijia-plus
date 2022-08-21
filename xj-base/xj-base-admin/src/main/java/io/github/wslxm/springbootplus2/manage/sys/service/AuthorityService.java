package io.github.wslxm.springbootplus2.manage.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.AuthorityDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Authority;
import io.github.wslxm.springbootplus2.manage.sys.model.query.AuthorityQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.AuthorityVO;

import java.util.List;
import java.util.Map;

/**
 * Url权限
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
public interface AuthorityService extends IService<Authority> {

    /**
     * 查询所有权限数据，根据不同的端的枚举code 拼接最顶级的目录, 顶级目录ID = -1 （list数据）
     *
     * @param query 字段
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    List<AuthorityVO> list(AuthorityQuery query);

    /**
     * 编辑
     *
     * @param id id
     * @param dto 字段
     * @author wangsong
     * @return boolean
     */
    Boolean upd(String id, AuthorityDTO dto);

//    /**
//     * 获取用户的url权限列表，只返回未禁用的 url
//     *
//     * @param userId 用户id
//     * @return void
//     * @date 2019/11/25 0025 11:55
//     */
//    List<String> findByUserIdAuthority(String userId);

    /**
     * 扫描接口, 加入权限管理中（用于url授权, 在项目启动时调用进行自动执行, 初始化相关权限接口）
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    Boolean refreshAuthDb();

    /**
     * 刷新接口信息 (用于url授权, 在项目启动时调用和，权限数据更新时调用)
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    void refreshAuthCache();


    /**
     * 查询接口信息 (用于url授权, 在项目启动时调用和，权限数据更新时调用)
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    Map<String, Authority> findListAllToMap();
}
