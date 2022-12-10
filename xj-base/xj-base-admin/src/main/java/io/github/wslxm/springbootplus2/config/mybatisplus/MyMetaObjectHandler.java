package io.github.wslxm.springbootplus2.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.github.wslxm.springbootplus2.common.auth.entity.JwtUser;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.core.result.Result;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
  * 创建人, 更新人统一处理
  * @author wangsong
  * @email  1720696548@qq.com
  * @date  2022/8/3 14:58
 *
 *  填充生效场景
 *     updateById(T entity)
 *     updateBatchById(Collection<T> entityList)
 *     saveOrUpdate(T entity)
 *     save(T entity)
 *     update(T t,Wrapper updateWrapper)， t不能为空,否则自动填充失效
 *     LambdaQueryWrapper<User> wrapper= new LambdaQueryWrapper<>(new User());
 *     LambdaUpdateWrapper<User> wrapper = Wrappers.lambdaUpdate(new User());
 *
 *   填充不生效场景
 *     update(Wrapper updateWrapper)
 *     检查字段有没有加上注解 @TableField(fill = FieldFill.INSERT_UPDATE)
 *     有没有实现 MetaObjectHandler 接口 ，并且加入到 Spring 容器中
 *     xml 写的也无效
  */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	/**
	 * 新增操作
	 *
	 * @param metaObject
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		Result<JwtUser> jwtUserR = JwtUtil.getJwtUserR(request, response);
		if (jwtUserR.getCode().equals(Result.success().getCode())) {
			JwtUser jwtUser = jwtUserR.getData();
			this.strictInsertFill(metaObject, "createUser", String.class, jwtUser.getUserId());
			this.strictInsertFill(metaObject, "updateUser", String.class, jwtUser.getUserId());
		} else {
			this.strictInsertFill(metaObject, "createUser", String.class, "0");
			this.strictInsertFill(metaObject, "updateUser", String.class, "0");
		}
	}


	/**
	 * 更新操作
	 *
	 * @param metaObject
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		Result<JwtUser> jwtUserR = JwtUtil.getJwtUserR(request, response);
		if (jwtUserR.getCode().equals(Result.success().getCode())) {
			JwtUser jwtUser = jwtUserR.getData();
			this.strictUpdateFill(metaObject, "updateUser", String.class, jwtUser.getUserId());
		} else {
			this.strictUpdateFill(metaObject, "updateUser", String.class, "0");
		}
	}
}

