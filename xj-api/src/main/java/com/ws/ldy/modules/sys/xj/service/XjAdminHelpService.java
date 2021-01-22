package com.ws.ldy.modules.sys.xj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.sys.xj.model.entity.XjAdminHelp;
import com.ws.ldy.modules.sys.admin.model.vo.HelpTreeVO;

import java.util.List;

/**
 * 帮助中心表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-10-20 16:42:28
 */
public interface XjAdminHelpService extends IService<XjAdminHelp> {
    List<HelpTreeVO> findTree();



}

