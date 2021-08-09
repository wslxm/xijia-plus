package com.test.springbootplus2.manage.test1.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.*;
import java.util.Arrays;
import java.time.LocalDateTime;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.result.RType;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.config.error.ErrorException;
import com.test.springbootplus2.manage.test1.model.entity.TestUser;
import com.test.springbootplus2.manage.test1.model.vo.TestUserVO;
import com.test.springbootplus2.manage.test1.model.dto.TestUserDTO;
import com.test.springbootplus2.manage.test1.service.TestUserService;




/**
 * 测试表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author  ws
 * @email  1720696548@qq.com
 * @date  2021-08-06 17:04:37
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/test1/testUser")
@Api(value ="TestUserController" ,tags = "测试表")
public class TestUserController extends BaseController<TestUserService>  {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes= "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query",example = "20"),
 
    })
    public R<IPage<TestUserVO>> findPage(
                                              ) {
        Page<TestUser> page = baseService.page(this.getPage(), new LambdaQueryWrapper<TestUser>()
                .orderByDesc(TestUser::getCreateTime)
 
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, TestUserVO.class));
    }


    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes= "")
    public R<TestUserVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert( baseService.getById(id),TestUserVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes= "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated TestUserDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        TestUser testUser = dto.convert(TestUser.class);
        return R.successInsert(baseService.save(testUser));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes= "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated TestUserDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(TestUser.class)));
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes= "")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }

}
