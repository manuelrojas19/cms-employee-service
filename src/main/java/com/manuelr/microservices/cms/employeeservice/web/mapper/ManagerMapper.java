package com.manuelr.microservices.cms.employeeservice.web.mapper;

import com.manuelr.microservices.cms.employeeservice.dto.ManagerDto;
import com.manuelr.microservices.cms.employeeservice.entity.Manager;
import org.mapstruct.Mapper;

@Mapper(uses = {DataMapper.class})
public interface ManagerMapper {
    ManagerDto managerToManagerDto(Manager manager);
    Manager managerDtoToManager(ManagerDto managerDto);
}
