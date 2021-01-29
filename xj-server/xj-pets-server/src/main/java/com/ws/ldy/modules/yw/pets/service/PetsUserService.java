package com.ws.ldy.modules.yw.pets.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.client.yw.pets.model.dto.LoginDTO;
import com.ws.ldy.client.yw.pets.model.dto.UpdHeadOrNameOrPhoneDTO;
import com.ws.ldy.modules.yw.pets.model.dto.PetsUserDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsUser;
import com.ws.ldy.modules.yw.pets.model.vo.PetsUserFindUserCountVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsUserVO;

import java.util.List;

/**
 * 用户表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 11:03:46
 */
public interface PetsUserService extends IService<PetsUser> {

    /**
     * 登录
     * @author wangsong
     * @date 2020/12/30 0030 9:24
     * @return java.lang.String
     * @version 1.0.0
     */
    Boolean login(LoginDTO dto);


    /**
     * 修改
     * @author wangsong
     * @date 2020/12/30 0030 9:24
     * @return java.lang.String
     * @version 1.0.0
     */
    Boolean updHeadOrNameOrPhone(UpdHeadOrNameOrPhoneDTO dto);


    /**
     * 完善主人信息
     * @author wangsong
     * @date 2020/12/31 0031 17:35
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    Boolean updPetOwner(PetsUserDTO dto);


    /**
     * 查询登录用户信息
     * @author wangsong
     * @date 2020/12/30 0030 9:24
     * @return java.lang.String
     * @version 1.0.0
     */
    PetsUser findUser();


    /**
     * 查询最近五个注册用户
     */
    List<PetsUser> findNew5();

    /**
     * 分页查询
     *   // 查询报销次数
     *   // 宠物数量
     *   // 缴费次数
     * @return
     */
    Page<PetsUserVO> findPage(Page<PetsUser> page, String fullName, String phone, String disable);

    /**
     * id查询
     * @author wangsong
     * @param userId
     * @date 2021/1/5 0005 9:42
     * @return boolean
     * @version 1.0.0
     */
    public PetsUser findId(String userId);

    /**
     * 获取指定用户的wxOpenId
     * @author wangsong
     * @param userId
     * @date 2021/1/5 0005 9:42
     * @return boolean
     * @version 1.0.0
     */
    public String findWxOrderId(String userId);

    /**
     * 指定用户是否认证
     * @author wangsong
     * @param userId
     * @date 2021/1/5 0005 9:42
     * @return boolean
     * @version 1.0.0
     */
    public boolean isItCertified(String userId);


    /**
     * 添加注入地址信息
     * @author wangsong
     * @param userId
     * @date 2021/1/5 0005 9:42
     * @return boolean
     * @version 1.0.0
     */
    public boolean updAddress(String province, String city, String area, String userId);


    /**
     * 查询总数(1-所有 2-男 3-女 4-未知)
     * @author wangsong
     * @date 2021/1/5 0005 9:42
     * @return boolean
     * @version 1.0.0
     */
    public PetsUserFindUserCountVO findUserCount();
}


