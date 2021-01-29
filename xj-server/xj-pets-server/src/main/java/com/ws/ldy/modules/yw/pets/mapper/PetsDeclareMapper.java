package com.ws.ldy.modules.yw.pets.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.modules.yw.pets.model.entity.PetsDeclare;
import com.ws.ldy.modules.yw.pets.model.vo.*;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 申报信息表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 13:48:14
 */
public interface PetsDeclareMapper extends BaseMapper<PetsDeclare> {


    /**
     * 分页查询
     * @param page
     * @param fullName
     * @param state
     * @param startPassTime
     * @param endPassTime
     * @return
     */
    Page<PetsDeclarePageVO> findPage(@Param("page") Page<PetsDeclare> page,
                                     @Param("id") String id,
                                     @Param("userId") String userId,
                                     @Param("fullName") String fullName,
                                     @Param("state") Integer state,
                                     @Param("startPassTime") LocalDateTime startPassTime,
                                     @Param("endPassTime") LocalDateTime endPassTime);


    /**
     * 查询指定时间内的申报数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<PetsDeclareFindNewDayListVO> findNewDayList(@Param("startTime") LocalDateTime startTime,
                                                     @Param("endTime") LocalDateTime endTime);


    /**
     * 查询首页最新申报数据
     * @param page
     * @return
     */
    Page<PetsDeclareFindHomePageVO> findHomePage(Page<PetsDeclare> page);

    /**
     * 查询指定用户申报成功的数据
     * @param userId
     * @return
     */
    List<PetsDeclaretWindControlVO> findListWindControl(@Param("userId") String userId);

    /**
     * 申报城市统计需要的数据(成功的)
     * @return
     */
    List<PetsDeclareFindDeclareSuccessCityVO> findDeclareSuccessCity(Integer state);
}

