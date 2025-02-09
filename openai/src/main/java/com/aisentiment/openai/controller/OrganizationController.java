package com.aisentiment.openai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisentiment.openai.entity.OrganizationalChange;
import com.aisentiment.openai.exception.NotFoundException;
import com.aisentiment.openai.service.OrganizationService;

@RestController
@RequestMapping("/organization-changes")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    
    @PostMapping("/save")
    public OrganizationalChange creatOrganizationalChange(@RequestBody OrganizationalChange org)
    {
         return  organizationService.createOrganizationalChange(org);
    }

    @GetMapping("/{id}")
    public OrganizationalChange getOrganizationalChange(@PathVariable Long id)
    {
              return organizationService.getOrganizationalChange(id);
    }

}
