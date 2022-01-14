package com.manuelr.microservices.cms.employeeservice.web.mapper;

import com.manuelr.cms.commons.dto.ManagerDto;
import com.manuelr.microservices.cms.employeeservice.entity.Manager;
import org.mapstruct.Mapper;

@Mapper(uses = {DataMapper.class})
public interface ManagerMapper extends PersonMapper<ManagerDto, Manager> {
}
