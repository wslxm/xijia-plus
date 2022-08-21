package com.test.springbootplus2.manage.test.controller;

import com.test.springbootplus2.manage.test.model.dto.CacheDTO;
import com.test.springbootplus2.manage.test.model.dto.ValidDTO;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存测试
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/3/22 16:35
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_OPEN + "/test/cache")
@Api(value = "CacheTestController", tags = "缓存测试")
public class CacheTestController {


	@RequestMapping(value = "/getCache", method = RequestMethod.POST)
	@ApiOperation(value = "获取缓存测试")
	@Cacheable(cacheNames = "user", key = "#dto.id", unless = "#result.getCode() != 200")
	public R<CacheDTO> getCache(@RequestBody CacheDTO dto) {
		return R.success(dto);
	}


	@RequestMapping(value = "/updCache", method = RequestMethod.PUT)
	@ApiOperation(value = "更新缓存测试")
	@CachePut(cacheNames = "user", key = "#dto.id", unless = "#result.getCode() != 200")
	public R<CacheDTO> updCache(@RequestBody CacheDTO dto) {
		return R.success(dto);
	}


	@RequestMapping(value = "/delCache", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除缓存测试")
	@CacheEvict(cacheNames = "user", key = "#dto.id") // , allEntries = true 删除key下所有
	public R<Boolean> delCache(@RequestBody CacheDTO dto) {
		return R.success(true);
	}
}
