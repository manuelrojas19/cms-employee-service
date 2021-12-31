package com.manuelr.microservices.cms.employeeservice.repository;

import com.manuelr.microservices.cms.employeeservice.entity.FinanceAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceAdminRepository extends JpaRepository<FinanceAdmin, Long> {
}
