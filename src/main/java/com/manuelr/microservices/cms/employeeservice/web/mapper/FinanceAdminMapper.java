package com.manuelr.microservices.cms.employeeservice.web.mapper;

import com.manuelr.cms.commons.dto.FinanceAdminDto;
import com.manuelr.microservices.cms.employeeservice.entity.FinanceAdmin;
import org.mapstruct.Mapper;

@Mapper(uses = {DataMapper.class})
public interface FinanceAdminMapper extends PersonMapper<FinanceAdminDto, FinanceAdmin> {
}
