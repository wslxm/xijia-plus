package io.github.wslxm.springbootplus2.manage.xj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminBlacklistDTO;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminBlacklist;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminBlacklistQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminBlacklistVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 黑名单
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-27 22:44:49
 */
public interface XjAdminBlacklistService extends IService<XjAdminBlacklist> {

    /**
     * 分页查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminBlacklistVO>
     * @version 1.0.0
     */
    IPage<XjAdminBlacklistVO> list(XjAdminBlacklistQuery query);


    /**
     * 列表查询
     *
     * @return
     */
    List<XjAdminBlacklistVO> listByType(Integer type);

    /**
     * 添加
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(XjAdminBlacklistDTO dto);

    /**
     * 编辑
     *
     * @param id  id
     * @param dto dto
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean upd(String id, @RequestBody @Validated XjAdminBlacklistDTO dto);

    /**
     * 删除
     *
     * @param id id
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean del(String id);
}

