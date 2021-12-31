package com.manuelr.microservices.cms.employeeservice.web.mapper;

import com.manuelr.microservices.cms.employeeservice.entity.Employee;
import com.manuelr.microservices.cms.employeeservice.dto.EmployeeDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DataMapper.class})
public interface EmployeeMapper {
//    EmployeeDto employeeEntityToEmployeeDto(Employee employee);
//
//    Employee employeeDtoToEmployeeEntity(EmployeeDto employeeDto);
}
