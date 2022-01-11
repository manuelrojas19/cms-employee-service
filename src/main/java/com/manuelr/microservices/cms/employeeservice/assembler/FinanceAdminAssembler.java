package com.manuelr.microservices.cms.employeeservice.assembler;

import com.manuelr.cms.commons.dto.FinanceAdminDto;
import com.manuelr.microservices.cms.employeeservice.entity.FinanceAdmin;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.mapper.FinanceAdminMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FinanceAdminAssembler implements PersonAssembler {
    private final FinanceAdminMapper mapper;

    @Override
    public @NonNull com.manuelr.cms.commons.dto.PersonDto toModel(@NonNull Person entity) {
        FinanceAdminDto dto = mapper.entityToDto((FinanceAdmin) entity);
        return dto;
    }
}
