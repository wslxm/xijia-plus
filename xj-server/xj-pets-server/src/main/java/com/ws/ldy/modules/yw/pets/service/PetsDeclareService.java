package com.ws.ldy.modules.yw.pets.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.client.yw.pets.model.vo.NewDeclareVO;
import com.ws.ldy.modules.yw.pets.model.dto.PetsDeclareDTO;
import com.ws.ldy.modules.yw.pets.model.dto.PetsDeclareUpdStateDTO;
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
public interface PetsDeclareService extends IService<PetsDeclare> {


    /**
     * 最新互助
     * @author wangsong
     * @date 2020/12/30 0030 10:04
     * @return void
     * @version 1.0.0
     */
    public NewDeclareVO newDeclareSuccess();


    /**
     * 平台总申报成功的总数
     * @author wangsong
     * @date 2020/12/30 0030 10:04
     * @return void
     * @version 1.0.0
     */
    public Integer findSuccessTotal();


    /**
     * 指定用户申报成功的总数
     * @author wangsong
     * @date 2020/12/30 0030 10:04
     * @return void
     * @version 1.0.0
     */
    public Integer findSuccessTotalByUserId(String userId);


    /**
     * 指定用户申报次数和申报总金额
     * @author wangsong
     * @date 2020/12/30 0030 10:04
     * @return void
     * @version 1.0.0
     */
    public PetsDeclareFindTotalUserIdVO findTotalByUserId(String userId);


    /**
     * 申报提交
     * @author wangsong
     * @param dto
     * @date 2020/12/31 0031 17:21
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    public Boolean insert(PetsDeclareDTO dto);


    /**
     * 申报编辑并重新提交
     * @author wangsong
     * @param dto
     * @date 2021/1/4 0004 17:35
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    public Boolean upd(PetsDeclareDTO dto);


    /**
     * 列表查询(联表重新用户，宠物，医院信息)
     * @author wangsong
     * @param page
     * @param fullName
     * @param state
     * @param startPassTime
     * @param endPassTime
     * @date 2021/1/4 0004 17:35
     * @return com.ws.ldy.common.result.R<com.baomidou.mybatisplus.core.metadata.IPage < com.ws.ldy.modules.pets.model.vo.PetsDeclareVO>>
     * @version 1.0.0
     */
    public Page<PetsDeclarePageVO> findPage(Page<PetsDeclare> page,
                                            String id,
                                            String userId,
                                            String fullName,
                                            Integer state,
                                            LocalDateTime startPassTime,
                                            LocalDateTime endPassTime
    );


    /**
     * 查询指定时间内的申报数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<PetsDeclareFindNewDayListVO> findNewDayList(@Param("startTime") LocalDateTime startTime,
                                                     @Param("endTime") LocalDateTime endTime);


    /**
     * 统计页最新申报数据
     * @param page
     * @return
     */
    public Page<PetsDeclareFindHomePageVO> findHomePage(Page<PetsDeclare> page);

    /**
     * 风控列表查询审报列表信息
     * @return
     */
    public List<PetsDeclaretWindControlVO> findListWindControl(String userId);


    /**
     * id查询
     * @param id
     * @return
     */
    public PetsDeclareVO findId(String id);


    /**
     * 申报成功统计 需要的数据查询
     * @return
     */
    public List<PetsDeclareFindDeclareSuccessCityVO> findDeclareSuccessCity();

    /**
     * 申报通过/不通过
     * @param dto
     */
    public boolean updState(PetsDeclareUpdStateDTO dto);
}

