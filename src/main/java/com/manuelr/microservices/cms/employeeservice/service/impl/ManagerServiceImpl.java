package com.manuelr.microservices.cms.employeeservice.service.impl;

import com.manuelr.microservices.cms.employeeservice.repository.PersonRepository;
import com.manuelr.microservices.cms.employeeservice.service.ManagerService;
import com.manuelr.microservices.cms.employeeservice.web.assembler.PersonAssembler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends PersonServiceImpl implements ManagerService {

    public ManagerServiceImpl(@Qualifier("managerRepository") PersonRepository repository,
                              @Qualifier("managerAssembler") PersonAssembler resourceAssembler) {
        super(repository, resourceAssembler);
    }
}


