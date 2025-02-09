package com.aisentiment.openai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aisentiment.openai.entity.OrganizationalChange;
import com.aisentiment.openai.exception.NotFoundException;
import com.aisentiment.openai.repository.OrganizationRepo;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepo organizationRepo;

    public OrganizationalChange createOrganizationalChange(OrganizationalChange organizationalChange)
    {
          return organizationRepo.save(organizationalChange);
    }

    public OrganizationalChange getOrganizationalChange(Long id)
    {
        return organizationRepo.findById(id).orElseThrow(()-> new NotFoundException("not found!!"));
    }

    // other api are pending you have to do Okkkkkk

}
