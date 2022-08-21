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
     *
     * @param loginUserId loginUserId
     * @param disable disable
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.MenuVO>
     * @version 1.0.0
     */
    List<MenuVO> list(@Param("loginUserId") String loginUserId,
                           @Param("disable") Integer disable);

}
