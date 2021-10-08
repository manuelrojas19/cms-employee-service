package com.manuelr.microservices.cms.employeeservice.web.proxy;

import com.manuelr.microservices.cms.employeeservice.dto.CommissionDto;
import com.manuelr.microservices.cms.employeeservice.service.impl.CommissionsServiceFailoverFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "commissions-failover", fallback = CommissionsServiceFailoverFeignClient.class)
public interface CommissionsFailoverFeignClient {

    @GetMapping("/commissions-failover")
    ResponseEntity<CollectionModel<CommissionDto>> findCommissionsByEmployeeId(@PathVariable Long id);
}
