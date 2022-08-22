package com.test.springbootplus2.manage.test.controller;

import io.github.wslxm.springbootplus2.core.config.threadpool.XjThreadUtil;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.core.utils.XjTransactionUtil;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.SysUser;
import io.github.wslxm.springbootplus2.manage.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 异步工具类测试
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/6/10 14:49
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_OPEN + "/test/transaction")
@Api(value = "TransactionTestController", tags = "事务执行后执行")
@Slf4j
public class TransactionTestController {

	@Autowired
	private SysUserService sysUserService;


	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ApiOperation(value = "事务执行后执行测试")
	@Transactional(rollbackFor = Exception.class)
	public Result<Boolean> test() {
		log.info("1、进入方法");

		// 查询数据模拟
		List<SysUser> list = sysUserService.list();

		XjTransactionUtil.runOfAfterCommit(() -> {
			log.info("3、我开始执行了");
		});

		// 延时3秒
		XjThreadUtil.sleep(3000);
		log.info("2、方法执行完毕");
		return Result.success(true);
	}
}
