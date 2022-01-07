package com.manuelr.microservices.cms.employeeservice.mapper;

import com.manuelr.cms.commons.dto.EmployeeDto;
import com.manuelr.microservices.cms.employeeservice.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(uses = {DataMapper.class})
public interface EmployeeMapper extends PersonMapper<EmployeeDto, Employee> {
}
