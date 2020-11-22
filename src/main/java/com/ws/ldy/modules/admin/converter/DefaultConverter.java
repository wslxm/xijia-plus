package com.ws.ldy.modules.admin.converter;


import com.ws.ldy.others.base.converter.BaseConverter;
import com.ws.ldy.others.base.model.BaseDto;
import com.ws.ldy.others.base.model.BaseEntity;
import com.ws.ldy.others.base.model.BaseVo;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;


/**
 * 通用 BaseConverter 的默认对象（无实际用处）
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/20 0020 14:49
 * @version 1.0.0
 */
@Primary
@Mapper(componentModel = "spring")
public abstract class DefaultConverter extends BaseConverter<BaseDto, BaseEntity, BaseVo> {

}
