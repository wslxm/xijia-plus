package com.ws.ldy.modules.yw.pets.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.modules.yw.pets.model.entity.PetsUser;
import com.ws.ldy.modules.yw.pets.model.vo.PetsUserVO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 11:03:46
 */
public interface PetsUserMapper extends BaseMapper<PetsUser> {


    /**
     * 分页查询并统计
     *         // 查询报销次数
     *         // 宠物数量
     *         // 缴费次数
     * @param page
     * @param fullName
     * @param phone
     * @param disable
     * @return
     */
    Page<PetsUserVO> findPage(Page<PetsUser> page,
                              @Param("fullName") String fullName,
                              @Param("phone") String phone,
                              @Param("disable") String disable);
}

