package com.ws.ldy.modules.sys.admin.converter;

import com.ws.ldy.modules.sys.admin.model.dto.AdminAuthorityDTO;
import com.ws.ldy.modules.sys.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.sys.admin.model.vo.AdminAuthorityVO;
import com.ws.ldy.modules.sys.base.converter.BaseConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AdminAuthorityConverter extends BaseConverter<AdminAuthorityDTO, AdminAuthority, AdminAuthorityVO>  {


}
