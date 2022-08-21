
import java.time.LocalDateTime;

import com.alibaba.fastjson.JSON;
import com.test.springbootplus2.SpringBootPlus2DemoServer;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.Config;
import io.github.wslxm.springbootplus2.manage.xj.service.ConfigService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


/**
 * 事务测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= SpringBootPlus2DemoServer.class)
public class SwTest {

	@Autowired
	private ConfigService xjAdminConfigService;

	@Test
	public void test() {

		Config config = new Config();
		config.setCode("测试");
		config.setName("测试");
		config.setType(0);
		config.setContent("测试数据");
		config.setSort(0);
		xjAdminConfigService.save(config);

		//
		Config xjAdminConfig = xjAdminConfigService.getById(config.getId());
		System.out.println(JSON.toJSONString(xjAdminConfig));

	}
}