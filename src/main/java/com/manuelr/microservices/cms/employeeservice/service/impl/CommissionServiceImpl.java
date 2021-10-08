package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.microservices.cms.employeeservice.dto.CommissionDto;
import com.manuelr.microservices.cms.employeeservice.service.CommissionsService;
import com.manuelr.microservices.cms.employeeservice.web.proxy.CommissionsServiceFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@AllArgsConstructor
@Service
@Slf4j
public class CommissionServiceImpl implements CommissionsService {
    private final CommissionsServiceFeignClient commissionsServiceFeignClient;

    @Override
    public CollectionModel<CommissionDto> findCommissionsByEmployeeId(Long id) {
        CollectionModel<CommissionDto> commissionDtos = null;
        try {
            ResponseEntity<CollectionModel<CommissionDto>> responseEntity =
                    commissionsServiceFeignClient.findCommissionsByEmployeeId(id);
            commissionDtos = Objects.requireNonNull(responseEntity.getBody());
        } catch (FeignException e) {
            e.printStackTrace();
            log.info("Commission was not found");
        }
        return commissionDtos;
    }
}
