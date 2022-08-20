package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.core.utils.bean.SpringContextUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGcSevice;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 执行生成某一个文件
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/8/9 14:53
 */
@Service
public class GcIteratorPattern {

	/**
	 * 每个模板的代码生成实现 bean
	 */
	Map<String, XjGcSevice> beans = null;

	/**
	 * 初始化代码生成实现类到容器中
	 *
	 * @return void
	 * @author wangsong
	 * @date 2022/8/9 15:32
	 */
	public void init() {
		if (beans == null) {
			beans = SpringContextUtil.getApplicationContext().getBeansOfType(XjGcSevice.class);
		}

	}


	/**
	 * 依次执行代码生成
	 *
	 * @param gcConfig
	 * @return void
	 * @author wangsong
	 * @date 2022/8/9 15:02
	 */
	public void run(GcConfig gcConfig) {
		this.init();
		for (XjGcSevice xjGcSevice : beans.values()) {
			xjGcSevice.run(gcConfig);
		}
	}
}
