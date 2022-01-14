package com.manuelr.microservices.cms.employeeservice.service.impl;
import com.manuelr.microservices.cms.employeeservice.entity.Person;
import com.manuelr.microservices.cms.employeeservice.repository.PersonRepository;
import com.manuelr.microservices.cms.employeeservice.service.ManagerService;
import com.manuelr.microservices.cms.employeeservice.web.assembler.PersonAssembler;
import com.manuelr.microservices.cms.employeeservice.web.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends PersonServiceImpl implements ManagerService {

    public ManagerServiceImpl(@Qualifier("managerRepository") PersonRepository repository,
                              @Qualifier("managerAssembler") PersonAssembler resourceAssembler,
                              PagedResourcesAssembler<Person> pagedResourcesAssembler,
                              ManagerMapper mapper) {
        super(repository, resourceAssembler, pagedResourcesAssembler, mapper);
    }
}


