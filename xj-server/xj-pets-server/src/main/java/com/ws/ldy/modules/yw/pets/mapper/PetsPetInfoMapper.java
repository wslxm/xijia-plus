package com.ws.ldy.modules.yw.pets.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPetInfo;
import com.ws.ldy.modules.yw.pets.model.vo.PetsPetInfoFindCityVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsPetInfoVO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 宠物表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:33
 */
public interface PetsPetInfoMapper extends BaseMapper<PetsPetInfo> {


    /**
     * 分页查询
     *  <P>
     *    统计报销次数，报销总金额
     *  </P>
     * @param page
     * @param nickname
     * @param type
     * @return
     */
    Page<PetsPetInfoVO> findPage(Page<PetsPetInfo> page,
                                 @Param("userId") String userId,
                                 @Param("nickname") String nickname,
                                 @Param("type") String type,
                                 @Param("startJoinTime") LocalDateTime startJoinTime,
                                 @Param("endJoinTime") LocalDateTime endJoinTime
    );


    /**
     * 指定宠物获帮助次数+1
     * @param petId
     * @return
     */
    Boolean addOneInHelpNum(@Param("petId") String petId);


    /**
     * 除指定宠物外，其他宠物帮助次数+1 (在续费的)
     * @param petId
     * @return
     */
    Boolean addOneHelpNum(@Param("petId") String petId);


    /**
     * 数据统计查询宠物分布城市
     * @return
     */
    List<PetsPetInfoFindCityVO> findPetsCity();
}

