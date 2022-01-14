package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.microservices.cms.employeeservice.web.assembler.PersonAssembler;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.repository.PersonRepository;
import com.manuelr.microservices.cms.employeeservice.service.FinanceAdminService;
import com.manuelr.microservices.cms.employeeservice.web.mapper.FinanceAdminMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

@Service
public class FinanceAdminServiceImpl extends PersonServiceImpl implements FinanceAdminService {

    public FinanceAdminServiceImpl(@Qualifier("financeAdminRepository") PersonRepository repository,
                                   @Qualifier("financeAdminAssembler") PersonAssembler resourceAssembler,
                                   PagedResourcesAssembler<Person> pagedResourcesAssembler,
                                   FinanceAdminMapper mapper) {
        super(repository, resourceAssembler, pagedResourcesAssembler, mapper);
    }
}
