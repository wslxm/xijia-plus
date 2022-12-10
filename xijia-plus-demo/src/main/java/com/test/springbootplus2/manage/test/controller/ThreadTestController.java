//package com.test.springbootplus2.manage.test.controller;
//
//import io.github.wslxm.springbootplus2.core.config.threadpool.XjThreadUtil;
//import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
//import io.github.wslxm.springbootplus2.core.constant.NumberConst;
//import io.github.wslxm.springbootplus2.core.result.Result;
//
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 异步工具类测试
// *
// * @author wangsong
// * @email 1720696548@qq.com
// * @date 2022/6/10 14:49
// */
//@RestController
//@RequestMapping(BaseConstant.Uri.API_OPEN + "/test/thread")
//@Api(value = "ThreadTestController", tags = "异步测试")
//public class ThreadTestController {
//
//
//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	@ApiOperation(value = "异步测试")
//	public Result<Boolean> test() {
//		XjThreadUtil.asyncExecute(() -> {
//			for (int i = 0; i < NumberConst.HUNDRED; i++) {
//				XjThreadUtil.sleep(100L);
//				System.out.println("让我飞一会儿-" + i + 1);
//			}
//		});
//		return Result.success(true);
//	}
//}
