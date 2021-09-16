package com.ws.ldy.manage.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.manage.admin.model.dto.AdminAuthorityDTO;
import com.ws.ldy.manage.admin.model.entity.AdminAuthority;
import com.ws.ldy.manage.admin.model.query.AdminAuthorityQuery;
import com.ws.ldy.manage.admin.model.vo.AdminAuthorityVO;

import java.util.List;

/**
 *   Url权限
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
public interface AdminAuthorityService extends IService<AdminAuthority> {

    /**
     * 查询所有权限数据，根据不同的端的枚举code 拼接最顶级的目录, 顶级目录ID = -1 （list数据）
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    List<AdminAuthorityVO> list(AdminAuthorityQuery query);

    /**
     * 编辑
     * @author wangsong
     * @param dto
     */
    Boolean upd(String id, AdminAuthorityDTO dto);

    /**
     * 获取用户的url权限列表，只返回未禁用的 url
     *
     * @param  userId 用户id
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    List<String> findByUserIdAuthority(String userId);

    /**
     * 扫描接口, 加入权限管理中（用于url授权, 在项目启动时调用进行自动执行, 初始化相关权限接口）
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    Boolean refreshAuthDB();

    /**
     * 刷新缓存中的接口接口信息 (用于url授权, 在项目启动时调用和，权限数据更新时调用)
     *
     * @param
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    void refreshAuthCache();
}
