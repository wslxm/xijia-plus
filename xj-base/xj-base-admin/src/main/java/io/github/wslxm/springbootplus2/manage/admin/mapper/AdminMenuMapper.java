package io.github.wslxm.springbootplus2.manage.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminMenu;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminMenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author wangsong
 */
public interface AdminMenuMapper extends BaseMapper<AdminMenu> {


    /**
     * 菜单列表
     *
     * @param terminal terminal
     * @param loginUserId loginUserId
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminMenuVO>
     * @version 1.0.0
     */
    List<AdminMenuVO> list(@Param("terminal") Integer terminal,
                           @Param("loginUserId") String loginUserId,
                           @Param("disable") Integer disable);

}
