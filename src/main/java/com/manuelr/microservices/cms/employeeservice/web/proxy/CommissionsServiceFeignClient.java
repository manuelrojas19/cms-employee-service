package com.manuelr.microservices.cms.employeeservice.web.proxy;

import com.manuelr.microservices.cms.employeeservice.dto.CommissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("commissions-service")
public interface CommissionsServiceFeignClient {

    @GetMapping("/api/v1/employees/{id}/commissions")
    ResponseEntity<CollectionModel<CommissionDto>> findCommissionsByEmployeeId(@PathVariable Long id);

}
