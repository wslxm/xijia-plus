//package com.test.springbootplus2.manage.test.controller;
//
//import com.test.springbootplus2.manage.test.model.dto.CacheDTO;
//import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
//import io.github.wslxm.springbootplus2.core.result.Result;
//
//
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 缓存测试
// *
// * @author wangsong
// * @email 1720696548@qq.com
// * @date 2022/3/22 16:35
// */
//@RestController
//@RequestMapping(BaseConstant.Uri.API_OPEN + "/test/cache")
//@Api(value = "CacheTestController", tags = "缓存测试")
//public class CacheTestController {
//
//
//	@RequestMapping(value = "/getCache", method = RequestMethod.POST)
//	@ApiOperation(value = "获取缓存测试")
//	@Cacheable(cacheNames = "user", key = "#dto.id", unless = "#result.getCode() != 200")
//	public Result<CacheDTO> getCache(@RequestBody CacheDTO dto) {
//		return Result.success(dto);
//	}
//
//
//	@RequestMapping(value = "/updCache", method = RequestMethod.PUT)
//	@ApiOperation(value = "更新缓存测试")
//	@CachePut(cacheNames = "user", key = "#dto.id", unless = "#result.getCode() != 200")
//	public Result<CacheDTO> updCache(@RequestBody CacheDTO dto) {
//		return Result.success(dto);
//	}
//
//
//	@RequestMapping(value = "/delCache", method = RequestMethod.DELETE)
//	@ApiOperation(value = "删除缓存测试")
//	@CacheEvict(cacheNames = "user", key = "#dto.id")
//	public Result<Boolean> delCache(@RequestBody CacheDTO dto) {
//		return Result.success(true);
//	}
//}
