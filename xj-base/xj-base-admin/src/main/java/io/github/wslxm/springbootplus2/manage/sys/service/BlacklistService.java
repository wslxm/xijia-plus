package io.github.wslxm.springbootplus2.manage.sys.service;

import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.BlacklistDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Blacklist;
import io.github.wslxm.springbootplus2.manage.sys.model.query.BlacklistQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.BlacklistVO;
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
public interface BlacklistService extends IService<Blacklist> {

    /**
     * 分页查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.BasePage<io.github.wslxm.springbootplus2.manage.xj.model.vo.BlacklistVO>
     * @version 1.0.0
     */
    BasePage<BlacklistVO> findPage(BlacklistQuery query);


    /**
     * 列表查询
     * @author wangsong
     * @param type
     * @date 2022/8/22 0022 20:42
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.sys.model.vo.BlacklistVO>
     * @version 1.0.0
     */
    List<BlacklistVO> listByType(Integer type);

    /**
     * 添加
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(BlacklistDTO dto);

    /**
     * 编辑
     *
     * @param id  id
     * @param dto dto
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean upd(String id, @RequestBody @Validated BlacklistDTO dto);

    /**
     * 删除
     *
     * @param id id
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean del(String id);
}

