package com.ws.ldy.modules.admin.converter;

import com.ws.ldy.modules.admin.model.dto.AdminAuthorityDTO;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.model.vo.AdminAuthorityVO;
import com.ws.ldy.others.base.converter.BaseConverter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AdminAuthorityConverter extends BaseConverter<AdminAuthorityDTO, AdminAuthority, AdminAuthorityVO>  {


}
