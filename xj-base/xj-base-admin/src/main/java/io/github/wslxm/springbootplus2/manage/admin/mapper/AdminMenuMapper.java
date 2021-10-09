package io.github.wslxm.springbootplus2.manage.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminMenu;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminMenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMenuMapper extends BaseMapper<AdminMenu> {

    List<AdminMenuVO> list(@Param("terminal") Integer terminal, @Param("loginUserId") String loginUserId);

}
