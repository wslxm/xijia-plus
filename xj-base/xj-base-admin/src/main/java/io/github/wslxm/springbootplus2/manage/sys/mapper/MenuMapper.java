package io.github.wslxm.springbootplus2.manage.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Menu;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangsong
 */
public interface MenuMapper extends BaseMapper<Menu> {


    /**
     * 菜单列表
     * <P> 如果传递了 loginUserId 只查询指定账号id 当前拥有的菜单（不查询禁用的菜单/角色/用户 数据） </P>
     *
     * @param loginUserId loginUserId
     * @param disable disable
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.MenuVO>
     * @version 1.0.0
     */
    List<MenuVO> list(@Param("loginUserId") String loginUserId,
                      @Param("disable") Integer disable);

}
