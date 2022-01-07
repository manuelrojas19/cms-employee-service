package com.manuelr.microservices.cms.employeeservice.service;

import com.manuelr.cms.commons.dto.CommissionDto;
import org.springframework.hateoas.CollectionModel;

public interface CommissionsService {
    CollectionModel<CommissionDto> findCommissionsByEmployeeId(Long id);
}
