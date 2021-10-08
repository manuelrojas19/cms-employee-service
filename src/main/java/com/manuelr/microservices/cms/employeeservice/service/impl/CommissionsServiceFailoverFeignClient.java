package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.microservices.cms.employeeservice.dto.CommissionDto;
import com.manuelr.microservices.cms.employeeservice.web.proxy.CommissionsServiceFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommissionsServiceFailoverFeignClient implements CommissionsServiceFeignClient {
    private final CommissionsServiceFeignClient commissionsServiceFeignClient;

    @Override
    public ResponseEntity<CollectionModel<CommissionDto>> findCommissionsByEmployeeId(Long id) {
        return commissionsServiceFeignClient.findCommissionsByEmployeeId(id);
    }
}
