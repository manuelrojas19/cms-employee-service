package com.manuelr.microservices.cms.employeeservice.service;

import com.manuelr.microservices.cms.employeeservice.dto.CommissionDto;
import org.springframework.hateoas.CollectionModel;

import java.util.Collection;
import java.util.List;

public interface CommissionsService {
    CollectionModel<CommissionDto> findCommissionsByEmployeeId(Long id);
}
